package Exceptions;

import javafx.scene.control.Alert;
import people.*;

public class NotToBeFriendsException extends Exception{

	Person currentPerson,friend;

public NotToBeFriendsException(Person currentPerson,Person friend) {
	this.currentPerson = currentPerson;
	this.friend= friend;
		
	}
public void notToBeFriendsException()
{

	
	Alert alert = new Alert(Alert.AlertType.WARNING);
	alert.setTitle("MESSAGES");
	alert.setHeaderText("WARNING!");
	if(currentPerson instanceof Adult) {
		alert.setContentText(friend.getName()+" is not ADULT. They cannot be friends. Please rechoose person or relationship.");
	}
	else {
		if(friend instanceof Adult)
		{
			alert.setContentText(friend.getName()+" is not CHILD. They cannot be friends. Please rechoose person or relationship.");
		}
		else
		{
			alert.setContentText(currentPerson.getName()+" and "+friend.getName()+" have an age gap. They cannot be friends. Please rechoose person or relationship.");
		}
		
	}
	
	alert.show();
}
}
