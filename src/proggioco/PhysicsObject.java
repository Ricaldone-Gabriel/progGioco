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
        boolean topLeft = false, topRight = false, bottomLeft = false, bottomRight = false;
        if (obj.intersects(platform.obj)) {

            if ((obj.x >= platform.obj.x && obj.x <= platform.obj.x + platform.obj.width)
                    && (obj.y >= platform.obj.y && obj.y <= platform.obj.y + platform.obj.height)) {
                topLeft = true;
            }
            if ((obj.x + obj.width >= platform.obj.x && obj.x + obj.width <= platform.obj.x + platform.obj.width)
                    && (obj.y >= platform.obj.y && obj.y <= platform.obj.y + platform.obj.height)) {
                topRight = true;
            }
            if ((obj.x >= platform.obj.x && obj.x <= platform.obj.x + platform.obj.width)
                    && (obj.y + obj.height >= platform.obj.y && obj.y + obj.height <= platform.obj.y + platform.obj.height)) {
                bottomLeft = true;
            }
            if ((obj.x + obj.width >= platform.obj.x && obj.x + obj.width <= platform.obj.x + platform.obj.width)
                    && (obj.y + obj.height >= platform.obj.y && obj.y + obj.height <= platform.obj.y + platform.obj.height)) {
                bottomRight = true;
            }

            if (topLeft && topRight && bottomLeft && bottomRight) {
                obj.y = platform.obj.y + obj.height;
                velocityY = 0;
                velocityX = 0;
                isGrounded = true;
            } else if (topLeft && topRight) {
                obj.y = platform.obj.y + platform.obj.height;
                velocityY = 0;
            } else if (bottomLeft && bottomRight) {
                obj.y = platform.obj.y - obj.height;
                isGrounded = true;
                velocityY = 0;
            } else if (topLeft && bottomLeft) {
                obj.x = platform.obj.x + platform.obj.width;
                velocityX = 0;
            } else if (topRight && bottomRight) {
                obj.x = platform.obj.x - obj.width;
                velocityX = 0;
            } else if (topLeft) {
                if (velocityX < 0) {
                    obj.x = platform.obj.x;
                }
                if (velocityY < 0) {
                    obj.y = platform.obj.y + platform.obj.height;
                    velocityY = 0;
                }
                if (velocityX > 0 && velocityY < 0) {
                    obj.y = platform.obj.y + platform.obj.height;
                    velocityY = 0;
                }
            } else if (topRight) {
                if (velocityX > 0) {
                    obj.x = platform.obj.x - obj.width;
                }
                if (velocityY < 0) {
                    obj.y = platform.obj.y + platform.obj.height;
                    velocityY = 0;
                }
                if (velocityX < 0 && velocityY < 0) {
                    obj.y = platform.obj.y + platform.obj.height;
                    velocityY = 0;
                }
            } else if (bottomLeft) {
                if (isGrounded) {
                    obj.y = platform.obj.y - obj.height;
                    velocityY = 0;
                } else {
                    System.out.println(velocityX + " " + velocityY);
                    if (velocityX < 0) {
                        obj.x = platform.obj.x + platform.obj.width;
                        velocityX = 0;
                    }
                    if (velocityY > 0) {
                        obj.y = platform.obj.y - obj.height;
                        velocityY = 0;
                    }
                }
            } else if (bottomRight) {
                if (isGrounded) {
                    obj.y = platform.obj.y - obj.height;
                    velocityY = 0;
                } else {
                    if (!(velocityX == 0 && velocityY == 0)) {
                        if (velocityX > 0) {
                            obj.x = platform.obj.x - obj.width;
                        }
                        if (velocityY > 0) {
                            obj.y = platform.obj.y - obj.height;
                            velocityY = 0;
                        }
                    }

                }

            }

            /*if (obj.y <= platform.obj.y && obj.y + obj.height >= platform.obj.y
                    && obj.x + obj.width > platform.obj.x && obj.x < platform.obj.x + platform.obj.width) {
                obj.y = platform.obj.y - obj.height;
                velocityY = 0;
                isGrounded = true;
            } else if (obj.y <= platform.obj.y + platform.obj.height && obj.y + obj.height >= platform.obj.y + platform.obj.height
                    && obj.x + obj.width >= platform.obj.x && obj.x <= platform.obj.x + platform.obj.width) {
                obj.y = platform.obj.y + platform.obj.height;
                velocityY = 0;
            }//Controlli per l'altezzdwadwaa

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
            }*/
            switch (platform.terreno) {
                case "terra":
                    accelerazione = 1f;
                    break;
                case "ghiaccio":
                    accelerazione = 0.10f;
                    break;
                default:
                    accelerazione = 1f;
                    break;
            }
        }
    }

}
