public class Mikstura extends Przedmiot{

    private int mana;
    private int zdrowie;

    public Mikstura(String nazwa, double cena, String opis, int mana, int zdrowie,String sciezka) {
        super(nazwa, cena, opis,sciezka);
        this.mana = mana;
        this.zdrowie = zdrowie;
    }

    public int getMana() {
        return mana;
    }

    public int getZdrowie() {
        return zdrowie;
    }
}
