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
public class Piattaforma {
    String terreno;
    double positionX,positionY;
    double sizeX, sizeY;

    public Piattaforma(String terreno, double positionX, double positionY, double sizeX, double sizeY) {
        this.terreno = terreno;
        this.positionX = positionX;
        this.positionY = positionY;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
    }
    
    Piattaforma(double positionX, double positionY, double sizeX, double sizeY) {
        terreno = "terra";
        this.positionX = positionX;
        this.positionY = positionY;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
    }
    
}
