package relations;

import java.util.ArrayList;

import Exceptions.NotToBeClassmatesException;
import people.*;

/**
 * This is the Classmate relation class for modify classmate relationship
 * @author YILEI XU 
 *
 */
public class Classmate implements RelationManipulator{

	Person selectPerson;
	Person classmate;
	
	/**
	 * This is the constructor for create Classmate object
	 * @param selectPerson the person be selected
	 * @param classmate the person be added as classmate
	 */	
	public Classmate(Person selectPerson, Person classmate)
	{
		this.selectPerson = selectPerson;
		this.classmate = classmate;
		if(!this.selectPerson.getRelationship().containsKey("classmates"))
		{
			this.selectPerson.getRelationship().put("classmates",new ArrayList<Person>());
		}
	}
	
	/**
	 * This method is the override method which implements add classmate relation 
	 */
	@Override
	public void add() throws NotToBeClassmatesException {
		
		if(classmate instanceof YoungChild) {
			throw new NotToBeClassmatesException(classmate);
		}
		else
		{
			this.selectPerson.getRelationship().get("classmates").add(classmate);
			if(!classmate.getRelationship().containsKey("classmates"))
				classmate.getRelationship().put("classmates", new ArrayList<Person>());
			classmate.getRelationship().get("classmates").add(selectPerson);
		}	
	}
	
	/**
	 * This method is the override method which implements remove classmate relation 
	 */
	@Override
	public void remove() {
		this.classmate.getRelationship().get("classmates").remove(selectPerson);
		if(classmate.getRelationship().get("classmates").isEmpty())
			classmate.getRelationship().remove("classmates");
	}
}
