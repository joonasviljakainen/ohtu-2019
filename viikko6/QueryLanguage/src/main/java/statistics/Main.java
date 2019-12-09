package statistics;

import statistics.matcher.*;

public class Main {
    public static void main(String[] args) {
        // seuraavassa osoitteessa 27.11.2019 päivitetyt tilastot
        String url = "https://nhl27112019.herokuapp.com/players.txt";
        // ajan tasalla olevat tilastot osoitteessa
        // "https://nhlstatisticsforohtu.herokuapp.com/players.txt"

        Statistics stats = new Statistics(new PlayerReaderImpl(url));

        Matcher m = new And(new HasAtLeast(5, "goals"), new HasAtLeast(5, "assists"), new PlaysIn("PHI"));

        for (Player player : stats.matches(m)) {
            System.out.println(player);
        }

        Matcher b = new All();
        for (Player p : stats.matches(b)) {
            System.out.println(p);
        }

        Matcher c = new Not(new HasAtLeast(3, "goals"), new HasAtLeast(3, "assists"), new PlaysIn("CGY"));
        for (Player p : stats.matches(c)) {
            System.out.println(p);
        }
    }
}
