package proggioco;

import javax.swing.JLabel;

public class Personaggio extends PhysicsObject {
    
    Personaggio(int positionX, int positionY,int width, int height,double gravity) {
        super(positionX, positionY, width, height,gravity);
    }
}
