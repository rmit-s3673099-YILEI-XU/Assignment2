package relations;

import java.util.ArrayList;

import Exceptions.*;
import people.*;

/**
 * This is the Couple relation class for modify couple relationship
 * @author YILEI XU 
 *
 */
public class Couple implements RelationManipulator{
	
	private Person selectPerson;
	private Person partner;
	
	/**
	 * This is the constructor for create couple object
	 * @param selectPerson the person be selected
	 * @param partner the person be added as partner
	 */
	public Couple(Person selectPerson, Person partner)
	{
		this.selectPerson = selectPerson;
		this.partner = partner;
		
		if(!this.selectPerson.getRelationship().containsKey("couple"))
			this.selectPerson.getRelationship().put("couple", new ArrayList<Person>());
	}
	
	/**
	 * This method is the override method which implements add couple relation 
	 */
	@Override
	public void add() throws NoAvailableException, NotToBeCoupledException {

		if(!(partner instanceof Adult))
		{
			throw new NotToBeCoupledException(partner);
		}
		else if(selectPerson.getRelationship().get("couple").size()>0) {
			throw new NoAvailableException(selectPerson);
		}
		else if(this.partner.getRelationship().containsKey("couple")) {
			if(this.partner.getRelationship().get("couple").size()>0)
			throw new NoAvailableException(partner);
		}
		else {	
			selectPerson.getRelationship().get("couple").add(partner);
			partner.getRelationship().put("couple", new ArrayList<Person>());
			partner.getRelationship().get("couple").add(selectPerson);
		}
	}
	
	/**
	 * This method is the override method which implements remove colleague relation 
	 */
	@Override
	public void remove() {
		
		partner.getRelationship().remove("couple");

	}

}
