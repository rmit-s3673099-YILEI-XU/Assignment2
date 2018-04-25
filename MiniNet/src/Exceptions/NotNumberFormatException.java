package Exceptions;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

public class NotNumberFormatException extends Exception{
	
	public void notNumberFormatWarning()
	{
		Alert alert = new Alert(Alert.AlertType.WARNING);
		alert.setTitle("MESSAGES");
		alert.setHeaderText("WARNING!");
		alert.setContentText("Please input a valid number for age.");
		alert.show();
	}
}
