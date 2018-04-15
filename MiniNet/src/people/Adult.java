package people;

import java.util.ArrayList;
import java.util.HashMap;

import relations.*;

public class Adult extends Person{

	public Adult(String name){
		
		this.setRelationship(new HashMap<String,ArrayList<Person>>());
		this.setName(name);
	}
	
	@Override
	public void addRelationship(String relationType, Person relation){
		
		if(relationType.equals("friends")){
			this.setRelationManipulator(new AdultFriend(this, relation));
			
		}else if(relationType.equals("colleagues")){
			this.setRelationManipulator(new Colleague(this, relation));
			
		}
	}
}
