package ohtu;

import javax.swing.JTextField;

public abstract class Komento {
    JTextField tulos;
    JTextField syote;

    public Komento(JTextField tulos, JTextField syote) {
        this.tulos = tulos;
        this.syote = syote;
    }

    public abstract void suorita();

    public abstract void peru();
}