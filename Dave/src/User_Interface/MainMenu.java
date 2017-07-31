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
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.paint.Color;


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
    public static PauseMenu pauseMenu;
    Image mainImage;
    
    public MainMenu(GridPane grid, Stage primaryStage) {
        super(grid, 800, 600);
        grid.setAlignment(Pos.CENTER);
        grid.setVgap(20);
        
        Text sceneTitle = new Text ("More Dangerous Dave");
        sceneTitle.setFont(Font.font("Tahoma", FontWeight.BOLD, 30));
        sceneTitle.setFill(Color.LIME);
        
        this.mainImage = new Image("/mainImage.png");
        BackgroundImage main = new BackgroundImage(mainImage,BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT,BackgroundPosition.DEFAULT,BackgroundSize.DEFAULT);
        
        Button contGame = new Button("Continue Game");
        Button newGame = new Button("New Game");
        Button multiPlayer = new Button("MultiPlayer");
        Button highScore = new Button ("High Scores");
        Button help = new Button ("Help");
        Button credits = new Button ("Credits");
        Button exit = new Button ("EXIT");
        
        contGame.setMaxWidth(Double.MAX_VALUE);
        newGame.setMaxWidth(Double.MAX_VALUE);
        multiPlayer.setMaxWidth(Double.MAX_VALUE);
        highScore.setMaxWidth(Double.MAX_VALUE);
        help.setMaxWidth(Double.MAX_VALUE);
        credits.setMaxWidth(Double.MAX_VALUE);
        exit.setMaxWidth(Double.MAX_VALUE);
     
        contGame.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
               primaryStage.setScene(GuiManager.gameScene); 
               GuiManager.gameController.setMulti(false);
               GuiManager.animationTimer.start();
               }
        });
        
        newGame.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
               GuiManager.gameController.readMap("src/MapObjects1.txt");
               GuiManager.gameController.resetGame();
               GuiManager.gameController.setMulti(false);
               primaryStage.setScene(GuiManager.gameScene);
               GuiManager.animationTimer.start();
               }
        });
        
        multiPlayer.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
               GuiManager.gameController.readMap("src/MultiMapObjects1.txt");
               GuiManager.gameController.resetGame();
               GuiManager.gameController.setMulti(true);
               primaryStage.setScene(GuiManager.gameScene);
               GuiManager.animationTimer.start();
               }
        });
        
        help.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
               primaryStage.setScene(GuiManager.help); 
               }
        });
        
        highScore.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
               primaryStage.setScene(GuiManager.highScore); 
               }
        });
        
          credits.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
               primaryStage.setScene(GuiManager.credits); 
               }
        });
       
        exit.setOnAction(event -> System.exit(0));
        
        grid.add(sceneTitle,0,0);
        grid.add(contGame,0,1);    
        grid.add(newGame,0,2);
        grid.add(multiPlayer,0,3);
        grid.add(highScore,0,4);
        grid.add(help,0,5);
        grid.add(credits,0,6);
        grid.add(exit,0,7);
        grid.setBackground(new Background(main));
        
    }   
}
