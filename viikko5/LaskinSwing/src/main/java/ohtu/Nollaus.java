package ohtu;

import javax.swing.JTextField;

public class Nollaus extends Komento {

    int value;

    public Nollaus(JTextField tulos, JTextField syote) {
        super(tulos, syote);
    }

    @Override
    public void suorita() {
        value = Integer.parseInt(this.tulos.getText());
        this.tulos.setText(String.valueOf(0));
        this.syote.setText(String.valueOf(0));
    }

    @Override
    public void peru() {
        this.tulos.setText(String.valueOf(value));
    }

}