/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity_Objects;

import javafx.scene.image.Image;

/**
 *
 * @author Sebahattin
 */
public class Boss extends Enemy {
    
    private Gun gun;
    private Image swingIm;
    
    public Boss(double X, double Y, String I) {
        super(X, Y, I);
        swingIm = new Image("/enemywithbladeatt.png");
        gun = new Gun(X,Y,"/gun.png");
    }
    
    public Gun getGun() {
        return gun;
    }
    
    public void bossShoot() {
        gun.shoot(this.posX - 65, this.posY, "L");
    }
    
    public void swing() {
        this.setImage(swingIm);
    }
    
}
