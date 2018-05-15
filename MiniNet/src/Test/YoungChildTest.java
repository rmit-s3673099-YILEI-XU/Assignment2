package Test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Exceptions.*;
import people.*;

public class YoungChildTest {

	Person youngChild;

	@Before
	public void setUp() {
		youngChild = new YoungChild("Emma", "", "", "F", 2, "VIC");
	}
	

	@Test
	public void addRelationshipParentTest1() throws Exception {

		// only one parent, won't insert to relationship network
		Person sherry = new Adult("Sherry", "", "", "F", 22, "VIC");
		youngChild.addRelationship("parent", sherry);
		assertTrue(youngChild.getRelationship().get("parent").size() == 0);

	}

	@Test
	public void addRelationshipParentTest2_1() throws Exception {

		Person sherry1 = new Adult("Sherry", "", "", "F", 22, "VIC");
		Person sherry2 = new Adult("Sherry", "", "", "F", 22, "VIC");
		youngChild.addRelationship("parent", sherry1);
		youngChild.addRelationship("parent", sherry2);

		assertEquals(youngChild.getRelationship().get("parent").get(0), sherry1);
		

	}
	
	@Test
	public void addRelationshipParentTest2_2() throws Exception {

		Person sherry1 = new Adult("Sherry", "", "", "F", 22, "VIC");
		Person sherry2 = new Adult("Sherry", "", "", "F", 22, "VIC");
		youngChild.addRelationship("parent", sherry1);
		youngChild.addRelationship("parent", sherry2);

		assertEquals(youngChild.getRelationship().get("parent").get(1), sherry2);
		

	}
	
	@Test
	public void addRelationshipParentTest2_3() throws Exception {

		Person sherry1 = new Adult("Sherry", "", "", "F", 22, "VIC");
		Person sherry2 = new Adult("Sherry", "", "", "F", 22, "VIC");
		youngChild.addRelationship("parent", sherry1);
		youngChild.addRelationship("parent", sherry2);

		assertEquals(sherry1.getRelationship().get("child").get(0), youngChild);
		

	}
	
	@Test
	public void addRelationshipParentTest2_4() throws Exception {

		Person sherry1 = new Adult("Sherry", "", "", "F", 22, "VIC");
		Person sherry2 = new Adult("Sherry", "", "", "F", 22, "VIC");
		youngChild.addRelationship("parent", sherry1);
		youngChild.addRelationship("parent", sherry2);

		assertEquals(sherry2.getRelationship().get("child").get(0), youngChild);

	}
	

	@Test
	public void removeRelationshipParentTest1_1() throws Exception {

		Person sherry1 = new Adult("Sherry", "", "", "F", 22, "VIC");
		Person sherry2 = new Adult("Sherry", "", "", "F", 22, "VIC");
		youngChild.addRelationship("parent", sherry1);
		youngChild.addRelationship("parent", sherry2);
		youngChild.removeRelationship("parent", sherry1);
		youngChild.removeRelationship("parent", sherry2);

		assertTrue(sherry1.getRelationship().get("child").size() == 0);

	}
	
	@Test
	public void removeRelationshipParentTest1_2() throws Exception {

		Person sherry1 = new Adult("Sherry", "", "", "F", 22, "VIC");
		Person sherry2 = new Adult("Sherry", "", "", "F", 22, "VIC");
		youngChild.addRelationship("parent", sherry1);
		youngChild.addRelationship("parent", sherry2);
		youngChild.removeRelationship("parent", sherry1);
		youngChild.removeRelationship("parent", sherry2);

		assertTrue(sherry2.getRelationship().get("child").size() == 0);

	}

	@Test
	public void removeRelationshipParentTest2_1() throws Exception {

		Person sherry1 = new Adult("Sherry", "", "", "F", 22, "VIC");
		Person sherry2 = new Adult("Sherry", "", "", "F", 22, "VIC");
		youngChild.addRelationship("parent", sherry1);
		youngChild.addRelationship("parent", sherry2);
		youngChild.removeRelationship("parent", sherry1);

		assertTrue(sherry1.getRelationship().get("child").size() == 0);

	}
	
	@Test
	public void removeRelationshipParentTest2_2() throws Exception {

		Person sherry1 = new Adult("Sherry", "", "", "F", 22, "VIC");
		Person sherry2 = new Adult("Sherry", "", "", "F", 22, "VIC");
		youngChild.addRelationship("parent", sherry1);
		youngChild.addRelationship("parent", sherry2);
		youngChild.removeRelationship("parent", sherry1);

		assertTrue(sherry1.getRelationship().get("child").size() == 0);
	}

	@Test
	public void removeRelationshipParentTest3_1() throws Exception {

		Person sherry1 = new Adult("Sherry", "", "", "F", 22, "VIC");
		Person sherry2 = new Adult("Sherry", "", "", "F", 22, "VIC");
		youngChild.addRelationship("parent", sherry1);
		youngChild.addRelationship("parent", sherry2);
		youngChild.removeRelationship("parent", sherry2);

		assertTrue(sherry1.getRelationship().get("child").size() == 0);
		
	}
	
	@Test
	public void removeRelationshipParentTest3_2() throws Exception {

		Person sherry1 = new Adult("Sherry", "", "", "F", 22, "VIC");
		Person sherry2 = new Adult("Sherry", "", "", "F", 22, "VIC");
		youngChild.addRelationship("parent", sherry1);
		youngChild.addRelationship("parent", sherry2);
		youngChild.removeRelationship("parent", sherry2);

		assertTrue(sherry2.getRelationship().get("child").size() == 0);
		
	}
	
	@Test 
	public void siblingRelationTest1_1() throws Exception{
		
		Person sherry1 = new Adult("Sherry", "", "", "F", 22, "VIC");
		Person sherry2 = new Adult("Sherry", "", "", "F", 22, "VIC");
		Person emma = new YoungChild("emma", "", "", "F", 2, "VIC");
		youngChild.addRelationship("parent", sherry1);
		youngChild.addRelationship("parent", sherry2);
		emma.addRelationship("parent", sherry1);
		emma.addRelationship("parent", sherry2);
		
		assertTrue(youngChild.getRelationship().containsKey("sibling"));
				
	}
	
	@Test 
	public void siblingRelationTest1_2() throws Exception{
		
		Person sherry1 = new Adult("Sherry", "", "", "F", 22, "VIC");
		Person sherry2 = new Adult("Sherry", "", "", "F", 22, "VIC");
		Person emma = new YoungChild("emma", "", "", "F", 2, "VIC");
		youngChild.addRelationship("parent", sherry1);
		youngChild.addRelationship("parent", sherry2);
		emma.addRelationship("parent", sherry1);
		emma.addRelationship("parent", sherry2);
		
		assertTrue(emma.getRelationship().containsKey("sibling"));
				
	}
	
	@Test 
	public void siblingRelationTest1_3() throws Exception{
		
		Person sherry1 = new Adult("Sherry", "", "", "F", 22, "VIC");
		Person sherry2 = new Adult("Sherry", "", "", "F", 22, "VIC");
		Person emma = new YoungChild("emma", "", "", "F", 2, "VIC");
		youngChild.addRelationship("parent", sherry1);
		youngChild.addRelationship("parent", sherry2);
		emma.addRelationship("parent", sherry1);
		emma.addRelationship("parent", sherry2);
		
		assertEquals(youngChild.getRelationship().get("sibling").get(0), emma);
				
	}
	
	@Test 
	public void siblingRelationTest1_4() throws Exception{
		
		Person sherry1 = new Adult("Sherry", "", "", "F", 22, "VIC");
		Person sherry2 = new Adult("Sherry", "", "", "F", 22, "VIC");
		Person emma = new YoungChild("emma", "", "", "F", 2, "VIC");
		youngChild.addRelationship("parent", sherry1);
		youngChild.addRelationship("parent", sherry2);
		emma.addRelationship("parent", sherry1);
		emma.addRelationship("parent", sherry2);
		
		assertEquals(emma.getRelationship().get("sibling").get(0), youngChild);
				
	}

}
