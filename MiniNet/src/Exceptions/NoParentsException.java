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
	private Person parent = new Adult();
	
	/**
	 * This is the constructor 
	 * @param noParentsChildList the child list who do not have parents
	 */
	public NoParentsException(ArrayList<Person> noParentsChildList)
	{
		this.noParentsChildList = noParentsChildList;
	}
	
	public NoParentsException(Person parent) {
		this.parent = parent;
	}
	
	/**
	 * This method is the handle action when there is a child without parents in the file/database	
	 */
	public void noParentsWarning() {
		
		String childName="";
		Alert alert = new Alert(Alert.AlertType.WARNING);
		alert.setTitle("MESSAGES");
		alert.setHeaderText("WARNING!");
		if(parent.getName() != null) {
			alert.setContentText(parent.getName()+" has children."+parent.getName()+" cannot be deleted!");
			parent = new Adult();
		}else {
		for(Person child:noParentsChildList)
		{
			childName+=child.getName()+", ";
		}
		alert.setContentText("Childen: "+childName+"'s parents information is missing. Therefore, they can't be added into network.");
		}
		alert.show();
	}
}
