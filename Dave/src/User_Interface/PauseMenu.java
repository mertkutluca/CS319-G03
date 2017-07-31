/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package User_Interface;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author ahmetnamli
 */
public class PauseMenu extends Scene {
    Image mainImage;
    public PauseMenu(GridPane grid, Stage primaryStage){
        super (grid,800,600);
        grid.setAlignment(Pos.CENTER);
        grid.setVgap(20);
        
        this.mainImage = new Image("/mainImage.png");
        BackgroundImage main = new BackgroundImage(mainImage,BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT,BackgroundPosition.DEFAULT,BackgroundSize.DEFAULT);
           
        Text sceneTitle = new Text("Pause");
        sceneTitle.setFont(Font.font("Tahoma", FontWeight.BOLD, 30));
        sceneTitle.setFill(Color.LIME);
        
        Button resume = new Button("Resume Game");
        Button newGame = new Button("New Game");
        Button help = new Button("Help");
        Button mainMenu = new Button("Main Menu");
        Button exit = new Button("EXIT");
        
        resume.setMaxWidth(Double.MAX_VALUE);
        newGame.setMaxWidth(Double.MAX_VALUE);
        help.setMaxWidth(Double.MAX_VALUE);
        mainMenu.setMaxWidth(Double.MAX_VALUE);
        exit.setMaxWidth(Double.MAX_VALUE);
        
        newGame.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
               GuiManager.gameController.resetGame();
               primaryStage.setScene(GuiManager.gameScene); 
               GuiManager.animationTimer.start();
               }
        });
        
        resume.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
               primaryStage.setScene(GuiManager.gameScene); 
               GuiManager.animationTimer.start();
               }
        });
        
        help.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
               primaryStage.setScene(GuiManager.help); 
               }
        });
       
        mainMenu.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
               primaryStage.setScene(GuiManager.mainMenu); 
               }
        });
        
        exit.setOnAction(event -> System.exit(0));
        
        grid.add(sceneTitle,0,0);
        grid.add(resume,0,1);
        grid.add(newGame,0,2);
        grid.add(help,0,3);
        grid.add(mainMenu,0,4);
        grid.add(exit,0,6);
        grid.setBackground(new Background(main));    
    }
    
}
