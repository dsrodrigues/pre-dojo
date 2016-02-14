package br.com.amil.game.event;

import java.time.LocalDateTime;

public class MatchEvent implements Event {

	private long id;
	private LocalDateTime when;
	private EventType type;

	public MatchEvent() {
	}

	public MatchEvent(long id, LocalDateTime when, EventType type) {
		this.id = id;
		this.when = when;
		this.type = type;
	}

	@Override
	public LocalDateTime getWhen() {
		return this.when;
	}

	public void setWhen(LocalDateTime when) {
		this.when = when;
	}

	@Override
	public EventType getType() {
		return this.type;
	}

	public void setType(EventType type) {
		this.type = type;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Override
	public int compareTo(Event o) {
		return this.when.compareTo(o.getWhen());
	}
}
