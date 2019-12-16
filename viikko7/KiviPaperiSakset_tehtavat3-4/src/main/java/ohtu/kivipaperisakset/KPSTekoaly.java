package ohtu.kivipaperisakset;


public class KPSTekoaly extends KSP {
    Tekoaly tekoaly;

    public KPSTekoaly () {
        super();
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