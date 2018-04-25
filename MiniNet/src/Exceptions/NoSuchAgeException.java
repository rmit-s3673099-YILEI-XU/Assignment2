package Exceptions;

import javafx.scene.control.Alert;

public class NoSuchAgeException extends Exception{

	public void noSuchAgeWarning()
	{
		Alert alert = new Alert(Alert.AlertType.WARNING);
		alert.setTitle("MESSAGES");
		alert.setHeaderText("WARNING!");
		alert.setContentText("Please input a valid number for age bewteen 0~150.");
		alert.show();
	}
}
