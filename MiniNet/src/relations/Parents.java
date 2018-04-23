package relations;

import java.util.ArrayList;

import people.*;

public class Parents implements RelationManipulator{
	
	private Person parent1;
	private Person parent2;
	private Person child;
	
	public Parents(Person parent1, Person parent2, Person child)
	{
		this.parent1 = parent1;
		this.parent2 = parent2;
		this.child = child;
		add();
	}

	
	
	@Override
	public void add() {
		// TODO Auto-generated method stub
		
		if(!parent1.getRelationship().containsKey("couple")) {
			parent1.setRelationManipulator(new Couple(parent1, parent2));		
		}
		if(!parent1.getRelationship().containsKey("child"))
		{
			parent1.getRelationship().put("child", new ArrayList<Person>());
		}
		if(!parent2.getRelationship().containsKey("child"))
		{
			parent2.getRelationship().put("child", new ArrayList<Person>());
		}
		child.getRelationship().get("parent").add(parent1);
		child.getRelationship().get("parent").add(parent2);
		parent1.getRelationship().get("child").add(child);
		parent2.getRelationship().get("child").add(child);
		
		
	
	}

	@Override
	public void remove() {
		// TODO Auto-generated method stub
		parent1.getRelationship().get("child").remove(child);
		parent2.getRelationship().get("child").remove(child);
	}

}
