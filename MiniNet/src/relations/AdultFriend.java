package relations;

import java.util.ArrayList;
import java.util.HashMap;

import Exceptions.*;
import people.*;

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
	public void add() throws NotToBeFriendsException, TooYoungException {
		// TODO Auto-generated method stub
		
		if(friend instanceof Child)
			throw new NotToBeFriendsException(selectPerson,friend);
		else if(friend instanceof YoungChild) {
			throw new TooYoungException(selectPerson);
		}else {
			selectPerson.getRelationship().get("friends").add(friend);
			if(!friend.getRelationship().containsKey("friends"))
			{
				friend.getRelationship().put("friends", new ArrayList<Person>());
			}
			friend.getRelationship().get("friends").add(selectPerson);
		}
		//this.selectPerson.setRelationship(relationship)
	}

	@Override
	public void remove() {
		// TODO Auto-generated method stub
		friend.getRelationship().get("friends").remove(selectPerson);
		if(friend.getRelationship().get("friends").isEmpty())
			friend.getRelationship().remove("friends");
		
	}

}
