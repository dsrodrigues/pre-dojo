package br.com.amil.game.player;

import java.time.LocalDateTime;

public class PlayerAction implements Comparable<PlayerAction> {

	private final Player attacker;
	private final Player attacked;
	private final String weapon;
	private final ActionType actionType;
	private final LocalDateTime when;

	public PlayerAction(Player attacker, Player attacked, String weapon, ActionType actionType, LocalDateTime when) {
		this.attacker = attacker;
		this.attacked = attacked;
		this.weapon = weapon;
		this.actionType = actionType;
		this.when = when;
	}

	public Player getAttacker() {
		return attacker;
	}

	public Player getAttacked() {
		return attacked;
	}

	public String getWeapon() {
		return weapon;
	}

	public ActionType getActionType() {
		return actionType;
	}

	public LocalDateTime getWhen() {
		return when;
	}

	@Override
	public int compareTo(PlayerAction o) {
		return this.when.compareTo(o.getWhen());
	}
}
