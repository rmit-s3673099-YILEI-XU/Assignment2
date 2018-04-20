package people;

import relations.Parents;

public class YoungChild extends Person{

	@Override
	public void addRelationship(String relationType, Person relation) {
		// TODO Auto-generated method stub
		
	}
	
	private void addParent(Person parent1, Person parent2) {
		
		this.setRelationManipulator(new Parents(parent1,parent2,this));
		
	}

}
