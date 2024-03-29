package ohtu;

import com.google.gson.Gson;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import org.apache.http.client.fluent.Request;

public class Main {
    public static void main(String[] args) throws IOException {
        String url = "https://nhlstatisticsforohtu.herokuapp.com/players";
        
        String bodyText = Request.Get(url).execute().returnContent().asString();
                
        //System.out.println("json-muotoinen data:");
        //System.out.println( bodyText );

        Gson mapper = new Gson();
        ArrayList<Player> playerList = new ArrayList<>();
        Player[] players = mapper.fromJson(bodyText, Player[].class);
        for (Player p: players) {
            playerList.add(p);

        }

        //players.sort();
        Collections.sort(playerList);
        
        System.out.println("Oliot:");
        for (Player player : playerList) {
            System.out.println(player);
        }   
    }
  
}
