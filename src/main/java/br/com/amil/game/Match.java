package br.com.amil.game;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.amil.game.player.Player;

public class Match {

	private final long id;
	private final LocalDateTime startedAt;
	private final LocalDateTime endedAt;
	private Map<String, Player> players = new HashMap<String, Player>();

	public Match(long id, LocalDateTime startedAt, LocalDateTime endedAt) {
		this.id = id;
		this.startedAt = startedAt;
		this.endedAt = endedAt;
	}

	public List<Player> getPlayers() {
		return new ArrayList<Player>(this.players.values());
	}

	public long getId() {
		return id;
	}

	public LocalDateTime getStartedAt() {
		return startedAt;
	}

	public LocalDateTime getEndedAt() {
		return endedAt;
	}

	public void addPlayer(Player player) {
		this.players.put(player.getName(), player);
	}
	
	public Player findPlayerByName(String name){
		if (!players.containsKey(name))
			players.put(name, new Player(name));
		return players.get(name);
	}
}
