package relations;

import java.util.ArrayList;

import Exceptions.*;
import people.*;

public class ChildFriend implements RelationManipulator{
	
	private Person selectPerson;
	private Person friend;
	
	public ChildFriend(Person selectPerson, Person friend)
	{
		this.selectPerson = selectPerson;
		this.friend = friend;
		
		if(!this.selectPerson.getRelationship().containsKey("friends"))
		{
			this.selectPerson.getRelationship().put("friends", new ArrayList<Person>());
		}
		
	}

	@Override
	public void add() throws NotToBeFriendsException,TooYoungException{
		// TODO Auto-generated method stub
		if(friend instanceof YoungChild)
		{
			throw new TooYoungException(selectPerson);	
		}
		else if(Math.abs(selectPerson.getAge()-friend.getAge())>3||friend instanceof Adult)
		{	
			throw new NotToBeFriendsException(selectPerson,friend);
		}else {
			selectPerson.getRelationship().get("friends").add(friend);
			friend.getRelationship().get("friends").add(selectPerson);
		}
	}

	@Override
	public void remove() {
		// TODO Auto-generated method stub
		selectPerson.getRelationship().get("friends").remove(friend);
	}

}
