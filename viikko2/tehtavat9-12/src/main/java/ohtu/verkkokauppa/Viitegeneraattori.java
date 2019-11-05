package ohtu.verkkokauppa;

public class Viitegeneraattori implements ReferenceGeneratorInterface {
    
    private int seuraava;
    
    public int uusi(){
        return seuraava++;
    }
}
