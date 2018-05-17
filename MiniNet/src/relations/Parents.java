package relations;

import java.util.ArrayList;

import people.*;

/**
 * This is the Parents relation class for modify Parents relationship
 * @author YILEI XU 
 *
 */
public class Parents implements RelationManipulator{
	
	private Person parent1;
	private Person parent2;
	private Person child;
	
	/**
	 * This is the constructor for create Parents object
	 * @param selectPerson the person be selected
	 * @param parent1 the first person be added as parent
	 * @param parent2 the second person be added as parent
	 */	
	public Parents(Person parent1, Person parent2, Person child) 
	{
		this.parent1 = parent1;
		this.parent2 = parent2;
		this.child = child;
		
	}
	
	/**
	 * This method is the override method which implements add parents relation 
	 */ 
	@Override
	public void add() throws Exception {
		
		if(!parent1.getRelationship().containsKey("couple")) {
			parent1.setRelationManipulator(new Couple(parent1, parent2));	
			parent1.getRelationManipulator().add();
		}
		if(!parent1.getRelationship().containsKey("child"))
		{
			parent1.getRelationship().put("child", new ArrayList<Person>());
		}
		if(!parent2.getRelationship().containsKey("child"))
		{
			parent2.getRelationship().put("child", new ArrayList<Person>());
		}
		if(parent1.getRelationship().get("child").size()>0)
		{
			for(Person sibling: parent1.getRelationship().get("child")) {
				child.setRelationManipulator(new Sibling(child,sibling));
				child.getRelationManipulator().add();
			}
		}
		child.getRelationship().get("parent").add(parent1);
		child.getRelationship().get("parent").add(parent2);
		parent1.getRelationship().get("child").add(child);
		parent2.getRelationship().get("child").add(child);
	
	}
	
	/**
	 * This method is the override method which implements remove parents relation 
	 */
	@Override
	public void remove() {
		
		if (parent1.getRelationship().containsKey("child")) {
			parent1.getRelationship().get("child").remove(child);
			if (parent1.getRelationship().get("child").isEmpty())
				parent1.getRelationship().remove("child");
		}
		if (parent2.getRelationship().containsKey("child")) {
			parent2.getRelationship().get("child").remove(child);
			if (parent2.getRelationship().get("child").isEmpty())
				parent2.getRelationship().remove("child");
		}

	}

}
