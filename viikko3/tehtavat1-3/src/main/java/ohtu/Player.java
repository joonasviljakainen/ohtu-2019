
package ohtu;

public class Player implements Comparable<Player> {
    private String name;
    private int goals; 
    private int assists;
    private int penalties;
    private String team;
    private String nationality;
    private String birthdate;


    public void setName(String name) {
        this.name = name;
    }
    public void setGoals(int goals) {
        this.goals = goals;
    }
    public void setAssists(int assists) {
        this.assists = assists;
    }
    public void setPenalties(int penalties) {
        this.penalties = penalties;
    }
    public void setTeam(String team) {
        this.team = team;
    }
    public void setNationality(String nationality) {
        this.nationality = nationality;
    }
    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getName() {
        return this.name;
    }
    public int getGoals() {
        return this.goals;
    }
    public int getAssists() {
        return this.assists;
    }
    public int getPenalties() {
        return this.penalties;
    }
    public String getTeam() {
        return this.team;
    }
    public String getNationality() {
        return this.nationality;
    }
    public String getBirthdate() {
        return this.birthdate;
    }
    public int getScore() {
        return goals + assists;
    }

    @Override
    public String toString() {
        return name + "\t | Team: " + team + "\t | Goals: " + goals + "\t | Assists: " + assists + "\t | Score: " + (assists + goals);
    }

    @Override
    public int compareTo(Player p2) {
        int p1Score = assists + goals;
        int res = Integer.compare(p2.getScore(), p1Score);
        if (res == 0) {
            int goalComparison = Integer.compare(p2.getGoals(), goals);
            if (goalComparison != 0) {
                return goalComparison;
            } else {
                return Integer.compare(p2.getAssists(), assists);
            }
        } else return res;

    }
      
}
