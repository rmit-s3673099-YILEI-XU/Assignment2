package Exceptions;

import javafx.scene.control.Alert;

public class AlreadyExistPersonException extends Exception{

	public void alreadyExistPersonWarning() {
		
		Alert alert = new Alert(Alert.AlertType.WARNING);
		alert.setTitle("MESSAGES");
		alert.setHeaderText("WARNING!");
		alert.setContentText("The person is already existing, please check the name and fill agin.");
		alert.show();
		
	}
}
