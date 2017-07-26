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
    ArrayList<String> buttonsPressed;
    
    public InputManager(){
        buttonsPressed = new ArrayList<String>();
            
            GameApplication.getGameScene().setOnKeyPressed(
	        new EventHandler<KeyEvent>()
	        {
                        @Override
			public void handle(KeyEvent e)
	                {
	                	String code = e.getCode().toString();
	                    	GameEngine.getInstance().buttonPressed(code);
	                        	
	                }
                });
	        
            GameApplication.getGameScene().setOnKeyReleased(
	 	new EventHandler<KeyEvent>()
	        {
                        @Override
	                public void handle(KeyEvent e)
	                {
                                
	    	                String code = e.getCode().toString();
	        	        GameEngine.getInstance().buttonReleased(code);
	                }
                });
    }
}
