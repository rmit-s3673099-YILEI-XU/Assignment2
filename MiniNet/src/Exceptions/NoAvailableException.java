package Exceptions;

import javafx.scene.control.Alert;
import people.*;

public class NoAvailableException extends Exception{

//	Person person1, person2;
	private Person person;
	public NoAvailableException(Person person)
	{
		this.person = person;
		
	}
	
	public void noAvailableWarning() {
		Alert alert = new Alert(Alert.AlertType.WARNING);
		alert.setTitle("MESSAGES");
		alert.setHeaderText("WARNING!");
		alert.setContentText(person.getName()+" already has a partner ("+person.getRelationship().get("couple").get(0).getName()+"). They can't be couple.");
//		if(person1.getRelationship().containsKey("couple")) {
//		alert.setContentText(person1.getName() +" already has a partner ("+person1.getRelationship().get("couple").get(0).getName()+"). "+person1.getName() +" and "+person2.getName()+" cannot be couple. "
//				+ "Please rechoose partner.");
//		}
//		else {
//			alert.setContentText(person2.getName() +" already has a partner ("+person2.getRelationship().get("couple").get(0).getName()+"). "+person1.getName() +" and "+person2.getName()+" cannot be couple. "
//					+ "Please rechoose partner.");
//		}
		alert.show();
	}
}
