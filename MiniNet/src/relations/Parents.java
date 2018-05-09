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
		
	}

	
	
	@Override
	public void add() throws Exception {
		// TODO Auto-generated method stub
		
		if(!parent1.getRelationship().containsKey("couple")) {
			parent1.setRelationManipulator(new Couple(parent1, parent2));	
			parent1.getRelationManipulator().add();
		}
		else if(!parent1.getRelationship().containsKey("child"))
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

	@Override
	public void remove() {
		// TODO Auto-generated method stub
		
		parent1.getRelationship().get("child").remove(child);
		parent2.getRelationship().get("child").remove(child);
//		if(parent1.getRelationship().get("child").isEmpty())
//			parent1.getRelationship().remove("child");
//		if(parent2.getRelationship().get("child").isEmpty())
//			parent2.getRelationship().remove("child");
	}

}
