package Exceptions;

import javafx.scene.control.Alert;
import people.*;

public class NoAvailableException extends Exception{

	Person parent1, parent2;
	public NoAvailableException(Person parent1, Person parent2)
	{
		this.parent1 = parent1;
		this.parent2 = parent2;
	}
	
	public void noAvailableWarning() {
		Alert alert = new Alert(Alert.AlertType.WARNING);
		alert.setTitle("MESSAGES");
		alert.setHeaderText("WARNING!");
		if(parent1.getRelationship().containsKey("couple")) {
		alert.setContentText(parent1.getName() +" already has a partner ("+parent1.getRelationship().get("couple").get(0).getName()+"). "+parent1.getName() +" and "+parent2.getName()+" cannot be couple. "
				+ "Please rechoose parents.");
		}
		else {
			alert.setContentText(parent2.getName() +" already has a partner ("+parent2.getRelationship().get("couple").get(0).getName()+"). "+parent1.getName() +" and "+parent2.getName()+" cannot be couple. "
					+ "Please rechoose parents.");
		}
		alert.show();
	}
}
