package ohtu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JTextField;
 
public class Tapahtumankuuntelija implements ActionListener {
    private JButton plus;
    private JButton miinus;
    private JButton nollaa;
    private JButton undo;
    private JTextField tuloskentta;
    private JTextField syotekentta;
    private Sovelluslogiikka sovellus;
    private Map<JButton, Komento> komennot;
    private Komento edellinen = null;
 
    public Tapahtumankuuntelija(JButton plus, JButton miinus, JButton nollaa, JButton undo, JTextField tuloskentta, JTextField syotekentta) {
        this.plus = plus;
        this.miinus = miinus;
        this.nollaa = nollaa;
        this.undo = undo;
        this.tuloskentta = tuloskentta;
        this.syotekentta = syotekentta;

        this.undo = undo;
        this.sovellus = new Sovelluslogiikka();
        komennot = new HashMap<>();
        komennot.put(plus, new Summa(tuloskentta, syotekentta) );
        komennot.put(miinus, new Erotus(tuloskentta, syotekentta) );
        komennot.put(nollaa, new Nollaus(tuloskentta, syotekentta) );
    }

    
    @Override
    public void actionPerformed(ActionEvent ae) {
        if ( ae.getSource() != undo ) {
            Komento komento = komennot.get((JButton) ae.getSource());
            komento.suorita();
            edellinen = komento;
        } else {
            //edellinen.peru();
            edellinen = null;
        }                  
    }
 
}