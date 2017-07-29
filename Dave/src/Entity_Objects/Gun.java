/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity_Objects;

import User_Interface.GuiManager;
import java.util.ArrayList;

/**
 *
 * @author Sebahattin
 */
public class Gun extends ObtainableStaticGameObject {
    
    ArrayList<Bullet> bullets;
    String bulletImagePath;
    
    public Gun(double X, double Y, String I) {
        super(X, Y, I);
        bullets = new ArrayList<>();
        this.bulletImagePath = "/bullet.png";
        for (int i = 0; i < 5; i++)
            bullets.add(new Bullet(X,Y,"/bullet.png"));
    }
    
    public void shoot() {
       // if (!bullets.isEmpty()) {
            GuiManager.gameController.gameObjectList.add(bullets.get(0));
            //bullets.remove(bullets.size()-1);
       // }
    }
    
}
