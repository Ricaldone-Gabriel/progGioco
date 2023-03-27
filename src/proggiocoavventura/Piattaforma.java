package proggiocoavventura;

import java.awt.FlowLayout;
import javax.swing.JPanel;

public class Piattaforma extends JPanel {

    String terreno;
    int posX, posY;
    int size;

    Piattaforma(String terreno, int posX, int posY) {
        this.terreno = terreno;
        this.posX = posX;
        this.posY = posY;
        size = 50;
    }

}
