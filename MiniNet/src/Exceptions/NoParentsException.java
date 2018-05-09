package Exceptions;

import java.util.ArrayList;

import javafx.scene.control.Alert;
import people.*;

public class NoParentsException extends Exception{

	private ArrayList<Person> noParentsChildList;
	
	
	public NoParentsException(ArrayList<Person> noParentsChildList)
	{
		this.noParentsChildList = noParentsChildList;
	}
	
	public void noParentsWarning() {
		
		String childName="";
		Alert alert = new Alert(Alert.AlertType.WARNING);
		alert.setTitle("MESSAGES");
		alert.setHeaderText("WARNING!");
		for(Person child:noParentsChildList)
		{
			childName+=child.getName()+" ";
		}
		alert.setContentText("Childen: "+childName+" don't have their parents. Therefore, they can't be added into network.");
		alert.show();
	}
}
