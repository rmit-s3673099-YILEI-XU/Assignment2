package relations;

import java.util.ArrayList;

import people.*;

/**
 * This is the Sibling relation class for modify Sibling relationship
 * @author YILEI XU 
 *
 */
public class Sibling implements RelationManipulator{
	
	private Person selectPerson;
	private Person sibling;
	
	/**
	 * This is the constructor for create Sibling object
	 * @param selectPerson the person be selected
	 * @param sibling the person be added as sibling
	 */	
	public Sibling(Person selectPerson, Person sibling)
	{
		this.selectPerson = selectPerson;
		this.sibling = sibling;
		if(!this.selectPerson.getRelationship().containsKey("sibling"))
		{
			this.selectPerson.getRelationship().put("sibling", new ArrayList<Person>());
		}
	}
	
	/**
	 * This method is the override method which implements add sibling relation 
	 */
	@Override
	public void add() {
		
		selectPerson.getRelationship().get("sibling").add(sibling);
		if(!sibling.getRelationship().containsKey("sibiling"))
			sibling.getRelationship().put("sibling", new ArrayList<Person>());
		sibling.getRelationship().get("sibling").add(selectPerson);
	}
	
	/**
	 * This method is the override method which implements remove sibling relation 
	 */
	@Override
	public void remove() {
		
		sibling.getRelationship().get("sibling").remove(selectPerson);
		if(sibling.getRelationship().get("sibling").isEmpty())
			sibling.getRelationship().remove("sibling");
		
	}

}
