/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity_Objects;

/**
 *
 * @author Sebahattin
 */
public class EnemywithGun extends Enemy {
    
    private Gun gun;
    
    public EnemywithGun(double X, double Y, String I) {
        super(X, Y, I);
        gun = new Gun(X,Y,"/gun.png");
    }
    
    public Gun getGun() {
        return gun;
    }
    
    public void enemyShoot() {
            gun.shoot(this.posX - 65, this.posY, "L");
    }
    
}
