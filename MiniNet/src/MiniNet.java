import java.io.IOException;

import Controller.*;
import Exceptions.NoParentsException;
import GUI.MainMenuGUI;
import javafx.application.Application;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
/**
 * This class is the main method class to run the program
 * @author YILEI XU
 *
 */
public class MiniNet extends Application{
	
	
	@Override
	/**
	 * This is the GUI override start method, which provide the primary stage
	 * @param primaryStage the primary stage of the interface
	 */
    public void start(Stage primaryStage) throws IOException{
		
		  DriverClass dc = new DriverClass();

		  primaryStage.setTitle("MiniNet");
		  primaryStage.setScene(new MainMenuGUI(primaryStage, dc).startScene());
		  primaryStage.setResizable(false);
		  primaryStage.show();	        
	        try {
	            dc.initialData();
	        } catch (IOException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }  catch(NoParentsException e) {
	        	e.noParentsWarning();
	        }
	        
    } 
	/**
	 * This is the main method
	 * @param args
	 */
	public static void main(String[] args)
	{
	  	Application.launch(args);
	
	}
	
 
}
