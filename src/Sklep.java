import javax.swing.*;
import java.util.ArrayList;
import java.util.List;




public class Sklep {

    private List<Przedmiot> przedmioty;

    public Sklep() {

    }




    public void dodajPrzedmiot(Przedmiot przedmiot) {
        przedmioty.add(przedmiot);
    }





    public boolean czyPrzedmiotDostepny(Przedmiot przedmiot) {
        return przedmioty.contains(przedmiot);
    }

    public void sprzedajPrzedmiot(Przedmiot przedmiot, Gracz gracz) {
        if (czyPrzedmiotDostepny(przedmiot)) {
            if (!gracz.sprawdzPrzedmiot(przedmiot)) {
                gracz.ekwipunek.add(przedmiot);
                przedmiot.sprzedaj(gracz);
                przedmioty.remove(przedmiot);
                String message = "Kupiono: " + przedmiot.getNazwa();
                JOptionPane.showMessageDialog(null, message, "Informacja", JOptionPane.INFORMATION_MESSAGE);
            } else {
                String message = "Masz juz taki przedmiot w ekwipunku";
                JOptionPane.showMessageDialog(null, message, "Informacja", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            String message = "Przedmiot niedostępny w sklepie";
            JOptionPane.showMessageDialog(null, message, "Informacja", JOptionPane.INFORMATION_MESSAGE);
        }
    }






    public List<Przedmiot> inicjalizacjaAsortymentu() {
        przedmioty = new ArrayList<>();
        Przedmiot kolczuga = new Zbroja("Kolczuga", 70, "+15 obrony, chroni tors",15,"Resources/Itemki/chain-mail.png");
        Przedmiot puklerz = new Zbroja("Puklerz", 150, "+25 obrony, chroni tors",25,"Resources/Itemki/armor-vest.png");
        Przedmiot zelazneButy = new Zbroja("ZelazneButy", 65, "+10 obrony, chroni stopy ", 10,"Resources/Itemki/metal-boot.png");
        Przedmiot tasak = new Bron("Tasak",80,"+15 ataku, krotki zasieg",15,"Resources/Itemki/meat-cleaver.png");
        Przedmiot miecz = new Bron("Miecz", 140, "+25 ataku, dobrze tnie to i owo",25,"Resources/Itemki/miecz.png");
        Przedmiot sztylet = new Bron("Sztylet", 100, "+20 ataku, dobrze wchodzi w podbrzusze",20,"Resources/Itemki/sztylet.png");

        Przedmiot miksturaZOgnistejPokrzywy = new Mikstura("Mikstura z pokrzywy",50,"+15 zdrowia, piecze ale dziala, mikstura z ognistej pokrzywy ", 0,15,"Resources/Itemki/fire-bottle.png");
        Przedmiot miksturaZZiola = new Mikstura("Mikstura z ziola",100,"+35 zdrowia, +15 many, zioło :D KMWTW",15,35,"Resources/Itemki/drink-me.png");

        dodajPrzedmiot(kolczuga);
        dodajPrzedmiot(puklerz);
        dodajPrzedmiot(zelazneButy);
        dodajPrzedmiot(tasak);
        dodajPrzedmiot(miecz);
        dodajPrzedmiot(sztylet);
        dodajPrzedmiot(miksturaZOgnistejPokrzywy);
        dodajPrzedmiot(miksturaZZiola);
        return przedmioty;
    }
    public List<Przedmiot> getPrzedmioty() {
        inicjalizacjaAsortymentu();
        return przedmioty;
    }


}
