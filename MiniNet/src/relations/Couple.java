package relations;

import java.util.ArrayList;

import Exceptions.*;
import people.*;

public class Couple implements RelationManipulator{
	
	private Person selectPerson;
	private Person partner;
	
	public Couple(Person selectPerson, Person partner)
	{
		this.selectPerson = selectPerson;
		this.partner = partner;
		
		if(!this.selectPerson.getRelationship().containsKey("couple"))
			this.selectPerson.getRelationship().put("couple", new ArrayList<Person>());
	}

	@Override
	public void add() throws NoAvailableException, NotToBeCoupledException {
		
		// TODO Auto-generated method stub
		if(!(partner instanceof Adult))
		{
		
			throw new NotToBeCoupledException(partner);
		}
		else if(selectPerson.getRelationship().get("couple").size()>0) {
			throw new NoAvailableException(selectPerson);
		}
		else if(this.partner.getRelationship().containsKey("couple")) {
			throw new NoAvailableException(partner);
		}
		else {
		
			selectPerson.getRelationship().get("couple").add(partner);
			partner.getRelationship().put("couple", new ArrayList<Person>());
			partner.getRelationship().get("couple").add(selectPerson);
		}
	}

	@Override
	public void remove() {
		// TODO Auto-generated method stub

		if(selectPerson.getRelationship().containsKey("couple"))
			selectPerson.getRelationship().remove("couple");
		
		partner.getRelationship().remove("couple");

	}

}
