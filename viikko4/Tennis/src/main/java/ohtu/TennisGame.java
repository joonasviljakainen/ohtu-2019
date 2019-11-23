package ohtu;

import java.security.InvalidParameterException;

public class TennisGame {
    
    private int player1Score = 0;
    private int player2Score = 0;
    private String player1Name;
    private String player2Name;

    public TennisGame(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
    }

    public void wonPoint(String playerName) {
        if (playerName == this.player1Name) {
            player1Score++;
        } else if (playerName == this.player2Name) {
            player2Score++;
        } else {
            throw new InvalidParameterException("No player by name " + playerName);
        }
    }

    public String getEvenScore() {
        if (player1Score <= 3) {
            return getSingleScore(player1Score) + "-All";
        } else {
            return "Deuce";
        }
    }

    public String getAdvantageScore() {
        int difference = player1Score - player2Score;
        
        if (difference == 1) {
            return "Advantage player1";
        } else if (difference == -1) {
            return "Advantage player2";
        } else if (difference >= 2) { 
            return "Win for player1"; 
        } else {
            return "Win for player2";
        }
    }

    public String getSingleScore(int score) {
        if (score >= 0 && score <= 3) {
            switch(score) {
                case 0:
                    return "Love";
                case 1:
                    return "Fifteen";
                case 2:
                    return "Thirty";
                case 3:
                    return "Forty";
                default: 
                    return null;
            }
        } else {
            throw new IllegalArgumentException("Score must be between 0 and 3!");
        }   
    }

    public String getUnevenScore() {
        if (player1Score >= 4 || player2Score >= 4) {
            return getAdvantageScore();
        } else {
            return getSingleScore(player1Score) + "-" + getSingleScore(player2Score);
        }
    }

    public String getScore() {
        if (player1Score == player2Score) {
            return getEvenScore();    
        } else {
            return getUnevenScore();
        }
    }
}