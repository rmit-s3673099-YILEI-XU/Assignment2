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

	}
	
	@Override
	public void add() {
		// TODO Auto-generated method stub
		selectPerson.getRelationship().get("friends").add(friend);
		if(!friend.getRelationship().containsKey("friends"))
		{
			friend.getRelationship().put("friends", new ArrayList<Person>());
		}
		friend.getRelationship().get("friends").add(selectPerson);
		//this.selectPerson.setRelationship(relationship)
	}

	@Override
	public void remove() {
		// TODO Auto-generated method stub
		selectPerson.getRelationship().get("friend").remove(friend);
		friend.getRelationship().get("friend").remove(selectPerson);
	}

}
