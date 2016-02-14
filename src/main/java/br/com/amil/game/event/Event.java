package br.com.amil.game.event;

import java.time.LocalDateTime;

public interface Event extends Comparable<Event> {

	LocalDateTime getWhen();

	EventType getType();
}
