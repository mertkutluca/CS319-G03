/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity_Objects;

import User_Interface.GuiManager;
import java.util.ArrayList;
import javafx.animation.Animation;
import javafx.scene.image.Image;

/**
 *
 * @author Sebahattin
 */
public class Player extends DynamicGameObject {
    public Gun gun;
    Image img1, img2, img3, img4;
    Image images[];
    String dir = "RIGHT";

    public Player(double X, double Y, String I) {
        super(X, Y, I);
        img1 = new Image(I);
        img2 = new Image("/dave_left.png");
        img3 = new Image("/leftruNer.png");
        img4 = new Image("/rightrun.png");
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
    public void runRightAnimation(){
        this.setImage(img4);
        if(System.nanoTime() % 400000000 > 200000000) {
            this.setImage(img4);     
        }
        else {
            this.setImage(img1);
        }
    }
    public void runLeftAnimation(){
        this.setImage(img3);
        if(System.nanoTime() % 400000000 > 200000000) {
            this.setImage(img3);     
        }
        else {
            this.setImage(img2);
        }
    }
}
