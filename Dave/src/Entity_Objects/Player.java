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
    public JetPack jetpack;
    Image img1, img2, img3, img4, imgJR, imgJL;
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
        img1 = new Image(I);
        img2 = new Image("/dave_left.png");
        img3 = new Image("/leftruNer.png");
        img4 = new Image("/rightrun.png");
        imgJR = new Image("/jetPackr.png");
        imgJL = new Image("/jetPackl.png");
    }
    
    public void move(ArrayList<String> keyList){  
         if(!bottomCol){    
            speedY = speedY + 0.25;
        }
        if(keyList.contains("RIGHT") && !rightCol){
             if(GuiManager.gameController.getMulti())
                go_right();
            else
                GuiManager.gameController.slideMap(-1.0);
            this.setPlayerDirection("RIGHT");
        }
        else if(keyList.contains("LEFT") && !leftCol){
            this.setPlayerDirection("LEFT");
            if(GuiManager.gameController.getMulti()) 
                go_left();
            else
                GuiManager.gameController.slideMap(1.0);
        }
        else{}
        if(keyList.contains("RIGHT")){
            this.setPlayerDirection("RIGHT");
            runRightAnimation();
            leftCol = false;
        }
        if(keyList.contains("LEFT")){
            this.setPlayerDirection("LEFT");
            runLeftAnimation();
            rightCol = false;
        }
        if(keyList.contains("UP") && !isJumped){
            speedY = -7.0;
            if(jetpack == null)
                isJumped = true;
        }
        if(topCol) {
            speedY = 0;
            topCol = false;
        }
        if(keyList.contains("SPACE")) {
            if (gun != null) {
                if(this.getDirection() == "LEFT")
                    gun.shoot(this.getPosX()-65,this.getPosY() - 10,(this.getDirection()).substring(0,1));
                else
                    gun.shoot(this.getPosX()+65,this.getPosY() - 5,(this.getDirection()).substring(0,1));
            }
        }
        else {
            if (gun != null)
                gun.setIsAvailable(true);
        }
        posY += speedY;
        this.setBottomCol(false);
        this.setRightCol(false);
        this.setLeftCol(false);
        
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
    
    public void runRightAnimation(){
        this.setImage(img4);
        if(System.nanoTime() % 400000000 > 200000000) {
            this.setImage(img4);     
        }
        else {
            this.setImage(img1);
        }
        if(jetpack != null)
            this.setImage(imgJR);
    }
    public void runLeftAnimation(){
        this.setImage(img3);
        if(System.nanoTime() % 400000000 > 200000000) {
            this.setImage(img3);     
        }
        else {
            this.setImage(img2);
        }
        if(jetpack != null)
            this.setImage(imgJL);
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
