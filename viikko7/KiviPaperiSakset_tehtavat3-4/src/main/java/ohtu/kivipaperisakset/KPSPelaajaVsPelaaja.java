package ohtu.kivipaperisakset;

import java.util.Scanner;

public class KPSPelaajaVsPelaaja extends KSP {

    public KPSPelaajaVsPelaaja(Scanner scanner) {
        super(scanner);
    }

    @Override
    public String haeSeuraava() {
        return super.getScanner().nextLine();
    }

    @Override
    public void asetaSiirto(String siirto) {

    }
}