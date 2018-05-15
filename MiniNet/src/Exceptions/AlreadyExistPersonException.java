package Exceptions;

import javafx.scene.control.Alert;
/**
 * This class is handle the exception of a person is already in the member map
 * @author YILEI XU
 *
 */
public class AlreadyExistPersonException extends Exception{
/**
 * This method is about the action when a person is already in the member map
 */
	public void alreadyExistPersonWarning() {
		
		Alert alert = new Alert(Alert.AlertType.WARNING);
		alert.setTitle("MESSAGES");
		alert.setHeaderText("WARNING!");
		alert.setContentText("The person is already exist, please check the name and fill agin.");
		alert.show();
		
	}
}
