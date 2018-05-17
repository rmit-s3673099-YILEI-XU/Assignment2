package Exceptions;

import javafx.scene.control.Alert;
import people.*;
/**
 * This class is the exception when the user want to add a couple relationship to a person who is already has a partner
 * @author YILEI XU
 *
 */
public class NoAvailableException extends Exception{

	private Person person;
	/**
	 * This is the constructor of NoAvailableException
	 * @param person the person is being modified
	 */
	public NoAvailableException(Person person)
	{
		this.person = person;
		
	}
	/**
	 * This method is the handle action when the user want to add a couple relationship to a person who is already has a partner
	 */
	public void noAvailableWarning() {
		Alert alert = new Alert(Alert.AlertType.WARNING);
		alert.setTitle("MESSAGES");
		alert.setHeaderText("WARNING!");
		alert.setContentText(person.getName()+" already has a partner ("+person.getRelationship().get("couple").get(0).getName()+"). They can't be couple.");

		alert.show();
	}
}
