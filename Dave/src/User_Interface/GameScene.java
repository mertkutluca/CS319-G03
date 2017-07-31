/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package User_Interface;

import Entity_Objects.Player;
import Game_Management.GameController;
import Input_Management.InputManager;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.Window;
import javax.swing.JButton;
import javax.swing.JPopupMenu;

/**
 *
 * @author Sebahattin
 */
public class GameScene extends Scene {
   
    public GameScene(Group group, Stage primaryStage) {
	super(group,800, 600,Color.BLACK);
                
        GuiManager.gameController = new GameController();
        GuiManager.inputManager = new InputManager();
        Canvas c = new Canvas(800,600);
        group.getChildren().add(c);
        GraphicsContext g = c.getGraphicsContext2D();
        GuiManager.gameController.fillList();  
        
        Label point = new Label();
        point.setFont(Font.font("Tahoma", FontWeight.MEDIUM, 20));
        point.setTextFill(Color.LIME);
        point.setLayoutX(350);
        point.autosize();
        group.getChildren().add(point);
       
         
        GuiManager.animationTimer = new AnimationTimer()
        {
            double frameTime = 0;
            long oldFrameTime = System.nanoTime();
            
            @Override
            public void handle(long currentNanoTime)
            {
                if(GuiManager.gameController.pressedButtonsList.contains("P")){
                     primaryStage.setScene(GuiManager.pauseMenu);
                     GuiManager.gameController.pressedButtonsList.remove("P");
                }
                for(int i = 0; i < GuiManager.gameController.gameObjectList.size(); i++){
                         if(GuiManager.gameController.gameObjectList.get(i) instanceof Player){
                             point.setText("Score: " + ((Player)(GuiManager.gameController.gameObjectList.get(i))).getPoint());
                         }
                }
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
            }
        };
        
    }
    public void drawAll(GraphicsContext g) {
        for (int i = 0; i < GuiManager.gameController.gameObjectList.size(); i++){
            g.drawImage(GuiManager.gameController.gameObjectList.get(i).getImage(), GuiManager.gameController.gameObjectList.get(i).getPosX(), GuiManager.gameController.gameObjectList.get(i).getPosY());
        }
    }

}
