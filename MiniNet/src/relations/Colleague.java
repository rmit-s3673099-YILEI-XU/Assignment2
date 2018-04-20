package relations;

import java.util.ArrayList;

import people.Person;

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
	public void add() {
		// TODO Auto-generated method stub
		selectPerson.getRelationship().get("colleagues").add(colleague);
		if(!colleague.getRelationship().containsKey("colleagues"))
		{
			colleague.getRelationship().put("colleagues", new ArrayList<Person>());
		}
		colleague.getRelationship().get("colleagues").add(selectPerson);
	}

	@Override
	public void remove() {
		// TODO Auto-generated method stub
		selectPerson.getRelationship().get("colleagues").remove(colleague);
		colleague.getRelationship().get("colleagues").remove(selectPerson);
	}

}
