package ohtu.kivipaperisakset;

import java.util.Scanner;

public abstract class KSP implements Peli {

    
    private Scanner scanner;
    protected KSP(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public void pelaa() {
        Tuomari tuomari = new Tuomari();
        //Tekoaly tekoaly = new Tekoaly();

        System.out.print("Ensimmäisen pelaajan siirto: ");
        String ekanSiirto = scanner.nextLine();
        String tokanSiirto;

        //tokanSiirto = tekoaly.annaSiirto();
        tokanSiirto = haeSeuraava();
        System.out.println("Tietokone valitsi: " + tokanSiirto);


        while (onkoOkSiirto(ekanSiirto) && onkoOkSiirto(tokanSiirto)) {
            tuomari.kirjaaSiirto(ekanSiirto, tokanSiirto);
            System.out.println(tuomari);
            System.out.println();

            System.out.print("Ensimmäisen pelaajan siirto: ");
            ekanSiirto = scanner.nextLine();

            //tokanSiirto = tekoaly.annaSiirto();
            tokanSiirto = haeSeuraava();
            System.out.println("Tietokone valitsi: " + tokanSiirto);
            //tekoaly.asetaSiirto(ekanSiirto);
            asetaSiirto(ekanSiirto);

        }

        System.out.println();
        System.out.println("Kiitos!");
        System.out.println(tuomari);
    }

    protected abstract String haeSeuraava();

    protected abstract void asetaSiirto(String siirto);

    protected Scanner getScanner() {
        return this.scanner;
    }

    private static boolean onkoOkSiirto(String siirto) {
        return "k".equals(siirto) || "p".equals(siirto) || "s".equals(siirto);
    }
}