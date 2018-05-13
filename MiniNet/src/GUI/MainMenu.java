package GUI;

import Controller.DriverClass;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public  class MainMenu {

    static public Stage window;
    static public DriverClass dc;

    public MainMenu(Stage window, DriverClass dc){
        this.window = window;
        this.dc = dc;

    }


    static public GridPane setUpPane() {

        GridPane primaryPane = new GridPane();
        primaryPane.setAlignment(Pos.CENTER);
        primaryPane.setPadding(new Insets(5, 5, 5, 5));
        primaryPane.setHgap(5.5);
        primaryPane.setVgap(5.5);

        return primaryPane;
    }
    
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
	    	
	    	
	    	
//    		GridPane pane = setUpPane();    
//    		
//    		Label label = new Label("Welcome to Mininet, Please choose one from the menu");
//    		label1.setStyle("-fx-font-size: 13pt");
//        pane.add(label1, 0, 0);
//        Button addPersonBt = new Button("Add person");
//        pane.add(addPersonBt, 0, 1);
//        Button selectPersonBt = new Button("Select a Person");
//        pane.add(selectPersonBt, 0, 2);
//        Button findOutBt = new Button("Find out relationship");
//        pane.add(findOutBt, 0, 3);
//
//        Button exitBt = new Button("Exit");
//        pane.add(exitBt, 0, 4);
    
    
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
        		MainMenu.dc.getDatabaseController().disconnectDB();
            window.close();
        });
        
        // define the close button event on the stage
        window.setOnCloseRequest(e ->{
        	MainMenu.dc.getDatabaseController().disconnectDB();
        window.close();
        
        });

        Scene scene = new Scene(pane, 700, 500);
        scene.getStylesheets().add("GUI.css");
        return scene;

    }
    
    


//    static public void showMessageForAddPerson(boolean isSuccess) {
//        Alert alert = new Alert(Alert.AlertType.WARNING);
//        if (isSuccess) {
//            alert.setTitle("MESSAGES");
//            alert.setHeaderText("SUCCESS!");
//            alert.setContentText("Congratulations! Add person successfully!");
//        } else {
//            alert.setTitle("MESSAGES");
//            alert.setHeaderText("FAIL!");
//            alert.setContentText("Sorry, add person unsuccessfully!");
//        }
//        alert.showAndWait();
//    }

}
