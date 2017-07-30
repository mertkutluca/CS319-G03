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
public class DynamicGameObject extends GameObject {
    
    double speedX = 5.0;
    double speedY = 0.0;
    boolean rightCol = false;
    boolean leftCol = false;
    boolean bottomCol = false;
    boolean topCol = false;
    boolean isJumped = false;
    int patternIndex = 0;
    
    public DynamicGameObject(double X, double Y, String I) {
        super(X, Y, I);
    }
    
    public void move(String pattern) {
        long oldTime = System.nanoTime();
        char dir = pattern.charAt(patternIndex % pattern.length());
        if (dir == 'L' && !leftCol) {
            go_left();
            rightCol = false;
        }
        if (dir == 'R' && !rightCol) {
            go_right();
            leftCol = false;
        }
        if (dir == 'J')
            setIsJumped(true);
        
        if(System.nanoTime() % 400000000 > 200000000) {
            patternIndex++;
            if(patternIndex == pattern.length())
                patternIndex = 0;
        }
        
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

    public void setTopCol(boolean topCol) {
        this.topCol = topCol;
    }

    public void setSpeedX(double speedX) {
        this.speedX = speedX;
    }

    public void setSpeedY(double speedY) {
        this.speedY = speedY;
    }

    public void setIsJumped(boolean isJumped) {
        this.isJumped = isJumped;
    }
    
}
