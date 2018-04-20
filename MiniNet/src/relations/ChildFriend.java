package relations;

import java.util.ArrayList;

import Exceptions.*;
import people.Person;

public class ChildFriend implements RelationManipulator{
	
	private Person selectPerson;
	private Person friend;
	
	public ChildFriend(Person selectPerson, Person friend)
	{
		this.selectPerson = selectPerson;
		this.friend = friend;
		
		if(!this.selectPerson.getRelationship().containsKey("friend"))
		{
			this.selectPerson.getRelationship().put("friend", new ArrayList<Person>());
		}
		
	}

	@Override
	public void add() throws NotToBeFriendsException{
		// TODO Auto-generated method stub
		if(Math.abs(selectPerson.getAge()-friend.getAge())>3)
		{
			throw new NotToBeFriendsException();
		}
		selectPerson.getRelationship().get("friends").add(friend);
	}

	@Override
	public void remove() {
		// TODO Auto-generated method stub
		selectPerson.getRelationship().get("friend").remove(friend);
	}

}
