package GUI;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;

import Controller.DriverClass;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import people.Person;
import people.YoungChild;

public class SelectPersonGUI {

	public Scene selectPersonScene() {

		// set up layout

		GridPane GPane = MainMenuGUI.setUpPane();

		Label label = new Label("All members as below, please select one person");
		Button submit = new Button("Submit");
		Button back = new Button("Back");
		VBox vBox = new VBox(10);
		HBox hBox = new HBox();
		hBox.setSpacing(300);
		hBox.getChildren().addAll(back, submit);

		ListView<String> memberList = new ListView<>();

		memberList.getItems().addAll(MainMenuGUI.dc.getMember().keySet());
		memberList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		memberList.getSelectionModel().select(0);

//		 vBox.setPadding(new Insets(20, 20, 20, 20));
//		vBox.prefWidth(100);
		vBox.setMaxWidth(250);
		vBox.setMaxHeight(350);
		vBox.getChildren().addAll(memberList);

		BorderPane pane = MainMenuGUI.setUpBorderPane(label, vBox, hBox, null, null);
		hBox.setAlignment(Pos.CENTER);

		// pane.add(label, 0, 0);
		// pane.add(layout, 0, 1);
		// pane.add(submit, 0, 2);
		// pane.add(cancel, 1, 2);

		// create events

		submit.setOnAction(e -> {
			String personName = memberList.getSelectionModel().getSelectedItem();
			System.out.print(personName);
			Person selectedPerson = MainMenuGUI.dc.getMemberObj(personName);
			// ViewPersonGUI viewP = new ViewPersonGUI(window);
			MainMenuGUI.window.setScene(viewPersonScene(selectedPerson));

		});

		back.setOnAction(e -> {
			MainMenuGUI.window.setScene(MainMenuGUI.startScene());
		});

		Scene scene = new Scene(pane, 700, 500);
		return scene;

	}

	public Scene viewPersonScene(Person selectedPerson) {

		// set up layout

		GridPane GPane = MainMenuGUI.setUpPane();
		Label label = new Label(
				"You have selected" + " " + selectedPerson.getName() + " " + "please select one option below");
		// Label label1 = new Label("please select one option below");
		Button btDisplayP = new Button("Display the profile");
		Button btDisplayR = new Button("Display relations");
		Button btModifyR = new Button("Modify relation");
		Button btDelete = new Button("Delete this person");
		Button btBack = new Button("Back");

		// GPane.add(label, 0, 0);
		// GPane.add(label1, 0, 1);
		GPane.add(btDisplayP, 0, 2);
		GPane.add(btDisplayR, 0, 3);
		GPane.add(btModifyR, 0, 4);
		GPane.add(btDelete, 0, 5);
		GPane.add(btBack, 0, 6);

		BorderPane pane = MainMenuGUI.setUpBorderPane(label, GPane, null, null, null);

		// create event

		btDisplayP.setOnAction(e -> {
			try {
				displayProfileAction(selectedPerson);
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}

		});

		btDisplayR.setOnAction(e -> {
			displayRelationsAction(selectedPerson);

		});

		btModifyR.setOnAction(e -> {
			MainMenuGUI.window.setScene(new ModifyRelationGUI().addRelationScene(selectedPerson));
			if(selectedPerson instanceof YoungChild)
				youngChildModifyRwarning();
		});


		btDelete.setOnAction(e -> {
			deletePersonAction(selectedPerson);
			// MainMenu.window.setScene(selectPersonScene());

		});

		btBack.setOnAction(e -> {
			// SelectPersonGUI selectP = new SelectPersonGUI(window);
			MainMenuGUI.window.setScene(selectPersonScene());
		});

		Scene scene = new Scene(pane, 700, 500);
		scene.getStylesheets().add("GUI.css");
		return scene;

	}

	public void displayProfileAction(Person selectedPerson) throws FileNotFoundException {

		String name = selectedPerson.getName();
		int age = selectedPerson.getAge();
		String status = selectedPerson.getStatus();
		String gender = selectedPerson.getGender();
		String state = selectedPerson.getState();
		String photo = selectedPerson.getPhoto();

		Button btBack = new Button("Back");

		GridPane GPane = MainMenuGUI.setUpPane();

		GPane.add((new Label("Name: ")), 1, 3);
		GPane.add(new Label("Age: "), 1, 4);
		GPane.add(new Label("Status: "), 1, 5);
		GPane.add(new Label("Gender: "), 1, 6);
		GPane.add(new Label("State: "), 1, 7);
		GPane.add(btBack, 7, 10);
		// btBack action, put it in the right position after
		btBack.setOnAction(e -> {
			MainMenuGUI.window.setScene(viewPersonScene(selectedPerson));
		});

		// pane.add(new Label(photo), 2, 1);

		ImageView imageView = new ImageView();

		if (photo.equals("")) {

			Image defaultImage = new Image(new FileInputStream("image/default.png"));
			imageView.setImage(defaultImage);
		} else {

			Image image = new Image(new FileInputStream("image/" + photo));
			imageView.setImage(image);

		}
		imageView.setFitHeight(100);
		imageView.setFitWidth(100);
		GPane.add(imageView, 3, 0);

		GPane.add((new Label(name)), 4, 3);
		GPane.add(new Label(Integer.toString(age)), 4, 4);
		GPane.add(new Label(status), 4, 5);
		GPane.add(new Label(gender), 4, 6);
		GPane.add(new Label(state), 4, 7);

		
		BorderPane pane = MainMenuGUI.setUpBorderPane(imageView, GPane, btBack, null, null);
		pane.setAlignment(btBack, Pos.CENTER);
//		btBack.setLayoutX(50);
//		btBack.setLayoutY(30);
		Scene scene = new Scene(pane, 700, 500);
		scene.getStylesheets().add("GUI.css");
		MainMenuGUI.window.setScene(scene);
		MainMenuGUI.window.show();

	}

	public GridPane displayRelationsAction(Person person) {

		GridPane GPane = MainMenuGUI.setUpPane();
		Label label;

		if (!person.getRelationship().isEmpty()) {
			label = new Label("People who have relationship with" + " " + person.getName() + " " + "is/are:");
			// pane.add(label, 0, 0);

			int i = 2;
			for (String type : person.getRelationship().keySet()) {

				ArrayList<Person> relatedPerson = person.getRelationship().get(type);

				for (Person r : relatedPerson) {
					GPane.add(new Label(r.getName()), 0, i);
					GPane.add(new Label(type), 3, i);
					i++;
					// System.out.println(r.getName() + type + i);
				}

			}
		} else {
			label = new Label(person.getName() + " " + "does not have relationship with anyone.");
			// GPane.add(new Label(person.getName() + " " + "does not have relationship with
			// anyone."), 0, 0);
			// System.out.print("no relation");
		}

		Button btBack = new Button("Back");

		// pane.add(btBack, 2, 12);
		btBack.setOnAction(e -> {
			MainMenuGUI.window.setScene(viewPersonScene(person));
		});

		BorderPane pane = MainMenuGUI.setUpBorderPane(label, GPane, btBack, null, null);
		pane.setAlignment(btBack, Pos.CENTER);

		Scene scene = new Scene(pane, 700, 500);
		scene.getStylesheets().add("GUI.css");
		MainMenuGUI.window.setScene(scene);
		return GPane;
	}

	public void deletePersonAction(Person selectedPerson) {

		if (showDeletePersonMessage()) {
			if (selectedPerson.getRelationship().containsKey("child")) {
				if (showDeleteChildMessage()) {
					MainMenuGUI.dc.deletePerson(selectedPerson);
					MainMenuGUI.window.setScene(selectPersonScene());
					showMessageForDeletePerson();
				}
			} else {
				MainMenuGUI.dc.deletePerson(selectedPerson);
				MainMenuGUI.window.setScene(selectPersonScene());
				showMessageForDeletePerson();
			}
		}
	}

	public boolean showDeletePersonMessage() {

		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("MESSAGES");
		alert.setHeaderText("WARNING!");
		alert.setContentText(
				"Delete this person will also delete all the relationship of the person, are you sure you want to delete this person?");

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {
			return true;
		} else if (result.get() == ButtonType.CANCEL) {
			alert.close();
		}
		return false;
	}

	public boolean showDeleteChildMessage() {

		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("MESSAGES");
		alert.setHeaderText("WARNING! Children issue!");
		alert.setContentText(
				"This person has children, if delete this person, the children also will be deleted, are you sure you want to delete this person?");
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {
			return true;
		} else if (result.get() == ButtonType.CANCEL) {
			alert.close();
		}
		return false;
	}

	public void showMessageForDeletePerson() {
		Alert alert = new Alert(Alert.AlertType.WARNING);
		alert.setTitle("MESSAGES");
		alert.setHeaderText("SUCCESS!");
		alert.setContentText("Congratulations! Delete person successfully!");
		alert.show();
	}
	
	public void youngChildModifyRwarning() {
		Alert alert = new Alert(Alert.AlertType.WARNING);

		alert.setTitle("MESSAGES");
		alert.setHeaderText("WARNING!");
		alert.setContentText("The relation of young child cannot be modified !");

		alert.show();
	}

}
