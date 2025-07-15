import java.util.ArrayList;

public class Gracz {
    private double pieniadze;
    private int atak ;
    private int obrona ;
    private int zdrowie ;
    private int mana ;
    private boolean czyZyje;
    private boolean czyOdwazny ;

    private Bron aktualnaBron =  new Bron("",0,"",0,"");

    private Zbroja aktualnaZbroja = new Zbroja("",0,"",0,"");


    ArrayList<Przedmiot> ekwipunek = new ArrayList<>();
    public Gracz() {
        this.pieniadze = 100;
        this.atak = 15;
        this.obrona = 50;
        this.zdrowie = 100;
        this.mana = 10;
        this.czyZyje = true;
        this.czyOdwazny = false;
    }



    public void wyposaz(Bron bron) {
        if (aktualnaBron == null) {
            aktualnaBron = bron;
            System.out.println("Gracz wyposażony w broń: " + bron.getNazwa());
            dodajAtak(bron.getAtak());
        } else {
            odejmijAtak(aktualnaBron.getAtak());
            aktualnaBron = bron;
            System.out.println("Zmieniono broń na: " + bron.getNazwa());
            dodajAtak(bron.getAtak());
        }
    }

    public void wyposaz(Zbroja zbroja) {
        if (aktualnaZbroja == null) {
            aktualnaZbroja = zbroja;
            System.out.println("Gracz wyposażony w zbroję: " + zbroja.getNazwa());
            dodajObrone(zbroja.getObrona());
        } else {
            odejmijObrone(aktualnaZbroja.getObrona());
            aktualnaZbroja = zbroja;
            System.out.println("Zmieniono zbroję na: " + zbroja.getNazwa());
            dodajObrone(zbroja.getObrona());
        }
    }

    public void wypij(Mikstura mikstura) {
        dodajMane(mikstura.getMana());
        dodajZdrowie(mikstura.getZdrowie());
    }

    public void zdejmij(Przedmiot przedmiot) {
        odejmijObrone(aktualnaZbroja.getObrona());
        aktualnaZbroja = new Zbroja("brak",0,"",0,null);
    }

    public void zdejmij(Bron bron) {
        odejmijAtak(aktualnaBron.getAtak());
        aktualnaBron = new Bron("brak",0,"",0,null);
    }





    public boolean sprawdzPrzedmiot(Przedmiot przedmiot) {
        for (Przedmiot p : ekwipunek) {
            if (p.getNazwa().equals(przedmiot.getNazwa())) {
                return true;
            }
        }
        return false;
    }

    public Bron getAktualnaBron() {
        return aktualnaBron;
    }

    public Zbroja getAktualnaZbroja() {
        return aktualnaZbroja;
    }

    private void dodajAtak(int atak) {
        this.atak += atak;
    }

    private void odejmijAtak(int atak) {
        this.atak -= atak;
    }

    private void dodajObrone(int obrona) {
        this.obrona += obrona;
    }

    private void odejmijObrone(int obrona) {
        this.obrona -= obrona;
    }

    private void dodajZdrowie(int zdrowie) {
        this.zdrowie += zdrowie;
    }

    private void dodajMane(int mana) {
        this.mana += mana;
    }


    public void setObrona(int obrona) {
        this.obrona = obrona;
    }

    public void setZdrowie(int zdrowie) {
        this.zdrowie = zdrowie;
    }

    public double getPieniadze() {
        return pieniadze;
    }
    public void dodajPieniadze(double pieniadze){
        this.pieniadze += pieniadze;
    }

    public void setPieniadze(double pieniadze) {
        this.pieniadze = pieniadze;
    }

    public int getAtak() {
        return atak;
    }


    public int getObrona() {
        return obrona;
    }


    public int getZdrowie() {
        return zdrowie;
    }


    public int getMana() {
        return mana;
    }


    public void setMana(int mana) {
        this.mana = mana;
    }


    public boolean isCzyOdwazny() {
        return czyOdwazny;
    }

    public void setCzyOdwazny(boolean czyOdwazny) {
        this.czyOdwazny = czyOdwazny;
    }

    @Override
    public String toString() {
        return
                "<br>atak: " + atak +
                "<br>obrona: " + obrona +
                "<br>zdrowie: " + zdrowie +
                "<br>mana: " + mana +
              "<br>aktualnaBron: " + aktualnaBron.getNazwa() +
               "<br>aktualnaZbroja: " + aktualnaZbroja.getNazwa();
    }
}
