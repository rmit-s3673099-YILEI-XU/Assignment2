import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import Exceptions.AlreadyExistPersonException;
import Exceptions.NoSuchAgeException;
import Exceptions.NotFillAllNecessInfo;
import Exceptions.NotNumberFormatException;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import people.Adult;
import people.Person;

public class AddPersonGUI extends MiniNetInterface {

	DriverClass dc;
	MiniNetInterface mini = new MiniNetInterface(window);

	public AddPersonGUI(Stage window) {
		super(window);
		dc = mini.getDriver();
	}

	public void addPersonAction(String name, String photo, String status, String gender, String ageText, String state)
			throws NotFillAllNecessInfo, NotNumberFormatException, NoSuchAgeException, AlreadyExistPersonException {

		Person currentPerson;
		
		if (name.equals("") || ageText.equals("") || state.equals("Select State")) {
			throw new NotFillAllNecessInfo();
		} else if (!(ageText.matches("\\d*") || ageText.matches("-\\d*"))) {
			throw new NotNumberFormatException();
		} else {
			int age = Integer.parseInt(ageText);
			if (age < 0 || age > 150)
				throw new NoSuchAgeException();
			else {
				currentPerson = dc.addPerson(name, photo, status, gender, age, state);
				if (currentPerson == null)
					throw new AlreadyExistPersonException();
				else if (currentPerson instanceof Adult) {

					dc.getMember().put(name, currentPerson);
					currentPerson.setPhoto(saveImage(name, photo));
					showMessageForAddPerson(true);

					window.setScene(mini.addRelationScene(currentPerson));
				} else {
					window.setScene(mini.addParentsScene(currentPerson));
					showMessageForAddPerson(true);
					
					/* add relation window */
					
				}
			}
		}
	}

	public File uploadPhoto(String name, Label personPhoto) {

		FileChooser fileChooser = new FileChooser();
		FileChooser.ExtensionFilter photoFilter = new FileChooser.ExtensionFilter("PNG files, JPG files, JPEG files",
				"*.PNG", "*.JPG", "*JPEG");
		fileChooser.getExtensionFilters().addAll(photoFilter);

		File file = fileChooser.showOpenDialog(null);

		return file;

	}

	public String saveImage(String name, String path) {

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
