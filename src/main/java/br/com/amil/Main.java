package br.com.amil;

import br.com.amil.game.Ranking;

public class Main {

	public static void main(String[] args) {
		if (args.length == 0) {
			System.err.println("Execute java -jar pre-dojo.jar PATH_TO_FILE");
		}
		try {
			Ranking ranking = Ranking.loadFromFilePath(args[0]);
			System.out.println(ranking);
		} catch (Exception e) {
			System.err.printf("An error ocurred: %s%n", e.getMessage());
		}
	}

}
