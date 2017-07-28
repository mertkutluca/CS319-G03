/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dave;

import javafx.scene.image.Image;

/**
 *
 * @author Sebahattin
 */
public class GameObject {
    protected double posX;
    protected double posY;
    protected Image image;
    
    GameObject(double X, double Y, String I){
        posX = X;
        posY = Y;
        image = new Image(I);
    }

    public double getPosX() {
        return posX;
    }

    public void setPosX(double posX) {
        this.posX = posX;
    }

    public double getPosY() {
        return posY;
    }

    public void setPosY(double posY) {
        this.posY = posY;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
    

}
