package ohtu;

import javax.swing.JButton;
import javax.swing.JTextField;

public class Erotus extends Komento {

    int value;

    public Erotus(JTextField tulos, JTextField syote) {
        super(tulos, syote);
    }

    @Override
    public void suorita() {
        int a = Integer.parseInt(tulos.getText());
        int b = Integer.parseInt(syote.getText());
        value = b;
        this.tulos.setText(String.valueOf(a - b));
    }

    @Override
    public void peru() {
        int a = Integer.parseInt(tulos.getText());
        this.tulos.setText(String.valueOf(a + value));
        this.syote.setText(String.valueOf(value));
    }

}