package relations;

import java.util.ArrayList;

import people.*;

public class Couple implements RelationManipulator{
	
	private Person selectPerson;
	private Person partner;
	
	public Couple(Person selectPerson, Person partner)
	{
		this.selectPerson = selectPerson;
		this.partner = partner;
		
//		if(!this.selectPerson.getRelationship().containsKey("couple"))
		this.selectPerson.getRelationship().put("couple", new ArrayList<Person>());
	}

	@Override
	public void add() {
		// TODO Auto-generated method stub
		selectPerson.getRelationship().get("couple").add(partner);
		partner.getRelationship().put("couple", new ArrayList<Person>());
		partner.getRelationship().get("couple").add(selectPerson);
	}

	@Override
	public void remove() {
		// TODO Auto-generated method stub
//		selectPerson.getRelationship().remove("couple");
		partner.getRelationship().remove("couple");
		if(selectPerson.getRelationship().containsKey("child"))
		{
			
		}
	}

}
