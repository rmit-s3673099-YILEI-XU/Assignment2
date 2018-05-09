import java.io.IOException;

import Controller.DriverClass;
import GUI.MainMenu;
import javafx.application.Application;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MiniNet extends Application{
	
	Stage window;
	@Override
    public void start(Stage primaryStage) throws IOException{
		
		  DriverClass dc = new DriverClass();
	        window = primaryStage;
	        // window.initStyle(StageStyle.TRANSPARENT);
	        window.setTitle("MiniNet");
//	       window.setScene(new MiniNetInterface(window).startScene());
	        window.setScene(new MainMenu(window, dc).startScene());
	        window.setResizable(false);
	        window.show();
	        
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
