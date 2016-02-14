package br.com.amil.game.player;

import java.time.LocalDateTime;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PlayerTest {

	private Player player;
	private Player otherPlayer;

	@Before
	public void setup() {
		this.player = new Player("Roman");
		this.otherPlayer = new Player("Prince");
		Player nick = new Player("Nick");

		this.player.addAction(new PlayerAction(this.player, nick, "AK47", ActionType.KILLED, LocalDateTime.now()));
		this.player.addAction(
				new PlayerAction(this.player, new Player("Lock"), "AK47", ActionType.KILLED, LocalDateTime.now().plusSeconds(1L)));
		this.player.addAction(
				new PlayerAction(this.player, new Player("Maind"), "M16", ActionType.KILLED, LocalDateTime.now().plusSeconds(2L)));
		this.player.addAction(
				new PlayerAction(this.player, new Player("Long"), "Pistol", ActionType.KILLED, LocalDateTime.now().plusSeconds(3L)));
		this.player.addAction(new PlayerAction(this.player, nick, "AK47", ActionType.KILLED, LocalDateTime.now().plusSeconds(4L)));

		this.player.addAction(new PlayerAction(nick, this.player, "M16", ActionType.DIED, LocalDateTime.now().plusSeconds(15L)));

		this.player.addAction(
				new PlayerAction(this.player, new Player("Lock"), "AK47", ActionType.KILLED, LocalDateTime.now().plusSeconds(75L)));
		this.player.addAction(
				new PlayerAction(this.player, new Player("Maind"), "M16", ActionType.KILLED, LocalDateTime.now().plusSeconds(90L)));
		this.player.addAction(
				new PlayerAction(this.player, new Player("Long"), "Pistol", ActionType.KILLED, LocalDateTime.now().plusSeconds(120L)));

		this.otherPlayer.addAction(new PlayerAction(this.player, nick, "AK47", ActionType.KILLED, LocalDateTime.now()));
	}

	@Test
	public void testAddAction() {
		Player lock = new Player("Lock");
		PlayerAction action = new PlayerAction(lock, lock, "Grenade", ActionType.DIED, LocalDateTime.now());
		lock.addAction(action);

		Assert.assertSame(lock.getActions().size(), 1);
		Assert.assertSame(lock.getActions().get(0), action);
	}

	@Test
	public void testGetPreferredWeapon() {
		Assert.assertSame(this.player.getPreferredWeapon(), "AK47");
	}

	@Test
	public void testGetStreak() {
		Assert.assertSame(this.player.getStreak(), 5);
	}

	@Test
	public void testIsPerfect() {
		Assert.assertSame(this.otherPlayer.isPerfect(), true);
	}

	@Test
	public void testIsNotPerfect() {
		Assert.assertSame(this.player.isPerfect(), false);
	}

	@Test
	public void testGetKills() {
		Assert.assertSame(this.player.getKills(), 8);
	}

	@Test
	public void testGetDeaths() {
		Assert.assertSame(this.player.getDeaths(), 1);
	}

	@Test
	public void testHasSequence() {
		Assert.assertSame(this.player.hasSequence(), true);
		Assert.assertSame(this.otherPlayer.hasSequence(), false);
	}
}
