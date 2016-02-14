package br.com.amil.game;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;

import br.com.amil.game.event.Event;
import br.com.amil.game.event.EventType;
import br.com.amil.game.event.MatchEvent;
import br.com.amil.game.event.PlayerEvent;
import br.com.amil.game.player.ActionType;
import br.com.amil.game.player.Player;
import br.com.amil.game.player.PlayerAction;
import br.com.amil.script.LoadFile;

public class Ranking {

	private static final DateTimeFormatter DTF = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

	private final Match match;

	public Ranking(Match match) {
		this.match = match;
	}

	public static Ranking loadFromFilePath(String filePath) {
		List<Event> events = LoadFile.loadFromFilePath(filePath);
		return loadRanking(events);
	}

	public static Ranking loadFromString(List<String> lines) {
		List<Event> events = LoadFile.loadFromListString(lines);
		return loadRanking(events);
	}

	private static Ranking loadRanking(List<Event> events) {
		MatchEvent start = findGameByType(events, EventType.START);
		MatchEvent end = findGameByType(events, EventType.END);

		Match match = new Match(start.getId(), start.getWhen(), end.getWhen());

		for (Event event : events) {
			if (EventType.KILL.equals(event.getType())) {
				PlayerEvent playerEvent = (PlayerEvent) event;
				Player attacker = match.findPlayerByName(playerEvent.getKiller());
				Player attacked = match.findPlayerByName(playerEvent.getKilled());
				String weapon = playerEvent.getWeapon();
				LocalDateTime when = playerEvent.getWhen();
				attacker.addAction(new PlayerAction(attacker, attacked, weapon, ActionType.KILLED, when));
				attacked.addAction(new PlayerAction(attacker, attacked, weapon, ActionType.DIED, when));
			}
		}

		return new Ranking(match);
	}

	private static MatchEvent findGameByType(List<Event> events, EventType start) {
		for (Event event : events) {
			if (start.equals(event.getType()))
				return (MatchEvent) event;
		}
		throw new RuntimeException();
	}

	public Match getMatch() {
		return match;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		String headerPattern = "%-10s%s%n";
		builder.append(String.format(headerPattern, "Match ID: ", String.valueOf(this.match.getId())));
		builder.append(String.format(headerPattern, "Start at: ", DTF.format(this.match.getStartedAt())));
		builder.append(String.format(headerPattern, "Ended at: ", DTF.format(this.match.getEndedAt())));
		builder.append("\n");
		String formatPattern = "%-20s%15s%15s%10s%20s%10s%15s%20s%n";
		builder.append(
				String.format(formatPattern, "Player", "Kills", "Deaths", "KDR", "Weapon More Kill", "Streak", "Perfect", "Five in One Minute"));
		List<Player> players = this.match.getPlayers();
		Collections.sort(players);

		for (Player player : players) {
			if (!Player.WORLD.equals(player.getName()))
				builder.append(String.format(formatPattern, player.getName(), player.getKills(), player.getDeaths(), player.getKDR(),
						player.getPreferredWeapon(), player.getStreak(), player.isPerfect() ? "*" : "", player.hasSequence() ? "*" : ""));
		}
		return builder.toString();
	}
}
