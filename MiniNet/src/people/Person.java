package people;

import java.util.ArrayList;
import java.util.TreeMap;

import relations.RelationManipulator;

public abstract class Person {

	private String name;
	private String photo;
	private String status;
	private String gender;	
	private int age;
	private String state;
	
	private TreeMap<String, ArrayList<Person>> relationship;
	RelationManipulator relationManipulator;
	public Person() {
		
	}
	public Person(String name, String photo, String status, String gender,int age, String state)
	{
		this.name = name;
		this.photo = photo;
		this.status = status;
		this.gender = gender;
		this.age = age;
		this.state = state;
		this.relationship = new TreeMap<String,ArrayList<Person>>();
	}
	
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


	public TreeMap<String, ArrayList<Person>> getRelationship() {
		return relationship;
	}


	public void setRelationship(TreeMap<String, ArrayList<Person>> relationship) {
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
	
	abstract public void addRelationship(String relationType, Person relatedPerson) throws Exception;
	abstract public void removeRelationship(String relationType, Person relatedPerson);
}
