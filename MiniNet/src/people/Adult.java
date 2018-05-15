package people;

import java.util.ArrayList;
import java.util.TreeMap;

import relations.*;
/**
 * This class is the subclass of Person, it creates Adult type of person
 * @author YILEI XU
 *
 */
public class Adult extends Person{

	/**
	 * This is the constructor for creating the Adult object
	 * @param name Adult name
	 * @param photo Adult profile photo
	 * @param status Adult status
	 * @param gender Adult gender
	 * @param age Adult age
	 * @param state the state the Adult from
	 */
	public Adult(String name, String photo, String status, String gender,int age, String state){
		
		super(name, photo,status,gender,age,state);
		
	}
	/**
	 * This is the constructor create parents for child and young child
	 */
	public Adult()
	{
		
	}
	
	/**
	 * This method is for add the relationship for adult by create relation object
	 * @param relationType the type of the relation
	 * @param relatedPerson the related person in the relationship
	 */
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

	/**
	 * This method is for remove relationship for Adult 
	 * @param relationType the type of the relation
	 * @param relatedPerson the related person in the relationship
	 */
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
		
		}
		
	}
}
