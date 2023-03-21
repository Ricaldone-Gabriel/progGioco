package proggioco;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import javax.swing.*;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class ProgGioco extends JFrame {

    JFrame frame;
    JButton play, exit;
    GamePanel panel;

    ProgGioco() {
        panel = new GamePanel(60);
        play = new JButton("GIOCA");
        exit = new JButton("ESCI");
        frame = new JFrame("TITOLO");

        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setBackground(Color.black);

        /*
        Container c = frame.getContentPane(); //Gets the content layer
        JLabel label = new JLabel(); //JLabel Creation
        label.setIcon(new ImageIcon("background.jpg")); //Sets the image to be displayed as an icon
        Dimension size = label.getPreferredSize(); //Gets the size of the image
        label.setBounds(0, 0, frame.getWidth(), frame.getHeight()); //Sets the location of the image

        c.add(label);

        play.setBounds(100, 100, 250, 100);
        play.setContentAreaFilled(false);
        play.setBorderPainted(false);
        play.setFocusPainted(false);
        //font utilizzabili
        //Engravers MT
        play.setFont(new Font("Engravers MT", Font.PLAIN, 50));
        play.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                play.setForeground(Color.WHITE);
            }

            public void mouseExited(MouseEvent evt) {
                play.setForeground(null);
            }
        });
        play.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent E) {

            }
        });

        label.add(play);

        exit.setBounds(100, 300, 250, 100);
        exit.setContentAreaFilled(false);
        exit.setBorderPainted(false);
        exit.setFocusPainted(false);
        //font utilizzabili
        //Engravers MT
        exit.setFont(new Font("Engravers MT", Font.PLAIN, 50));
        exit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                exit.setForeground(Color.WHITE);
            }

            public void mouseExited(MouseEvent evt) {
                exit.setForeground(null);
            }
        });
        exit.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent E) {
                System.exit(0);
            }
        });

        label.add(exit);*/

        frame.setVisible(true);

        frame.add(panel);
        panel.startGameThread();
    }

    public static void main(String[] args) {
        new ProgGioco();
    }
}
