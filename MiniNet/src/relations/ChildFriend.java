package relations;

import java.util.ArrayList;

import Exceptions.*;
import people.*;

/**
 * This is the ChildFriend relation class for modify ChildFriend relationship
 * @author YILEI XU 
 *
 */
public class ChildFriend implements RelationManipulator{
	
	private Person selectPerson;
	private Person friend;
	
	/**
	 * This is the constructor for create ChildFriend object
	 * @param selectPerson the person be selected
	 * @param friend the person be added as friend
	 */
	public ChildFriend(Person selectPerson, Person friend)
	{
		this.selectPerson = selectPerson;
		this.friend = friend;
		
		if(!this.selectPerson.getRelationship().containsKey("friends"))
		{
			this.selectPerson.getRelationship().put("friends", new ArrayList<Person>());
		}		
	}
	
	/**
	 * This method is the override method which implements add friend relation 
	 */
	@Override
	public void add() throws NotToBeFriendsException,TooYoungException{

		if(friend instanceof YoungChild)
		{
			throw new TooYoungException(selectPerson);	
		}
		else if(Math.abs(selectPerson.getAge()-friend.getAge())>3||friend instanceof Adult)
		{	
			throw new NotToBeFriendsException(selectPerson,friend);
		}else {
			selectPerson.getRelationship().get("friends").add(friend);
			if(!friend.getRelationship().containsKey("friends")) {
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
