package Exceptions;

import javafx.scene.control.Alert;
import people.*;

public class TooYoungException extends Exception{

	Person person;
	public TooYoungException(Person person) {
		this.person = person;
	}
	public void tooYoungException() {
		Alert alert = new Alert(Alert.AlertType.WARNING);
		alert.setTitle("MESSAGES");
		alert.setHeaderText("WARNING!");
		alert.setContentText(person.getName()+" is too young, he/she cannot has that relationship.");
		alert.show();
	}
}
