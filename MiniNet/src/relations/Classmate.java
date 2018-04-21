package relations;

import java.util.ArrayList;

import people.Person;

public class Classmate implements RelationManipulator{

	Person selectPerson;
	Person classmate;
	
	public Classmate(Person selectPerson, Person classmate)
	{
		this.selectPerson = selectPerson;
		this.classmate = classmate;
		if(!this.selectPerson.getRelationship().containsKey("classmates"))
		{
			this.selectPerson.getRelationship().put("classmates",new ArrayList<Person>());
		}
	}
	
	@Override
	public void add() {
		// TODO Auto-generated method stub
		this.selectPerson.getRelationship().get("classmates").add(classmate);
		if(!classmate.getRelationship().containsKey("classmates"))
			classmate.getRelationship().put("classmates", new ArrayList<Person>());
		classmate.getRelationship().get("classmates").add(classmate);
		
	}

	@Override
	public void remove() {
		// TODO Auto-generated method stub
		this.selectPerson.getRelationship().get("classmates").remove(classmate);
		this.classmate.getRelationship().get("classmates").remove(selectPerson);
		
	}

}