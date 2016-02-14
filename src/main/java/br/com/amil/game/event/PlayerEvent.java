package br.com.amil.game.event;

import java.time.LocalDateTime;

public class PlayerEvent implements Event {

	private String killer;
	private String killed;
	private String weapon;
	private LocalDateTime when;
	private EventType type;

	public PlayerEvent() {
	}

	public PlayerEvent(String killer, String killed, String weapon, LocalDateTime when, EventType type) {
		this.killer = killer;
		this.killed = killed;
		this.weapon = weapon;
		this.when = when;
		this.type = type;
	}

	public String getKiller() {
		return killer;
	}

	public void setKiller(String killer) {
		this.killer = killer;
	}

	public String getKilled() {
		return killed;
	}

	public void setKilled(String killed) {
		this.killed = killed;
	}

	public String getWeapon() {
		return weapon;
	}

	public void setWeapon(String weapon) {
		this.weapon = weapon;
	}

	@Override
	public LocalDateTime getWhen() {
		return this.when;
	}

	public void setWhen(LocalDateTime when) {
		this.when = when;
	}

	public void setType(EventType type) {
		this.type = type;
	}

	@Override
	public EventType getType() {
		return this.type;
	}

	@Override
	public int compareTo(Event o) {
		return this.when.compareTo(o.getWhen());
	}

}
