
package ohtu.intjoukkosovellus;

import java.security.InvalidParameterException;

public class IntJoukko {

    public final static int OLETUS_KOKO = 5; // aloitustalukon koko
    private int kasvatuskoko = 5;     // Uusi taulukko on tämän verran vanhaa suurempi.
    private int[] lukujono;      // Joukon luvut säilytetään taulukon alkupäässä. 
    private int alkioidenLkm = 0;    // Tyhjässä joukossa alkioiden_määrä on nolla. 

    public void alusta() {
        for (int i = 0; i < lukujono.length; i++) {
            lukujono[i] = 0;
        }
    }

    public IntJoukko() {
        lukujono = new int[OLETUS_KOKO];
        alusta();
    }

    public IntJoukko(int koko) {
        if (koko < 0) {
            throw new InvalidParameterException("Koko ei voi olla negatiivinen!");
        }
        lukujono = new int[koko];
        alusta();
    }
    
    
    public IntJoukko(int koko, int kasvatuskoko) {
        if (koko < 0) {
            throw new InvalidParameterException("Koko ei voi olla negatiivinen!");
        }
        if (kasvatuskoko <= 0) {
            throw new InvalidParameterException("Kasvatuskoon on oltava suurempi kuin 0!");//heitin vaan jotain :D
        }
        lukujono = new int[koko];
        alusta();
    }

    public boolean lisaa(int luku) {

        if (!kuuluu(luku)) {
            lukujono[alkioidenLkm] = luku;
            alkioidenLkm++;
            if (alkioidenLkm >= lukujono.length) {
                kasvataTaulukkoa();
            }
            return true;
        }
        return false;
    }

    public void kasvataTaulukkoa () {
        int[] uusiTaulukko = new int[alkioidenLkm + kasvatuskoko];        
        kopioiTaulukko(lukujono, uusiTaulukko);        
        lukujono = uusiTaulukko;
    }

    public boolean kuuluu(int luku) {
        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == lukujono[i]) {
                return true;
            }
        }    
        return false;
    }

    public boolean poista(int luku) {
        
        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == lukujono[i]) {
                lukujono[i] = 0;
                siirraKohtiAlkua(i);   
                return true;
            }
        }
        return false;
    }

    public void siirraKohtiAlkua(int alkaen) {
        for (int j = alkaen; j < alkioidenLkm - 1; j++) {
            lukujono[j] = lukujono[j + 1];
        }
        alkioidenLkm--;
    }

    private void kopioiTaulukko(int[] vanha, int[] uusi) {
        for (int i = 0; i < vanha.length; i++) {
            uusi[i] = vanha[i];
        }
    }

    public int mahtavuus() {
        return alkioidenLkm;
    }


    @Override
    public String toString() {
        String joukkoMerkkijonona = "{";
        for (int i = 0; i < alkioidenLkm; i++) {
            joukkoMerkkijonona += lukujono[i];
            if (i != alkioidenLkm - 1){
                joukkoMerkkijonona += ", ";
            }
        }
        joukkoMerkkijonona = joukkoMerkkijonona + "}";
        return joukkoMerkkijonona;
    }

    public int[] toIntArray() {
        int[] taulu = new int[alkioidenLkm];
        for (int i = 0; i < taulu.length; i++) {
            taulu[i] = lukujono[i];
        }
        return taulu;
    }
   

    public static IntJoukko yhdiste(IntJoukko a, IntJoukko b) {
        IntJoukko x = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        for (int i = 0; i < aTaulu.length; i++) {
            x.lisaa(aTaulu[i]);
        }
        for (int i = 0; i < bTaulu.length; i++) {
            x.lisaa(bTaulu[i]);
        }
        return x;
    }

    public static IntJoukko leikkaus(IntJoukko a, IntJoukko b) {
        IntJoukko y = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        for (int i = 0; i < aTaulu.length; i++) {
            for (int j = 0; j < bTaulu.length; j++) {
                if (aTaulu[i] == bTaulu[j]) {
                    y.lisaa(bTaulu[j]);
                }
            }
        }
        return y;

    }
    
    public static IntJoukko erotus ( IntJoukko a, IntJoukko b) {
        IntJoukko z = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        for (int i = 0; i < aTaulu.length; i++) {
            z.lisaa(aTaulu[i]);
        }
        for (int i = 0; i < bTaulu.length; i++) {
            z.poista(bTaulu[i]);
        }
 
        return z;
    }
        
}
