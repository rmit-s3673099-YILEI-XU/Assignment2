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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import people.Person;

public class SelectPersonGUI {

	 public Scene selectPersonScene() {

	        // set up layout

	        GridPane pane = MainMenu.setUpPane();

	        Label label = new Label("All members as below, please select one person");
	        Button submit = new Button("Submit");
	        Button cancel = new Button("Cancel");
	        VBox layout = new VBox(10);

	        ListView<String> memberList = new ListView<>();
//	        System.out.println(getDriver());
	        memberList.getItems().addAll(MainMenu.dc.getMember().keySet());
	        memberList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

	        layout.setPadding(new Insets(20, 20, 20, 20));
	        layout.getChildren().addAll(memberList);

	        pane.add(label, 0, 0);
	        pane.add(layout, 0, 1);
	        pane.add(submit, 0, 2);
	        pane.add(cancel, 1, 2);
	        // create events

	        submit.setOnAction(e -> {
	            String personName = memberList.getSelectionModel().getSelectedItem();
	            System.out.print(personName);
	            Person selectedPerson = MainMenu.dc.getMemberObj(personName);
	            //ViewPersonGUI viewP = new ViewPersonGUI(window);
	            MainMenu.window.setScene(viewPersonScene(selectedPerson));

	        });

	        cancel.setOnAction(e -> {
	            MainMenu.window.setScene(MainMenu.startScene());
	        });

	        Scene scene = new Scene(pane, 700, 500);
	        return scene;

	    }
	 
	 
	 public Scene viewPersonScene(Person selectedPerson) {

//	         set up layout

	    		GridPane pane = MainMenu.setUpPane();
	        Label label = new Label("Menu for view the person, please select one");
	        Button btDisplayP = new Button("Display the profile");
	        Button btDisplayR = new Button("Display relations");
	        Button btAddR = new Button("Add relation");
	        Button btDelete = new Button("Delete this person");
	        Button btBack = new Button("Back");

	        pane.add(label, 0, 0);
	        pane.add(btDisplayP, 0, 1);
	        pane.add(btDisplayR, 0, 2);
	        pane.add(btAddR, 0, 3);
	        pane.add(btDelete, 0, 4);
	        pane.add(btBack, 0, 5);

	        // create event

	        btDisplayP.setOnAction(e -> {
	            try {
	                displayProfileAction(selectedPerson);
	            } catch (FileNotFoundException e1) {
	                e1.printStackTrace();
	            }

	        });

	        btDisplayR.setOnAction(e->{
	        	displayRelationsAction(selectedPerson);

	        });

	        btAddR.setOnAction(e -> {
	        	 MainMenu.window.setScene(new AddRelationGUI().addRelationScene(selectedPerson));
	        });

	        btDelete.setOnAction(e -> {
	        	deletePersonAction(selectedPerson);
//	        	MainMenu.window.setScene(selectPersonScene());

	        });

	        btBack.setOnAction(e -> {
	        		//SelectPersonGUI selectP = new SelectPersonGUI(window);
	            MainMenu.window.setScene(selectPersonScene());
	        });

	        Scene scene = new Scene(pane, 700, 500);
	        return scene;

	    }

	 public void displayProfileAction(Person selectedPerson) throws FileNotFoundException {

//    Person currentPerson = dc.getMemberObj(theName);
		String name =selectedPerson.getName();
    int age = selectedPerson.getAge();
    String status = selectedPerson.getStatus();
    String gender = selectedPerson.getGender();
    String state = selectedPerson.getState();
    String photo = selectedPerson.getPhoto();

    Button btBack = new Button("Back");

    GridPane pane = new GridPane();
    pane.setAlignment(Pos.CENTER);
    pane.setPadding(new Insets(5, 5, 5, 5));
    pane.setHgap(5.5);
    pane.setVgap(5.5);

    pane.add((new Label("Name: ")), 1, 3);
    pane.add(new Label("Age: "), 1, 4);
    pane.add(new Label("Status: "), 1, 5);
    pane.add(new Label("Gender: "), 1, 6);
    pane.add(new Label("State: "), 1, 7);
    pane.add(btBack, 7, 10);
    // btBack action, put it in the right position after
    btBack.setOnAction(e -> {
    	MainMenu.window.setScene(viewPersonScene(selectedPerson));
    });

    // pane.add(new Label(photo), 2, 1);

    ImageView imageView = new ImageView();

    if( photo.equals("")) {

        Image defaultImage = new Image(new FileInputStream("image/default.png"));
        imageView.setImage(defaultImage);
    }else {

    		Image image = new Image(new FileInputStream("image/"+ photo));
        imageView.setImage(image);

    }
    imageView.setFitHeight(100);
    imageView.setFitWidth(100);
    pane.add(imageView, 3, 0);

    pane.add((new Label(name)), 4, 3);
    pane.add(new Label(Integer.toString(age)), 4, 4);
    pane.add(new Label(status), 4, 5);
    pane.add(new Label(gender), 4, 6);
    pane.add(new Label(state), 4, 7);

    Scene scene = new Scene(pane, 700, 500);
    MainMenu.window.setScene(scene);
    MainMenu.window.show();

}
	 
	 public void displayRelationsAction(Person person)
	    {
	    		GridPane pane = MainMenu.setUpPane();

	        if(!person.getRelationship().isEmpty()) {
	        pane.add(new Label("People who have relationship with" + " " + person.getName() + " " + "is/are:"), 0, 0);

	        int i = 2;
	        for(String type: person.getRelationship().keySet() ) {

	        ArrayList<Person> relatedPerson = person.getRelationship().get(type);

	        for(Person r: relatedPerson) {
	        	pane.add(new Label(r.getName()), 0, i);
	        	pane.add(new Label(type), 4, i);
	        	i++;
	        	//System.out.println(r.getName() + type + i);
	        }

	        }
	        }else {
	        	pane.add(new Label(person.getName() + " " + "does not have relationship with anyone."), 0, 0);
	        	System.out.print("no relation");
	        }

	        Button btBack = new Button("Back");
	        pane.add(btBack, 2, 12);
	        btBack.setOnAction(e ->{
	        	MainMenu.window.setScene(viewPersonScene(person));
	        });
	        Scene scene = new Scene(pane, 700, 500);
	        MainMenu.window.setScene(scene);
	    }


	 
//	 public void findOutPorC(Person selectedPerson) {
//		 /* find out the parent or child of the person*/
//	 }
//
//	 public boolean showDeletePersonMessage(boolean temp) {
//
//			Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
//			alert.setTitle("MESSAGES");
//
//			if (temp) {
//				alert.setHeaderText("WARNING!");
//				alert.setContentText(
//						"Delete this person will also delete all the relationship of the person, are you sure you want to delete this person?");
//			} else {
//				alert.setHeaderText("DELETE PERSON SUCCESSFUL!");
//			}
//
//			Optional<ButtonType> result = alert.showAndWait();
//			if(result.get() == ButtonType.OK) {
//				return true;
//			}else if(result.get() == ButtonType.CANCEL) {
//				alert.close();
//			}
//			return false;
//		}
//
	 public void deletePersonAction(Person selectedPerson) {

		if (showDeletePersonMessage()) {
			if (showDeleteChildMessage()) {
				MainMenu.dc.deletePerson(selectedPerson);
				MainMenu.window.setScene(selectPersonScene());
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
		if(result.get() == ButtonType.OK) {
			return true;
		}else if(result.get() == ButtonType.CANCEL) {
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
		if(result.get() == ButtonType.OK) {
			return true;
		}else if(result.get() == ButtonType.CANCEL) {
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
	

}
