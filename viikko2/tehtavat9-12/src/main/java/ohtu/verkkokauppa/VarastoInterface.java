package ohtu.verkkokauppa;

public interface VarastoInterface {
    public static VarastoInterface getInstance() {
        return Varasto.getInstance();
    };
    public Tuote haeTuote(int id);
    public int saldo(int id);
    public void otaVarastosta(Tuote t);
    public void palautaVarastoon(Tuote t);

}