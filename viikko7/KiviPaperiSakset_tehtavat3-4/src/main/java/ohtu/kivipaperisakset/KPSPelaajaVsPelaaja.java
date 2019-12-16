package ohtu.kivipaperisakset;

import java.util.Scanner;

public class KPSPelaajaVsPelaaja extends KSP {

    private static final Scanner scanner = new Scanner(System.in);

    public KPSPelaajaVsPelaaja() {
        super();
    }

    @Override
    public String haeSeuraava() {
        return scanner.nextLine();
    }

    @Override
    public void asetaSiirto(String siirto) {

    }
}