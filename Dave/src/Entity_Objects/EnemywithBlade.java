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
public class EnemywithBlade extends Enemy {
    
    Image swingIm;
    
    public EnemywithBlade(double X, double Y, String I) {
        super(X, Y, I);
        swingIm = new Image("/enemywithbladeatt.png");
    }
    
    public void swing() {
        this.setImage(swingIm);
    }
    
}
