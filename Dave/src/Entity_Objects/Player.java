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
public class Player extends DynamicGameObject {
    
    public Gun gun;
    
    public Player(double X, double Y, String I) {
        super(X, Y, I);
    }
    
    public void move(ArrayList<String> keyList){        
        if(!bottomCol){    
            speedY = speedY + 0.25;
        }
        if(keyList.contains("RIGHT") && !rightCol){
            //go_right();
            GuiManager.gameController.slideMap(-1.0);
        }
        else if(keyList.contains("LEFT") && !leftCol){
            //go_left();
            GuiManager.gameController.slideMap(1.0);
            //rightCol = false;
        }
        else{}
        if(keyList.contains("RIGHT")){
            leftCol = false;
        }
        if(keyList.contains("LEFT")){
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
                gun.shoot();
            }
        }
        posY += speedY;
        this.setBottomCol(false);
        this.setRightCol(false);
        this.setLeftCol(false);
        
        handle_bounds();
    }
}
