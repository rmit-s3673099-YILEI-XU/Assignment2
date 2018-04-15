package relations;

import java.util.ArrayList;
import java.util.HashMap;

import people.Person;

public class AdultFriend implements RelationManipulator{

	private Person selectPerson;
	private Person friend;
	
	public AdultFriend(Person selectPerson, Person friend)
	{
		this.selectPerson = selectPerson;
		this.friend = friend;
		if(!selectPerson.getRelationship().containsKey("friends"))
		{
			selectPerson.getRelationship().put("friends", new ArrayList<Person>());
		}
		add();
		
	}
	
	@Override
	public void add() {
		// TODO Auto-generated method stub
		selectPerson.getRelationship().get("friends").add(friend);
		
		
		//this.selectPerson.setRelationship(relationship)
	}

	@Override
	public void remove() {
		// TODO Auto-generated method stub
		
	}

}
