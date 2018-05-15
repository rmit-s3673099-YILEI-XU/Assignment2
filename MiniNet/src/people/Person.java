package people;

import java.util.ArrayList;
import java.util.TreeMap;

import relations.RelationManipulator;
/**
 * This is the abstract class which contains a general person's information and function
 * @author YILEI XU
 *
 */
public abstract class Person {

	private String name;
	private String photo;
	private String status;
	private String gender;	
	private int age;
	private String state;
	
	private TreeMap<String, ArrayList<Person>> relationship;
	RelationManipulator relationManipulator;
	
	/**
	 * This is the constructor create for the subclass Adult to create parents
	 */
	public Person() {
		
	}
	/**
	 * This is the constructor 
	 * @param name person name
	 * @param photo person profile photo
	 * @param status person status
	 * @param gender person gender
	 * @param age person age
	 * @param state the state the person from
	 */
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
	/**
	 * This method set the relation interface to the person
	 * @param relationManipulator the manipulator relation interface
	 */
	public void setRelationManipulator(RelationManipulator relationManipulator) {
		this.relationManipulator = relationManipulator;
	
	}

	/**
	 * This method get the relation interface to the person
	 * @return relationManipulator the manipulator relation interface
	 */
	public RelationManipulator getRelationManipulator() {
		return relationManipulator;
	}

	/**
	 * This method get the name of the person
	 * @return person name
	 */
	public String getName() {
		return name;
	}

/**
 * This method set the name to the person
 * @param name the person's name
 */
	public void setName(String name) {
		this.name = name;
	}

/**
 * This method get the age of the person
 * @return the age of the person
 */
	public int getAge() {
		return age;
	}

/**
 * This method set the age of the person
 * @param age the insert age
 */
	public void setAge(int age) {
		this.age = age;
	}

/**
 * This method get the profile photo of the person
 * @return the url of the photo
 */
	public String getPhoto() {
		return photo;
	}

/**
 * This method set the photo for the person
 * @param photo the url of the photo
 */
	public void setPhoto(String photo) {
		this.photo = photo;
	}

/**
 * This method get the status of the person
 * @return status the status of the person
 */
	public String getStatus() {
		return status;
	}

/**
 * This method set the status of the person
 * @param status the input status 
 */
	public void setStatus(String status) {
		this.status = status;
	}

/**
 * This method get the gender of the person
 * @return the gender of the person
 */
	public String getGender() {
		return gender;
	}

/**
 * This method set the gender of the person
 * @param gender the select gender
 */
	public void setGender(String gender) {
		this.gender = gender;
	}

/**
 * This method get the state of the person
 * @return the state where the person belong to
 */
	public String getState() {
		return state;
	}

/**
 * This method set the state of the person 
 * @param state the select state
 */
	public void setState(String state) {
		this.state = state;
	}

/**
 * This method get the relationship of the person
 * @return the treeMap element which is a relationship, contains relationship type and the related person 
 */
	public TreeMap<String, ArrayList<Person>> getRelationship() {
		return relationship;
	}

	/**
	 * This method set the relationship of the person
	 * @param the treeMap element which is a relationship, contains relationship type and the related person 
	 */
	public void setRelationship(TreeMap<String, ArrayList<Person>> relationship) {
		this.relationship = relationship;
	}

/**
 * This method is going to be removed
 */
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
	/**
	 * This is the abstract method which is doing the add relationship function
	 * @param relationType the relationship type
	 * @param relatedPerson the related person in this relationship
	 * @throws Exception if there is any exception
	 */
	abstract public void addRelationship(String relationType, Person relatedPerson) throws Exception;
	/**
	 * This method is the abstract method which is doing remove relationship function
	 * @param relationType the relationship type
	 * @param relatedPerson the related person in this relationship
	 */
	abstract public void removeRelationship(String relationType, Person relatedPerson);
}
