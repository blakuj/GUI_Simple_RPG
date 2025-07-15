public class Bron extends Przedmiot{
    private int atak;

    public Bron(String nazwa, double cena, String opis, int atak, String sciezka) {
        super(nazwa, cena, opis,sciezka);
        this.atak = atak;
    }

    public int getAtak() {
        return atak;
    }


}
