package Exceptions;

import javafx.scene.control.Alert;
import people.*;

/**
 * This class is the exception when the person is too young to have that type of relation
 * @author YILEI XU
 *
 */
public class TooYoungException extends Exception{

	Person person;
	
	/**
	 * This is the constructor
	 * @param person the person be selected 
	 */
	public TooYoungException(Person person) {
		this.person = person;
	}
	/**
	 * This method is the action when the person is too young to have that type of relation
	 */
	public void tooYoungException() {
		Alert alert = new Alert(Alert.AlertType.WARNING);
		alert.setTitle("MESSAGES");
		alert.setHeaderText("WARNING!");
		alert.setContentText(person.getName()+" is too young, he/she cannot have that relationship.");
		alert.show();
	}
}
