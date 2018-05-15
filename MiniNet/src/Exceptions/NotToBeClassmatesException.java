package Exceptions;

import javafx.scene.control.Alert;
import people.Person;
/**
 * This class is the exception when user want to add classmate relationship to a young child
 * @author YILEI XU
 *
 */
public class NotToBeClassmatesException extends Exception{
	
	Person classmate;
	/**
	 * This is the constructor
	 * @param classmate the person who is being added as the classmate
	 */
	public NotToBeClassmatesException(Person classmate)
	{
		this.classmate = classmate;
	}
	/**
	 * This method is the action when user want to add classmate relationship to a young child
	 */
	public void notToBeClassmatesWarning()
	{
		Alert alert = new Alert(Alert.AlertType.WARNING);
		alert.setTitle("MESSAGES");
		alert.setHeaderText("WARNING!");
		alert.setContentText("Classmate relation ONLY applies on ADULTS and CHILDREN, not on YOUNG CHILDREN! "
		+classmate.getName() +" is young children. They cannot be classmates.");
		alert.show();
	}

}
