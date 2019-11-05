package ohtu.verkkokauppa;

public interface TuoteInterface {

    //public Tuote(int id, String nimi, int hinta);
    public int getId();
    public boolean equals(Object o);
    public int getHinta();
    public String toString();

}