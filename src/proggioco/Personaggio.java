package proggioco;

import javax.swing.JLabel;

public class Personaggio extends PhysicsObject {

    float decelerazione;

    Personaggio(int positionX, int positionY, int width, int height, double gravity) {
        super(positionX, positionY, width, height, gravity);
        decelerazione = 1;
    }

}
