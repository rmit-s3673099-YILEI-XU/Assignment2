package relations;

import java.util.ArrayList;
import java.util.HashMap;

import Exceptions.*;
import people.*;

/**
 * This is the AdultFriend relation class for modify AdultFriend relationship
 * @author YILEI XU 
 *
 */
public class AdultFriend implements RelationManipulator{

	private Person selectPerson;
	private Person friend;
	
	/**
	 * This is the constructor for create AdultFriend object
	 * @param selectPerson the person be selected
	 * @param friend the person be added as friend
	 */	
	public AdultFriend(Person selectPerson, Person friend)
	{
		this.selectPerson = selectPerson;
		this.friend = friend;
		if(!selectPerson.getRelationship().containsKey("friends"))
		{
			selectPerson.getRelationship().put("friends", new ArrayList<Person>());
		}

	}
	
	/**
	 * This method is the override method which implements add friend relation 
	 */
	@Override
	public void add() throws NotToBeFriendsException, TooYoungException {
		
		if(friend instanceof Child)
			throw new NotToBeFriendsException(selectPerson,friend);
		else if(friend instanceof YoungChild) {
			throw new TooYoungException(friend);
		}else {
			selectPerson.getRelationship().get("friends").add(friend);
			if(!friend.getRelationship().containsKey("friends"))
			{
				friend.getRelationship().put("friends", new ArrayList<Person>());
			}
			friend.getRelationship().get("friends").add(selectPerson);
		}
	}
	
	/**
	 * This method is the override method which implements remove friend relation 
	 */
	@Override
	public void remove() {
		friend.getRelationship().get("friends").remove(selectPerson);
		if(friend.getRelationship().get("friends").isEmpty())
			friend.getRelationship().remove("friends");
	}

}
