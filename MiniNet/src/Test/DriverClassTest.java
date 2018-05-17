package Test;
import Controller.*;
import Exceptions.NoParentsException;
import people.*;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.*;

public class DriverClassTest {

	DriverClass dc;
	@Before
	public void setUp() throws IOException{
		
		  dc = new DriverClass();
		  dc.getDatabaseController().initialDatabase();
	}
	
	// test initial data: child has no parents
	@Test(expected = NoParentsException.class)
	 public void initialDataTest() throws IOException, NoParentsException{
	  
		dc.initialData();
	 }
	
	// test add adult
	@Test
	public void addPersonTest1_1() throws IOException {
		  
		  assertTrue(dc.addPerson("Adult", "", "", "F", 20, "VIC") instanceof Adult);
		
	}
	
	// test add child
	@Test
	public void addPersonTest1_2() throws IOException {
		  
		  assertTrue(dc.addPerson("child", "", "", "F", 10, "VIC") instanceof Child);
	}
	
	// test add youngChild
	@Test
	public void addPersonTest1_3() throws IOException {
		  
		  assertTrue(dc.addPerson("youngChild", "", "", "F", 2, "VIC") instanceof YoungChild);
	}
	
	//test delete person: the adult has been deleted
	@Test
	public void deletePersonTest1_1() throws Exception{
		
		Person adult = dc.addPerson("Adult", "", "", "F", 20, "VIC");
		dc.getMember().put("Adult1", adult);
		dc.deletePerson(adult);
		assertFalse(dc.getMember().containsKey("Adult"));
			
	}

	//test delete person: friend relationship be deleted from the other person relation list
	@Test
	public void deletePersonTest1_2_1() throws Exception{  
		
		Person adult = dc.addPerson("Adult", "", "", "F", 20, "VIC");
		Person adult1 = dc.addPerson("Adult1", "", "", "F", 20, "VIC");	
		dc.getMember().put("Adult", adult);	
		adult.addRelationship("friends", adult1);	
		dc.deletePerson(adult);
		assertFalse(adult1.getRelationship().containsKey("friends"));
	}
	
	//test delete person: classmates relationship be deleted from the other person relation list
	@Test
	public void deletePersonTest1_2_2() throws Exception{  
		
		Person adult = dc.addPerson("Adult", "", "", "F", 20, "VIC");
		Person adult2 = dc.addPerson("Adult2", "", "", "F", 20, "VIC");	
		dc.getMember().put("Adult", adult);
		adult.addRelationship("classmates", adult2);	
		dc.deletePerson(adult);
		assertFalse(adult2.getRelationship().containsKey("classmates"));
	}
	
	//test delete person: colleagues relationship be deleted from the other person relation list
	@Test
	public void deletePersonTest1_2_3() throws Exception{  
		
		Person adult = dc.addPerson("Adult", "", "", "F", 20, "VIC");
		Person adult3 = dc.addPerson("Adult3", "", "", "F", 20, "VIC");	
		dc.getMember().put("Adult", adult);
		adult.addRelationship("colleagues", adult3);	
		dc.deletePerson(adult);
		assertFalse(adult3.getRelationship().containsKey("colleagues"));
	}
	
	//test delete person: couple relationship be deleted from the other person relation list
	@Test
	public void deletePersonTest1_2_4() throws Exception{  
		
		Person adult = dc.addPerson("Adult", "", "", "F", 20, "VIC");
		Person adult4 = dc.addPerson("Adult4", "", "", "F", 20, "VIC");
		dc.getMember().put("Adult", adult);
		adult.addRelationship("couple", adult4);	
		dc.deletePerson(adult);
		assertFalse(adult4.getRelationship().containsKey("couple"));
	}
	
	//the child of the person also be deleted
	@Test
	public void deletePersonTest1_3_1() throws Exception{
		
		Person adult1 = dc.addPerson("Adult1", "", "", "F", 20, "VIC");
		Person adult2 = dc.addPerson("Adult2", "", "", "F", 20, "VIC");
		Person child = dc.addPerson("Child", "", "", "F", 12, "VIC");
		
		child.addRelationship("parent", adult1);
		child.addRelationship("parent", adult2);
		
		dc.getMember().put("Adult1", adult1);
		dc.getMember().put("Adult2", adult2);
		dc.getMember().put("Child", child);
		
		dc.deletePerson(adult1);
	    
		assertFalse(dc.getMember().containsValue(child));
		
	}
	
	// test delete person: the young child of the person also be deleted
	@Test
	public void deletePersonTest1_3_2() throws Exception{
		
		Person adult1 = dc.addPerson("Adult1", "", "", "F", 20, "VIC");
		Person adult2 = dc.addPerson("Adult2", "", "", "F", 20, "VIC");
		Person youngChild = dc.addPerson("YoungChild", "", "", "F", 2, "VIC");
		
		youngChild.addRelationship("parent", adult1);
		youngChild.addRelationship("parent", adult2);
		
		dc.getMember().put("Adult1", adult1);
		dc.getMember().put("Adult2", adult2);
		dc.getMember().put("YoungChild", youngChild);
		
		dc.deletePerson(adult1);
	    
		assertFalse(dc.getMember().containsValue(youngChild));
		
	}

	//test delete person: delete child
	
	@Test
	public void deletePersonTest2_1() throws Exception{
		
		Person child = dc.addPerson("Child", "", "", "F", 12, "VIC");
		dc.getMember().put("Adult1", child);
		dc.deletePerson(child);
		assertFalse(dc.getMember().containsKey("Child"));
			
	}
	
	//test delete person: the parent relation of the child be deleted 
	
	@Test
	public void deletePersonTest2_2_1_1() throws Exception{
		
		Person child = dc.addPerson("Child", "", "", "F", 12, "VIC");
		Person parent1 = dc.addPerson("parent1", "", "", "F", 20, "VIC");
		Person parent2 = dc.addPerson("parent2", "", "", "F", 20, "VIC");
		
		child.addRelationship("parent", parent1);
		child.addRelationship("parent", parent2);
		System.out.println(child.getRelationship().get("parent").get(0).getName() + child.getRelationship().get("parent").get(1).getName());
		
		dc.deletePerson(child);

		assertFalse(parent1.getRelationship().containsValue(child));		
	}
	
	//test delete person: the parent relation of the child be deleted 
	@Test
	public void deletePersonTest2_2_1_2() throws Exception{
		
		Person child = dc.addPerson("Child", "", "", "F", 12, "VIC");
		Person parent1 = dc.addPerson("parent1", "", "", "F", 20, "VIC");
		Person parent2 = dc.addPerson("parent2", "", "", "F", 20, "VIC");
		
		
		child.addRelationship("parent", parent1);
		child.addRelationship("parent", parent2);
		
		dc.deletePerson(child);
		assertFalse(parent2.getRelationship().containsValue(child));		
	}
	
	//test delete person: the friends relation of the child be deleted 
	@Test
	public void deletePersonTest2_2_2() throws Exception{
		
		Person child1 = dc.addPerson("child1", "", "", "F", 12, "VIC");
		Person child2 = dc.addPerson("child2", "", "", "F", 12, "VIC");

		child1.addRelationship("friends", child2);	
		dc.deletePerson(child1);
		
		assertFalse(child2.getRelationship().containsKey("friends"));
			
	}
	
	//test delete person: the classmates relation of the child be deleted 
	@Test
	public void deletePersonTest2_2_3() throws Exception{
		
		Person child1 = dc.addPerson("child1", "", "", "F", 12, "VIC");
		Person child2 = dc.addPerson("child2", "", "", "F", 12, "VIC");

		child1.addRelationship("classmates", child2);	
		dc.deletePerson(child1);
		
		assertFalse(child2.getRelationship().containsKey("classmates"));
				
	}
	
	//test delete person: the siblings relation of the child be deleted 
	@Test
	public void deletePersonTest2_2_4() throws Exception{
		
		Person parent1 = new Adult("parent1", "", "", "F", 22, "VIC");
		Person parent2 = new Adult("parent2", "", "", "F", 22, "VIC");
		Person child1 = dc.addPerson("child1", "", "", "F", 12, "VIC");
		Person child2 = dc.addPerson("child2", "", "", "F", 12, "VIC");
		
	
		child1.addRelationship("parent", parent1);	
		child1.addRelationship("parent", parent2);
		child2.addRelationship("parent", parent1);
		child2.addRelationship("parent", parent2);
		
		dc.deletePerson(child1);

		assertFalse(child2.getRelationship().containsKey("siblings"));
			
	}
	
	//test delete person: delete young child
	
	@Test
	public void deletePersonTest3_1() throws Exception{
		
		Person youngChild = dc.addPerson("youngChild", "", "", "F", 2, "VIC");
		dc.getMember().put("youngChild", youngChild);
		dc.deletePerson(youngChild);
		assertFalse(dc.getMember().containsKey("youngChild"));	
	}
	
	//test delete person: the parent relation of the youngChild be deleted 
	@Test
	public void deletePersonTest3_2_1_1() throws Exception{
		
		Person youngChild = dc.addPerson("youngChild", "", "", "F", 2, "VIC");
		Person parent1 = dc.addPerson("parent1", "", "", "F", 20, "VIC");
		Person parent2 = dc.addPerson("parent2", "", "", "F", 20, "VIC");
	
		
		youngChild.addRelationship("parent", parent1);
		youngChild.addRelationship("parent", parent2);
		System.out.println(youngChild.getRelationship().get("parent").get(0).getName() + youngChild.getRelationship().get("parent").get(1).getName());
		
		dc.deletePerson(youngChild);

		assertFalse(parent1.getRelationship().containsValue(youngChild));		
	}
	
	//test delete person: the parent relation of the young child be deleted 
	@Test
	public void deletePersonTest3_2_1_2() throws Exception{
		
		Person youngChild = dc.addPerson("youngChild", "", "", "F", 2, "VIC");
		Person parent1 = dc.addPerson("parent1", "", "", "F", 20, "VIC");
		Person parent2 = dc.addPerson("parent2", "", "", "F", 20, "VIC");
	
		
		youngChild.addRelationship("parent", parent1);
		youngChild.addRelationship("parent", parent2);
		
		dc.deletePerson(youngChild);
		assertFalse(parent2.getRelationship().containsValue(youngChild));		
	}
	
	//test delete person: the siblings relation of the young child be deleted 
	@Test
	public void deletePersonTest3_2_4() throws Exception{
		
		Person parent1 = new Adult("parent1", "", "", "F", 22, "VIC");
		Person parent2 = new Adult("parent2", "", "", "F", 22, "VIC");
		Person youngChild1 = dc.addPerson("child1", "", "", "F", 2, "VIC");
		Person youngChild2 = dc.addPerson("child2", "", "", "F", 12, "VIC");
		
	
		youngChild1.addRelationship("parent", parent1);	
		youngChild1.addRelationship("parent", parent2);
		youngChild2.addRelationship("parent", parent1);
		youngChild2.addRelationship("parent", parent2);
		
		dc.deletePerson(youngChild1);

		assertFalse(youngChild2.getRelationship().containsKey("siblings"));
			
	}
	
	

}
