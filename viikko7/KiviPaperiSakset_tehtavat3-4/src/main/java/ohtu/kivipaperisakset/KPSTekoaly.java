package ohtu.kivipaperisakset;

import java.util.Scanner;

public class KPSTekoaly extends KSP {
    Tekoaly tekoaly;

    public KPSTekoaly (Scanner scanner) {
        super(scanner);
        tekoaly = new Tekoaly();
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