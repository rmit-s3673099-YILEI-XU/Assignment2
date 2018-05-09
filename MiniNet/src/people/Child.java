package people;

import java.util.ArrayList;
import java.util.HashMap;

import relations.*;

public class Child extends Person{
	
	Person[] parent;
	
	public Child(String name, String photo, String status, String gender,int age, String state)
	{
		this.setRelationship(new HashMap<String,ArrayList<Person>>());
		this.getRelationship().put("parent",new ArrayList<Person>());
		this.setName(name);
		this.setPhoto(photo);
		this.setStatus(status);
		this.setGender(gender);
		this.setAge(age);
		this.setState(state);
		parent= new Adult[2];
		parent[0]= new Adult();
		parent[1]= new Adult();
		
//		System.out.println("constractor! "+name);
		
	
	}

	@Override
	public void addRelationship(String relationType, Person relation) throws Exception {
	
		// TODO Auto-generated method stub
		switch(relationType){
		case "friends":
//			System.out.println("kkkkkkk");
			this.setRelationManipulator(new ChildFriend(this, relation));
			this.relationManipulator.add();
			break;
		case "classmates":
			this.setRelationManipulator(new Classmate(this, relation));
			this.relationManipulator.add();
			break;
		case "parent":
			
			if(parent[0].getName()==null) {
				parent[0]= relation;
			}
			else {
				parent[1]= relation;
			}
//			System.out.println(parent[0].getName()+" "+parent[1].getName());
			if(parent[0].getName()!=null&&parent[1].getName()!=null) {
				System.out.println(parent[0].getName()+" "+parent[1].getName());
				addParent(parent[0],parent[1]);
			}
			
			break;
		default:
			break;
		}
		
		
//		if(relationType.equals("friends"))
//		{
//		
//			this.setRelationManipulator(new ChildFriend(this, relation));
//			this.relationManipulator.add();
//		}else if(relationType.equals("classmates"))
//		{
//			this.setRelationManipulator(new Classmate(this, relation));
//		}
//		else if(relationType.equals("parent"))
//		{
//			
//			if(parent[0].getName()==null) {
//				parent[0]= relation;
//			}
//			else {
//				parent[1]= relation;
//			}
//			
//			if(parent[0].getName()!=null&&parent[1].getName()!=null)
//				addParent(parent[0],parent[1]);
//		}
		
	}
	
	private void addParent(Person parent1, Person parent2) throws Exception {
		
	
		this.setRelationManipulator(new Parents(parent1,parent2,this));
		this.getRelationManipulator().add();
		
	}

	@Override
	public void removeRelationship(String relationType, Person relatedPerson) {
		// TODO Auto-generated method stub
		
	}

}
