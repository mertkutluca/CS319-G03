/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package User_Interface;

import File_Management.FileManager;
import Game_Management.GameController;
import Input_Management.InputManager;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 *
 * @author Sebahattin
 */
public class GuiManager extends Application {
    protected static AnimationTimer animationTimer;
    public static GameController gameController;
    public static GameScene gameScene;
    public static InputManager inputManager;
    public static MainMenu mainMenu;
    public static Help help;
    public static Credits credits;
    public static PauseMenu pauseMenu;
    public static FileManager fileManager;
    public static HighScore highScore;
    public static Group group = new Group();
    public static void main(String[] args) {
	// TODO - implement GameApplication.main
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("More Dangerous Dave");
	primaryStage.setResizable(false);
        
       
        GridPane grid2 = new GridPane();
        GridPane grid3 = new GridPane();
        GridPane grid4 = new GridPane();
        GridPane grid5 = new GridPane();
        GridPane grid = new GridPane();
        highScore = new HighScore(grid, primaryStage);
        pauseMenu = new PauseMenu(grid5, primaryStage);
        credits = new Credits(grid4, primaryStage);
        help = new Help(grid3, primaryStage);
        gameScene = new GameScene(group,primaryStage);
	mainMenu = new MainMenu(grid2,primaryStage);
        primaryStage.setScene(mainMenu);
        primaryStage.show();
    }
}
