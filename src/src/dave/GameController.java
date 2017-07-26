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
public class GameController {
    public ArrayList<GameObject> gameObjectList;
    public ArrayList<String> pressedButtonList;
    
    public GameController(){
        gameObjectList = new ArrayList<>();
    }
    
    public void fillList(){
        DynamicGameObject player = new DynamicGameObject(10.0, 346.0, "/davepic.png");
        gameObjectList.add(player);
        StaticGameObject brick1 = new StaticGameObject(138.0, 410.0, "/brick.png");
        gameObjectList.add(brick1);
        StaticGameObject brick2 = new StaticGameObject(10.0, 410.0, "/brick.png");
        gameObjectList.add(brick2);
        StaticGameObject brick3 = new StaticGameObject(202.0, 410.0, "/brick.png");
        gameObjectList.add(brick3);
        StaticGameObject brick4 = new StaticGameObject(330.0, 346.0, "/brick.png");
        gameObjectList.add(brick4);
        StaticGameObject brick5 = new StaticGameObject(266.0, 346.0, "/brick.png");
        gameObjectList.add(brick5);
        StaticGameObject brick6 = new StaticGameObject(394.0, 410.0, "/brick.png");
        gameObjectList.add(brick6);
        StaticGameObject brick7 = new StaticGameObject(458.0, 410.0, "/brick.png");
        gameObjectList.add(brick7);
        StaticGameObject brick8 = new StaticGameObject(522.0, 410.0, "/brick.png");
        gameObjectList.add(brick8);
        StaticGameObject brick9 = new StaticGameObject(330.0, 282.0, "/brick.png");
        gameObjectList.add(brick9);
    }
    
    public void moveAllObjects() {
        for (int i = 0; i < gameObjectList.size(); i++) {
            if (gameObjectList.get(i) instanceof DynamicGameObject) {
                ((DynamicGameObject)gameObjectList.get(i)).move(pressedButtonList);
            }
        }
    }
    
    public void handleCollisions() {
        for (int i = 0; i < gameObjectList.size(); i++) {
            for (int j = 0; j < gameObjectList.size(); j++) {
                if (gameObjectList.get(i) instanceof DynamicGameObject && gameObjectList.get(j) instanceof StaticGameObject) {
                    checkCollision((DynamicGameObject)gameObjectList.get(i),(StaticGameObject)gameObjectList.get(j));
                }
            }
        }
    }
    public void checkCollision(DynamicGameObject obj1, StaticGameObject obj2){
        if(obj1.getPosY() - obj2.getPosY() < 63 && -63 < obj1.getPosY() - obj2.getPosY()){
            if(obj1.getPosX() - obj2.getPosX() < 64 && obj1.getPosX() - obj2.getPosX() > 0)
                obj1.setLeftCol(true);
            if(obj2.getPosX() - obj1.getPosX() < 64 && obj2.getPosX() - obj1.getPosX() > 0)
                obj1.setRightCol(true);
        }
        
        if(obj1.getPosX() - obj2.getPosX() < 50 && -50 < obj1.getPosX() - obj2.getPosX()){
            if(obj1.getPosY() - obj2.getPosY() < 10 && obj1.getPosY() - obj2.getPosY() > 0){}
                //obj1.setLeftCol(true);
            if(obj2.getPosY() - obj1.getPosY() < 68 && obj2.getPosY() - obj1.getPosY() > 0){
                obj1.setBottomCol(true);
                obj1.setSpeedY(0.0);
                obj1.setIsJumped(false);
            }
        }
    }
    
}
