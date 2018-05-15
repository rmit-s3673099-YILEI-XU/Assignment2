package Test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Exceptions.*;
import people.*;

public class ChildTest {

	Person child;

	@Before
	public void setUp() {
		child = new Child("Emma", "", "", "F", 10, "VIC");
	}

	@Test
	public void addRelationshipFriendTest1_1() throws Exception {

		Person sherry = new Child("Sherry", "", "", "F", 13, "VIC");
		child.addRelationship("friends", sherry);
		assertTrue(child.getRelationship().containsKey("friends"));
	}
	
	public void addRelationshipFriendTest1_2() throws Exception {

		Person sherry = new Child("Sherry", "", "", "F", 13, "VIC");
		child.addRelationship("friends", sherry);
		assertEquals(child.getRelationship().get("friends").get(0), sherry);
		
	}
	
	public void addRelationshipFriendTest1_3() throws Exception {

		Person sherry = new Child("Sherry", "", "", "F", 13, "VIC");
		child.addRelationship("friends", sherry);
		assertTrue(sherry.getRelationship().containsKey("friends"));
	}
	
	public void addRelationshipFriendTest1_4() throws Exception {

		Person sherry = new Child("Sherry", "", "", "F", 13, "VIC");
		child.addRelationship("friends", sherry);
		assertEquals(sherry.getRelationship().get("friends").get(0), child);
	}

	@Test(expected = NotToBeFriendsException.class)
	public void addRelationshipFriendTest2() throws Exception {

		Person sherry = new Child("Sherry", "", "", "F", 14, "VIC");
		child.addRelationship("friends", sherry);

	}

	@Test(expected = NotToBeFriendsException.class)
	public void addRelationshipFriendTest3() throws Exception {

		Person sherry = new Adult("Sherry", "", "", "F", 20, "VIC");
		child.addRelationship("friends", sherry);

	}

	@Test(expected = TooYoungException.class)
	public void addRelationshipFriendTest4() throws Exception {

		Person sherry = new YoungChild("Sherry", "", "", "F", 2, "VIC");
		child.addRelationship("friends", sherry);

	}

	@Test
	public void addRelationshipClassmateTest1_1() throws Exception {

		Person sherry = new Child("Sherry", "", "", "F", 13, "VIC");
		child.addRelationship("classmates", sherry);
		assertTrue(child.getRelationship().containsKey("classmates"));
	}
	
	@Test
	public void addRelationshipClassmateTest1_2() throws Exception {

		Person sherry = new Child("Sherry", "", "", "F", 13, "VIC");
		child.addRelationship("classmates", sherry);
		assertEquals(child.getRelationship().get("classmates").get(0), sherry);
	}
	
	@Test
	public void addRelationshipClassmateTest1_3() throws Exception {

		Person sherry = new Child("Sherry", "", "", "F", 13, "VIC");
		child.addRelationship("classmates", sherry);
		assertTrue(sherry.getRelationship().containsKey("classmates"));
	}
	
	@Test
	public void addRelationshipClassmateTest1_4() throws Exception {

		Person sherry = new Child("Sherry", "", "", "F", 13, "VIC");
		child.addRelationship("classmates", sherry);
		assertEquals(sherry.getRelationship().get("classmates").get(0), child);
	}

	@Test
	public void addRelationshipClassmateTest2_1() throws Exception {

		Person sherry = new Adult("Sherry", "", "", "F", 20, "VIC");
		child.addRelationship("classmates", sherry);
		assertTrue(child.getRelationship().containsKey("classmates"));
	}
	
	@Test
	public void addRelationshipClassmateTest2_2() throws Exception {

		Person sherry = new Adult("Sherry", "", "", "F", 20, "VIC");
		child.addRelationship("classmates", sherry);
		assertEquals(child.getRelationship().get("classmates").get(0), sherry);
	}
	
	@Test
	public void addRelationshipClassmateTest2_3() throws Exception {

		Person sherry = new Adult("Sherry", "", "", "F", 20, "VIC");
		child.addRelationship("classmates", sherry);
		assertTrue(sherry.getRelationship().containsKey("classmates"));
	}
	
	@Test
	public void addRelationshipClassmateTest2_4() throws Exception {

		Person sherry = new Adult("Sherry", "", "", "F", 20, "VIC");
		child.addRelationship("classmates", sherry);
		assertEquals(sherry.getRelationship().get("classmates").get(0), child);
	}

	@Test(expected = NotToBeClassmatesException.class)
	public void addRelationshipClassmateTest3() throws Exception {

		Person sherry = new YoungChild("Sherry", "", "", "F", 2, "VIC");
		child.addRelationship("classmates", sherry);

	}

	@Test
	public void addRelationshipParentTest1() throws Exception {

		// only one parent,won't insert to relationship network
		Person sherry = new Adult("Sherry", "", "", "F", 23, "VIC");
		child.addRelationship("parent", sherry);
		assertTrue(child.getRelationship().get("parent").size() == 0);

	}

	@Test
	public void addRelationshipParentTest2_1() throws Exception {

		Person sherry1 = new Adult("Sherry", "", "", "F", 13, "VIC");
		Person sherry2 = new Adult("Sherry", "", "", "F", 13, "VIC");
		child.addRelationship("parent", sherry1);
		child.addRelationship("parent", sherry2);

		assertEquals(child.getRelationship().get("parent").get(0), sherry1);
		

	}
	
	@Test
	public void addRelationshipParentTest2_2() throws Exception {

		Person sherry1 = new Adult("Sherry", "", "", "F", 13, "VIC");
		Person sherry2 = new Adult("Sherry", "", "", "F", 13, "VIC");
		child.addRelationship("parent", sherry1);
		child.addRelationship("parent", sherry2);

		assertEquals(child.getRelationship().get("parent").get(1), sherry2);
		

	}
	
	@Test
	public void addRelationshipParentTest2_3() throws Exception {

		Person sherry1 = new Adult("Sherry", "", "", "F", 13, "VIC");
		Person sherry2 = new Adult("Sherry", "", "", "F", 13, "VIC");
		child.addRelationship("parent", sherry1);
		child.addRelationship("parent", sherry2);

		assertEquals(sherry1.getRelationship().get("child").get(0), child);
		

	}
	
	@Test
	public void addRelationshipParentTest2_4() throws Exception {

		Person sherry1 = new Adult("Sherry", "", "", "F", 13, "VIC");
		Person sherry2 = new Adult("Sherry", "", "", "F", 13, "VIC");
		child.addRelationship("parent", sherry1);
		child.addRelationship("parent", sherry2);

		assertEquals(sherry2.getRelationship().get("child").get(0), child);

	}

	@Test
	public void removeRelationshipFriendTest() throws Exception {

		Person sherry = new Child("Sherry", "", "", "F", 13, "VIC");
		child.addRelationship("friends", sherry);
		child.removeRelationship("friends", sherry);

		assertFalse(sherry.getRelationship().containsKey("friends"));

	}

	@Test
	public void removeRelationshipClassmateTest() throws Exception {

		Person sherry = new Child("Sherry", "", "", "F", 13, "VIC");
		child.addRelationship("classmates", sherry);
		child.removeRelationship("classmates", sherry);

		assertFalse(sherry.getRelationship().containsKey("classmates"));

	}

	@Test
	public void removeRelationshipParentTest1_1() throws Exception {

		Person sherry1 = new Adult("Sherry", "", "", "F", 13, "VIC");
		Person sherry2 = new Adult("Sherry", "", "", "F", 13, "VIC");
		child.addRelationship("parent", sherry1);
		child.addRelationship("parent", sherry2);
		child.removeRelationship("parent", sherry1);
		child.removeRelationship("parent", sherry2);

		assertTrue(sherry1.getRelationship().get("child").size() == 0);

	}
	
	@Test
	public void removeRelationshipParentTest1_2() throws Exception {

		Person sherry1 = new Adult("Sherry", "", "", "F", 13, "VIC");
		Person sherry2 = new Adult("Sherry", "", "", "F", 13, "VIC");
		child.addRelationship("parent", sherry1);
		child.addRelationship("parent", sherry2);
		child.removeRelationship("parent", sherry1);
		child.removeRelationship("parent", sherry2);

		assertTrue(sherry2.getRelationship().get("child").size() == 0);

	}

	@Test
	public void removeRelationshipParentTest2_1() throws Exception {

		Person sherry1 = new Adult("Sherry", "", "", "F", 13, "VIC");
		Person sherry2 = new Adult("Sherry", "", "", "F", 13, "VIC");
		child.addRelationship("parent", sherry1);
		child.addRelationship("parent", sherry2);
		child.removeRelationship("parent", sherry1);

		assertTrue(sherry1.getRelationship().get("child").size() == 0);

	}
	
	@Test
	public void removeRelationshipParentTest2_2() throws Exception {

		Person sherry1 = new Adult("Sherry", "", "", "F", 13, "VIC");
		Person sherry2 = new Adult("Sherry", "", "", "F", 13, "VIC");
		child.addRelationship("parent", sherry1);
		child.addRelationship("parent", sherry2);
		child.removeRelationship("parent", sherry1);

		assertTrue(sherry1.getRelationship().get("child").size() == 0);
	}

	@Test
	public void removeRelationshipParentTest3_1() throws Exception {

		Person sherry1 = new Adult("Sherry", "", "", "F", 13, "VIC");
		Person sherry2 = new Adult("Sherry", "", "", "F", 13, "VIC");
		child.addRelationship("parent", sherry1);
		child.addRelationship("parent", sherry2);
		child.removeRelationship("parent", sherry2);

		assertTrue(sherry1.getRelationship().get("child").size() == 0);
		
	}
	
	@Test
	public void removeRelationshipParentTest3_2() throws Exception {

		Person sherry1 = new Adult("Sherry", "", "", "F", 13, "VIC");
		Person sherry2 = new Adult("Sherry", "", "", "F", 13, "VIC");
		child.addRelationship("parent", sherry1);
		child.addRelationship("parent", sherry2);
		child.removeRelationship("parent", sherry2);

		assertTrue(sherry2.getRelationship().get("child").size() == 0);

	}
	
	@Test 
	public void siblingRelationTest1_1() throws Exception{
		
		Person sherry1 = new Adult("Sherry", "", "", "F", 22, "VIC");
		Person sherry2 = new Adult("Sherry", "", "", "F", 22, "VIC");
		Person emma = new Child("emma", "", "", "F", 12, "VIC");
		child.addRelationship("parent", sherry1);
		child.addRelationship("parent", sherry2);
		emma.addRelationship("parent", sherry1);
		emma.addRelationship("parent", sherry2);
		
		assertTrue(child.getRelationship().containsKey("sibling"));
				
	}
	
	@Test 
	public void siblingRelationTest1_2() throws Exception{
		
		Person sherry1 = new Adult("Sherry", "", "", "F", 22, "VIC");
		Person sherry2 = new Adult("Sherry", "", "", "F", 22, "VIC");
		Person emma = new YoungChild("emma", "", "", "F", 12, "VIC");
		child.addRelationship("parent", sherry1);
		child.addRelationship("parent", sherry2);
		emma.addRelationship("parent", sherry1);
		emma.addRelationship("parent", sherry2);
		
		assertTrue(emma.getRelationship().containsKey("sibling"));
				
	}
	
	@Test 
	public void siblingRelationTest1_3() throws Exception{
		
		Person sherry1 = new Adult("Sherry", "", "", "F", 22, "VIC");
		Person sherry2 = new Adult("Sherry", "", "", "F", 22, "VIC");
		Person emma = new YoungChild("emma", "", "", "F", 12, "VIC");
		child.addRelationship("parent", sherry1);
		child.addRelationship("parent", sherry2);
		emma.addRelationship("parent", sherry1);
		emma.addRelationship("parent", sherry2);
		
		assertEquals(child.getRelationship().get("sibling").get(0), emma);
				
	}
	
	@Test 
	public void siblingRelationTest1_4() throws Exception{
		
		Person sherry1 = new Adult("Sherry", "", "", "F", 22, "VIC");
		Person sherry2 = new Adult("Sherry", "", "", "F", 22, "VIC");
		Person emma = new YoungChild("emma", "", "", "F", 12, "VIC");
		child.addRelationship("parent", sherry1);
		child.addRelationship("parent", sherry2);
		emma.addRelationship("parent", sherry1);
		emma.addRelationship("parent", sherry2);
		
		assertEquals(emma.getRelationship().get("sibling").get(0), child);
				
	}

}