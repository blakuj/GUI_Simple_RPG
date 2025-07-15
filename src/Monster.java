import javax.swing.*;
import java.util.concurrent.TimeUnit;

public class Monster {
    private String nazwa;
    private int atak;
    private int obrona;
    private double nagroda;

    public ImageIcon obrazPotwora;
    private int zdrowie;

    public Monster(String nazwa, int atak, int obrona, double nagroda, int zdrowie,String sciezkaDoObrazka) {
        this.nazwa = nazwa;
        this.atak = atak;
        this.obrona = obrona;
        this.nagroda = nagroda;
        this.zdrowie = zdrowie;
        this.obrazPotwora = new ImageIcon(sciezkaDoObrazka);
    }

    @Override
    public String toString() {
//        StringBuilder sb = new StringBuilder();
//        sb.append("Nazwa ").append(System.getProperty("line.separator")).append(nazwa);
//
//        return sb.toString();

        return
                "<br/><br/>Nazwa: " + nazwa+
                " <br/>Atak: " + atak +
                " <br/>Obrona: " + obrona +
                " <br/>Nagroda: " + nagroda +
                " <br/>Zdrowie: " + zdrowie;
    }


    public int getAtak() {
        return atak;
    }


    public int getObrona() {
        return obrona;
    }

    public void setObrona(int obrona) {
        this.obrona = obrona;
    }

    public double getNagroda() {
        return nagroda;
    }

    public int getZdrowie() {
        return zdrowie;
    }

    public void setZdrowie(int zdrowie) {
        this.zdrowie = zdrowie;
    }

    public String getNazwa() {
        return nazwa;
    }
}
