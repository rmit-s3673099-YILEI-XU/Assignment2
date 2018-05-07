package GUI;

import Controller.DriverClass;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Border;
import javafx.scene.layout.GridPane;
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

    static public Scene startScene() {

        // set up layout
        Label label1 = new Label("Welcome to Mininet, Please choose one from the menu");

        GridPane pane = setUpPane();
        pane.add(label1, 0, 0);
        Button addPersonBt = new Button("Add person");
        pane.add(addPersonBt, 0, 1);
        Button selectPersonBt = new Button("Select a Person");
        pane.add(selectPersonBt, 0, 2);
        Button findOutBt = new Button("Find out relationship");
        pane.add(findOutBt, 0, 3);
        Button defineReBt = new Button("Define relationship");
        pane.add(defineReBt, 0, 4);
        Button exitBt = new Button("Exit");
        pane.add(exitBt, 0, 5);

        // create events

        addPersonBt.setOnAction(event -> {
//            AddPersonGUI addP = new AddPersonGUI();
            window.setScene(new AddPersonGUI().addPersonScene());
        });

        selectPersonBt.setOnAction(event -> {
//            SelectPersonGUI selectP = new SelectPersonGUI();
            window.setScene(new SelectPersonGUI().selectPersonScene());
        });

        findOutBt.setOnAction(e -> {
//            window.setScene(findOutScene());
        });

        exitBt.setOnAction(event -> {
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
