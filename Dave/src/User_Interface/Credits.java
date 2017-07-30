/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package User_Interface;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author ahmetnamli
 */
public class Credits extends Scene {
    public Credits(GridPane grid, Stage primaryStage) {
        super(grid, 800, 600);
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(20);
        grid.setVgap(20);
        
        Text sceneTitle = new Text("Credits");
        sceneTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        
        Label label = new Label();
        Button backBut = new Button("Back");
        
      label.setText("Halil Selman Atmaca\n"
				+ "Mert Sebahattin Kutluca \n"
				+ "Ahmet Namlı \n"
				+ "Muhammed Emin Tosun");
      
      backBut.setMaxWidth(Double.MAX_VALUE);
      
      backBut.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
               primaryStage.setScene(GuiManager.mainMenu); 
               }
        });
      
      grid.add(sceneTitle, 0, 0);
      grid.add(label, 0, 1);
      grid.add(backBut, 0, 5);
    }
    
}
