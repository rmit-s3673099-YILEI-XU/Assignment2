package relations;

import java.util.ArrayList;

import Exceptions.NotToBeColleaguesException;
import people.*;

public class Colleague implements RelationManipulator{

	
	private Person selectPerson;
	private Person colleague;
	
	public Colleague(Person selectPerson, Person colleague)
	{
		this.selectPerson = selectPerson;
		this.colleague = colleague;
		if(!selectPerson.getRelationship().containsKey("colleagues"))
		{
			selectPerson.getRelationship().put("colleagues", new ArrayList<Person>());
		}
		
		
	}
	@Override
	public void add() throws NotToBeColleaguesException {
		// TODO Auto-generated method stub
		selectPerson.getRelationship().get("colleagues").add(colleague);
		if(!colleague.getRelationship().containsKey("colleagues"))
		{
			colleague.getRelationship().put("colleagues", new ArrayList<Person>());
		}
		if(colleague instanceof Adult) {
			colleague.getRelationship().get("colleagues").add(selectPerson);
		}else
		{
			throw new NotToBeColleaguesException(colleague);
		}
	}

	@Override
	public void remove() {
		// TODO Auto-generated method stub
		selectPerson.getRelationship().get("colleagues").remove(colleague);
		colleague.getRelationship().get("colleagues").remove(selectPerson);
	}

}
