package ohtu;

import javax.swing.JButton;
import javax.swing.JTextField;

public class Summa extends Komento {

    int value;

    public Summa(JTextField tulos, JTextField syote) {
        super(tulos, syote);
    }

    @Override
    public void suorita() {
        int a = Integer.parseInt(tulos.getText());
        int b = Integer.parseInt(syote.getText());

        value = a;
        this.tulos.setText(String.valueOf(a + b));
    }

    @Override
    public void peru() {
        int a = Integer.parseInt(tulos.getText());
        this.tulos.setText(String.valueOf(a - value));
        this.syote.setText(String.valueOf(value));
    }

}