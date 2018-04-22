import javafx.application.Application;
import javafx.stage.Stage;

public class MiniNet extends Application{
	
	Stage window;
	@Override
    public void start(Stage primaryStage) throws Exception{
    	   window = primaryStage;
       window.setTitle("MiniNet");
       window.setScene(new MiniNetInterface(window).startScene());
       window.setResizable(false);
       window.show();
    } 

	public static void main(String[] args)
	{
//		DriverClass dc = new DriverClass();
//		dc.initialData();
	  	Application.launch(args);
	  	
	}

	
 
}
