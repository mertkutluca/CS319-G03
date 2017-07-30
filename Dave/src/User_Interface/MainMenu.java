/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package User_Interface;

import Game_Management.GameController;
import Input_Management.InputManager;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 *
 * @author ahmetnamli
 */
public class MainMenu extends Scene{
    public static GameScene gameScene;
    protected static AnimationTimer animationTimer;
    public static GameController gameController;
    public static InputManager inputManager;
    public static MainMenu mainMenu;
    
    public MainMenu(GridPane grid, Stage primaryStage) {
        super(grid, 800, 600);
        grid.setAlignment(Pos.CENTER);
        grid.setVgap(20);
        
        Text sceneTitle = new Text ("More Dangerous Dave");
        sceneTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        
        Button contGame = new Button("Continue Game");
        Button newGame = new Button("New Game");
        Button highScore = new Button ("High Scores");
        Button help = new Button ("Help");
        Button credits = new Button ("Credits");
        Button exit = new Button ("EXIT");
        
        contGame.setMaxWidth(Double.MAX_VALUE);
        newGame.setMaxWidth(Double.MAX_VALUE);
        highScore.setMaxWidth(Double.MAX_VALUE);
        help.setMaxWidth(Double.MAX_VALUE);
        credits.setMaxWidth(Double.MAX_VALUE);
        exit.setMaxWidth(Double.MAX_VALUE);
        
       // contGame.setOnAction(event -> continueGame());
      
        newGame.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
               primaryStage.setScene(GuiManager.gameScene); 
               GuiManager.animationTimer.start();
               }
        });
        //highScore.setOnAction(event -> highScore());
        help.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
               primaryStage.setScene(GuiManager.help); 
               }
        });
        
          credits.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
               primaryStage.setScene(GuiManager.credits); 
               }
        });
        //credits.setOnAction(event -> credits());
        exit.setOnAction(event -> System.exit(0));
        
        grid.add(sceneTitle,0,0);
        grid.add(contGame,0,1);
        grid.add(newGame,0,2);
        grid.add(highScore,0,3);
        grid.add(help,0,4);
        grid.add(credits,0,5);
        grid.add(exit,0,6);
        
    }

   
    
}
