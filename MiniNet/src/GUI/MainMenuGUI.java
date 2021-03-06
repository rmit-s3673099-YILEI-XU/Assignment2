package GUI;

import Controller.DriverClass;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * This class is the main menu of the GUI, connect with driverClass and other GUI classes
 * @author CIFANG ZHANG
 *
 */
public  class MainMenuGUI {

    static public Stage window;
    static public DriverClass dc;
    
/**
 * This is the constructor
 * @param window the primary stage
 * @param dc the driverClass object
 */
    public MainMenuGUI(Stage window){
        this.window = window;
        this.dc = new DriverClass();
    }

/**
 * This method is to set up the basic layout of the GridPane for other pane to reuse
 * @return GridPane
 */
    static public GridPane setUpPane() {

        GridPane primaryPane = new GridPane();
        primaryPane.setAlignment(Pos.CENTER);
        primaryPane.setPadding(new Insets(5, 5, 5, 5));
        primaryPane.setHgap(5.5);
        primaryPane.setVgap(5.5);

        return primaryPane;
    }
    
  /**
   * This method is to set up the basic layout of the BorderPane for most of the scenes to reuse
   * @param top the top part
   * @param center the center part
   * @param bottom the bottom part
   * @param left the left part
   * @param right the right part
   * @return BorderPane
   */
    static public BorderPane setUpBorderPane(Node top, Node center, Node bottom, Node left, Node right) {
    	
     	BorderPane pane = new BorderPane();
     	pane.setTop(top);
     	pane.setAlignment(top, Pos.CENTER);
     	pane.setCenter(center);
     	pane.setBottom(bottom);
     	pane.setLeft(left);
     	pane.setRight(right);
     	pane.setPadding(new Insets(20, 20, 20, 20));
     	return pane;   
    }
    
/**
 * This method is the main menu start scene
 * @return Scene
 */
    static public Scene startScene() {

        // set up layout
   
	    	Label label = new Label("Welcome to MiniNet, Please choose one from the menu");
	    	Button addPersonBt = new Button("Add person");
	    	Button selectPersonBt = new Button("Select a Person");	
	    	Button findOutBt = new Button("Find out relationship");
	    	Button exitBt = new Button("Exit");

	    	GridPane GPane = setUpPane();
	    	GPane.add(addPersonBt, 0, 0);
	    	GPane.add(selectPersonBt, 0, 1);
	    	GPane.add(findOutBt, 0, 2);
	    	GPane.add(exitBt, 0, 3);

	    	BorderPane pane = setUpBorderPane(label, GPane, null, null, null);

        // create events

        addPersonBt.setOnAction(event -> {
            window.setScene(new AddPersonGUI().addPersonScene());
        });

        selectPersonBt.setOnAction(event -> {
            window.setScene(new SelectPersonGUI().selectPersonScene());
        });

        findOutBt.setOnAction(e -> {
        window.setScene(new FindOutRelationGUI().findOutRelationScene());
        });

        exitBt.setOnAction(event -> {
        	System.exit(0);
        });
        
        // define the close button event on the stage
        window.setOnCloseRequest(e ->{
        	System.exit(0);   
        });
        Scene scene = new Scene(pane, 700, 500);
        scene.getStylesheets().add("GUI.css");
        System.out.println("Main Menu");
        return scene;
    }
    
}
