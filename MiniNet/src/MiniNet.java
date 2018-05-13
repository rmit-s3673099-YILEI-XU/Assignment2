import java.io.IOException;

import Controller.*;
import GUI.MainMenuGUI;
import javafx.application.Application;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MiniNet extends Application{
	
	@Override
    public void start(Stage primaryStage) throws IOException{
		
		  DriverClass dc = new DriverClass();
	      
	        // window.initStyle(StageStyle.TRANSPARENT);
		  primaryStage.setTitle("MiniNet");
//	       window.setScene(new MiniNetInterface(window).startScene());
		  primaryStage.setScene(new MainMenuGUI(primaryStage, dc).startScene());
		  primaryStage.setResizable(false);
		  primaryStage.show();
	        
	        try {
	            dc.initialData();
	        } catch (IOException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	        
	    
	        
    } 

	public static void main(String[] args)
	{
//		DriverClass dc = new DriverClass();
//		dc.initialData();
	  	Application.launch(args);
	
	}

	
 
}
