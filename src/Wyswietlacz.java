
import javax.swing.JFrame;

public class Wyswietlacz extends JFrame {

    public Wyswietlacz(Game game){
        setSize(1200, 800);
        setLocationRelativeTo(null); // wyswietla okno na srodku ekranu
        setResizable(false); // nie mozna zmienic wielkosci okna
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Gra RPG");
        add(game);

        setVisible(true);
    }





}
