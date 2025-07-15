import java.awt.*;
import javax.swing.*;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Objects;



public class Game extends JPanel implements Runnable {


    int MonsterImageX =900;
    int MonsterImageY =430;

    ImageIcon wygranaBackground =  new ImageIcon(Objects.requireNonNull(getClass().getResource("/Background/trophy.png")));

    ImageIcon przegranaBackground =  new ImageIcon(Objects.requireNonNull(getClass().getResource("/Background/game over.png")));
    ImageIcon arenaBackground =  new ImageIcon(Objects.requireNonNull(getClass().getResource("/Background/arena.png")));

    ImageIcon shopBackground = new ImageIcon(Objects.requireNonNull(getClass().getResource("/Background/trollshop.jpg")));
    ImageIcon menuBackGround = new ImageIcon(Objects.requireNonNull(getClass().getResource("/Background/cloud_scene_preview.jpg")));

    ImageIcon graBackground = new ImageIcon(Objects.requireNonNull(getClass().getResource("/Background/country-platform-preview.jpg")));


    ImageIcon inventoryBackground = new ImageIcon(Objects.requireNonNull(getClass().getResource("/Background/22.png")));
    ArrayList<Monster> monsters = new ArrayList<>();
    GridBagLayout grid = new GridBagLayout();
    int szerokosc =125;
    int wysokosc = 125;

    Dimension przysiskSize = new Dimension(szerokosc,wysokosc);

    Gracz gracz = new Gracz();
    Sklep sklep = new Sklep();

    StanGry stanGry = StanGry.MenuGlowne;

    Thread gameThread;

    public Game() {
        setPreferredSize(new Dimension(1200, 800));
        setFocusable(true);
        setLayout(grid);
        wybierzRysowanke();
        setUpMonsters();
    }

    private void wybierzRysowanke() {
        removeAll();
        setBorder(null);
        switch (stanGry) {
            case Gra -> rysujGre();
            case MenuGlowne -> rysujMenu();
            case GameOver -> rysujGameOver();
            case Sklep -> rysujSklep();
            case Walka -> rysujWalke();
            case Ekwipunek -> rysujEkwipunek();
            case Wygrana -> rysujWygrana();

        }

        revalidate();
        repaint();
    }

    private void rysujWygrana(){

    }
    private void rysujGameOver() {

    }
    private void rysujEkwipunek() {
        removeAll();

        JButton powrot = new JButton("Powrót");

        powrot.setBackground(Color.red);
        powrot.setForeground(Color.WHITE);
        powrot.setBounds(490,600,200,100);

        setLayout(null);
        add(powrot);





        JPanel AllPrzedmiotPanel = new JPanel(new FlowLayout());
        AllPrzedmiotPanel.setBackground(new Color(0,0,0,0));
        AllPrzedmiotPanel.setBounds(90,50,1000,500);

        for (Przedmiot przedmiot : gracz.ekwipunek) {

            JPanel przedmiotPanel = new JPanel(new BorderLayout());


            JButton przedmiotButton = new JButton();
            przedmiotButton.setPreferredSize(przysiskSize);
            przedmiotButton.setBackground(new Color(0,0,0));
            przedmiotButton.setIcon(przedmiot.image);




            przedmiotPanel.add(przedmiotButton, BorderLayout.CENTER);

            JButton usunButton = new JButton("Usuń");
            usunButton.setBackground(Color.RED);
            usunButton.setPreferredSize(new Dimension(125, 30));
            przedmiotPanel.add(usunButton, BorderLayout.SOUTH);



            przedmiotButton.addActionListener(e -> {
                String message;
                if (przedmiot.getClass() == Bron.class) {
                    gracz.wyposaz((Bron) przedmiot);
                    message ="Gracz wyposażony w bron: " + przedmiot.getNazwa();
                    JOptionPane.showMessageDialog(null, message, "Informacja", JOptionPane.INFORMATION_MESSAGE);


                } else if (przedmiot.getClass() == Zbroja.class) {
                    gracz.wyposaz((Zbroja) przedmiot);
                    message ="Gracz wyposażony w zbroja: " + przedmiot.getNazwa();
                    JOptionPane.showMessageDialog(null, message, "Informacja", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    gracz.wypij((Mikstura) przedmiot);
                    message ="Wypito: " + przedmiot.getNazwa();
                    gracz.ekwipunek.remove(przedmiot);
                    JOptionPane.showMessageDialog(null, message, "Informacja", JOptionPane.INFORMATION_MESSAGE);
                    wybierzRysowanke();
                }
            });



            usunButton.addActionListener(e -> {
                gracz.ekwipunek.remove(przedmiot);
                if (przedmiot.getClass()==Bron.class){
                    gracz.zdejmij((Bron)przedmiot);
                }
                if (przedmiot.getClass()==Zbroja.class){
                    gracz.zdejmij((Zbroja) przedmiot);
                }
                String message = "Usunieto przedmiot: "+przedmiot.getNazwa();
                JOptionPane.showMessageDialog(null, message, "Informacja", JOptionPane.INFORMATION_MESSAGE);
                wybierzRysowanke();
            });



            add(przedmiotPanel);
            AllPrzedmiotPanel.add(przedmiotPanel);
        }


        add(AllPrzedmiotPanel);





        powrot.addActionListener(e -> {
            stanGry = StanGry.Gra;
            wybierzRysowanke();
        });
    }
    int counterTur = 0;
    private void rysujWalke(){
        removeAll();


        setLayout(null);
        JButton ucieczka = new JButton("WIEJ!",new ImageIcon("Resources/Buttons/run.png"));
        ucieczka.setBounds(520,120,150,100);
        ucieczka.setBackground(Color.white);
        add(ucieczka);

        JLabel opisGracz = new JLabel();
        opisGracz.setText("<html>Aktualne statystyki: " + gracz+"</html>");
        opisGracz.setForeground(new Color(237,237,237));
        opisGracz.setHorizontalAlignment(SwingConstants.LEFT);
        opisGracz.setVerticalAlignment(SwingConstants.TOP);
        opisGracz.setFont(getFont().deriveFont(Font.BOLD, 16));


        JPanel infoGracz = new JPanel();
        infoGracz.setBackground(new Color(0,0,0,80));
        infoGracz.setBounds(30,170,300,200);

        infoGracz.add(opisGracz);
        add(infoGracz);




        JLabel opisMonster = new JLabel();
        opisMonster.setText("<html>Aktualne statystyki: " + monsters.get(0) +"</html>");
        opisMonster.setForeground(new Color(237,237,237));
        opisMonster.setHorizontalAlignment(SwingConstants.LEFT);
        opisMonster.setVerticalAlignment(SwingConstants.TOP);
        opisMonster.setFont(getFont().deriveFont(Font.BOLD, 16));


        JPanel infoMonster = new JPanel();
        infoMonster.setBackground(new Color(0,0,0,80));
        infoMonster.setBounds(870,170,300,200);

        infoMonster.add(opisMonster);
        add(infoMonster);



        JButton tura = new JButton("Tura: "+counterTur,new ImageIcon("Resources/Buttons/empty-hourglass.png"));
        tura.setBounds(520,20,150,100);
        tura.setBackground(Color.white);
        add(tura);

        JButton atakMele = new JButton("Atakuj",new ImageIcon("Resources/Buttons/boot-kick.png"));
        atakMele.setBounds(30,370,150,70);
        atakMele.setBackground(new Color(255,255,255));
        add(atakMele);

        JButton atakMagiczny = new JButton("Czar",new ImageIcon("Resources/Buttons/riposte.png"));
        atakMagiczny.setBounds(190,370,150,70);
        atakMagiczny.setBackground(new Color(255,255,255));
        add(atakMagiczny);

        atakMele.addActionListener(e -> {
            counterTur++;
            tura.setText("Tura: " + counterTur);

            atakGracza();
            atakPotwora();
            wybierzRysowanke();
        });

        atakMagiczny.addActionListener(e -> {
            int kosztTmp = (int)(Math.random()*7+1);
            if (gracz.getMana()>kosztTmp) {


                gracz.setMana(gracz.getMana()-kosztTmp);

            }else{
                String message = "Masz za malo many tym razem i zaatakował cie potwor";
                JOptionPane.showMessageDialog(null, message, "Informacja", JOptionPane.INFORMATION_MESSAGE);
                atakPotwora();




            }
            counterTur++;
            tura.setText("Tura: " + counterTur);
            atakGracza();

            wybierzRysowanke();
        });

        ucieczka.addActionListener(e -> {
            if (gracz.getAktualnaZbroja().getNazwa().equals("Kolczuga") && gracz.getAktualnaBron().getNazwa().equals("Miecz")) {
                gracz.setCzyOdwazny(true);
            }

            double escapeChance = 0.5;

            if (gracz.isCzyOdwazny()) {
                escapeChance += 0.1;
            }

            if (Math.random() < escapeChance) {

                String message = "Udało Ci się uciec!";
                JOptionPane.showMessageDialog(null, message, "Informacja", JOptionPane.INFORMATION_MESSAGE);
                stanGry = StanGry.Gra;


            } else {

                String message = "Nie udało Ci się uciec!";
                JOptionPane.showMessageDialog(null, message, "Informacja", JOptionPane.INFORMATION_MESSAGE);

                atakPotwora();
                counterTur++;
                int manaCost = (int)(Math.random()*5);
                gracz.setMana(gracz.getMana() - manaCost);



            }
            wybierzRysowanke();

        });





    }


    private void atakGracza(){
        if (monsters.get(0).getObrona()>0){

            monsters.get(0).setObrona((int) (monsters.get(0).getObrona()-(gracz.getAtak()*Math.random()*2+0.5)));
            if (monsters.get(0).getObrona()<0){
                monsters.get(0).setObrona(0);
            }
        }else{
            monsters.get(0).setZdrowie(monsters.get(0).getZdrowie()-gracz.getAtak());
        }

        if (monsters.get(0).getZdrowie()<=0){
            gracz.setZdrowie(100);
            gracz.setObrona(10);
            gracz.dodajPieniadze(monsters.get(0).getNagroda());

            Timer timer = new Timer(10,new ActionListener(){
                int deltaX = 1;

                @Override
                public void actionPerformed(ActionEvent e) {

                    MonsterImageX = 900 + deltaX;
                    if (MonsterImageX > arenaBackground.getIconWidth()+300){
                        ((Timer) e.getSource()).stop();

                        String message = "Pokonano: "+ monsters.get(0).getNazwa();
                        JOptionPane.showMessageDialog(null, message, "Informacja", JOptionPane.INFORMATION_MESSAGE);
                        monsters.remove(0);

                        if (monsters.isEmpty()){
                            stanGry = StanGry.Wygrana;
                            wybierzRysowanke();
                        }


                        stanGry = StanGry.Gra;
                        wybierzRysowanke();

                        counterTur = 0;
                        MonsterImageX = 900;
                        MonsterImageY = 430;

                    }else {
                        deltaX++;
                    }
                }
            });
            timer.start();
        }
    }
    private void atakPotwora(){
        if (monsters.isEmpty()){
            stanGry = StanGry.Wygrana;
            wybierzRysowanke();
        }else {
            if (gracz.getObrona() > 0) {
                gracz.setObrona((int) (gracz.getObrona() - (monsters.get(0).getAtak() * Math.random() * 2 + 0.5)));
            } else
                gracz.setZdrowie(gracz.getZdrowie() - monsters.get(0).getAtak());

            if (gracz.getZdrowie() <= 0) {
                stanGry = StanGry.GameOver;


            }
        }
    }



    private void setUpMonsters(){

        monsters.add(new Monster("Weszamot",8,5,100,50,"Resources/Postaci/Weszamot.png"));
        monsters.add(new Monster("Dr.Pershing",15,10,200,200,"Resources/Postaci/egyptian-walk.png"));
        monsters.add(new Monster("Czapi",25,20,400,400,"Resources/Postaci/police-car.png"));

    }


    private void rysujMenu() {

        setLayout(new GridLayout(2,1,100,10));
        setBackground(new Color(105, 185, 209));
        setBorder(BorderFactory.createLineBorder(new Color(0,0,0,0),300,false));
        JButton nowaGra = new JButton("Nowa gra");
        JButton wyjscieZGry = new JButton("Wyjscie z gry");
        nowaGra.setBackground(new Color(252, 255, 254));
        wyjscieZGry.setBackground(new Color(252, 255, 254));
        nowaGra.setForeground(new Color(0, 0, 0)); // kolor tekstu
        wyjscieZGry.setForeground(new Color(0, 0, 0));
        add(nowaGra);
        add(wyjscieZGry);

        wyjscieZGry.setPreferredSize(new Dimension(300, 100));
        nowaGra.setPreferredSize(new Dimension(300, 100));

        wyjscieZGry.addActionListener(e -> System.exit(0));

        nowaGra.addActionListener(e -> {
            stanGry = StanGry.Gra;
            wybierzRysowanke();
        });
    }
    private void rysujSklep() {
        setLayout(new FlowLayout(FlowLayout.CENTER, 100, 80)); // Ustawienie FlowLayout z odstępem między przyciskami

        JPanel hajsy = new JPanel();
        hajsy.setBackground(new Color(255,255,255));

        JLabel moneyLabel = new JLabel("<html> PIENIĄDZE: <br/>" + gracz.getPieniadze()+" $ </html>");
        moneyLabel.setPreferredSize(przysiskSize);
        moneyLabel.setBorder(BorderFactory.createLineBorder(new Color(68, 146, 25),5,true)); ///ramka
        moneyLabel.setForeground(new Color(68, 146, 25)); // kolor tekstu
        moneyLabel.setHorizontalTextPosition(JLabel.CENTER);
        moneyLabel.setHorizontalAlignment(0);//wyjustowanie
        moneyLabel.setFont(getFont().deriveFont(Font.BOLD, 18));//rozmiar tekstu

        hajsy.add(moneyLabel);
        add(hajsy);

        String opis = "Opis: ";
        JLabel informacje =  new JLabel(opis);


        for (Przedmiot przedmiot : sklep.getPrzedmioty()) {
            JButton itemButton = new JButton();
            itemButton.setPreferredSize(przysiskSize);
            itemButton.setBackground(new Color(0,0,0));
            itemButton.setIcon(przedmiot.image);

            add(itemButton);

            itemButton.addActionListener(e -> {
                if (gracz.getPieniadze() >= przedmiot.getCena()) {
                    sklep.sprzedajPrzedmiot(przedmiot, gracz);
                    moneyLabel.setText("<html> PIENIĄDZE: <br/>" + gracz.getPieniadze()+" $ </html>");
                } else {
                    String message = "Brak środków na koncie";
                    JOptionPane.showMessageDialog(null, message, "Informacja", JOptionPane.INFORMATION_MESSAGE);
                }
            });

            itemButton.addMouseListener(new MouseAdapter() {
                public void mouseEntered(MouseEvent e) {
                    informacje.setText("<html>Opis: <br/>"+przedmiot + "</html>"); // Aktualizacja etykiety informacje
                }
            });
        }


        informacje.setPreferredSize(new Dimension(440,200));

        informacje.setForeground(new Color(237,237,237)); // kolor tekstuinformacje
        informacje.setHorizontalAlignment(SwingConstants.LEFT);
        informacje.setVerticalAlignment(SwingConstants.TOP);
        informacje.setFont(getFont().deriveFont(Font.BOLD, 16));//rozmiar tekstu
        add(informacje);

        JPanel infor =  new JPanel();
        infor.add(informacje);
        infor.setBackground(Color.GRAY);
        add(infor);


        JButton wrocDoGry =  new JButton("Wroc do gry");
        wrocDoGry.setPreferredSize(przysiskSize);
        wrocDoGry.setBackground(new Color(219, 18, 18));
        wrocDoGry.setForeground(new Color(255, 255, 255));
        add(wrocDoGry);

        wrocDoGry.addActionListener(e -> {
            stanGry = StanGry.Gra;
            wybierzRysowanke();
        });
        repaint();
    }



    private void rysujGre() {
        removeAll();
        setLayout(new FlowLayout());


        JButton walczButton = new JButton("Walcz");
        walczButton.setPreferredSize(new Dimension(150, 80));
        walczButton.setBackground(Color.white);
        add(walczButton);

        JButton sklepButton = new JButton("Wejdź do sklepu");
        sklepButton.setPreferredSize(new Dimension(150, 80));
        sklepButton.setBackground(Color.white);
        add(sklepButton);

        JButton ekwipunek =  new JButton("Ewkwipunek");
        ekwipunek.setPreferredSize(new Dimension(150, 80));
        ekwipunek.setBackground(Color.white);
        add(ekwipunek);

        ekwipunek.addActionListener(e -> {
                    stanGry = StanGry.Ekwipunek;
                    wybierzRysowanke();
                }
        );



        JPanel infoPrzec = new JPanel();
        JLabel opisPrzeciwnika =  new JLabel();

        opisPrzeciwnika.setText("Opis nastepnego przeciwnika: ");
        opisPrzeciwnika.setPreferredSize(new Dimension(600,200));
        opisPrzeciwnika.setForeground(new Color(237,237,237));
        opisPrzeciwnika.setHorizontalAlignment(SwingConstants.LEFT);
        opisPrzeciwnika.setVerticalAlignment(SwingConstants.TOP);
        opisPrzeciwnika.setFont(getFont().deriveFont(Font.BOLD, 16));//rozmiar tekstu

        add(opisPrzeciwnika);

        infoPrzec.add(opisPrzeciwnika);
        infoPrzec.setBackground(new Color(0,0,0,80));

        add(infoPrzec);

        walczButton.addActionListener(e -> {
            stanGry = StanGry.Walka;
            wybierzRysowanke();
        });

        walczButton.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                opisPrzeciwnika.setText("<html>Opis nastepnego przeciwnika: " + monsters.get(0)+"</html>"); // Aktualizacja etykiety informacje
            }


        });
        sklepButton.addActionListener(e -> {
            stanGry = StanGry.Sklep;
            wybierzRysowanke();
        });
    }

    @Override
    public void run() {
        double drawInterval = 1000000000.0 / 60;
        double delta = 0;
        long lastTime = System.nanoTime();

        while (gameThread != null) {
            long currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;

            if (delta >= 1) {
                repaint();
                delta--;
            }
        }
    }



    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D g2 = (Graphics2D) graphics;



        if (stanGry == StanGry.Sklep) {

            int width = getWidth();
            int height = getHeight();


            g2.drawImage(shopBackground.getImage(), 0, 0, width, height, null);
        }
        if (stanGry == StanGry.MenuGlowne){
            int width = getWidth();
            int height = getHeight();

            g2.drawImage(menuBackGround.getImage(), 0, 0, width, height, null);
        }


        if (stanGry == StanGry.Gra){
            int width = getWidth();
            int height = getHeight();
            if (monsters.isEmpty()){
                stanGry = StanGry.Wygrana;
                wybierzRysowanke();
            }else {
                g2.drawImage(graBackground.getImage(), 0, 0, width, height, null);
                g2.drawImage(new ImageIcon("Resources/Postaci/wojownikOrtalionu.png").getImage(), 200, 430, 256, 256, null);
                g2.drawImage(monsters.get(0).obrazPotwora.getImage(), 900, 430, 256, 256, null);
            }
        }

        if (stanGry == StanGry.Ekwipunek){
            int width = getWidth();
            int height = getHeight();

            g2.drawImage(inventoryBackground.getImage(), 0, 0, width, height, null);
        }


        if (stanGry == StanGry.Walka) {
            int width = getWidth();
            int height = getHeight();


            g2.drawImage(arenaBackground.getImage(), 0, 0, width, height, null);
            g2.drawImage(new ImageIcon("Resources/Postaci/wojownikOrtalionu.png").getImage(), 70, 430, 256, 256, null);
            g2.drawImage(monsters.get(0).obrazPotwora.getImage(), MonsterImageX, MonsterImageY, 256, 256, null);

        }








        if (stanGry == StanGry.GameOver){
            int width = getWidth();
            int height = getHeight();

            g2.drawImage(przegranaBackground.getImage(), 0, 0, width, height, null);
        }

        if (stanGry == StanGry.Wygrana){
            int width = getWidth();
            int height = getHeight();
            g2.drawImage(menuBackGround.getImage(), 0, 0, width, height, null);
            g2.drawImage(wygranaBackground.getImage(), 0, 0, width, height, null);
        }


    }



    public void startGame() {
        setupGame();
        startGameThread();
    }

    protected void setupGame() {
        stanGry = StanGry.MenuGlowne;
    }

    private void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }



}
