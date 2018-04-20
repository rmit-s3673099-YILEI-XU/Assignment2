package people;

import java.util.ArrayList;
import java.util.HashMap;

import relations.RelationManipulator;

public abstract class Person {

	private String name;
	private int age;
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


	public HashMap<String, ArrayList<Person>> getRelationship() {
		return relationship;
	}


	public void setRelationship(HashMap<String, ArrayList<Person>> relationship) {
		this.relationship = relationship;
	}


	public void displayProfile(){
	

		for(String key:relationship.keySet())
		{
			System.out.print(key+": ");
			for(int i=0;i<relationship.get(key).size();i++)
			{
				System.out.print(relationship.get(key).get(i).getName()+" ");
			}
			System.out.println();
		}
		
	}
	
	abstract public void addRelationship(String relationType, Person relation);
}
