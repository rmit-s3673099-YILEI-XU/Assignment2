package people;

import java.util.ArrayList;
import java.util.HashMap;

import relations.ChildFriend;
import relations.Classmate;
import relations.Parents;
import relations.Sibling;

public class YoungChild extends Person{
	
	Person[] parent;
	
	public YoungChild(String name, String photo, String status, String gender,int age, String state)
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
		
	}

	@Override
	public void addRelationship(String relationType, Person relation) throws Exception {
		
		// TODO Auto-generated method stub
		if(relationType.equals("parent"))
		{
			
			if(parent[0].getName()==null) {
				parent[0]= relation;
			}
			else {
				parent[1]= relation;
			}
			
			if(parent[0].getName()!=null&&parent[1].getName()!=null)
				addParent(parent[0],parent[1]);
		}
	}
	
	private void addParent(Person parent1, Person parent2) throws Exception  {
		
		this.setRelationManipulator(new Parents(parent1,parent2,this));
		this.getRelationManipulator().add();
		
	}

	@Override
	public void removeRelationship(String relationType, Person relatedPerson) {
		// TODO Auto-generated method stub
		switch(relationType){
		
		case"parent":
			if(parent[1].getName().equals(relatedPerson.getName()))
			{
				this.setRelationManipulator(new Parents(parent[0],parent[1],this));
				this.relationManipulator.remove();
			}
				
//			if(parent[0].getName()==null) {
//				parent[0]= relatedPerson;
//			}
//			else if (parent[1].getName()==null){
//				parent[1]= relatedPerson;
//			}
//			if(parent[0].getName()!=null&&parent[1].getName()!=null) {
////				this.setRelationManipulator(new Parents(parent[0],parent[1],this));
//				this.relationManipulator.remove();
////			}
			break;
		case "sibling":
			if(relatedPerson!=null) {
			this.setRelationManipulator(new Sibling(this, relatedPerson));
			this.relationManipulator.remove();
			}
			break;
		}
		
	}

}
