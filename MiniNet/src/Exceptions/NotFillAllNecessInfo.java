package Exceptions;

import javafx.scene.control.Alert;
/**
 * This class is about the exception when the user has not input all the necessary information of a person
 * @author zhangmo
 *
 */
public class NotFillAllNecessInfo extends Exception{
/**
 * This method is the handle action when the user has not input all the necessary information of the person	
 */
	public void lackNecessInforWarning()
	{
		Alert alert = new Alert(Alert.AlertType.WARNING);
		alert.setTitle("WARNING!");
		alert.setHeaderText("LACK NECESSARY INFORMATION");
		alert.setContentText("Please fill in all necessary information.");
		alert.show();
	}

}
