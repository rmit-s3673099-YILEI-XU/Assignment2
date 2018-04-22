package people;

import java.util.ArrayList;
import java.util.HashMap;
import relations.RelationManipulator;

public abstract class Person {

	private String name;
	private String photo;
	private String status;
	private String gender;	
	private int age;
	private String state;
	
	private HashMap<String, ArrayList<Person>> relationship;
//	private ArrayList<RelationManipulator> relationManipulator; 
	RelationManipulator relationManipulator;
	
	public void setRelationManipulator(RelationManipulator relationManipulator) {
		this.relationManipulator = relationManipulator;
	
	}


	public RelationManipulator getRelationManipulator() {
		return relationManipulator;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getAge() {
		return age;
	}


	public void setAge(int age) {
		this.age = age;
	}


	public String getPhoto() {
		return photo;
	}


	public void setPhoto(String photo) {
		this.photo = photo;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getGender() {
		return gender;
	}


	public void setGender(String gender) {
		this.gender = gender;
	}


	public String getState() {
		return state;
	}


	public void setState(String state) {
		this.state = state;
	}


	public HashMap<String, ArrayList<Person>> getRelationship() {
		return relationship;
	}


	public void setRelationship(HashMap<String, ArrayList<Person>> relationship) {
		this.relationship = relationship;
	}


	public void displayProfile(){
		
		System.out.println("Name: "+ this.getName());
		System.out.println("Photo: "+ this.getPhoto());
		System.out.println("Status: "+ this.getStatus());
		System.out.println("Gender: "+ this.getGender());
		System.out.println("Age: "+ this.getAge());
		System.out.println("State: "+ this.getState());

		for(String key:relationship.keySet())
		{
			System.out.print(key+": ");
			for(int i=0;i<relationship.get(key).size();i++)
			{
				System.out.print(relationship.get(key).get(i).getName()+" ");
			}
			System.out.println();
		}
		
		System.out.println();
		
	}
	
	abstract public void addRelationship(String relationType, Person relation);
}
