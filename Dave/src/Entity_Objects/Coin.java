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
public class Coin extends ObtainableStaticGameObject{
    public int point;
    public Coin(double X, double Y, String I) {
        super(X, Y, I);
        this.point = 100;
    }
    public void setPoint(int point){
        this.point = point;
    }
    public int getPoint(){
        return this.point;
    }
}
