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

public class AddPersonGUI {

	public Scene addPersonScene() {

		// set up layout
	

		GridPane pane = MainMenu.setUpPane();

		pane.add(new Label("Name:"), 0, 0);
		TextField personName = new TextField();
		pane.add(personName, 1, 0);
		pane.add(new Label("Age"), 0, 1);
		TextField personAge = new TextField();
		pane.add(personAge, 1, 1);
		pane.add(new Label("Gender"), 0, 2);

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
		pane.add(new Label("Status"), 0, 3);
		TextField personStatus = new TextField();
		pane.add(personStatus, 1, 3);
		pane.add(new Label("(Optional)"), 2, 3);

		pane.add(new Label("State"), 0, 4);
		ComboBox<String> comboBox = new ComboBox<String>();
		comboBox.setValue("Select State");
		String[] allState = { "ACT", "NSW", "NT", "QLD", "SA", "TAS", "VIC", "WA" };

		for (String s : allState) {
			comboBox.getItems().add(s);
		}

		// comboBox.getSelectionModel().selectFirst();

		pane.add(comboBox, 1, 4);

		pane.add(new Label("Photo"), 0, 5);
		Label personPhoto = new Label();
		pane.add(personPhoto, 1, 5);
		Button upload = new Button("Upload");
		pane.add(upload, 2, 5);
		pane.add(new Label("(Optional)"), 3, 5);

		Button btAdd = new Button("Add");
		pane.add(btAdd, 0, 6);
		Button btCancel = new Button("Cancel");
		pane.add(btCancel, 2, 6);

		// create events
		btAdd.setOnAction(e -> {
			/* add a person */

			String name = personName.getText().trim();
			String photo = personPhoto.getText().trim();
			String status = personStatus.getText().trim();
			String gender = (String) group.getSelectedToggle().getUserData();
			String ageText = personAge.getText().trim();
			String state = (String) comboBox.getValue();
			// AddPersonGUI addPerson = new AddPersonGUI(window);
			try {
				// addPerson.addPersonAction(name, photo, status, gender, ageText, state);
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

		});

		btCancel.setOnAction(e -> {
			MainMenu.window.setScene(MainMenu.startScene());
		});

		upload.setOnAction(e -> {
			File photoFile;
			photoFile = uploadPhoto(personName.getText().trim(), personPhoto);
			personPhoto.setText(photoFile.getAbsolutePath());

			// System.out.println(uploadPhoto(personName.getText().trim()));
		});

		Scene scene = new Scene(pane, 700, 500);
		return scene;
	}

	private File uploadPhoto(String name, Label personPhoto) {

		FileChooser fileChooser = new FileChooser();
		FileChooser.ExtensionFilter photoFilter = new FileChooser.ExtensionFilter("PNG files, JPG files, JPEG files",
				"*.PNG", "*.JPG", "*JPEG");
		fileChooser.getExtensionFilters().addAll(photoFilter);

		File file = fileChooser.showOpenDialog(null);

		return file;

	}

	private String saveImage(String name, String path) {

		String fileType;

		if (!path.equals("")) {
			File file = new File(path);
			fileType = file.getName().substring(file.getName().lastIndexOf(".") + 1, file.getName().length());
			try {
				BufferedImage bufferedImage = ImageIO.read(file);
				File output = new File("image/" + name.trim() + "Photo." + fileType);
				System.out.println(output.getPath());
				ImageIO.write(bufferedImage, fileType, output);
				return output.getName();
			} catch (IOException e) {
				e.getMessage();
			}
		}
		return path;
	}

	private void addPersonAction(String name, String photo, String status, String gender, String ageText, String state)
			throws NotFillAllNecessInfo, NotNumberFormatException, NoSuchAgeException, AlreadyExistPersonException {

		Person currentPerson;
		AddRelationGUI addRelationGUI = new AddRelationGUI();

		if (name.equals("") || ageText.equals("") || state.equals("Select State")) {
			throw new NotFillAllNecessInfo();
		} else if (!(ageText.matches("\\d*") || ageText.matches("-\\d*"))) {
			throw new NotNumberFormatException();
		} else {
			int age = Integer.parseInt(ageText);
			if (age < 0 || age > 150)
				throw new NoSuchAgeException();
			else {
				currentPerson = MainMenu.dc.addPerson(name, photo, status, gender, age, state);
				if (currentPerson == null)
					throw new AlreadyExistPersonException();
				else if (currentPerson instanceof Adult) {
					
					currentPerson.setPhoto(saveImage(name, photo));
					MainMenu.dc.getMember().put(name, currentPerson);
					MainMenu.dc.getDatabaseController().modifyDatabase(currentPerson, "addPerson");
					showMessageForAddPerson(true);
					MainMenu.window.setScene(addRelationGUI.addRelationScene(currentPerson));

				} else {
					MainMenu.window.setScene(addRelationGUI.addParentsScene1(currentPerson));
//					showMessageForAddPerson(true);
//					MainMenu.window.setScene(addRelationGUI.addRelationScene(currentPerson));

					/* add relation window */

				}
				
			}
		}
	}

	

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
