package proggioco;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable {

    Personaggio papa;
    Thread gameThread;
    KeyHandler keyH = new KeyHandler();

    boolean onGround = false;
    int FPS;
    int numerone;
    int gravità;
    double accelerazione;
    LinkedList<Piattaforma> Mondo;

    GamePanel(int FPS) {
        papa = new Personaggio(100, 100, 0, 0, 50, 50);
        Mondo = new LinkedList<Piattaforma>();
        this.setPreferredSize(Toolkit.getDefaultToolkit().getScreenSize());
        this.setBackground(new Color(232, 244, 248));
        this.addKeyListener(keyH);
        this.setFocusable(true);
        this.FPS = FPS;
        gravità = 1;


        Mondo.add(new Piattaforma("terra", 100f, 400f, 250f, 250f));
        Mondo.add(new Piattaforma("ghiaccio", 350f, 500f, 750f, 200f));
        Mondo.add(new Piattaforma(350f, 50f, 250f, 250f));
        Mondo.add(new Piattaforma("terra", 0f, 750f, 3000f, 500f));

        if (FPS == 30) {
            numerone = 500000000;
        } else if (FPS == 60) {
            numerone = 1000000000;
        } else if (FPS == 120) {
            numerone = 2000000000;
        }
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void run() {

        double drawInterval = numerone / FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;

        while (gameThread != null) {
            update();

            repaint();

            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime /= 1000000;

                if (remainingTime < 0) {
                    remainingTime = 0;
                }

                Thread.sleep((long) remainingTime);

                nextDrawTime += drawInterval;
            } catch (InterruptedException ex) {
                Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void update() {

        if (onGround) {
            papa.velocityY = 0;
        } else {
            papa.velocityY += gravità;
        }

        /*Pezzo di codice sopra molto momentaneo
        Idea per migliorarlo: Controllare se c'è una piattaforma sotto la X del papa
         */
        //Tutte le velocità adesso sono accelerazioni e decelerazioni.
        if (keyH.up) {
            if (onGround) {
                papa.velocityY = -20;
                onGround = false;
            }
        }
        if (keyH.left) {
            if (papa.velocityX > -6) {
                papa.velocityX -= 2;
            }
        }
        if (keyH.down) {
            //papa.velocityY = 10;
            //Commentato perchè per adesso non serve a nulla.

        }
        if (keyH.right) {
            if (papa.velocityX < 6) {
                papa.velocityX += 2;
            }
        }

        if (keyH.shift) {
            if (keyH.right) {
                if (papa.velocityX < 8) {
                    papa.velocityX += 2;
                }
            }
            if (keyH.left) {
                if (papa.velocityX > -8) {
                    papa.velocityX -= 2;
                }
            }
        }

        System.out.println(papa.velocityX + " " + accelerazione);

        if (papa.velocityX > 0.5) {
            papa.velocityX -= accelerazione;
        } else if (papa.velocityX < -0.5) {
            papa.velocityX += accelerazione;
        } else {
            papa.velocityX = 0;
        }

        papa.positionX = papa.positionX + papa.velocityX;
        papa.positionY = papa.positionY + papa.velocityY;

        checkGround();

        //Da provare: velocità in float
    }

    public void checkGround() {
        boolean trov = false;
        int i;
        //Loop che controlla la posizione delle piattaforme e ti incula su se ci vai dentro
        for (i = 0; i < Mondo.size() && !trov; i++) {

            if (papa.positionY <= Mondo.get(i).positionY && papa.positionY + papa.height >= Mondo.get(i).positionY
                    && papa.positionX + papa.width > Mondo.get(i).positionX && papa.positionX < Mondo.get(i).positionX + Mondo.get(i).sizeX) {
                papa.positionY = Mondo.get(i).positionY - papa.height;
                papa.velocityY = 0;
            } else if (papa.positionY <= Mondo.get(i).positionY + Mondo.get(i).sizeY && papa.positionY + papa.height >= Mondo.get(i).positionY + Mondo.get(i).sizeY
                    && papa.positionX + papa.width >= Mondo.get(i).positionX && papa.positionX <= Mondo.get(i).positionX + Mondo.get(i).sizeX) {
                papa.positionY = Mondo.get(i).positionY + Mondo.get(i).sizeY;
                papa.velocityY = 0;
            }//Controlli per l'altezza

            if (papa.positionX <= Mondo.get(i).positionX && papa.positionX + papa.width >= Mondo.get(i).positionX
                    && papa.positionY > Mondo.get(i).positionY && papa.positionY < Mondo.get(i).positionY + Mondo.get(i).sizeY) {
                papa.positionX = Mondo.get(i).positionX - papa.width;
                papa.velocityX = 0;
            } else if (papa.positionX <= Mondo.get(i).positionX + Mondo.get(i).sizeX && papa.positionX + papa.width >= Mondo.get(i).positionX + Mondo.get(i).sizeX
                    && papa.positionY > Mondo.get(i).positionY && papa.positionY < Mondo.get(i).positionY + Mondo.get(i).sizeY) {
                papa.positionX = Mondo.get(i).positionX + Mondo.get(i).sizeX;
                papa.velocityX = 0;
            } //Controlli per il muro

            if (((papa.positionY + papa.height) == Mondo.get(i).positionY
                    && papa.positionY < Mondo.get(i).positionY)
                    && (papa.positionX + papa.width >= Mondo.get(i).positionX && papa.positionX <= Mondo.get(i).positionX + Mondo.get(i).sizeX)) {
                onGround = true;
                trov = true;
            } else {
                onGround = false;
            }//Controllo per il pavimento e con trov per stopparlo

            /*if (papa.positionY + papa.height >= Mondo.get(i).positionY
                    && papa.positionY + papa.height <= Mondo.get(i).positionY + Mondo.get(i).sizeY
                    && papa.positionX + papa.width >= Mondo.get(i).positionX
                    && papa.positionX <= Mondo.get(i).positionX + Mondo.get(i).sizeX ) {
                papa.positionY = Mondo.get(i).positionY - papa.height;
                onGround = true;
                trov = true;
            } else {
                onGround = false;
            }*/
        }
        if (trov) {
            switch (Mondo.get(i - 1).terreno) {
                case "terra":
                    accelerazione = 1;
                    break;
                case "ghiaccio":
                    accelerazione = 0.1;
                    break;
                default:
                    accelerazione = 0.25;
                    break;
            }
        } else {
            accelerazione = 0.25;
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        //g.drawImage(new Image("background.jpg"), WIDTH, WIDTH, this);
        g.setColor(Color.black);
        g.fillRect((int) papa.positionX, (int) papa.positionY, 50, 50);

        for (int i = 0; i < Mondo.size(); i++) {
            switch (Mondo.get(i).terreno) {
                case "terra":
                    g.setColor(new Color(205, 133, 63));
                    break;
                case "ghiaccio":
                    g.setColor(new Color(173, 216, 230));
                    break;
                default:
                    g.setColor(Color.red);
                    break;
            }
            g.fillRect((int) Mondo.get(i).positionX, (int) Mondo.get(i).positionY, (int) Mondo.get(i).sizeX, (int) Mondo.get(i).sizeY);
        }
        g.dispose();
    }

}
