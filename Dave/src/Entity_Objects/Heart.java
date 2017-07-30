/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity_Objects;

/**
 *
 * @author emintosun
 */
public class Heart extends ObtainableStaticGameObject{
    private int health;
    public Heart(double X, double Y, String I) {
        super(X, Y, I);
        this.health = 1;
    }
    public void setHealth(int a){
        this.health = a;
    }
    public int getHealth(){
        return this.health;
    }
}
