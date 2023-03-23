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
        this.setBackground(new Color(230, 230, 255));
        this.addKeyListener(keyH);
        this.setFocusable(true);
        this.FPS = FPS;

        Mondo.add(new Piattaforma("terra", 100, 400, 250, 250));
        Mondo.add(new Piattaforma("ghiaccio", 350, 500, 750, 200));
        Mondo.add(new Piattaforma(350, 50, 250, 250));
        Mondo.add(new Piattaforma("terra", 0, 750, 3000, 500));;

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

        if (keyH.up) {
            if (papa.isGrounded) {
                papa.velocityY = -20;
                papa.isGrounded = false;
            }
        }
        if (keyH.left) {
            if (papa.velocityX > -4) {
                papa.velocityX += -2;
            } else {
                papa.velocityX = -4;
            }
        }
        if (keyH.down) {
            //papa.velocityY = 10;
            //Commentato perchè per adesso non serve a nulla.
        }
        if (keyH.right) {
            if (papa.velocityX < 4) {
                papa.velocityX += 2;
            } else {
                papa.velocityX = 4;
            }
        }
        if (keyH.shift) {
            if (keyH.left) {
                if (papa.velocityX > -6) {
                    papa.velocityX += -2;
                } else {
                    papa.velocityX = -6;
                }
            }
            if (keyH.right) {
                if (papa.velocityX < 6) {
                    papa.velocityX += 2;
                } else {
                    papa.velocityX = 6;
                }
            }
        }
        papa.positionUpdate();
        for (int i = 0; i < Mondo.size(); i++) {
            papa.checkCollision(Mondo.get(i));
        }
        papa.gravityUpdate();

        if (papa.velocityX > 0.5) {
            papa.velocityX -= papa.accelerazione;
        } else if (papa.velocityX < -0.5) {
            papa.velocityX += papa.accelerazione;
        } else {
            papa.velocityX = 0;
        }

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        //g.drawImage(new Image("background.jpg"), WIDTH, WIDTH, this);
        g.setColor(Color.black);
        g.fillRect(papa.obj.x, papa.obj.y, papa.obj.width, papa.obj.height);

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
            g.fillRect((int) Mondo.get(i).obj.x, (int) Mondo.get(i).obj.y, (int) Mondo.get(i).obj.width, (int) Mondo.get(i).obj.height);
        }
        g.dispose();
    }

}
