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
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "pulla", 12));

        Kauppa k = new Kauppa(varasto, pankki, viite);              
        k.aloitaAsiointi();
        k.lisaaKoriin(1);     
        k.lisaaKoriin(1);     

        k.tilimaksu("Seppo", "99999");
        
        verify(pankki).tilisiirto("Seppo", 345, "99999", "33333-44455", 2 * 12);   
    }

    @Test
    public void kunLisataanTuoteJotaEiOleEiLaskuteta() {
        when(viite.uusi()).thenReturn(223);
        when(varasto.saldo(1)).thenReturn(10); 
        when(varasto.saldo(2)).thenReturn(0); 
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 6));
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "pulla", 12));

        Kauppa k = new Kauppa(varasto, pankki, viite);              
        k.aloitaAsiointi();
        k.lisaaKoriin(1);     
        k.lisaaKoriin(2);     

        k.tilimaksu("Vilma", "0145567");
        
        verify(pankki).tilisiirto("Vilma", 223, "0145567", "33333-44455", 6);   
    }

    @Test
    public void aloitaAsiointiNollaaOstoksenTiedot() {
        when(viite.uusi()).thenReturn(667);
        when(varasto.saldo(1)).thenReturn(10); 
        when(varasto.saldo(2)).thenReturn(10); 
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 6));
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "pulla", 5));

        Kauppa k = new Kauppa(varasto, pankki, viite);              
        k.aloitaAsiointi();
        k.lisaaKoriin(1);  
        k.aloitaAsiointi();   
        k.lisaaKoriin(2);     

        k.tilimaksu("Selma", "09871234");
        
        verify(pankki).tilisiirto("Selma", 667, "09871234", "33333-44455", 5);   
    }

    @Test
    public void kysyyAinauudenViitenumeron() {
        when(viite.uusi()).thenReturn(667);
        when(varasto.saldo(1)).thenReturn(10); 
        when(varasto.saldo(2)).thenReturn(10); 
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 6));
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "pulla", 5));

        Kauppa k = new Kauppa(varasto, pankki, viite);              
        k.aloitaAsiointi();
        k.lisaaKoriin(1);  
        k.tilimaksu("Valma", "0000000");
        verify(pankki).tilisiirto("Valma", 667, "0000000", "33333-44455", 6);   

        when(viite.uusi()).thenReturn(888);
        k.aloitaAsiointi();   
        k.lisaaKoriin(2);     
        k.tilimaksu("Kulma", "55555");
        verify(pankki).tilisiirto("Kulma", 888, "55555", "33333-44455", 5);   

        verify(viite, times(2)).uusi();
    }

    @Test
    public void poistetaanKorista() {
        when(viite.uusi()).thenReturn(125);
        when(varasto.saldo(1)).thenReturn(10); 
        when(varasto.saldo(2)).thenReturn(10); 
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 6));
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "pulla", 5));

        Kauppa k = new Kauppa(varasto, pankki, viite);              
        k.aloitaAsiointi();
        k.lisaaKoriin(1);     
        k.lisaaKoriin(2);  
        k.poistaKorista(1);   
        k.tilimaksu("Kalmo", "99755");
        verify(pankki).tilisiirto("Kalmo", 125, "99755", "33333-44455", 5);   

    }
}