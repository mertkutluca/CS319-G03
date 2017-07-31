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
 * @author emintosun
 */
public class Davy extends DynamicGameObject{
    
    public Gun gun;
    Image img1, img2, img3, img4, imgJR, imgJL;
    String dir = "LEFT";
    public int point;
    private int health;
    public Blade blade;
    public JetPack jetpack;
    
    public Davy(double X, double Y, String I) {
        super(X, Y, I);
        img1 = new Image(I);
        img2 = new Image("/dave_right.png");
        img3 = new Image("/leftruNer.png");
        img4 = new Image("/rightrun.png");
        imgJR = new Image("/jetPackr.png");
        imgJL = new Image("/jetPackl.png");
    }
    
    public void move(ArrayList<String> keyList){  
        if(!bottomCol){    
            speedY = speedY + 0.25;
        }
        if(keyList.contains("D") && !rightCol){
            go_right();
            this.setDavyDirection("RIGHT");
        }
        else if(keyList.contains("A") && !leftCol){
            go_left();
            this.setDavyDirection("LEFT");
            rightCol = false;
        }
        else{}
        if(keyList.contains("D")){
            this.setDavyDirection("RIGHT");
            runRightAnimation();
            leftCol = false;
        }
        if(keyList.contains("A")){
            this.setDavyDirection("LEFT");
            runLeftAnimation();
            rightCol = false;
        }
        if(keyList.contains("W") && !isJumped){
            speedY = -7.0;
            if(jetpack == null)
                isJumped = true;
        }
        if(topCol) {
            speedY = 0;
            topCol = false;
        }
        if(keyList.contains("SHIFT")) {
            if (gun != null) {
                if(this.getDirection() == "LEFT")
                    gun.shoot(this.getPosX()-65,this.getPosY() - 5,(this.getDirection()).substring(0,1));
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
    public void setDavyDirection(String dir){
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
            this.setImage(img2);
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
            this.setImage(img1);
        }
        if(jetpack != null)
            this.setImage(imgJL);
    }
    
    public String getDirection(){
        return (this.dir);
    }
}