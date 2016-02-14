package br.com.amil.script;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import br.com.amil.game.event.Event;
import br.com.amil.game.event.EventType;

public class LoadFileTest {

	@Test
	public void testLoadFromListString() {
		List<Event> events = LoadFile.loadFromListString(Arrays.asList("14/02/2016 01:19:39 - New match 6515015 has started",
				"14/02/2016 01:19:41 - Kerbin killed Brumm using AK47", "14/02/2016 01:19:42 - Maind killed Kerbin using Izi9mm",
				"14/02/2016 01:19:45 - Roman killed Kerbin using Knife", "14/02/2016 01:20:01 - <WORLD> killed Kerbin by Lock",
				"14/02/2016 01:20:07 - Long killed Nick using Barrete", "14/02/2016 01:20:10 - Kerbin killed Brumm using Knife",
				"14/02/2016 01:26:36 - Brumm killed Nick using AK47", "14/02/2016 01:26:40 - Match 6515015 has ended"));
		Assert.assertSame(events.size(), 9);

		int startCount = 0;
		int endCount = 0;
		int killCount = 0;

		for (Event event : events) {
			if (EventType.START.equals(event.getType()))
				startCount++;
			else if (EventType.END.equals(event.getType()))
				endCount++;
			else if (EventType.KILL.equals(event.getType()))
				killCount++;
		}
		Assert.assertSame(startCount, 1);
		Assert.assertSame(endCount, 1);
		Assert.assertSame(killCount, 7);
	}
}
