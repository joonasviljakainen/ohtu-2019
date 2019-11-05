package ohtu.verkkokauppa;

import java.util.List;

public interface BookKeeping {
    public void lisaaTapahtuma(String tapahtuma);
    public List<String> getTapahtumat();
}