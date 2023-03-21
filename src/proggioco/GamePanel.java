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
    LinkedList<Piattaforma> Mondo;

    GamePanel(int FPS) {
        papa = new Personaggio(100, 100, 50, 50, 1);
        Mondo = new LinkedList<Piattaforma>();
        this.setPreferredSize(Toolkit.getDefaultToolkit().getScreenSize());
        this.setBackground(Color.black);
        this.addKeyListener(keyH);
        this.setFocusable(true);
        this.FPS = FPS;

        Mondo.add(new Piattaforma("terra", 100, 400, 250, 250));

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
        
        
        


        /*Pezzo di codice sopra molto momentaneo
        Idea per migliorarlo: Controllare se c'è una piattaforma sotto la X del papa
         */
        //Tutte le velocità adesso sono accelerazioni e decelerazioni.
        if (keyH.up) {
            if (papa.isGrounded) {
                papa.velocityY = -20;
                papa.isGrounded = false;
            }
        }
        if (keyH.left) {
            papa.velocityX = -3;
        }
        if (keyH.down) {
            //papa.velocityY = 10;
            //Commentato perchè per adesso non serve a nulla.
        }
        if (keyH.right) {
            papa.velocityX = 3;
        }

        papa.positionUpdate();
        papa.checkCollision(Mondo.get(0));
        papa.gravityUpdate();
        
        if (papa.velocityX > 0) {
            papa.velocityX -= 1;
        } else if (papa.velocityX < 0) {
            papa.velocityX += 1;
        }
        // QUA COSO CHE FA FUNZIONARE MODIFICALO COME VUOI

        //Da provare: velocità in float
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        //g.drawImage(new Image("background.jpg"), WIDTH, WIDTH, this);
        g.setColor(Color.white);
        g.fillRect(papa.obj.x, papa.obj.y, papa.obj.width, papa.obj.height);

        g.setColor(Color.yellow);
        for (int i = 0; i < Mondo.size(); i++) {
            g.fillRect((int) Mondo.get(i).obj.x, (int) Mondo.get(i).obj.y, (int) Mondo.get(i).obj.width, (int) Mondo.get(i).obj.height);
        }
        g.dispose();
    }

}
