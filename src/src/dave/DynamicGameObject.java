/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dave;

import java.util.ArrayList;

/**
 *
 * @author Sebahattin
 */
public class DynamicGameObject extends GameObject {
    
    double speedX = 5.0;
    double speedY = 0.0;
    boolean rightCol = false;
    boolean leftCol = false;
    boolean bottomCol = false;
    boolean isJumped = false;
    
    public DynamicGameObject(double X, double Y, String I) {
        super(X, Y, I);
    }
    
    public void move(ArrayList<String> keyList){        
        if(!bottomCol){    
            speedY = speedY + 0.25;
        }
        if(keyList.contains("RIGHT") && !rightCol){
            go_right();
            
        }
        else if(keyList.contains("LEFT") && !leftCol){
            go_left();
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
        posY += speedY;
        this.setBottomCol(false);
        this.setRightCol(false);
        this.setLeftCol(false);
        
        handle_bounds();
    }
    public void handle_bounds(){
        if(posX > 640 )
            posX = 0;
        if(posX < 0)
            posX = 640;
        if(posY > 500)
            posY = 0;
    }
    public void go_right(){
        posX += speedX;
    }
    public void go_left(){
        posX -= speedX;
    }

    public void setRightCol(boolean rightCol) {
        this.rightCol = rightCol;
    }

    public void setLeftCol(boolean leftCol) {
        this.leftCol = leftCol;
    }

    public void setBottomCol(boolean bottomCol) {
        this.bottomCol = bottomCol;
    }

    public void setSpeedY(double speedY) {
        this.speedY = speedY;
    }

    public void setIsJumped(boolean isJumped) {
        this.isJumped = isJumped;
    }
    
}
