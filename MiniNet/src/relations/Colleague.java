package relations;

import java.util.ArrayList;

import Exceptions.NotToBeColleaguesException;
import people.*;
/**
 * This is the colleague relation class for modify colleague relationship
 * @author YILEI XU 
 *
 */
public class Colleague implements RelationManipulator{

	
	private Person selectPerson;
	private Person colleague;
	/**
	 * This is the constructor for create Colleague object
	 * @param selectPerson the person be selected
	 * @param colleague the person be added as colleague
	 */
	public Colleague(Person selectPerson, Person colleague)
	{
		this.selectPerson = selectPerson;
		this.colleague = colleague;
		if(!selectPerson.getRelationship().containsKey("colleagues"))
		{
			selectPerson.getRelationship().put("colleagues", new ArrayList<Person>());
		}				
	}
	/**
	 * This method is the override method which implements add colleague relation 
	 */
	@Override
	public void add() throws NotToBeColleaguesException {
		// TODO Auto-generated method stub
		
		if(colleague instanceof Adult) {
			selectPerson.getRelationship().get("colleagues").add(colleague);
			if(!colleague.getRelationship().containsKey("colleagues"))
			{
				colleague.getRelationship().put("colleagues", new ArrayList<Person>());
			}
			colleague.getRelationship().get("colleagues").add(selectPerson);
		}else
		{
			throw new NotToBeColleaguesException(colleague);
		}
	}
	/**
	 * This method is the override method which implements remove colleague relation 
	 */
	@Override
	public void remove() {
		// TODO Auto-generated method stub
		selectPerson.getRelationship().get("colleagues").remove(colleague);
		colleague.getRelationship().get("colleagues").remove(selectPerson);
	}

}
