package people;

import java.util.ArrayList;
import java.util.HashMap;

import relations.*;

public class Child extends Person{
	
	public Child(String name, String photo, String status, String gender,int age, String state)
	{
		this.setRelationship(new HashMap<String,ArrayList<Person>>());
		this.setName(name);
		this.setPhoto(photo);
		this.setStatus(status);
		this.setGender(gender);
		this.setAge(age);
		this.setState(state);
	
		
	}

	@Override
	public void addRelationship(String relationType, Person relation) {
		// TODO Auto-generated method stub
		if(relationType.equals("friend"))
		{
			this.setRelationManipulator(new ChildFriend(this, relation));
		}else if(relationType.equals("classmate"))
		{
			this.setRelationManipulator(new Classmate(this, relation));
		}
		else if(relationType.equals("parent"))
		{
			
		}
		
	}
	
	private void addParent(Person parent1, Person parent2) {
		
		this.setRelationManipulator(new Parents(parent1,parent2,this));
		
	}

}
