package people;

import java.util.ArrayList;
import java.util.TreeMap;

import relations.*;
/**
 * This class is the subclass of Person, it creates Child type of person
 * @author YILEI XU
 *
 */
public class Child extends Person{
	
	Person[] parent;
	/**
	 * This is the constructor for creating the Child object
	 * @param name Child name
	 * @param photo Child profile photo
	 * @param status Child status
	 * @param gender Child gender
	 * @param age Child age
	 * @param state the state the Child from
	 */
	public Child(String name, String photo, String status, String gender,int age, String state)
	{
		super(name, photo,status,gender,age,state);
		this.getRelationship().put("parent",new ArrayList<Person>());
		parent= new Adult[2];
		parent[0]= new Adult();
		parent[1]= new Adult();
			
	}
	/**
	 * This method is for add the relationship for Child by create relation object
	 * @param relationType the type of the relation
	 * @param relatedPerson the related person in the relationship
	 */
	@Override
	public void addRelationship(String relationType, Person relation) throws Exception {
	
		// TODO Auto-generated method stub
		switch(relationType){
		case "friends":
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
			if(parent[0].getName()!=null&&parent[1].getName()!=null) {
				System.out.println(parent[0].getName()+" "+parent[1].getName());
				addParent(parent[0],parent[1]);
			}	
			break;
		default:
			break;
		}
		
	}
/**
 * This method add parents for the child
 * @param parent1 the first parent
 * @param parent2 the second parent
 * @throws Exception if there is any exception
 */
	private void addParent(Person parent1, Person parent2) throws Exception {
	
		this.setRelationManipulator(new Parents(parent1,parent2,this));
		this.getRelationManipulator().add();
		
	}
	/**
	 * This method is for remove the relationship for Child 
	 * @param relationType the type of the relation
	 * @param relatedPerson the related person in the relationship
	 */
	@Override
	public void removeRelationship(String relationType, Person relatedPerson) {
		// TODO Auto-generated method stub	
		switch(relationType){
		case "friends":
			this.setRelationManipulator(new ChildFriend(this, relatedPerson));
			this.relationManipulator.remove();
			break;
		case "classmates":
			this.setRelationManipulator(new Classmate(this, relatedPerson));
			this.relationManipulator.remove();
			break;
		case"parent":
			
			if(parent[1].getName().equals(relatedPerson.getName()))
			{
				this.setRelationManipulator(new Parents(parent[0],parent[1],this));
				this.relationManipulator.remove();
			}
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
