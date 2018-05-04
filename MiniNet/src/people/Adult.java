package people;

import java.util.ArrayList;
import java.util.HashMap;

import relations.*;

public class Adult extends Person{

	public Adult(String name, String photo, String status, String gender,int age, String state){
		
		this.setRelationship(new HashMap<String,ArrayList<Person>>());
		this.setName(name);
		this.setPhoto(photo);
		this.setStatus(status);
		this.setGender(gender);
		this.setAge(age);
		this.setState(state);
		
	}
	
	public Adult()
	{
		
	}
	
	@Override
	public void addRelationship(String relationType, Person relatedPerson) throws Exception{
		
		switch (relationType) {
		case "friends":
			this.setRelationManipulator(new AdultFriend(this, relatedPerson));
			this.relationManipulator.add();
			break;
		case "colleagues":
			this.setRelationManipulator(new Colleague(this, relatedPerson));
			this.relationManipulator.add();
			break;
		case "classmates":
			this.setRelationManipulator(new Classmate(this, relatedPerson));
			this.relationManipulator.add();
			break;
		case "couple":
			this.setRelationManipulator(new Couple(this, relatedPerson));
			this.relationManipulator.add();
			break;
		default:
			break;
		}
		
		
	}

	@Override
	public void removeRelationship(String relationType, Person relatedPerson)  {
		
		// TODO Auto-generated method stub
		switch(relationType){	
		case "friends":
			this.setRelationManipulator(new AdultFriend(this,relatedPerson));
			this.relationManipulator.remove();
		break;
		case "colleagues":
			this.setRelationManipulator(new Colleague(this, relatedPerson));
			this.relationManipulator.remove();
		break;
		case "classmates":
			this.setRelationManipulator(new Classmate(this, relatedPerson));
			this.relationManipulator.remove();
		break;
		case "couple":
			this.setRelationManipulator(new Couple(this, relatedPerson));
			this.relationManipulator.remove();
		break;
//		case "child":
//			this.setRelationManipulator(new Parents(relatedPerson.getRelationship().get("parent").get(0),relatedPerson.getRelationship().get("parent").get(1),relatedPerson));
//			this.getRelationManipulator().remove();
//			break;
			
		
		}
		
	}
}
