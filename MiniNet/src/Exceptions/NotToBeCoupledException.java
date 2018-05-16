package Exceptions;

import javafx.scene.control.Alert;
import people.*;
/**
 * This class is the exception when the user want to add a child to a couple relationship
 * @author YILEI XU
 *
 */
public class NotToBeCoupledException extends Exception{

	private Person partner;
/**
 * This is the constructor
 * @param partner the partner of the person
 */
	public NotToBeCoupledException(Person partner)
	{
		this.partner = partner;
		
	}
	/**
	 * This method is the action when the user want to add a child to a couple relationship
	 */
	public void notToBeCoupleWarning()
	{
		Alert alert = new Alert(Alert.AlertType.WARNING);
		alert.setTitle("MESSAGES");
		alert.setHeaderText("WARNING!");
		alert.setContentText(partner.getName()+" is not Adult. He/She cannot have a COUPLE relationship.");
		alert.show();

	}
}
