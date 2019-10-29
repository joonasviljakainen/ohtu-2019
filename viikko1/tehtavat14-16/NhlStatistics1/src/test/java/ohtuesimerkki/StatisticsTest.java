
package ohtuesimerkki;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class StatisticsTest {

    Statistics stats;

    Reader readerStub = new Reader() {
 
        public List<Player> getPlayers() {
            ArrayList<Player> players = new ArrayList<>();
 
            players.add(new Player("Crenellini", "SAI", 4, 12));
            players.add(new Player("Fortificare", "HEL", 45, 54));
            players.add(new Player("Kurri",   "EDM", 37, 53));
            players.add(new Player("Yzerman", "DET", 42, 56));
            players.add(new Player("Gretzky", "EDM", 35, 89));
            players.add(new Player("Lemmings", "ALA", 66, 49));
 
            return players;
        }
    };

    @Before
    public void setUp() {
        stats = new Statistics(readerStub);
    }

    @Test
    public void fetchesPlayersOfTeam() {
        List<Player> team = stats.team("ALA");
        assertEquals(1, team.size());
        team = stats.team("EDM");
        assertEquals(2, team.size());
    }

    @Test
    public void retrievesTopScorers() {
        List<Player> scorers = stats.topScorers(1);
        assertEquals(2, scorers.size());
        assertEquals(scorers.get(0).getName(), "Gretzky");
        assertEquals(scorers.get(1).getName(), "Lemmings");
    }

    @Test
    public void canSearch() {
        Player p = stats.search("Ku");
        assertEquals("Kurri", p.getName());
    }

    @Test
    public void returnsNullIfPlayerNotFound() {
        Player p = stats.search("Ibrahimovich");
        assertNull(p);
    }

}