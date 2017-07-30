/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity_Objects;

import User_Interface.GuiManager;
import java.util.ArrayList;
import javafx.scene.image.Image;

/**
 *
 * @author Sebahattin
 */
public class Player extends DynamicGameObject {
    
    public Gun gun;
    Image img1,img2;
    String dir = "RIGHT";
    public int point;
    private int health;
    public Blade blade;
    public Player(double X, double Y, String I) {
        super(X, Y, I);
        this.img1 = new Image(I);
        this.img2 = new Image("/dave_left.png");
        this.point = 0;
        this.health = 3;
    }
    
    public void move(ArrayList<String> keyList){  
        if(!bottomCol){    
            speedY = speedY + 0.25;
        }
        if(keyList.contains("RIGHT") && !rightCol){
            //go_right();
            GuiManager.gameController.slideMap(-1.0);
            this.setPlayerDirection("RIGHT");
        }
        else if(keyList.contains("LEFT") && !leftCol){
            //go_left();
            this.setPlayerDirection("LEFT");
            GuiManager.gameController.slideMap(1.0);
            //rightCol = false;
        }
        else{}
        if(keyList.contains("RIGHT")){
            this.setPlayerDirection("RIGHT");
            leftCol = false;
        }
        if(keyList.contains("LEFT")){
            this.setPlayerDirection("LEFT");
            rightCol = false;
        }
        if(keyList.contains("UP") && !isJumped){
            speedY = -7.0;
            isJumped = true;
        }
        if(topCol) {
            speedY = 0;
            topCol = false;
        }
        if(keyList.contains("SPACE")) {
            if (gun != null) {
                if(this.getDirection() == "LEFT")
                    gun.shoot(this.getPosX()-45,this.getPosY(),(this.getDirection()).substring(0,1));
                else
                    gun.shoot(this.getPosX()+45,this.getPosY(),(this.getDirection()).substring(0,1));
                keyList.remove(0);
            }
        }
        posY += speedY;
        this.setBottomCol(false);
        this.setRightCol(false);
        this.setLeftCol(false);
        
        handle_bounds();
    }

    /**
     *
     * @param dir
     */
    public void setPlayerDirection(String dir){
        if (dir == "LEFT"){
            this.dir = "LEFT";
            this.setImage(img2);
        } else {
            this.dir = "RIGHT";
            this.setImage(img1);
        }
    }
    public String getDirection(){
        return (this.dir);
    }
    public void increasePoint(int point){
        this.point += point;
    }
    public int getPoint(){
        return this.point;
    }
    public void increaseHealth(){
        this.health++;
    }
    public void decreaseHealth(){
        this.health--;
    }
    public int getHealth(){
        return this.health;
    }
}
