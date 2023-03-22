/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proggioco;

/**
 *
 * @author gabriel.ricaldone
 */
public class Piattaforma extends GameObject {
    String terreno;

    Piattaforma(String terreno, int positionX, int positionY, int width, int height) {
        super(positionX,positionY,width,height);
        this.terreno = terreno;
    }
    
    Piattaforma(int positionX, int positionY, int width, int height) {
        super(positionX,positionY,width,height);
        terreno = "terra";
    }
    
}
