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
        for (int i = 0; i < 50; i++)
            bullets.add(new Bullet(X,Y,"/bullet.png"));
    }
    
    public void shoot(double posX, double posY,String dir) {
        if (!bullets.isEmpty()) {
            bullets.get(bullets.size()-1).setDirection(dir);
            (bullets.get(bullets.size()-1)).setPosX(posX);
            (bullets.get(bullets.size()-1)).setPosY(posY);
            GuiManager.gameController.gameObjectList.add(bullets.get(bullets.size()-1));
            bullets.remove(bullets.size()-1);
            
        }
    }
    
}
