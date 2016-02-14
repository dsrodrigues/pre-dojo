package br.com.amil.game;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.amil.game.player.Player;

public class MatchTest {

	private Match match;

	@Before
	public void setup() {
		match = new Match(1L, LocalDateTime.now(), LocalDateTime.now());
		match.findPlayerByName("Roman");
		match.findPlayerByName("Down");
	}

	@Test
	public void testGetPlayers() {
		List<Player> players = match.getPlayers();
		Assert.assertSame(players.size(), 2);
	}

	@Test
	public void testFindPlayerByName() {
		Player roman = match.findPlayerByName("Roman");
		Assert.assertSame(roman.getName(), "Roman");

		Player down = match.findPlayerByName("Down");
		Assert.assertSame(down.getName(), "Down");
	}
}
