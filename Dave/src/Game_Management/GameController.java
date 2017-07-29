/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game_Management;

import Entity_Objects.Bullet;
import Entity_Objects.DynamicGameObject;
import Entity_Objects.GameObject;
import Entity_Objects.Gun;
import Entity_Objects.ObtainableStaticGameObject;
import Entity_Objects.Player;
import Entity_Objects.StaticGameObject;
import java.util.ArrayList;

/**
 *
 * @author Sebahattin
 */
public class GameController {
    public ArrayList<GameObject> gameObjectList;
    public ArrayList<String> pressedButtonsList;
    private static GameController gameController;
    
    public GameController(){
        gameObjectList = new ArrayList<>();
        pressedButtonsList = new ArrayList<>();
    }
    
    public void fillList(){
        Player player = new Player(200.0, 346.0, "/dave_right.png");
        gameObjectList.add(player);
        Gun gun = new Gun(778.0, 346.0, "/gun.png");
        gameObjectList.add(gun);
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
        StaticGameObject brick10 = new StaticGameObject(586.0, 410.0, "/brick.png");
        gameObjectList.add(brick10);
        StaticGameObject brick11 = new StaticGameObject(650.0, 410.0, "/brick.png");
        gameObjectList.add(brick11);
        StaticGameObject brick12 = new StaticGameObject(714.0, 410.0, "/brick.png");
        gameObjectList.add(brick12);
        StaticGameObject brick13 = new StaticGameObject(778.0, 410.0, "/brick.png");
        gameObjectList.add(brick13);
        StaticGameObject brick14 = new StaticGameObject(842.0, 410.0, "/brick.png");
        gameObjectList.add(brick14);
        StaticGameObject brick15 = new StaticGameObject(906.0, 410.0, "/brick.png");
        gameObjectList.add(brick15);
        StaticGameObject brick16 = new StaticGameObject(650.0, 218.0, "/brick.png");
        gameObjectList.add(brick16);
    }
    
    public void moveAllObjects() {
        int p1 = 0;
        for (int i = 0; i < gameObjectList.size(); i++) {
            if (gameObjectList.get(i) instanceof Player) {
                ((Player)gameObjectList.get(i)).move(pressedButtonsList);
                p1 = i;
            }
            if (gameObjectList.get(i) instanceof Bullet) {
                ((Bullet)gameObjectList.get(i)).move(((Bullet)gameObjectList.get(i)).getDirection());
            }
        }
    }
    
    public void slideMap(double direction) {
        for (int i = 0; i < gameObjectList.size(); i++) {
            if (!(gameObjectList.get(i) instanceof Player)) {
                if(gameObjectList.get(i) instanceof Bullet){
                    ((DynamicGameObject)gameObjectList.get(i)).posX += (5.0 * direction);
                } else {
                    ((StaticGameObject)gameObjectList.get(i)).posX += (5.0 * direction);
                }
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
            if(obj1.getPosX() - obj2.getPosX() < 64 && obj1.getPosX() - obj2.getPosX() > 0) {
                if (obj2 instanceof ObtainableStaticGameObject) {
                    ((Player)obj1 ).gun = (Gun)obj2;
                    gameObjectList.remove(obj2);
                } else if (obj1 instanceof Bullet){
                    gameObjectList.remove(obj1);
                } else{
                    obj1.setLeftCol(true);
                }
            }
            if(obj2.getPosX() - obj1.getPosX() < 64 && obj2.getPosX() - obj1.getPosX() > 0) {
                if (obj2 instanceof ObtainableStaticGameObject) {
                    ((Player)obj1 ).gun = (Gun)obj2;
                    gameObjectList.remove(obj2);
                } else if (obj1 instanceof Bullet){
                    gameObjectList.remove(obj1);
                } else {
                    obj1.setRightCol(true);
                }
            }
        }
        
        if(obj1.getPosX() - obj2.getPosX() < 50 && -50 < obj1.getPosX() - obj2.getPosX()){
            if(obj1.getPosY() - obj2.getPosY() < 64 && obj1.getPosY() - obj2.getPosY() > 0){
                //obj1.setLeftCol(true);
                obj1.setTopCol(true);
                obj1.posY = obj2.posY + 64;
            }               
            if(obj2.getPosY() - obj1.getPosY() < 68 && obj2.getPosY() - obj1.getPosY() > 0){
                obj1.setBottomCol(true);
                obj1.setSpeedY(0.0);
                obj1.setIsJumped(false);
                obj1.posY = obj2.posY - 64;
            }
        }
    }   
}
