package GUI;

import java.io.File;

import Exceptions.AlreadyExistPersonException;
import Exceptions.NoSuchAgeException;
import Exceptions.NotFillAllNecessInfo;
import Exceptions.NotNumberFormatException;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import people.*;
/**
 * This class is about modify profile of the person
 * @author CIFANG ZHANG
 *
 */
public class ModifyProfileGUI {
	
	AddPersonGUI addPersonGUI = new AddPersonGUI();
/**
 * This method is set up the scene of modify person's profile	
 * @param selectedPerson the person has been selected
 * @return Scene
 */
	public Scene modifyProfileScene(Person selectedPerson) {
		
		GridPane pane = setUpModifyPane();

		Label personName = new Label();
		pane.add(personName, 1, 0);
		TextField personAge = new TextField();
		pane.add(personAge, 1, 1);
		HBox root = new HBox();
		ToggleGroup group = new ToggleGroup();
		RadioButton female = new RadioButton("Female");
		female.setToggleGroup(group);
		female.setSelected(true);
		female.setUserData("F");
		RadioButton male = new RadioButton("Male");
		male.setToggleGroup(group);
		male.setUserData("M");
		root.getChildren().add(female);
		root.getChildren().add(male);
		pane.add(root, 1, 2);		
		TextField personStatus = new TextField();
		pane.add(personStatus, 1, 3);
		ComboBox<String> comboBox = new ComboBox<String>();
		String[] allState = { "ACT", "NSW", "NT", "QLD", "SA", "TAS", "VIC", "WA" };
		for (String s : allState) {
			comboBox.getItems().add(s);
		}
		pane.add(comboBox, 1, 4);
		Label personPhoto = new Label();
		personPhoto.setMaxWidth(200);
		pane.add(personPhoto, 1, 5);
		Button upload = new Button("Upload");
		pane.add(upload, 2, 5);
		Button btBack = new Button("Back");
		pane.add(btBack, 0, 9);
		Button btSubmit = new Button("Submit");
		pane.add(btSubmit, 2, 9);
		
		setOriginalData(selectedPerson, personName, personAge, female,male, personStatus, comboBox, personPhoto); 
				
		upload.setOnAction(e -> {		
			setUploadAction(personPhoto);
		});		
		btSubmit.setOnAction(e->{	
			setSubmitAction(selectedPerson, personAge, group, personStatus, comboBox, personPhoto);
		});				
		btBack.setOnAction(e -> {
			MainMenuGUI.window.setScene(new SelectPersonGUI().viewPersonScene(selectedPerson));// check where should it go back
		});	
		Scene scene = new Scene(pane, 700, 500);
		scene.getStylesheets().add("GUI2.css");
		return scene;		
	}
	
	/**
	 * This method is for setting up the fixed part of the pane
	 * @return GridPane
	 */
		public GridPane setUpModifyPane() {
			
			
			GridPane pane = MainMenuGUI.setUpPane();

			pane.add(new Label("Name*:"), 0, 0);
			pane.add(new Label("Age*"), 0, 1);
			pane.add(new Label("Gender*"), 0, 2);
			pane.add(new Label("Status"), 0, 3);
			pane.add(new Label("State*"), 0, 4);
			pane.add(new Label("Photo"), 0, 5);

			return pane;
			
		}
		/**
		 * This method is for set up the action of the upload button
		 * @param personPhoto the URL of the image
		 */
		public void setUploadAction(Label personPhoto) {
			
			File photoFile;
			photoFile = addPersonGUI.uploadPhoto();
			if(photoFile!=null)
				personPhoto.setText(photoFile.getAbsolutePath());
			
		}
		/**
		 * This method is for set up the submit button action
		 * @param selectedPerson the person has been selected
		 * @param personAge the input age
		 * @param personGender the selected gender
		 * @param personStatus the input status
		 * @param comboBox the select state comboBox
		 * @param personPhoto the url of the photo
		 */
		public void setSubmitAction(Person selectedPerson, TextField personAge, ToggleGroup personGender, TextField personStatus, ComboBox comboBox, Label personPhoto) {
			
			String photo = personPhoto.getText().trim();
			String status = personStatus.getText().trim();
			String gender = (String) personGender.getSelectedToggle().getUserData();
			String ageText = personAge.getText().trim();
			String state = (String) comboBox.getValue();
	
			try {
				ModifyProfileAction(selectedPerson, photo, status, gender, ageText, state);
			} catch (NotFillAllNecessInfo exception) {
				exception.lackNecessInforWarning();
			} catch (NotNumberFormatException exception) {
				exception.notNumberFormatWarning();
			} catch (NoSuchAgeException exception) {
				exception.noSuchAgeWarning();
			}
			
		}
		/**
		 * This method is for set the original data of the person to the scene
		 * @param selectedPerson the person has been selected
		 * @param personName the name of the person
		 * @param personAge the age of the person
		 * @param female the gender selection
		 * @param male the gender selection
		 * @param personStatus the status of the person
		 * @param comboBox the state selection 
		 * @param personPhoto the URL of the person's photo
		 */
		public void setOriginalData(Person selectedPerson, Label personName, TextField personAge, RadioButton female, RadioButton male, TextField personStatus, ComboBox comboBox, Label personPhoto) {
			
			personName.setText(selectedPerson.getName());
			personAge.setText(Integer.toString(selectedPerson.getAge()));
			if(selectedPerson.getGender().equals("F"))
				female.setSelected(true);
			else
				male.setSelected(true);
			personStatus.setText(selectedPerson.getStatus());
			comboBox.setValue(selectedPerson.getState());
			personPhoto.setText(selectedPerson.getPhoto());
			
		}
		/**
		 * This method is for modify the profile action
		 * @param currentPerson the person has been selected
		 * @param photo the update photo of the person	
		 * @param status the update status of the person
		 * @param gender the update gender of the person
		 * @param ageText the update age of the person
		 * @param state the selected state 
		 * @throws NotFillAllNecessInfo if cannot fill up all the necessary information
		 * @throws NotNumberFormatException is the number is invalid
		 * @throws NoSuchAgeException if the age is not human age
		 */
		public void ModifyProfileAction(Person currentPerson, String photo, String status, String gender, String ageText, String state) throws NotFillAllNecessInfo, NotNumberFormatException, NoSuchAgeException
		{
			if (ageText.trim().equals("")) {
				throw new NotFillAllNecessInfo();
			} else if (!(ageText.matches("\\d*") || ageText.matches("-\\d*"))) {
				throw new NotNumberFormatException();
			} else {
				int age = Integer.parseInt(ageText);
				if (age < 0 || age > 150)
					throw new NoSuchAgeException();
				else {
					if(currentPerson instanceof Adult) {
						if (age<=16)
						{
							showAgeChangeWarning(currentPerson);
						}else {
							updateProfileInfo(currentPerson,photo,status,gender,age,state);
						}
					}
					else if(currentPerson instanceof Child)
					{
						if (age>16||age<3)
						{
							showAgeChangeWarning(currentPerson);
						}else {
							updateProfileInfo(currentPerson,photo,status,gender,age,state);
						}
						
					}
					else {
						if(age>2)
							showAgeChangeWarning(currentPerson);
						else
							updateProfileInfo(currentPerson,photo,status,gender,age,state);
					}
				}
			}

		}
		
		/**
		 * This method is to update the profile information
		 * @param currentPerson the person has been selected
		 * @param photo the update photo
		 * @param status the update status
		 * @param gender the update gender
		 * @param age the update age
		 * @param state the update state
		 */
		private void updateProfileInfo(Person currentPerson,String photo, String status, String gender, int age, String state)
		{
			currentPerson.setAge(age);
			currentPerson.setGender(gender);
			currentPerson.setPhoto(photo);
			currentPerson.setState(state);
			currentPerson.setStatus(status);
			currentPerson.setPhoto(addPersonGUI.saveImage(currentPerson.getName(), photo));
			MainMenuGUI.dc.getDatabaseController().modifyDatabase(currentPerson, "modifyProfile");
			showSuccessMessage();
			MainMenuGUI.window.setScene(new SelectPersonGUI().viewPersonScene(currentPerson));
		}
		
	/**
	 * 	This method is to show the message when the user change the age out of range	
	 * @param currentPerson the selected person
	 */		
		private void showAgeChangeWarning(Person currentPerson)
		{
			Alert alert = new Alert(Alert.AlertType.WARNING);
			alert.setTitle("MESSAGES");
			alert.setHeaderText("WARNING!");
			if (currentPerson instanceof Adult) {
				alert.setContentText(currentPerson.getName()+" is Adult. Vaild age range is 17-150.");
			} else if(currentPerson instanceof Child){
				
				alert.setContentText(currentPerson.getName()+" is Child. Vaild age range is 3-16.");
			}else {
				alert.setContentText(currentPerson.getName()+" is Child. Vaild age range is 0-2.");
			}
			alert.show();
		}
	/**
	 * This method shows the message of modify profile successful	
	 */
		private void showSuccessMessage()
		{
			Alert alert = new Alert(Alert.AlertType.WARNING);
			alert.setTitle("MESSAGES");
			alert.setHeaderText("SUCCESS!");
			
			alert.setContentText("Modify profile information successful!");
			
			alert.showAndWait();
		}
				
		

}
