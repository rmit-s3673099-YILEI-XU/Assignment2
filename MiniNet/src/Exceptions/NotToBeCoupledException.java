package Exceptions;

import javafx.scene.control.Alert;
import people.*;

public class NotToBeCoupledException extends Exception{

	private Person parent1,parent2;
	public NotToBeCoupledException(Person parent1,Person parent2)
	{
		this.parent1 = parent1;
		this.parent2 = parent2; 
	}
	public void notToBeCoupleWarning()
	{
		Alert alert = new Alert(Alert.AlertType.WARNING);
		alert.setTitle("MESSAGES");
		alert.setHeaderText("WARNING!");
		if(!(parent1 instanceof Adult)&&!(parent2 instanceof Adult)) {
		alert.setContentText(parent1.getName() +" and "+parent2.getName()+ " are not ADULT! They cannot be couple. "
				+ "Please rechoose parents.");
		}
		else if(!(parent1 instanceof Adult))
		{
			alert.setContentText(parent1.getName() +" is not ADULT! "+parent1.getName() +" and "+parent2.getName()+" cannot be couple. "
					+ "Please rechoose the FIRST parent.");
		}
		else
		{
			alert.setContentText(parent2.getName() +" is not ADULT! "+parent1.getName() +" and "+parent2.getName()+" cannot be couple. "
					+ "Please rechoose the SECOND parent.");
			
		}
		alert.show();
	}
}
