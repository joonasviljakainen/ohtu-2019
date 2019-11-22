package ohtu.verkkokauppa;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class KauppaTest {

    Pankki pankki;
    Viitegeneraattori viite;
    Varasto varasto;

    @Before
    public void setUp() {
        pankki = mock(Pankki.class);
        viite = mock(Viitegeneraattori.class);
        varasto = mock(Varasto.class);
    }

    @Test
    public void ostoksenPaaytyttyaPankinMetodiaTilisiirtoKutsutaan() {
        // määritellään että viitegeneraattori palauttaa viitten 42
        when(viite.uusi()).thenReturn(42);

        // määritellään että tuote numero 1 on maito jonka hinta on 5 ja saldo 10
        when(varasto.saldo(1)).thenReturn(10); 
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));

        // sitten testattava kauppa 
        Kauppa k = new Kauppa(varasto, pankki, viite);              

        // tehdään ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        k.tilimaksu("pekka", "12345");
        System.out.println("MÖSÖMSÖMSÖMSS");

        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu
        verify(pankki).tilisiirto(anyString(), anyInt(), anyString(), anyString(),anyInt());   
        // toistaiseksi ei välitetty kutsussa käytetyistä parametreista
    }

    @Test
    public void ostoksenPaatyttyaKivastiKutsutaanOikeillaParametreilla() {
    
        when(viite.uusi()).thenReturn(42);

        when(varasto.saldo(1)).thenReturn(10); 
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));

        Kauppa k = new Kauppa(varasto, pankki, viite);              

        k.aloitaAsiointi();
        k.lisaaKoriin(1);     
        k.tilimaksu("pekka", "12345");
        
        verify(pankki).tilisiirto("pekka", 42, "12345", "33333-44455", 5);   
    }

    @Test
    public void useidenOstostenKanssaKutsutaanOikein() {
        when(viite.uusi()).thenReturn(66);
        when(varasto.saldo(1)).thenReturn(10); 
        when(varasto.saldo(2)).thenReturn(10); 
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "pulla", 12));
        
        Kauppa k = new Kauppa(varasto, pankki, viite);              

        k.aloitaAsiointi();
        k.lisaaKoriin(1);     
        k.lisaaKoriin(2);     
        k.tilimaksu("harri", "54321");
        
        verify(pankki).tilisiirto("harri", 66, "54321", "33333-44455", 5 + 12);   
    }
    
    @Test
    public void kahdenSamanTuotteenJalkeenOikeanParametrit() {
        when(viite.uusi()).thenReturn(345);
        when(varasto.saldo(1)).thenReturn(10); 
        when(varasto.saldo(2)).thenReturn(10); 
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "pulla", 12));

        Kauppa k = new Kauppa(varasto, pankki, viite);              
        k.aloitaAsiointi();
        k.lisaaKoriin(1);     
        k.lisaaKoriin(1);     

        k.tilimaksu("Seppo", "99999");
        
        verify(pankki).tilisiirto("Seppo", 345, "99999", "33333-44455", 2 * 12);   
    }
}

