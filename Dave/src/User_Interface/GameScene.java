/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package User_Interface;

import Entity_Objects.Player;
import Game_Management.GameController;
import Input_Management.InputManager;
import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author Sebahattin
 */
public class GameScene extends Scene {
    
    public GameScene(Group groupPane, Stage primaryStage) {
	super(groupPane,640,480,Color.BLACK);
                
        GuiManager.gameController = new GameController();
        GuiManager.inputManager = new InputManager();
        Canvas c = new Canvas(640,480);
        groupPane.getChildren().add(c);
        GraphicsContext g = c.getGraphicsContext2D();
        GuiManager.gameController.fillList();
        GuiManager.animationTimer = new AnimationTimer()
        {
            double frameTime = 0;
            long oldFrameTime = System.nanoTime();
            @Override
            public void handle(long currentNanoTime)
            {
                g.setFill(Color.BLACK);
                g.fillRect(0, 0, c.getWidth(), c.getHeight());
                drawAll(g);
                frameTime += System.nanoTime() - oldFrameTime;
                GuiManager.inputManager.listen();
                if(frameTime / 1000000.0 > 15){
                    GuiManager.gameController.moveAllObjects();
                    frameTime = 0.0f;
		}                
                GuiManager.gameController.handleCollisions(); 
                oldFrameTime = System.nanoTime();
                System.out.println(GuiManager.gameController.gameObjectList.size());
            }
        };
    }
    public void drawAll(GraphicsContext g) {
        for (int i = 0; i < GuiManager.gameController.gameObjectList.size(); i++){
            g.drawImage(GuiManager.gameController.gameObjectList.get(i).getImage(), GuiManager.gameController.gameObjectList.get(i).getPosX(), GuiManager.gameController.gameObjectList.get(i).getPosY());
        }
    }
    
}
