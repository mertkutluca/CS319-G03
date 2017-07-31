/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game_Management;

import Entity_Objects.Blade;
import Entity_Objects.Brick;
import Entity_Objects.Bullet;
import Entity_Objects.Chalice;
import Entity_Objects.Coin;
import Entity_Objects.Diamond;
import Entity_Objects.Door;
import Entity_Objects.DynamicGameObject;
import Entity_Objects.Enemy;
import Entity_Objects.EnemywithBlade;
import Entity_Objects.GameObject;
import Entity_Objects.Gun;
import Entity_Objects.Heart;
import Entity_Objects.ObtainableStaticGameObject;
import Entity_Objects.Player;
import Entity_Objects.PoisonedBrick;
import Entity_Objects.PoisonedSeaweed;
import Entity_Objects.StaticGameObject;
import File_Management.FileManager;
import User_Interface.GuiManager;
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
    public GuiManager guiManager;
    
    public GameController(){
        gameObjectList = new ArrayList<>();
        pressedButtonsList = new ArrayList<>();
        GuiManager.fileManager=new FileManager(9,50);
        objectKeys=new char[9][50];
        GuiManager.fileManager.readMapFile("src/MapObjects1.txt");
        objectKeys=GuiManager.fileManager.getMapObjects();
    }
    
    public void fillList(){
        GameObject object;
    	for(int i=0;i<9;i++){
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
    		PoisonedBrick pBrick= new PoisonedBrick(x*64,y*64,"/poisonedBrick.png");
    		return pBrick;
    	}else if(key=='3'){
    		PoisonedSeaweed seaweed=new PoisonedSeaweed(x*64,y*64,"/poisonedSeaweed.png");
    		return seaweed;
    	}else if(key=='4'){
    		Door door=new Door(x*64,y*64,"/door.png");
    		return door;
    	}else if(key=='5'){
                Coin gameObject = new Coin(x*64,y*64,"/coin.png");
    		return gameObject;
    	}else if(key=='6'){
                Diamond gameObject = new Diamond(x*64,y*64,"/diamond.png");
    		return gameObject;
    	}else if(key=='7'){
                Chalice gameObject = new Chalice(x*64,y*64,"/chalice.png");
    		return gameObject;
    	}else if(key=='8'){
                Heart gameObject = new Heart(x*64,y*64,"/heart.png");
    		return gameObject;
    	}else if(key==9){
    		return null;
    	}else if(key=='a'){
    		JetPack gameObject = new JetPack(x*64, y*64, "/jetPack.png");
                return gameObject;
    	}else if(key=='b'){
    		Gun gun = new Gun(x*64, y*64, "/gun.png");
    		return gun;
    	}else if(key=='c'){
    		Blade gameObject = new Blade(x*64,y*64,"/blade.png");
    		return gameObject;
    	}else if(key=='d'){
    		Player player = new Player(x*64, y*64, "/dave_right.png");
    		return player;
    	}else if(key=='e'){
    		return null;
    	}else if(key=='f'){
    		EnemywithBlade ewb = new EnemywithBlade(x*64, y*64, "/enemywithbladedef.png");
    		return ewb;
    	}else if(key=='g'){
    		EnemywithGun ewg = new EnemywithGun(x*64, y*64, "/enemywithgun.png");
    		return ewg;
    	}else if(key=='h'){
    		Boss boss = new Boss(x*64, y*64, "/boss.png");
    		return boss;
    	}else{
    		return null;
    	}
    }
    
    public void resetGame() {
        gameObjectList.clear();
        fillList();
    }
    
    public void moveAllObjects() {
        int p1 = 0;
        for (int i = 0; i < gameObjectList.size(); i++) {
            if (gameObjectList.get(i) instanceof Player) {
                ((Player)gameObjectList.get(i)).move(pressedButtonsList);
                p1 = i;
            }
            if (gameObjectList.get(i) instanceof Enemy) {
                ((Enemy)gameObjectList.get(i)).move("RLJJLRRLJRLR");
            }
            if (gameObjectList.get(i) instanceof Bullet) {
                ((Bullet)gameObjectList.get(i)).move(((Bullet)gameObjectList.get(i)).getDirection());
            }
        }
    }
    
    public void slideMap(double direction) {
        for (int i = 0; i < gameObjectList.size(); i++) {
            if (!(gameObjectList.get(i) instanceof Player)) {
                if(gameObjectList.get(i) instanceof Bullet || gameObjectList.get(i) instanceof Enemy){
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
                if (gameObjectList.get(i) instanceof Player && gameObjectList.get(j) instanceof Enemy) {
                    fight((Player)gameObjectList.get(i),(Enemy)gameObjectList.get(j));
                }
                if (gameObjectList.get(i) instanceof Enemy && gameObjectList.get(j) instanceof Enemy) {
                    checkEnemyCollision((Enemy)gameObjectList.get(i),(Enemy)gameObjectList.get(j));
                }
                if (gameObjectList.get(i) instanceof Bullet && gameObjectList.get(j) instanceof DynamicGameObject) {
                    checkBulletCollision((Bullet)gameObjectList.get(i),(DynamicGameObject)gameObjectList.get(j));
                }
            }
        }
    }
    public void checkCollision(DynamicGameObject obj1, StaticGameObject obj2){
        if(obj1.getPosY() - obj2.getPosY() < 63 && -63 < obj1.getPosY() - obj2.getPosY()){
            if(obj1.getPosX() - obj2.getPosX() < 64 && obj1.getPosX() - obj2.getPosX() > 0) {
                if (obj2 instanceof Gun  && obj1 instanceof Player) {
                    ((Player)obj1 ).gun = (Gun)obj2;
                    gameObjectList.remove(obj2);
                } else if (obj1 instanceof Bullet){
                    gameObjectList.remove(obj1);
                } else if(obj2 instanceof Coin && obj1 instanceof Player){
                    gameObjectList.remove(obj2);
                    ((Player)obj1).increasePoint(((Coin)obj2).getPoint());
                } else if(obj2 instanceof Diamond && obj1 instanceof Player){
                    gameObjectList.remove(obj2);
                    ((Player)obj1).increasePoint(((Diamond)obj2).getPoint());
                } else if(obj2 instanceof Heart && obj1 instanceof Player){
                    gameObjectList.remove(obj2);
                    ((Player)obj1).increaseHealth();
                } else if(obj2 instanceof Chalice && obj1 instanceof Player){
                    gameObjectList.remove(obj2);
                    for (int i = 0; i < gameObjectList.size(); i++) {
                        if (gameObjectList.get(i) instanceof Door) {
                            ((Door)gameObjectList.get(i)).setEnabled();
                        }
                    } 
                } else if (obj2 instanceof Blade) {
                    ((Player)obj1 ).blade = (Blade)obj2;
                    gameObjectList.remove(obj2);
                } else if (obj2 instanceof PoisonedBrick && (obj1 instanceof Player)) {
                    gameObjectList.remove(obj1);
                } else if (obj2 instanceof PoisonedSeaweed && (obj1 instanceof Player)) {
                    gameObjectList.remove(obj1);
                } else if (obj2 instanceof Door) {
                    if (((Door)(obj2)).getEnabled()){
                        int score = ((Player)obj1).getPoint();
                        gameObjectList.clear();
                        filemanager.readMapFile("src/MapObjects2.txt");
                        this.objectKeys = filemanager.getMapObjects();
                        this.fillList();
                        for(int i = 0; i<gameObjectList.size(); i++){
                            if(gameObjectList.get(i) instanceof Player)
                                ((Player)(gameObjectList.get(i))).increasePoint(score);
                        }
                    }
                } else if (obj2 instanceof JetPack) {
                    gameObjectList.remove(obj2);
                    ((Player)obj1).jetpack = ((JetPack)obj2);
                } else {
                    obj1.setLeftCol(true);
                }
            }
            if(obj2.getPosX() - obj1.getPosX() < 64 && obj2.getPosX() - obj1.getPosX() > 0) {
                if (obj2 instanceof Gun && obj1 instanceof Player) {
                    ((Player)obj1 ).gun = (Gun)obj2;
                    gameObjectList.remove(obj2);
                } else if (obj1 instanceof Bullet){
                    gameObjectList.remove(obj1);
                } else if(obj2 instanceof Coin && obj1 instanceof Player){
                    gameObjectList.remove(obj2);
                    ((Player)obj1).increasePoint(((Coin)obj2).getPoint());
                } else if(obj2 instanceof Diamond && obj1 instanceof Player){
                    gameObjectList.remove(obj2);
                    ((Player)obj1).increasePoint(((Diamond)obj2).getPoint());
                } else if(obj2 instanceof Heart && obj1 instanceof Player){
                    gameObjectList.remove(obj2);
                    ((Player)obj1).increaseHealth();
                } else if(obj2 instanceof Chalice && obj1 instanceof Player){
                    gameObjectList.remove(obj2);
                    for (int i = 0; i < gameObjectList.size(); i++) {
                        if (gameObjectList.get(i) instanceof Door) {
                            ((Door)gameObjectList.get(i)).setEnabled();
                        }
                    }              
                } else if (obj2 instanceof Blade) {
                    ((Player)obj1 ).blade = (Blade)obj2;
                    gameObjectList.remove(obj2);
                } else if (obj2 instanceof PoisonedBrick && (obj1 instanceof Player)) {
                    gameObjectList.remove(obj1);
                } else if (obj2 instanceof PoisonedSeaweed && (obj1 instanceof Player)) {
                    gameObjectList.remove(obj1);
                } else if (obj2 instanceof Door) {
                    if (((Door)(obj2)).getEnabled()){
                        int score = ((Player)obj1).getPoint();
                        gameObjectList.clear();
                        filemanager.readMapFile("src/MapObjects2.txt");
                        this.objectKeys = filemanager.getMapObjects();
                        this.fillList();
                        for(int i = 0; i<gameObjectList.size(); i++){
                            if(gameObjectList.get(i) instanceof Player)
                                ((Player)(gameObjectList.get(i))).increasePoint(score);
                        }
                    }
                } else if (obj2 instanceof JetPack && obj1 instanceof Player) {
                    gameObjectList.remove(obj2);
                    ((Player)obj1).jetpack = ((JetPack)obj2);
                } else {
                    obj1.setRightCol(true);
                }
            }
        }
        
        if(obj1.getPosX() - obj2.getPosX() < 50 && -50 < obj1.getPosX() - obj2.getPosX()){
            if(obj1.getPosY() - obj2.getPosY() < 64 && obj1.getPosY() - obj2.getPosY() > 0){
                if (obj2 instanceof Gun && obj1 instanceof Player) {
                    ((Player)obj1 ).gun = (Gun)obj2;
                    gameObjectList.remove(obj2);
                } else if (obj1 instanceof Bullet){
                    gameObjectList.remove(obj1);
                } else if(obj2 instanceof Coin && obj1 instanceof Player){
                    gameObjectList.remove(obj2);
                    ((Player)obj1).increasePoint(((Coin)obj2).getPoint());
                } else if(obj2 instanceof Diamond && obj1 instanceof Player){
                    gameObjectList.remove(obj2);
                    ((Player)obj1).increasePoint(((Diamond)obj2).getPoint());
                } else if(obj2 instanceof Heart && obj1 instanceof Player){
                    gameObjectList.remove(obj2);
                    ((Player)obj1).increaseHealth();
                } else if(obj2 instanceof Chalice && obj1 instanceof Player){
                    gameObjectList.remove(obj2);
                    for (int i = 0; i < gameObjectList.size(); i++) {
                        if (gameObjectList.get(i) instanceof Door) {
                            ((Door)gameObjectList.get(i)).setEnabled();
                        }
                    }
                } else if (obj2 instanceof Blade) {
                    ((Player)obj1 ).blade = (Blade)obj2;
                    gameObjectList.remove(obj2);
                } else if (obj2 instanceof PoisonedBrick && (obj1 instanceof Player)) {
                    gameObjectList.remove(obj1);
                } else if (obj2 instanceof PoisonedSeaweed && (obj1 instanceof Player)) {
                    gameObjectList.remove(obj1);
                } else if (obj2 instanceof Door) {
                    if (((Door)(obj2)).getEnabled()){
                        int score = ((Player)obj1).getPoint();
                        gameObjectList.clear();
                        filemanager.readMapFile("src/MapObjects2.txt");
                        this.objectKeys = filemanager.getMapObjects();
                        this.fillList();
                        for(int i = 0; i<gameObjectList.size(); i++){
                            if(gameObjectList.get(i) instanceof Player)
                                ((Player)(gameObjectList.get(i))).increasePoint(score);
                        }
                    }
                } else if (obj2 instanceof JetPack && obj1 instanceof Player) {
                    gameObjectList.remove(obj2);
                    ((Player)obj1).jetpack = ((JetPack)obj2);
                } else {
                    obj1.setTopCol(true);
                    obj1.posY = obj2.posY + 64;
                }
            }               
            if(obj2.getPosY() - obj1.getPosY() < 68 && obj2.getPosY() - obj1.getPosY() > 0){
                if (obj2 instanceof Gun && obj1 instanceof Player) {
                    ((Player)obj1 ).gun = (Gun)obj2;
                    gameObjectList.remove(obj2);
                } else if (obj1 instanceof Bullet){
                    gameObjectList.remove(obj1);
                } else if(obj2 instanceof Coin && obj1 instanceof Player){
                    gameObjectList.remove(obj2);
                    ((Player)obj1).increasePoint(((Coin)obj2).getPoint());
                } else if(obj2 instanceof Diamond && obj1 instanceof Player){
                    gameObjectList.remove(obj2);
                    ((Player)obj1).increasePoint(((Diamond)obj2).getPoint());
                } else if(obj2 instanceof Heart && obj1 instanceof Player){
                    gameObjectList.remove(obj2);
                    ((Player)obj1).increaseHealth();
                } else if(obj2 instanceof Chalice && obj1 instanceof Player){
                    gameObjectList.remove(obj2);
                    for (int i = 0; i < gameObjectList.size(); i++) {
                        if (gameObjectList.get(i) instanceof Door) {
                            ((Door)gameObjectList.get(i)).setEnabled();
                        }
                    }
                } else if (obj2 instanceof Blade) {
                    ((Player)obj1 ).blade = (Blade)obj2;
                    gameObjectList.remove(obj2);
                } else if (obj2 instanceof PoisonedBrick && (obj1 instanceof Player)) {
                    gameObjectList.remove(obj1);
                } else if (obj2 instanceof PoisonedSeaweed && (obj1 instanceof Player)) {
                    gameObjectList.remove(obj1);
                } else if (obj2 instanceof Door) {
                    if (((Door)(obj2)).getEnabled()){
                        int score = ((Player)obj1).getPoint();
                        gameObjectList.clear();
                        filemanager.readMapFile("src/MapObjects2.txt");
                        this.objectKeys = filemanager.getMapObjects();
                        this.fillList();
                        for(int i = 0; i<gameObjectList.size(); i++){
                            if(gameObjectList.get(i) instanceof Player)
                                ((Player)(gameObjectList.get(i))).increasePoint(score);
                        }
                    }
                } else if (obj2 instanceof JetPack && obj1 instanceof Player) {
                    gameObjectList.remove(obj2);
                    ((Player)obj1).jetpack = ((JetPack)obj2);
                } else {
                    obj1.setBottomCol(true);
                    obj1.setSpeedY(0.0);
                    obj1.setIsJumped(false);
                    obj1.posY = obj2.posY - 64;
                }
            }
        }
    }
    
    public void checkEnemyCollision (Enemy obj1, Enemy obj2) {
        if(obj1.getPosY() - obj2.getPosY() < 63 && -63 < obj1.getPosY() - obj2.getPosY()){
            if(obj1.getPosX() - obj2.getPosX() < 64 && obj1.getPosX() - obj2.getPosX() > 0) {
                obj1.setLeftCol(true);      
            }
            if(obj2.getPosX() - obj1.getPosX() < 64 && obj2.getPosX() - obj1.getPosX() > 0) {
                obj1.setRightCol(true);      
            }
        }
        
        if(obj1.getPosX() - obj2.getPosX() < 50 && -50 < obj1.getPosX() - obj2.getPosX()){
            if(obj1.getPosY() - obj2.getPosY() < 64 && obj1.getPosY() - obj2.getPosY() > 0){
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
    
    public void checkBulletCollision (Bullet obj1, DynamicGameObject obj2) {
        if(obj1.getPosY() - obj2.getPosY() < 63 && -63 < obj1.getPosY() - obj2.getPosY()){
            if(obj1.getPosX() - obj2.getPosX() < 64 && obj1.getPosX() - obj2.getPosX() > 0) {
                gameObjectList.remove(obj2);      
            }
            if(obj2.getPosX() - obj1.getPosX() < 64 && obj2.getPosX() - obj1.getPosX() > 0) {
                gameObjectList.remove(obj2);      
            }
        }
    }
    
    public void fight(DynamicGameObject obj1, Enemy obj2) {
        if (obj2 instanceof EnemywithGun || obj2 instanceof Boss) {
            if(obj1.getPosY() - obj2.getPosY() < 63 && -63 < obj1.getPosY() - obj2.getPosY()){
                if(obj1.getPosX() - obj2.getPosX() < 64 && obj1.getPosX() - obj2.getPosX() > 0) {     
                    //obj1.setLeftCol(true);
                }
                if(obj2.getPosX() - obj1.getPosX() < 512 && obj2.getPosX() - obj1.getPosX() > 0) {
                    if (obj2 instanceof EnemywithGun) {
                        ((EnemywithGun)obj2).enemyShoot();
                    } else if (obj2 instanceof Boss) {
                        ((Boss)obj2).bossShoot();
                    }
                    //obj1.setRightCol(true);    
                }
            }
        } else if (obj2 instanceof EnemywithBlade || obj2 instanceof Boss) {
            if(obj1.getPosY() - obj2.getPosY() < 63 && -63 < obj1.getPosY() - obj2.getPosY()){
                if(obj1.getPosX() - obj2.getPosX() < 64 && obj1.getPosX() - obj2.getPosX() > 0) {     
                    //obj1.setLeftCol(true);
                }
                if(obj2.getPosX() - obj1.getPosX() < 64 && obj2.getPosX() - obj1.getPosX() > 0) {
                    if (obj1 instanceof Player && obj2 instanceof EnemywithBlade) {
                        ((EnemywithBlade)obj2).swing();
                        gameObjectList.remove(obj1);
                    }
                    if (obj1 instanceof Player && obj2 instanceof Boss) {
                        ((Boss)obj2).swing();
                        gameObjectList.remove(obj1);
                    }
                    //obj1.setRightCol(true);    
                }
            }
        }
    }
}
