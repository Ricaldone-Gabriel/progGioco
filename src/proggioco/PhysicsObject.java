/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proggioco;

/**
 *
 * @author gigga
 */
public class PhysicsObject extends GameObject implements CollisionHandler {

    double velocityX, velocityY;
    boolean isGrounded;
    double gravity;
    float accelerazione;
    
    PhysicsObject(int positionX, int positionY, int width, int height, double gravity) {
        super(positionX, positionY, width, height);
        velocityX = 0;
        velocityY = 0;
        this.gravity = gravity;
        //player = new ImageIcon("player.png");
    }

    public void gravityUpdate() {
        velocityY += gravity;
    }

    public void positionUpdate() {
        obj.setLocation((int) (obj.x + velocityX), (int) (obj.y + velocityY));
    }

    @Override
    public void checkCollision(Piattaforma platform) {
        if (obj.intersects(platform.obj)) {
            if (obj.y <= platform.obj.y && obj.y + obj.height >= platform.obj.y
                    && obj.x + obj.width > platform.obj.x && obj.x < platform.obj.x + platform.obj.width) {
                obj.y = platform.obj.y - obj.height;
                velocityY = 0;
                isGrounded = true;
            } else if (obj.y <= platform.obj.y + platform.obj.height && obj.y + obj.height >= platform.obj.y + platform.obj.height
                    && obj.x + obj.width >= platform.obj.x && obj.x <= platform.obj.x + platform.obj.width) {
                obj.y = platform.obj.y + platform.obj.height;
                velocityY = 0;
            }//Controlli per l'altezza

            if (obj.x <= platform.obj.x && obj.x + obj.width >= platform.obj.x
                    && obj.y > platform.obj.y && obj.y < platform.obj.y + platform.obj.height) {
                obj.x = platform.obj.x - obj.width;
                velocityX = 0;
            } else if (obj.x <= platform.obj.x + platform.obj.width && obj.x + obj.width >= platform.obj.x + platform.obj.width
                    && obj.y > platform.obj.y && obj.y < platform.obj.y + platform.obj.height) {
                obj.x = platform.obj.x + platform.obj.width;
                velocityX = 0;
            } //Controlli per il muro

            if (((obj.y + obj.height) == platform.obj.y
                    && obj.y < platform.obj.y)
                    && (obj.x + obj.width >= platform.obj.x && obj.x <= platform.obj.x + platform.obj.width)) {
            } else {
                isGrounded = false;
            }
           
            switch (platform.terreno) {
                case "terra":
                    accelerazione = 1f;
                    break;
                case "ghiaccio":
                    accelerazione = 0.25f;
                    break;
                default:
                    accelerazione = 1f;
                    break;
            }
        }
    }

}
