package Exceptions;

import javafx.scene.control.Alert;
import people.Person;

public class NotToBeClassmatesException extends Exception{
	
	Person classmate;
	public NotToBeClassmatesException(Person classmate)
	{
		this.classmate = classmate;
	}
	public void notToBeClassmatesWarning()
	{
		Alert alert = new Alert(Alert.AlertType.WARNING);
		alert.setTitle("MESSAGES");
		alert.setHeaderText("WARNING!");
		alert.setContentText("Colleague relation ONLY applies on ADULTS and CHILDREN, not on YOUNG CHILDREN! "
		+classmate.getName() +" is young children. They cannot be classmates.");
		alert.show();
	}

}
