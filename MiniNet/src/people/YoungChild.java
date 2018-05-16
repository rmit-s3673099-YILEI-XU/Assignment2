package people;

import java.util.ArrayList;
import java.util.TreeMap;
import relations.ChildFriend;
import relations.Classmate;
import relations.Parents;
import relations.Sibling;
/**
 * This class is the subclass of Person, it creates Young Child type of person
 * @author YILEI XU
 *
 */
public class YoungChild extends Person{
	
	Person[] parent;
	/**
	 * This is the constructor for creating the YoungChild object
	 * @param name YoungChild name
	 * @param photo YoungChild profile photo
	 * @param status YoungChild status
	 * @param gender YoungChild gender
	 * @param age YoungChild age
	 * @param state the state the YoungChild from
	 */
	public YoungChild(String name, String photo, String status, String gender,int age, String state)
	{
		super(name, photo,status,gender,age,state);
		this.getRelationship().put("parent",new ArrayList<Person>());
	
		parent= new Adult[2];
		parent[0]= new Adult();
		parent[1]= new Adult();
		
	}
	/**
	 * This method is for add the relationship for Young Child by create relation object
	 * @param relationType the type of the relation
	 * @param relatedPerson the related person in the relationship
	 */
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
		
			if(parent[0].getName()!=null&&parent[1].getName()!=null) {
				addParent(parent[0],parent[1]);
		
			}
		}
	}
	/**
	 * This method add parents for the young child
	 * @param parent1 the first parent
	 * @param parent2 the second parent
	 * @throws Exception if there is any exception
	 */
	private void addParent(Person parent1, Person parent2) throws Exception  {
	
		this.setRelationManipulator(new Parents(parent1,parent2,this));
		this.getRelationManipulator().add();
		
	}
	/**
	 * This method is for remove the relationship for Young Child
	 * @param relationType the type of the relation
	 * @param relatedPerson the related person in the relationship
	 */
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
