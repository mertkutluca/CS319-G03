/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dave;

import java.util.ArrayList;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

/**
 *
 * @author Sebahattin
 */
public class InputManager {
    ArrayList<String> buttonList;
    
    public InputManager(){
        buttonList = new ArrayList<String>();
          /*  
            GuiManager.gameScene.setOnKeyPressed(
	        new EventHandler<KeyEvent>()
	        {
                    @Override
                    public void handle(KeyEvent e)
	            {
	                String code = e.getCode().toString();
	                if(!GuiManager.gameController.pressedButtonsList.contains(code))
                            GuiManager.gameController.pressedButtonsList.add(code);	
	            }
                });
	        
            GuiManager.gameScene.setOnKeyReleased(
	 	new EventHandler<KeyEvent>()
	        {
                    @Override
	            public void handle(KeyEvent e)
	            {
	    	        String code = e.getCode().toString();
	        	GuiManager.gameController.pressedButtonsList.remove(code);
	            }
                });*/
    }
    
    public void listen() {
        GuiManager.gameScene.setOnKeyPressed(
	        new EventHandler<KeyEvent>()
	        {
                    @Override
                    public void handle(KeyEvent e)
	            {
	                String code = e.getCode().toString();
	                if(!GuiManager.gameController.pressedButtonsList.contains(code))
                            GuiManager.gameController.pressedButtonsList.add(code);	
	            }
                });
	        
            GuiManager.gameScene.setOnKeyReleased(
	 	new EventHandler<KeyEvent>()
	        {
                    @Override
	            public void handle(KeyEvent e)
	            {
	    	        String code = e.getCode().toString();
	        	GuiManager.gameController.pressedButtonsList.remove(code);
	            }
                });
    }
}
