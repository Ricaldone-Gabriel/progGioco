package proggiocoavventura;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class GamePanel extends JPanel implements Runnable {

    Personaggio papa;
    Thread gameThread;
    KeyHandler keyH;
    int mondoSizeX, mondoSizeY;
    Piattaforma[][] mondo;

    JButton stat;
    JPanel statPanel;
    JLabel livello = new JLabel();

    GamePanel() {
        mondoSizeX = 26;
        mondoSizeY = 14;
        papa = new Personaggio(1, 1);
        keyH = new KeyHandler();
        mondo = new Piattaforma[mondoSizeX][mondoSizeY];
        stat = new JButton("Statistiche");

        creaStatPanel();

        stat.setBounds(100, 700, 400, 50);
        stat.setFocusable(false);
        stat.setFont(new Font("Gill Sans MT", Font.BOLD, 20));
        stat.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent E) {
                setStatPanel();
                if (statPanel.isVisible()) {
                    statPanel.setVisible(false);
                } else {
                    statPanel.setVisible(true);
                }
            }
        });
        this.add(stat);
        this.add(statPanel);

        this.setLayout(null);
        this.addKeyListener(keyH);
        this.setFocusable(true);

        startGameThread();
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void run() {
        while (gameThread != null) {
            update();

            repaint();
        }
    }

    public void update() {

        if (keyH.up) {
            if (papa.posY - 1 >= 0) {
                papa.posY -= 1;
            } else {
                System.out.println("non posso salire perchè no :c");
            }
            keyH.up = false;
        }
        if (keyH.right) {
            if (papa.posX + 1 < mondoSizeX) {
                papa.posX += 1;
            } else {
                System.out.println("non posso andare a destra perchè no :c");
            }
            keyH.right = false;
        }
        if (keyH.down) {
            if (papa.posY + 1 < mondoSizeY) {
                papa.posY += 1;
            } else {
                System.out.println("non posso scendere perchè no :c");
            }
            keyH.down = false;
        }
        if (keyH.left) {
            if (papa.posX - 1 >= 0) {
                papa.posX -= 1;
            } else {
                System.out.println("non posso andare a sinistra perchè no :c");
            }
            keyH.left = false;
        }

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (int i = 0; i < mondoSizeX; i++) {
            for (int j = 0; j < mondoSizeY; j++) {
                mondo[i][j] = new Piattaforma("terra", i, j);
                switch (mondo[i][j].terreno) {
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
                g.fillRect(mondo[i][j].posX * mondo[i][j].size, mondo[i][j].posY * mondo[i][j].size, mondo[i][j].size, mondo[i][j].size);
                g.setColor(Color.black);
                g.drawRect(mondo[i][j].posX * mondo[i][j].size, mondo[i][j].posY * mondo[i][j].size, mondo[i][j].size, mondo[i][j].size);
            }
        }

        g.fillRect((papa.posX * 50) + 5, (papa.posY * 50) + 5, papa.size, papa.size);
    }

    public void creaStatPanel() {
        statPanel = new JPanel();
        statPanel.setBounds(0, 0, 400, 400);
        statPanel.setVisible(false);

        livello.setText("Livello: " + papa.livello);
        livello.setFont(new Font("Gill Sans MT", Font.BOLD, 20));
        livello.setBounds(0, 0, 400, 50);
        livello.setBackground(Color.yellow);
        livello.setOpaque(true);
        statPanel.add(livello);
    }

    public void setStatPanel() {
        livello.setText("Livello: " + papa.livello);
    }

}
