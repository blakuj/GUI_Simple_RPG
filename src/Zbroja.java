public class Zbroja extends Przedmiot{
    private int obrona;

    public Zbroja(String nazwa, double cena, String opis, int obrona,String sciezka) {
        super(nazwa, cena, opis,sciezka);
        this.obrona = obrona;
    }

    public int getObrona() {
        return obrona;
    }
}
