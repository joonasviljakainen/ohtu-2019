package ohtu;

import javax.swing.JTextField;

public class Nollaus extends Komento {

    public Nollaus(JTextField tulos, JTextField syote) {
        super(tulos, syote);
    }

    @Override
    public void suorita() {
        this.tulos.setText(String.valueOf(0));
        this.syote.setText(String.valueOf(0));
    }

}