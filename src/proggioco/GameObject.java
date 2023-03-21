/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proggioco;

import java.awt.Rectangle;

/**
 *
 * @author gigga
 */
public class GameObject {
    Rectangle obj;
    
    GameObject(int positionX, int positionY, int width, int height) {
        obj = new Rectangle(positionX,positionY,width,height);
    }
}
