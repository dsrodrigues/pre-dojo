package br.com.amil.game.player;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import br.com.amil.util.MapUtil;

public class Player implements Comparable<Player> {

	public static final String WORLD = "<WORLD>";

	private String name;
	private List<PlayerAction> actions;
	private Map<String, Integer> preferredWeapon = new LinkedHashMap<String, Integer>();
	private Integer streak = 0;
	private List<PlayerAction> kills = new ArrayList<PlayerAction>();
	private List<PlayerAction> deaths = new ArrayList<PlayerAction>();

	public Player(String name) {
		this.name = name;
		this.actions = new ArrayList<PlayerAction>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public List<PlayerAction> getActions() {
		return actions;
	}

	public void addAction(PlayerAction action) {
		this.actions.add(action);
		if (ActionType.KILLED.equals(action.getActionType())) {
			processPreferredWeapon(action.getWeapon());
			this.kills.add(action);
		} else
			this.deaths.add(action);
	}

	public Integer getKills() {
		return kills.size();
	}

	public Integer getDeaths() {
		return deaths.size();
	}

	public BigDecimal getKDR() {
		BigDecimal kills = new BigDecimal(this.kills.size());
		BigDecimal deaths = new BigDecimal(this.deaths.size());
		try {
			return kills.divide(deaths, 2, RoundingMode.DOWN);
		} catch (Exception e) {
			return kills;
		}
	}

	public int getStreak() {
		if (this.deaths.size() == 0) {
			this.streak = kills.size();
		} else {
			int nextStreak = 0;
			Collections.sort(this.actions);
			for (PlayerAction playerAction : actions) {
				if (!ActionType.DIED.equals(playerAction.getActionType()))
					nextStreak++;
				else {
					if (this.streak < nextStreak)
						this.streak = nextStreak;
					nextStreak = 0;
				}
			}
			if (this.streak < nextStreak)
				this.streak = nextStreak;
		}
		return this.streak;
	}

	public boolean isPerfect() {
		// for (PlayerAction playerAction : actions) {
		// if (ActionType.DIED.equals(playerAction.getActionType()) &&
		// !WORLD.equals(playerAction.getAttacker().getName()))
		// return false;
		// }
		return deaths.isEmpty();
	}

	public boolean hasSequence() {
		if (kills.size() < 5)
			return false;
		Collections.sort(this.kills);
		for (int i = 0; i < kills.size() - 5; i++) {
			List<PlayerAction> subList = kills.subList(i, i + 5);
			if (ChronoUnit.SECONDS.between(subList.get(0).getWhen(), subList.get(4).getWhen()) <= 60L)
				return true;
		}
		return false;
	}

	public String getPreferredWeapon() {
		preferredWeapon = MapUtil.sortByValue(preferredWeapon);
		return preferredWeapon.keySet().iterator().next();
	}

	private void processPreferredWeapon(String weapon) {
		if (!preferredWeapon.containsKey(weapon))
			preferredWeapon.put(weapon, 0);
		preferredWeapon.put(weapon, preferredWeapon.get(weapon) + 1);
	}

	@Override
	public int compareTo(Player player) {
		if (player.getKills().compareTo(this.getKills()) == 0)
			return this.getDeaths().compareTo(player.getDeaths());
		return player.getKills().compareTo(this.getKills());
	}
}
