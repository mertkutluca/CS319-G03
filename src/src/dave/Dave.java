/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dave;

import java.util.ArrayList;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

/**
 *
 * @author Sebahattin
 */
public class Dave extends Application {
    
    GameController gc = new GameController();
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Dave");
        primaryStage.setResizable(false);
        
        Group root = new Group();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        Canvas c = new Canvas(640,480);
        root.getChildren().add(c);
        
        ArrayList<String> inputs = new ArrayList<String>();
        
        scene.setOnKeyPressed(
            new EventHandler<KeyEvent>()
            {
                public void handle(KeyEvent event)
                {
                    String key_code = event.getCode().toString();
                    if(!inputs.contains(key_code))
                        inputs.add(key_code);
                }
            });
        
        scene.setOnKeyReleased(
            new EventHandler<KeyEvent>()
            {
                public void handle(KeyEvent event)
                {
                    String key_code = event.getCode().toString();
                    inputs.remove(key_code);
                }
            });       
        
        GraphicsContext g = c.getGraphicsContext2D();
        
        gc.fillList();
        /*
        DynamicGameObject player = new DynamicGameObject(10.0, 346.0, "/davepic.png");
        StaticGameObject brick1 = new StaticGameObject(138.0, 410.0, "/brick.png");
        StaticGameObject brick2 = new StaticGameObject(10.0, 410.0, "/brick.png");
        StaticGameObject brick3 = new StaticGameObject(202.0, 410.0, "/brick.png");
        StaticGameObject brick4 = new StaticGameObject(330.0, 346.0, "/brick.png");
        StaticGameObject brick5 = new StaticGameObject(266.0, 346.0, "/brick.png");
        StaticGameObject brick6 = new StaticGameObject(394.0, 410.0, "/brick.png");
        StaticGameObject brick7 = new StaticGameObject(458.0, 410.0, "/brick.png");
        StaticGameObject brick8 = new StaticGameObject(522.0, 410.0, "/brick.png");
        StaticGameObject brick9 = new StaticGameObject(330.0, 282.0, "/brick.png");
        */
        new AnimationTimer()
        {
            public void handle(long currentNanoTime)
            {
                g.setFill(Color.BLACK);
                
                gc.handleCollisions();
                gc.pressedButtonList = inputs;
                gc.moveAllObjects();
                g.fillRect(0, 0, c.getWidth(), c.getHeight());
                drawAll(g);
            }
        }.start();
        
        primaryStage.show();
    }

    public void drawAll(GraphicsContext g) {
        for (int i = 0; i < gc.gameObjectList.size(); i++){
            g.drawImage(gc.gameObjectList.get(i).getImage(), gc.gameObjectList.get(i).getPosX(), gc.gameObjectList.get(i).getPosY());
        }
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
