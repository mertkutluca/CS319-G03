/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package User_Interface;

import File_Management.FileManager;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
public class HighScore extends Scene {
    public FileManager fileManager;
    Image mainImage;
    public HighScore(GridPane grid, Stage primaryStage) {
        super(grid, 800, 600);
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(20);
        grid.setVgap(20);
        fileManager=new FileManager(2,2);
        
        this.mainImage = new Image("/mainImage.png");
        BackgroundImage main = new BackgroundImage(mainImage,BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT,BackgroundPosition.DEFAULT,BackgroundSize.DEFAULT);
        
        Text sceneTitle = new Text("High Score");
        sceneTitle.setFont(Font.font("Tahoma", FontWeight.BOLD, 30));
        sceneTitle.setFill(Color.LIME);
        
        Label label = new Label();
        Button backBut = new Button("Back");
   	label.setText( " 1. " + fileManager.getHighScores("src/HighScore.txt").get(9) +" \n"
                     + " 2. " + fileManager.getHighScores("src/HighScore.txt").get(8) +" \n"
                     + " 3. " + fileManager.getHighScores("src/HighScore.txt").get(7) +" \n"
                     + " 4. " + fileManager.getHighScores("src/HighScore.txt").get(6) +" \n"
                     + " 5. " + fileManager.getHighScores("src/HighScore.txt").get(5) +" \n"
                     + " 6. " + fileManager.getHighScores("src/HighScore.txt").get(4) +" \n"   
                     + " 7. " + fileManager.getHighScores("src/HighScore.txt").get(3) +" \n"
                     + " 8. " + fileManager.getHighScores("src/HighScore.txt").get(2) +" \n"
                     + " 9. " + fileManager.getHighScores("src/HighScore.txt").get(1) +" \n"
                     + "10. " + fileManager.getHighScores("src/HighScore.txt").get(0) +" \n");
        label.setTextFill(Color.AZURE);
        label.setFont(Font.font("Tahoma", FontWeight.MEDIUM,25));
      backBut.setMaxWidth(Double.MAX_VALUE);
      
      backBut.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
               primaryStage.setScene(GuiManager.mainMenu); 
               }
        });
      
      grid.add(sceneTitle, 0, 0);
      grid.add(label, 0, 1);
      grid.add(backBut, 0, 5);
      grid.setBackground(new Background(main));
    }
    
}
