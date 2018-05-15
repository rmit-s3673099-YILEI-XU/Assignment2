package Exceptions;

import javafx.scene.control.Alert;
import people.*;
/**
 * This class is for the exception that when the user want to add a child or young child as a colleague
 * @author YILEI XU
 *
 */
public class NotToBeColleaguesException extends Exception{

	Person colleague;
	/**
	 * This the constructor
	 * @param colleague the person who is being added as colleague
	 */
	public NotToBeColleaguesException(Person colleague) {
		this.colleague = colleague;
	}
/**
 *  This method is the action that when the user want to add a child or young child as a colleague
 */
	public void notToBeColleaguesWarning()
	{
		Alert alert = new Alert(Alert.AlertType.WARNING);
		alert.setTitle("MESSAGES");
		alert.setHeaderText("WARNING!");
		alert.setContentText("Colleague relation ONLY applies on ADULTS! "+colleague.getName() +" is not Adult. They cannot be collegues.");
		alert.show();
	}
}
