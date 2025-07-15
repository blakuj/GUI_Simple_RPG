import javax.swing.*;

public class Przedmiot {
    private String nazwa;
    private double cena;
    private String opis;

    public ImageIcon image;

    public Przedmiot(String nazwa, double cena, String opis, String sciezkaDoObrazka) {
        this.nazwa = nazwa;
        this.cena = cena;
        this.opis = opis;
        this.image = new ImageIcon(sciezkaDoObrazka);
    }


    public String getNazwa() {
        return nazwa;
    }

    public double getCena() {
        return cena;
    }

    public String getOpis() {
        return this.opis;
    }
    public void sprzedaj(Gracz gracz) {
        gracz.setPieniadze(gracz.getPieniadze() - this.getCena());
    }



    @Override
    public String toString() {
        return
                "<br>Nazwa: " + nazwa +
                "<br>cena: " + cena +
                "<br>opis:" + opis ;
    }
}
