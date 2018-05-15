package GUI;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import Controller.DriverClass;
import Exceptions.AlreadyExistPersonException;
import Exceptions.NoAvailableException;
import Exceptions.NoSuchAgeException;
import Exceptions.NotFillAllNecessInfo;
import Exceptions.NotNumberFormatException;
import Exceptions.NotToBeCoupledException;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import people.Adult;
import people.Child;
import people.Person;
/**
 * This class is the add person user interface
 * @author CIFANG ZHANG
 *
 */
public class AddPersonGUI {
/**
 * This method is set up the add person scene
 * @return Scene
 */
	public Scene addPersonScene() {

		GridPane pane = MainMenuGUI.setUpPane();

		pane.add(new Label("Name*:"), 0, 0);
		TextField personName = new TextField();
		pane.add(personName, 1, 0);
		pane.add(new Label("Age*"), 0, 1);
		TextField personAge = new TextField();
		pane.add(personAge, 1, 1);		
		pane.add(new Label("Gender*"), 0, 2);
		ToggleGroup group = new ToggleGroup();		
		pane.add(setGenderButton(group), 1, 2);		
		pane.add(new Label("Status"), 0, 3);
		TextField personStatus = new TextField();
		pane.add(personStatus, 1, 3);
		pane.add(new Label("State*"), 0, 4);
		ComboBox<String> comboBox = new ComboBox<String>();
		comboBox.setValue("Select State");
		String[] allState = { "ACT", "NSW", "NT", "QLD", "SA", "TAS", "VIC", "WA" };
		for (String s : allState) {
			comboBox.getItems().add(s);
		}
		pane.add(comboBox, 1, 4);
		pane.add(new Label("Photo"), 0, 5);
		Label personPhoto = new Label();
		personPhoto.setMaxWidth(160);
		pane.add(personPhoto, 1, 5);
		Button upload = new Button("Upload");
		pane.add(upload, 2, 5);

		Button btBack = new Button("Back");
		pane.add(btBack, 0, 9);
		Button btAdd = new Button("Add");
		pane.add(btAdd, 2, 9);

		btAdd.setOnAction(e -> {			
			addButtonAction(personName, personPhoto, personStatus, group, personAge, comboBox);
		});
		btBack.setOnAction(e -> {
			MainMenuGUI.window.setScene(MainMenuGUI.startScene());
		});
		upload.setOnAction(e -> {
			File photoFile;
			photoFile = uploadPhoto();
			if(photoFile!=null)
				personPhoto.setText(photoFile.getAbsolutePath());
		});
		Scene scene = new Scene(pane, 700, 500);
		scene.getStylesheets().add("GUI2.css");
		return scene;
	}
/**
 * This method is set up the select gender radioButton
 * @param group is the radioButton of female/male
 * @return HBox which is the container of the two buttons
 */
	public HBox setGenderButton(ToggleGroup group) {
		
		HBox root = new HBox();

		RadioButton female = new RadioButton("Female");
		female.setToggleGroup(group);
		female.setSelected(true);
		female.setUserData("F");
		RadioButton male = new RadioButton("Male");
		male.setToggleGroup(group);
		male.setUserData("M");
		root.getChildren().add(female);
		root.getChildren().add(male);
		return root;
	}
/**
 * This method is set on the action of the add button
 * @param personName the input name
 * @param personPhoto the upload photo
 * @param personStatus the input status
 * @param personGender the selected gender
 * @param personAge the input age
 * @param personState the selected state
 */
	public void addButtonAction(
			
		TextField personName, Label personPhoto, TextField personStatus, ToggleGroup personGender, TextField personAge, ComboBox personState) {
		
		String name = personName.getText().trim();
		String photo = personPhoto.getText().trim();
		String status = personStatus.getText().trim();
		String gender = (String) personGender.getSelectedToggle().getUserData();
		String ageText = personAge.getText().trim();
		String state = (String) personState.getValue();

		try {
			addPersonAction(name, photo, status, gender, ageText, state);
		} catch (NotFillAllNecessInfo exception) {
			exception.lackNecessInforWarning();
		} catch (NotNumberFormatException exception) {
			exception.notNumberFormatWarning();
		} catch (NoSuchAgeException exception) {
			exception.noSuchAgeWarning();
		} catch (AlreadyExistPersonException exception) {
			exception.alreadyExistPersonWarning();
		}
		
	}
/**
 * This method is the function of upload photo 
 * @return the file has been uploaded
 */
	public File uploadPhoto() {

		FileChooser fileChooser = new FileChooser();
		FileChooser.ExtensionFilter photoFilter = new FileChooser.ExtensionFilter("PNG files, JPG files, JPEG files",
				"*.PNG", "*.JPG", "*JPEG");
		fileChooser.getExtensionFilters().addAll(photoFilter);

		File file = fileChooser.showOpenDialog(null);

		return file;

	}
/**
 * This method is saving the image to the file
 * @param name the image name
 * @param path the image path
 * @return the type of the image
 */
	public String saveImage(String name, String path) {

		String fileType;

		if (!path.equals("")) {
			File file = new File(path);
			fileType = file.getName().substring(file.getName().lastIndexOf(".") + 1, file.getName().length());
			try {
				BufferedImage bufferedImage = ImageIO.read(file);
				File output = new File("image/" + name.trim() + "Photo." + fileType);
				ImageIO.write(bufferedImage, fileType, output);
				return output.getName();
			} catch (IOException e) {
				e.getMessage();
			}
		}
		return path;
	}
/**
 * This method is the action of add a person
 * @param name person's name
 * @param photo person's photo
 * @param status person's status
 * @param gender person's gender
 * @param ageText person's age
 * @param state the state that the person from
 * @throws NotFillAllNecessInfo if not all the necessary information has been input
 * @throws NotNumberFormatException if the age number is not invalid
 * @throws NoSuchAgeException if the age is not for human
 * @throws AlreadyExistPersonException is the person is already in the network
 */
	private void addPersonAction(String name, String photo, String status, String gender, String ageText, String state)
			throws NotFillAllNecessInfo, NotNumberFormatException, NoSuchAgeException, AlreadyExistPersonException {

		Person currentPerson;
		ModifyRelationGUI addRelationGUI = new ModifyRelationGUI();

		if (name.equals("") || ageText.equals("") || state.equals("Select State")) {
			throw new NotFillAllNecessInfo();
		} else if (!(ageText.matches("\\d*") || ageText.matches("-\\d*"))) {
			throw new NotNumberFormatException();
		} else {
			int age = Integer.parseInt(ageText);
			if (age < 0 || age > 150)
				throw new NoSuchAgeException();
			else {
				currentPerson = MainMenuGUI.dc.addPerson(name, photo, status, gender, age, state);
				if (currentPerson == null)
					throw new AlreadyExistPersonException();
				else if (currentPerson instanceof Adult) {
					
					currentPerson.setPhoto(saveImage(name, photo));
					MainMenuGUI.dc.getMember().put(name, currentPerson);
					MainMenuGUI.dc.getDatabaseController().modifyDatabase(currentPerson, "addPerson");
					showMessageForAddPerson(true);
					MainMenuGUI.window.setScene(addRelationGUI.addRelationScene(currentPerson));

				} else {
					MainMenuGUI.window.setScene(addRelationGUI.addParentsScene1(currentPerson));
				}
			}
		}
	}
	/**
	 * This method shows the alert when add person successful or fail
	 * @param isSuccess if the add person successful or not 
	 */
public void showMessageForAddPerson(boolean isSuccess) {
		Alert alert = new Alert(Alert.AlertType.WARNING);
		if (isSuccess) {
			alert.setTitle("MESSAGES");
			alert.setHeaderText("SUCCESS!");
			alert.setContentText("Congratulations! Add person successfully!");
		} else {
			alert.setTitle("MESSAGES");
			alert.setHeaderText("FAIL!");
			alert.setContentText("Sorry, add person unsuccessfully!");
		}
		alert.showAndWait();
	}

}
