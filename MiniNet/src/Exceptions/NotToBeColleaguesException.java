package Exceptions;

import javafx.scene.control.Alert;
import people.*;

public class NotToBeColleaguesException extends Exception{

	Person colleague;
	public NotToBeColleaguesException(Person colleague) {
		this.colleague = colleague;
	}
	
	public void notToBeColleaguesWarning()
	{
		Alert alert = new Alert(Alert.AlertType.WARNING);
		alert.setTitle("MESSAGES");
		alert.setHeaderText("WARNING!");
		alert.setContentText("Colleague relation ONLY applies on ADULTS! "+colleague.getName() +" is not Adult. They cannot be collegues.");
		alert.show();
	}
}
