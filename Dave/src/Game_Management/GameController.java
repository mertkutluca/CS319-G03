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
import File_Management.FileManager;
import java.util.ArrayList;

/**
 *
 * @author Sebahattin
 */
public class GameController {
    public ArrayList<GameObject> gameObjectList;
    public ArrayList<String> pressedButtonsList;
    private static GameController gameController;
    private char[][] objectKeys;
    FileManager filemanager;
    
    public GameController(){
        gameObjectList = new ArrayList<>();
        pressedButtonsList = new ArrayList<>();
        filemanager=new FileManager(6,50);
        objectKeys=new char[6][50];
        filemanager.readMapFile("src/MapObjects1.txt");
        objectKeys=filemanager.getMapObjects();
    }
    
    public void fillList(){
        GameObject object;
    	for(int i=0;i<6;i++){
    		for(int j=0;j<50;j++){
    			object=createObject((char)objectKeys[i][j],i,j);
    			if(object!=null)
    				gameObjectList.add(object);
    		}
    	}
    }
    
    public GameObject createObject(char key, int y, int x){
    	if(key=='0'){
    		return null;
    	}else if(key=='1'){
        	Brick brick = new Brick(x*64, y*64, "/brick.png");
        	return brick;
    	}else if(key=='2'){
    		PoisonedBrick pBrick= new PoisonedBrick(x*64,y*64,"/brick.png");
    		return pBrick;
    	}else if(key=='3'){
    		PoisonedSeaweed seaweed=new PoisonedSeaweed(x*64,y*64,"/brick.png");
    		return seaweed;
    	}else if(key=='4'){
    		Door door=new Door(x*64,y*64,"/brick.png");
    		return door;
    	}else if(key==5){
    		return null;
    	}else if(key==6){
    		return null;
    	}else if(key==7){
    		return null;
    	}else if(key==8){
    		return null;
    	}else if(key==9){
    		return null;
    	}else if(key=='a'){
    		return null;
    	}else if(key=='b'){
    		 Gun gun = new Gun(x*64, y*64, "/gun.png");
    		 return gun;
    	}else if(key=='c'){
    		return null;
    	}else if(key=='d'){
    		 Player player = new Player(x*64, y*64, "/dave_right.png");
    		 return player;
    	}else if(key=='e'){
    		return null;
    	}else if(key=='f'){
    		return null;
    	}else if(key=='g'){
    		return null;
    	}else if(key=='h'){
    		return null;
    	}else{
    		return null;
    	}
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
