/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dave;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.stage.Stage;

/**
 *
 * @author Sebahattin
 */
public class GuiManager extends Application {
    protected static AnimationTimer animationTimer;
    protected static GameController gameController;
    protected static GameScene gameScene;
    protected static InputManager inputManager;
    
    
    public static void main(String[] args) {
	// TODO - implement GameApplication.main
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Saving Humanity");
	primaryStage.setResizable(false);
        
        Group group = new Group();
	gameScene = new GameScene(group,primaryStage);
        primaryStage.setScene(gameScene);
        animationTimer.start();
        primaryStage.show();
    }
}
