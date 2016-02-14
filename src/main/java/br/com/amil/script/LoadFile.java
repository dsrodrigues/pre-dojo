package br.com.amil.script;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.com.amil.game.event.Event;
import br.com.amil.game.event.EventType;
import br.com.amil.game.event.MatchEvent;
import br.com.amil.game.event.PlayerEvent;

public class LoadFile {

	private static final Pattern START = Pattern.compile("(\\d{2}/\\d{2}/\\d{4}\\s\\d{2}:\\d{2}:\\d{2}) - New match (\\d*) has started");
	private static final Pattern END = Pattern.compile("(\\d{2}/\\d{2}/\\d{4}\\s\\d{2}:\\d{2}:\\d{2}) - Match (\\d*) has ended");
	private static final Pattern PLAYER = Pattern
			.compile("(\\d{2}/\\d{2}/\\d{4}\\s\\d{2}:\\d{2}:\\d{2}) - (<WORLD>|\\w*) killed (\\w*) \\w* (\\w*)");
	private static final DateTimeFormatter DTF = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

	private LoadFile() {
	}

	public static List<Event> loadFromFilePath(String filePath) {
		try {
			List<String> lines = Files.readAllLines(Paths.get(filePath));
			return loadFromListString(lines);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static List<Event> loadFromListString(List<String> lines) {
		List<Event> events = new ArrayList<Event>();

		for (String line : lines) {
			Matcher matcherStart = START.matcher(line);
			Matcher matcherEnd = END.matcher(line);
			Matcher matcherPlayer = PLAYER.matcher(line);

			if (matcherStart.find()) {
				events.add(createStartEndEvent(matcherStart, EventType.START));
			} else if (matcherEnd.find()) {
				events.add(createStartEndEvent(matcherEnd, EventType.END));
			} else if (matcherPlayer.find()) {
				LocalDateTime when = LocalDateTime.parse(matcherPlayer.group(1), DTF);
				String killer = matcherPlayer.group(2);
				String killed = matcherPlayer.group(3);
				String weapon = matcherPlayer.group(4);
				events.add(new PlayerEvent(killer, killed, weapon, when, EventType.KILL));
			}
		}

		Collections.sort(events);

		return events;
	}

	private static MatchEvent createStartEndEvent(Matcher matcher, EventType type) {
		LocalDateTime when = LocalDateTime.parse(matcher.group(1), DTF);
		long gameId = Long.parseLong(matcher.group(2));
		
		return new MatchEvent(gameId, when, type);
	}
}
