package GUI;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Optional;
import Controller.DriverClass;
import Exceptions.NoParentsException;
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

/**
 * This class is the select person user interface
 * @author CIFANG ZHANG
 *
 */
public class SelectPersonGUI {
	
/**
 * This method is the scene of select person
 * @return Scene
 */
	public Scene selectPersonScene() {

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
		Collections.sort(memberList.getItems(),String.CASE_INSENSITIVE_ORDER);
		memberList.getSelectionModel().select(0);
		if(memberList.getItems().size()==0)
			submit.setDisable(true);

		vBox.setMaxWidth(250);
		vBox.setMaxHeight(350);
		vBox.getChildren().addAll(memberList);

		BorderPane pane = MainMenuGUI.setUpBorderPane(label, vBox, hBox, null, null);
		hBox.setAlignment(Pos.CENTER);

		submit.setOnAction(e -> {
			String personName = memberList.getSelectionModel().getSelectedItem();
			System.out.println(personName +" is selected.");
			Person selectedPerson = MainMenuGUI.dc.getMemberObj(personName);
			MainMenuGUI.window.setScene(viewPersonScene(selectedPerson));
		});

		back.setOnAction(e -> {
			MainMenuGUI.window.setScene(MainMenuGUI.startScene());
		});
		
		Scene scene = new Scene(pane, 700, 500);
		scene.getStylesheets().add("GUI2.css");
		System.out.println("Select person");
		return scene;
	}
	
/**
 * This method is the scene of view person menu
 * @param selectedPerson the person has been selected
 * @return Scene
 */
	public Scene viewPersonScene(Person selectedPerson) {

		Label label = new Label(
				"You have selected" + " " + selectedPerson.getName() + ", " + "please select one option below");
		Button btDisplayP = new Button("Display the profile");
		Button btModifyP = new Button("Modify the profile");
		Button btDisplayR = new Button("Display relations");
		Button btModifyR = new Button("Modify relation");
		Button btDelete = new Button("Delete this person");
		Button btBack = new Button("Back");
		
		VBox vBox = new VBox();
		vBox.getChildren().addAll(btDisplayP, btModifyP, btDisplayR, btModifyR, btDelete, btBack);

		BorderPane pane = MainMenuGUI.setUpBorderPane(label, vBox, null, null, null);
		vBox.setAlignment(Pos.CENTER);
		vBox.setSpacing(5.5);
		
		btDisplayP.setOnAction(e -> {
			try {
				displayProfileAction(selectedPerson);
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
		});
		btModifyP.setOnAction(e->{
			MainMenuGUI.window.setScene(new ModifyProfileGUI().modifyProfileScene(selectedPerson));
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
			try {
			deletePersonAction(selectedPerson);
			}catch (NoParentsException e1){
				e1.noParentsWarning();
			}
		});
		btBack.setOnAction(e -> {
			MainMenuGUI.window.setScene(selectPersonScene());
		});
		
		Scene scene = new Scene(pane, 700, 500);
		scene.getStylesheets().add("GUI.css");
		return scene;
	}
	
/**
 * This method is for display the profile of the person
 * @param selectedPerson the person has been selected
 * @throws FileNotFoundException if the file cannot be found
 */
	private void displayProfileAction(Person selectedPerson) throws FileNotFoundException {

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

		btBack.setOnAction(e -> {
			MainMenuGUI.window.setScene(viewPersonScene(selectedPerson));
		});

		ImageView imageView = new ImageView();

		if (photo.equals("")) {

			Image defaultImage = new Image(new FileInputStream("image/default.jpeg"));
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
		Scene scene = new Scene(pane, 700, 500);
		scene.getStylesheets().add("GUI2.css");
		System.out.println("Display profile for "+selectedPerson.getName());
		MainMenuGUI.window.setScene(scene);
		MainMenuGUI.window.show();
	}
	
/**
 * This method is for display the relations of the person
 * @param person the selected person
 * @return GridPane
 */
	private GridPane displayRelationsAction(Person person) {

		GridPane GPane = MainMenuGUI.setUpPane();
		Label label;

		if (!person.getRelationship().isEmpty()) {
			label = new Label("People who have relationship with" + " " + person.getName() + " " + "is/are:");

			int i = 2;
			   for (String type : person.getRelationship().keySet()) {
			    
			    ArrayList<String> tempPersonName = new ArrayList<String>();
			    for(Person relatedPerson:person.getRelationship().get(type)) {
			     tempPersonName.add(relatedPerson.getName());
			    } 
			    Collections.sort(tempPersonName,String.CASE_INSENSITIVE_ORDER);

			    for (String relatedName : tempPersonName) {
			     GPane.add(new Label(relatedName), 0, i);
			     GPane.add(new Label(type), 3, i);
			     i++;
			    }
			   }
		} else {
			label = new Label(person.getName() + " " + "does not have relationship with anyone.");
		}

		Button btBack = new Button("Back");

		btBack.setOnAction(e -> {
			MainMenuGUI.window.setScene(viewPersonScene(person));
		});

		BorderPane pane = MainMenuGUI.setUpBorderPane(label, GPane, btBack, null, null);
		pane.setAlignment(btBack, Pos.CENTER);

		Scene scene = new Scene(pane, 700, 500);
		scene.getStylesheets().add("GUI2.css");
		System.out.println("Display relations for "+person.getName());
		MainMenuGUI.window.setScene(scene);
		return GPane;
	}
	
/**
 * This method is for delete the person
 * @param selectedPerson the selected person
 * @throws NoParentsException 
 */
	private void deletePersonAction(Person selectedPerson) throws NoParentsException {

		if (showDeletePersonMessage()) {
			if (selectedPerson.getRelationship().containsKey("child") ) {
					throw new NoParentsException(selectedPerson);
			} else {
				System.out.println("Delete "+selectedPerson.getName());
				MainMenuGUI.dc.deletePerson(selectedPerson);
				MainMenuGUI.window.setScene(selectPersonScene());
				showMessageForDeletePerson();		
			}
		}
	}
	
/**
 * This method shows the message when delete the person will also delete the relation
 * @return boolean, true if click OK, false if cancel or close the window
 */
	private boolean showDeletePersonMessage() {

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
	
/**
 * This method is for show the message when delete the person has a child
 * @return boolean, true if click OK, false if cancel or close the window
 */
	private boolean showDeleteChildMessage() {

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
/**
 * This method is showing the message when delete person successful
 */
	private void showMessageForDeletePerson() {
		Alert alert = new Alert(Alert.AlertType.WARNING);
		alert.setTitle("MESSAGES");
		alert.setHeaderText("SUCCESS!");
		alert.setContentText("Congratulations! Delete person successfully!");
		alert.show();
	}
	/**
	 * This method is show the warning when modify the young child relation
	 */
	private void youngChildModifyRwarning() {
		Alert alert = new Alert(Alert.AlertType.WARNING);

		alert.setTitle("MESSAGES");
		alert.setHeaderText("WARNING!");
		alert.setContentText("The relation of young child cannot be modified !");

		alert.show();
	}
}
