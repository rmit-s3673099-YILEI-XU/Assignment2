package Exceptions;

import javafx.scene.control.Alert;
/**
 * This class is for the exception when the user input the age not in a human age range
 * @author YUILEI XU
 *
 */
public class NoSuchAgeException extends Exception{
/**
 * This method is the handle action when the user input the age notin a human age range
 */
	public void noSuchAgeWarning()
	{
		Alert alert = new Alert(Alert.AlertType.WARNING);
		alert.setTitle("MESSAGES");
		alert.setHeaderText("WARNING!");
		alert.setContentText("Please input a valid number for age bewteen 0~150.");
		alert.show();
	}
}
