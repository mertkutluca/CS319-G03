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
public class Bullet extends DynamicGameObject {
    
    private final int damage = 10;
    public String dir;
    
    public Bullet(double X, double Y, String I) {
        super(X, Y, I);
    }

    public int getDamage() {
        return damage;
    }
    
    public void setDirection(String dir){
        this.dir = dir;
    }
    
    public String getDirection(){
        return this.dir;
    }
}
