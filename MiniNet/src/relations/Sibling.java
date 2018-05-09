package relations;

import java.util.ArrayList;

import people.*;

public class Sibling implements RelationManipulator{
	
	private Person selectPerson;
	private Person sibling;
	
	public Sibling(Person selectPerson, Person sibling)
	{
		this.selectPerson = selectPerson;
		this.sibling = sibling;
		if(!this.selectPerson.getRelationship().containsKey("sibling"))
		{
			this.selectPerson.getRelationship().put("sibling", new ArrayList<Person>());
		}
	}

	@Override
	public void add() {
		// TODO Auto-generated method stub
		selectPerson.getRelationship().get("sibling").add(sibling);
		if(!sibling.getRelationship().containsKey("sibiling"))
			sibling.getRelationship().put("sibling", new ArrayList<Person>());
		sibling.getRelationship().get("sibling").add(selectPerson);
	}

	@Override
	public void remove() {
		// TODO Auto-generated method stub
		
		sibling.getRelationship().get("sibling").remove(selectPerson);
		if(sibling.getRelationship().get("sibling").isEmpty())
			sibling.getRelationship().remove("sibling");
		
	}

}
