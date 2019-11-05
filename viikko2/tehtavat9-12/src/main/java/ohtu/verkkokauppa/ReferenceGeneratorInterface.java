package ohtu.verkkokauppa;

public interface ReferenceGeneratorInterface {
    public static ReferenceGeneratorInterface getInstance() {
        return Viitegeneraattori.getInstance();
    };
    public int uusi();
}