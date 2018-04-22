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
	
	@Override
	public void addRelationship(String relationType, Person relation){
		
		switch (relationType) {
		case "friends":
			this.setRelationManipulator(new AdultFriend(this, relation));
			try {
				this.relationManipulator.add();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case "colleagues":
			this.setRelationManipulator(new Colleague(this, relation));
//			this.relationManipulator.add();
			break;
		case "classmates":
			this.setRelationManipulator(new Classmate(this, relation));
//			this.relationManipulator.add();
			break;
		case "couple":
			this.setRelationManipulator(new Couple(this, relation));
			try {
				this.relationManipulator.add();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		default:
			break;
		}
		
//		if(relationType.equals("friends")){
//			this.setRelationManipulator(new AdultFriend(this, relation));
//			this.relationManipulator.add();
//			
//		}else if(relationType.equals("colleagues")){
//			this.setRelationManipulator(new Colleague(this, relation));
//			this.relationManipulator.add();
//		}
//		else if(relationType.equals("classmates"))
//		{
//			this.setRelationManipulator(new Classmate(this, relation));
//			this.relationManipulator.add();
//		}
		
	}
}
