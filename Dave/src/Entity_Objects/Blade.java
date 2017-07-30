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
public class Blade extends ObtainableStaticGameObject{ 
    private int damage;
    private boolean swinged;
    public Blade(double X, double Y, String I) {
        super(X, Y, I);
        this.damage = 10;
        swinged = false;
    }
    public void setDamage(int damage){
        this.damage = damage;
    }
    public int getDamage(){
        return this.damage;
    }
    public void swing(boolean swing){
        this.swinged = swing;
    }
    public boolean getSwinged(){
        return this.swinged;
    }
}
