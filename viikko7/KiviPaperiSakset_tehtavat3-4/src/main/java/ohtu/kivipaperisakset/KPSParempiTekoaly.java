package ohtu.kivipaperisakset;

import java.util.Scanner;

// Kivi-Paperi-Sakset, jossa voidaan valita pelataanko vastustajaa
// vastaan vai ei
public class KPSParempiTekoaly extends KSP {

    TekoalyParannettu tekoaly;

    public KPSParempiTekoaly(Scanner scanner, int muistinKoko) {
        super(scanner);
        tekoaly = new TekoalyParannettu(muistinKoko);
    }

    @Override
    protected String haeSeuraava() {
        return tekoaly.annaSiirto();
    }

    @Override
    protected void asetaSiirto(String siirto) {
        tekoaly.asetaSiirto(siirto);
    }


}
