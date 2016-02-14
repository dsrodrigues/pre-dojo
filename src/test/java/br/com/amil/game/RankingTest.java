package br.com.amil.game;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

public class RankingTest {

	@Test
	public void testLoadFromString() {
		Ranking ranking = Ranking.loadFromString(Arrays.asList("14/02/2016 01:19:39 - New match 6515015 has started",
				"14/02/2016 01:19:41 - Kerbin killed Brumm using AK47", "14/02/2016 01:19:42 - Maind killed Kerbin using Izi9mm",
				"14/02/2016 01:19:45 - Roman killed Kerbin using Knife", "14/02/2016 01:20:01 - <WORLD> killed Kerbin by Lock",
				"14/02/2016 01:20:07 - Long killed Nick using Barrete", "14/02/2016 01:20:10 - Kerbin killed Brumm using Knife",
				"14/02/2016 01:26:36 - Brumm killed Nick using AK47", "14/02/2016 01:26:40 - Match 6515015 has ended"));
		Assert.assertNotNull(ranking);
		Assert.assertNotNull(ranking.getMatch());
	}
}
