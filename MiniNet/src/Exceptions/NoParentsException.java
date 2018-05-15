package Exceptions;

import java.util.ArrayList;

import javafx.scene.control.Alert;
import people.*;
/**
 * This class is for the exception when there is a child without parents in the file/database
 * @author YILEI XU
 *
 */
public class NoParentsException extends Exception{

	private ArrayList<Person> noParentsChildList;
	/**
	 * This is the constructor 
	 * @param noParentsChildList the child list who do not have parents
	 */
	public NoParentsException(ArrayList<Person> noParentsChildList)
	{
		this.noParentsChildList = noParentsChildList;
	}
/**
 * This method is the handle action when there is a child without parents in the file/database	
 */
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
