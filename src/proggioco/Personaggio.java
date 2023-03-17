package proggioco;

import javax.swing.JLabel;

public class Personaggio extends JLabel {

    double positionX, positionY;
    double velocityX, velocityY;
    double width,height;
    //ImageIcon player;

    Personaggio(int positionX, int positionY, double velocityX, double velocityY,int width, int height) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.velocityX = velocityX;
        this.velocityY = velocityY;
        this.width = width;
        this.height = height;
        //player = new ImageIcon("player.png");
    }
}
