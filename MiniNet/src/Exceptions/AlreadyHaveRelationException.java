package Exceptions;

import javafx.scene.control.Alert;
import people.*;
/**
 * This class is the exception when the user want to add relationship to two people who already have relationship
 * @author YILEI XU
 *
 */
public class AlreadyHaveRelationException extends Exception{
	
	Person selectedPerson, relatedPerson;
	public AlreadyHaveRelationException(Person selectedPerson, Person relatedPerson) {
		this.selectedPerson = selectedPerson;
		this.relatedPerson = relatedPerson;
	}
/**
 * This method is the handle action when the user want to add relationship to two people who already have relationship
 */
	public void alreadyHaveRelationWarning()
	{
		Alert alert = new Alert(Alert.AlertType.WARNING);
		alert.setTitle("MESSAGES");
		alert.setHeaderText("UNCCESSFUL!");
		alert.setContentText("Sorry, "+selectedPerson.getName()+" and "+relatedPerson.getName()+" already have relation. CANNOT add a new relation for them ! ");
		alert.show();
		
	}
}
