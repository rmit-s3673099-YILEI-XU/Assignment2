package Exceptions;

import javafx.scene.control.Alert;

public class NotFillAllNecessInfo extends Exception{
	
	public void lackNecessInforWarning()
	{
		Alert alert = new Alert(Alert.AlertType.WARNING);
		alert.setTitle("WARNING!");
		alert.setHeaderText("LACK NECESSARY INFORMATION");
		alert.setContentText("Please fill in all necessary information.");
		alert.show();
	}

}
