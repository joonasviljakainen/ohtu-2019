package ohtu.verkkokauppa;

import org.springframework.stereotype.Component;

@Component
public class Viitegeneraattori implements ReferenceGeneratorInterface {
    
    private int seuraava;
    
    public int uusi(){
        return seuraava++;
    }
}
