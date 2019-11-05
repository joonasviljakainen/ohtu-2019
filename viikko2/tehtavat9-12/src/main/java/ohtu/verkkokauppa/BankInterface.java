package ohtu.verkkokauppa;

public interface BankInterface {
    public static BankInterface getInstance() {
        return new Pankki();
    };
    public boolean tilisiirto(String nimi, int viitenumero, String tililta, String tilille, int summa);
}