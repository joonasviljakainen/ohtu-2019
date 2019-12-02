package ohtu;

import javax.swing.JButton;
import javax.swing.JTextField;

public class Erotus extends Komento {

    public Erotus(JTextField tulos, JTextField syote) {
        super(tulos, syote);
    }

    @Override
    public void suorita() {
        int a = Integer.parseInt(tulos.getText());
        int b = Integer.parseInt(syote.getText());
        this.tulos.setText(String.valueOf(a - b));

    }

}