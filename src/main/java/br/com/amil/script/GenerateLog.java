package br.com.amil.script;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class GenerateLog {

	private static final List<String> names = new ArrayList<String>(
			Arrays.asList("Roman", "Nick", "Lock", "Long", "Maind", "Brumm", "Kerbin", "Dota", "Tube", "Octor"));
	private static final List<String> weapon = new ArrayList<String>(
			Arrays.asList("AK47", "M16", "P90", "Pistol", "Knife", "Shotgun", "Barrete", "Izi9mm"));
	private static final DateTimeFormatter DTF = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

	public static void main(String[] args) {
		StringBuilder sb = new StringBuilder();
		LocalDateTime ldt = LocalDateTime.now();
		Integer matchId = new Random().nextInt(9999999);
		sb.append(String.format("%s - New match %d has started\n", DTF.format(ldt), matchId));
		sleep();
		for (int i = 0; i < 150; i++) {
			ldt = LocalDateTime.now();
			String firstName = names.get(new Random().nextInt(names.size()));
			sb.append(String.format("%s - %s killed %s using %s\n", DTF.format(ldt), firstName, randomNameDiff(firstName),
					weapon.get(new Random().nextInt(weapon.size()))));
			sleep();
			if (new Random().nextInt(100) % 10 == 0) {
				firstName = names.get(new Random().nextInt(names.size()));
				sb.append(String.format("%s - <WORLD> killed %s by %s\n", DTF.format(ldt), firstName, randomNameDiff(firstName),
						weapon.get(new Random().nextInt(weapon.size()))));
				sleep();
			}
		}

		ldt = LocalDateTime.now();
		sb.append(String.format("%s - Match %d has ended\n", DTF.format(ldt), matchId));
		System.out.println(sb.toString());
	}

	private static String randomNameDiff(String name) {
		String otherName = null;
		do {
			otherName = names.get(new Random().nextInt(names.size()));
		} while (name.equals(otherName));
		return otherName;
	}

	public static void sleep() {
		try {
			Thread.sleep(new Random().nextInt(5000));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
