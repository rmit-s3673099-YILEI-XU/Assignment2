import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.RadioButton;
import javafx.collections.*;

import java.io.IOException;

import javafx.*;

public class MiniNetInterface {

	// fix the size of the window!!!!

	Stage window;
	DriverClass dc;

	public MiniNetInterface(Stage window) {
		this.window = window;
		dc = new DriverClass();
		
		try {
			dc.initialData();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Scene startScene() {

		Label label1 = new Label("Welcome to Mininet, Please choose one from the menu");

		GridPane pane = new GridPane();
		pane.setAlignment(Pos.CENTER);
		pane.setPadding(new Insets(5, 5, 5, 5));
		pane.setHgap(5.5);
		pane.setVgap(5.5);

		pane.add(label1, 0, 0);
		Button addPersonBt = new Button("Add person");
		pane.add(addPersonBt, 0, 1);
		Button selectPersonBt = new Button("Select Person");
		pane.add(selectPersonBt, 0, 2);
		Button displayAllBt = new Button("Display All members");
		pane.add(displayAllBt, 0, 3);
		Button defineReBt = new Button("others");
		pane.add(defineReBt, 0, 4);
		Button findOutReBt = new Button("others");
		pane.add(findOutReBt, 0, 5);
		Button exitBt = new Button("Exit");
		pane.add(exitBt, 0, 6);

		// create events

		addPersonBt.setOnAction(event -> window.setScene(addPersonScene()));

		selectPersonBt.setOnAction(event -> {
			window.setScene(displayAllScene());
		});

		Scene scene = new Scene(pane, 700, 500);
		return scene;

	}

	public Scene addPersonScene() {

		GridPane pane = new GridPane();
		pane.setAlignment(Pos.CENTER);
		pane.setPadding(new Insets(5, 5, 5, 5));
		pane.setHgap(5.5);
		pane.setVgap(5.5);

		pane.add(new Label("Name:"), 0, 0);
		pane.add(new TextField(), 1, 0);
		pane.add(new Label("Age"), 0, 1);
		pane.add(new TextField(), 1, 1);
		pane.add(new Label("Gender"), 0, 2);

		HBox root = new HBox();
		ToggleGroup group = new ToggleGroup();
		RadioButton female = new RadioButton("Female");
		female.setToggleGroup(group);
		female.setSelected(true);
		RadioButton male = new RadioButton("Male");
		male.setToggleGroup(group);
		root.getChildren().add(female);
		root.getChildren().add(male);
		pane.add(root, 1, 2);
		pane.add(new Label("Status"), 0, 3);
		pane.add(new TextField(), 1, 3);
		pane.add(new Label("Photo"), 0, 4);
		pane.add(new TextField(), 1, 4);

		Button btAdd = new Button("Add");
		pane.add(btAdd, 0, 5);
		Button btCancel = new Button("Cancel");
		btCancel.setOnAction(e -> {
			window.setScene(startScene());
		});
		pane.add(btCancel, 2, 5);

		Scene scene = new Scene(pane, 700, 500);
		return scene;
	}

	public Scene displayAllScene() {

		// Stage displayStage = new Stage();
		// displayStage.setTitle("Display all members");
		GridPane pane = new GridPane();
		pane.setAlignment(Pos.CENTER);
		pane.setPadding(new Insets(5, 5, 5, 5));// 这是啥有没有没区别
		pane.setHgap(5.5);
		pane.setVgap(5.5);

		Label label = new Label("All members as below, please select one person");
		Button submit = new Button("Submit");
		Button cancel = new Button("Cancel");
		VBox layout = new VBox(10);

		ListView<String> memberList = new ListView<>();
		memberList.getItems().addAll(dc.getMember().keySet());
//		memberList.getItems().addAll("111", "222", "333", "111", "222", "333", "111", "222", "333", "111", "222", "333",
//				"111", "222", "333", "111", "222", "333", "111", "222", "333", "end");/* get the member list */
		memberList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

		layout.setPadding(new Insets(20, 20, 20, 20));// 没区别啊
		layout.getChildren().addAll(memberList, submit);

		pane.add(label, 0, 0);
		pane.add(layout, 0, 1);
		pane.add(submit, 0, 2);
		pane.add(cancel, 1, 2);

		submit.setOnAction(e -> {
			String selectPerson = memberList.getSelectionModel().getSelectedItem();
			System.out.print(selectPerson);
			// get member object, call display profile
		});

		cancel.setOnAction(e -> {
			window.setScene(startScene());
		});

		Scene scene = new Scene(pane, 700, 500);
		return scene;

	}

}
