package statistics;

import java.util.List;

import statistics.matcher.*;

public class Main {
    public static void main(String[] args) {
        // seuraavassa osoitteessa 27.11.2019 päivitetyt tilastot
        String url = "https://nhl27112019.herokuapp.com/players.txt";
        // ajan tasalla olevat tilastot osoitteessa
        // "https://nhlstatisticsforohtu.herokuapp.com/players.txt"

        Statistics stats = new Statistics(new PlayerReaderImpl(url));

        Matcher m = new And(new HasAtLeast(5, "goals"), new HasAtLeast(5, "assists"), new PlaysIn("PHI"));
        printPlayers(stats.matches(m));

        Matcher b = new All();
        printPlayers(stats.matches(b));

        Matcher c = new Not(new HasAtLeast(3, "goals"), new HasAtLeast(3, "assists"), new PlaysIn("CGY"));
        printPlayers(stats.matches(c));

        Matcher fewerTest = new And(new HasFewerThan(4, "goals"), new HasFewerThan(3, "assists"), new PlaysIn("CGY"));
        printPlayers(stats.matches(fewerTest));

        // TESTI: pitäisi olla samat

        Matcher m2 = new And(new Not(new HasAtLeast(1, "goals")), new PlaysIn("NYR"));
        printPlayers(stats.matches(m2));

        Matcher m3 = new And(new HasFewerThan(1, "goals"), new PlaysIn("NYR"));
        printPlayers(stats.matches(m3));

        Matcher or = new Or(new HasAtLeast(20, "goals"), new HasAtLeast(20, "assists"));
        printPlayers(stats.matches(or));
        Matcher or2 = new And(new HasAtLeast(20, "points"),
                new Or(new PlaysIn("NYR"), new PlaysIn("NYI"), new PlaysIn("NJD")));
        printPlayers(stats.matches(or2));

    }

    public static void printPlayers(List<Player> matches) {
        for (Player p : matches) {
            System.out.println(p);
        }

        System.out.println();
    }
}
