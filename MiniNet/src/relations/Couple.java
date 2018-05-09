package relations;

import java.util.ArrayList;

import Exceptions.*;
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
	public void add() throws NoAvailableException, NotToBeCoupledException {
		
//		System.out.println(selectPerson.getName()+" "+partner.getName());
		// TODO Auto-generated method stub
		if(!(partner instanceof Adult))
		{
			System.out.println("llllllll");
			throw new NotToBeCoupledException(partner);
		}
		else if(this.partner.getRelationship().containsKey("couple")) {
			throw new NoAvailableException(partner);
		}
		else {
			System.out.println("kkkkkk");
		selectPerson.getRelationship().get("couple").add(partner);
		partner.getRelationship().put("couple", new ArrayList<Person>());
		partner.getRelationship().get("couple").add(selectPerson);
		}
	}

	@Override
	public void remove() {
		// TODO Auto-generated method stub
//		selectPerson.getRelationship().remove("couple");
		partner.getRelationship().remove("couple");

//		if(selectPerson.getRelationship().containsKey("child"))
//		{
//		
//			for(Person child:selectPerson.getRelationship().get("child")) {
//				
//				for(String relationType: child.getRelationship().keySet()) {
//					
//					for(Person childRelation: child.getRelationship().get(relationType)) {
//						System.out.println("inside "+child.getName()+" "+relationType+" "+childRelation.getName());
//						child.removeRelationship(relationType, childRelation);
//					}
//				}
////				System.out.println("inside "+child.getName());
//				
//			}
////			partner.getRelationship().remove("child");
//		}
	}

}
