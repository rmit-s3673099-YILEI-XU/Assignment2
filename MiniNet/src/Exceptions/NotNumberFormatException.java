package Exceptions;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
/**
 * This class is about the exception when input the invalid number of the age
 * @author YILEI XU
 */
public class NotNumberFormatException extends Exception{

	/**
	 * This method is the handle action when the user input invalid number for the age
	 */
	public void notNumberFormatWarning()
	{
		Alert alert = new Alert(Alert.AlertType.WARNING);
		alert.setTitle("MESSAGES");
		alert.setHeaderText("WARNING!");
		alert.setContentText("Please input a valid number for age.");
		alert.show();
	}
}
