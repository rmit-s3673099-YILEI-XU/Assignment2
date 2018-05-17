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

	// test add friend: relation type be added
	@Test
	public void addRelationshipFriendTest1_1() throws Exception {
		Person sherry = new Child("Sherry", "", "", "F", 13, "VIC");
		child.addRelationship("friends", sherry);
		assertTrue(child.getRelationship().containsKey("friends"));
	}

	// test add friend: related person be added
	public void addRelationshipFriendTest1_2() throws Exception {
		Person sherry = new Child("Sherry", "", "", "F", 13, "VIC");
		child.addRelationship("friends", sherry);
		assertEquals(child.getRelationship().get("friends").get(0), sherry);
	}

	// test add friend: related person's relation type be added
	public void addRelationshipFriendTest1_3() throws Exception {
		Person sherry = new Child("Sherry", "", "", "F", 13, "VIC");
		child.addRelationship("friends", sherry);
		assertTrue(sherry.getRelationship().containsKey("friends"));
	}

	// test add friend: this person has been added to related person's relation list
	public void addRelationshipFriendTest1_4() throws Exception {
		Person sherry = new Child("Sherry", "", "", "F", 13, "VIC");
		child.addRelationship("friends", sherry);
		assertEquals(sherry.getRelationship().get("friends").get(0), child);
	}

	// test add friend: age gap over 3 years
	@Test(expected = NotToBeFriendsException.class)
	public void addRelationshipFriendTest2() throws Exception {
		Person sherry = new Child("Sherry", "", "", "F", 14, "VIC");
		child.addRelationship("friends", sherry);
	}

	// test add friend: add an adult friend to a child
	@Test(expected = NotToBeFriendsException.class)
	public void addRelationshipFriendTest3() throws Exception {
		Person sherry = new Adult("Sherry", "", "", "F", 20, "VIC");
		child.addRelationship("friends", sherry);
	}

	// test add friend: add young child friend to a child
	@Test(expected = TooYoungException.class)
	public void addRelationshipFriendTest4() throws Exception {
		Person sherry = new YoungChild("Sherry", "", "", "F", 2, "VIC");
		child.addRelationship("friends", sherry);
	}

	// test add colleagues to child
	@Test(expected = NotToBeColleaguesException.class)
	public void addRelationshipColleaguesTest1() throws Exception {
		Person sherry = new Child("Sherry", "", "", "F", 14, "VIC");
		child.addRelationship("colleagues", sherry);
	}

	// test add a couple to child
	@Test(expected = NotToBeCoupledException.class)
	public void addRelationshipCoupleTest1_1() throws Exception {
		Person sherry = new Child("Sherry", "", "", "F", 24, "VIC");
		child.addRelationship("couple", sherry);
	}
	
	// test add child couple to the adult
		@Test(expected = NotToBeCoupledException.class)
		public void addRelationshipCoupleTest1_2() throws Exception {
			Person sherry = new Child("Sherry", "", "", "F", 14, "VIC");
			Person adult = new Adult("Sherry", "", "", "F", 24, "VIC");
			adult.addRelationship("couple", sherry);
		}

	// test add classmate: relation type be added
	@Test
	public void addRelationshipClassmateTest1_1() throws Exception {

		Person sherry = new Child("Sherry", "", "", "F", 13, "VIC");
		child.addRelationship("classmates", sherry);
		assertTrue(child.getRelationship().containsKey("classmates"));
	}

	// test add classmate: related person be added
	@Test
	public void addRelationshipClassmateTest1_2() throws Exception {

		Person sherry = new Child("Sherry", "", "", "F", 13, "VIC");
		child.addRelationship("classmates", sherry);
		assertEquals(child.getRelationship().get("classmates").get(0), sherry);
	}

	// test add classmate: related person's relation type be added
	@Test
	public void addRelationshipClassmateTest1_3() throws Exception {

		Person sherry = new Child("Sherry", "", "", "F", 13, "VIC");
		child.addRelationship("classmates", sherry);
		assertTrue(sherry.getRelationship().containsKey("classmates"));
	}

	// test add classmate: related person's relationship list has this person
	@Test
	public void addRelationshipClassmateTest1_4() throws Exception {

		Person sherry = new Child("Sherry", "", "", "F", 13, "VIC");
		child.addRelationship("classmates", sherry);
		assertEquals(sherry.getRelationship().get("classmates").get(0), child);
	}

	// test add classmate: add adult classmate: relation type has been added
	@Test
	public void addRelationshipClassmateTest2_1() throws Exception {

		Person sherry = new Adult("Sherry", "", "", "F", 20, "VIC");
		child.addRelationship("classmates", sherry);
		assertTrue(child.getRelationship().containsKey("classmates"));
	}

	// test add classmate: add adult classmate: related person has been added
	@Test
	public void addRelationshipClassmateTest2_2() throws Exception {

		Person sherry = new Adult("Sherry", "", "", "F", 20, "VIC");
		child.addRelationship("classmates", sherry);
		assertEquals(child.getRelationship().get("classmates").get(0), sherry);
	}

	// test add adult classmate: related person's relation type has been added
	@Test
	public void addRelationshipClassmateTest2_3() throws Exception {

		Person sherry = new Adult("Sherry", "", "", "F", 20, "VIC");
		child.addRelationship("classmates", sherry);
		assertTrue(sherry.getRelationship().containsKey("classmates"));
	}

	// test add adult classmate: related person's relation list contain this person
	@Test
	public void addRelationshipClassmateTest2_4() throws Exception {

		Person sherry = new Adult("Sherry", "", "", "F", 20, "VIC");
		child.addRelationship("classmates", sherry);
		assertEquals(sherry.getRelationship().get("classmates").get(0), child);
	}

	// test add classmate: add young child classmate
	@Test(expected = NotToBeClassmatesException.class)
	public void addRelationshipClassmateTest3() throws Exception {

		Person sherry = new YoungChild("Sherry", "", "", "F", 2, "VIC");
		child.addRelationship("classmates", sherry);

	}

	// test add parent: only one parent,won't insert to relationship network
	@Test
	public void addRelationshipParentTest1() throws Exception {

		Person sherry = new Adult("Sherry", "", "", "F", 23, "VIC");
		child.addRelationship("parent", sherry);
		assertTrue(child.getRelationship().get("parent").size() == 0);

	}

	// test add parent: parent1 has been added
	@Test
	public void addRelationshipParentTest2_1() throws Exception {

		Person sherry1 = new Adult("Sherry", "", "", "F", 13, "VIC");
		Person sherry2 = new Adult("Sherry", "", "", "F", 13, "VIC");
		child.addRelationship("parent", sherry1);
		child.addRelationship("parent", sherry2);

		assertEquals(child.getRelationship().get("parent").get(0), sherry1);

	}

	// test add parent: parent2 has been added
	@Test
	public void addRelationshipParentTest2_2() throws Exception {

		Person sherry1 = new Adult("Sherry", "", "", "F", 13, "VIC");
		Person sherry2 = new Adult("Sherry", "", "", "F", 13, "VIC");
		child.addRelationship("parent", sherry1);
		child.addRelationship("parent", sherry2);

		assertEquals(child.getRelationship().get("parent").get(1), sherry2);

	}

	// test add parent: child has been added to the parent1
	@Test
	public void addRelationshipParentTest2_3() throws Exception {

		Person sherry1 = new Adult("Sherry", "", "", "F", 13, "VIC");
		Person sherry2 = new Adult("Sherry", "", "", "F", 13, "VIC");
		child.addRelationship("parent", sherry1);
		child.addRelationship("parent", sherry2);

		assertEquals(sherry1.getRelationship().get("child").get(0), child);
	}

	// test add parent: child has been added to the parent2
	@Test
	public void addRelationshipParentTest2_4() throws Exception {

		Person sherry1 = new Adult("Sherry", "", "", "F", 13, "VIC");
		Person sherry2 = new Adult("Sherry", "", "", "F", 13, "VIC");
		child.addRelationship("parent", sherry1);
		child.addRelationship("parent", sherry2);

		assertEquals(sherry2.getRelationship().get("child").get(0), child);

	}

	// test remove friend
	@Test
	public void removeRelationshipFriendTest() throws Exception {

		Person sherry = new Child("Sherry", "", "", "F", 13, "VIC");
		child.addRelationship("friends", sherry);
		child.removeRelationship("friends", sherry);

		assertFalse(sherry.getRelationship().containsKey("friends"));

	}

	// test remove classmate
	@Test
	public void removeRelationshipClassmateTest() throws Exception {

		Person sherry = new Child("Sherry", "", "", "F", 13, "VIC");
		child.addRelationship("classmates", sherry);
		child.removeRelationship("classmates", sherry);

		assertFalse(sherry.getRelationship().containsKey("classmates"));

	}

	// test remove parents: parents type has been removed from child
	@Test
	public void removeRelationshipParentTest1_1() throws Exception {

		Person sherry1 = new Adult("Sherry", "", "", "F", 22, "VIC");
		Person sherry2 = new Adult("Sherry", "", "", "F", 22, "VIC");
		child.addRelationship("parent", sherry1);
		child.addRelationship("parent", sherry2);
		child.removeRelationship("parent", sherry1);
		child.removeRelationship("parent", sherry2);

		assertFalse(sherry1.getRelationship().containsKey("child"));

	}

	// test remove parents: parent1 has been removed from child
	@Test
	public void removeRelationshipParentTest1_2() throws Exception {

		Person sherry1 = new Adult("Sherry", "", "", "F", 22, "VIC");
		Person sherry2 = new Adult("Sherry", "", "", "F", 22, "VIC");
		child.addRelationship("parents", sherry1);
		child.addRelationship("parents", sherry2);
		child.removeRelationship("parents", sherry1);
		child.removeRelationship("parents", sherry2);

		assertFalse(child.getRelationship().containsValue(sherry1));

	}

	// test remove parents: parent2 has been removed from the child
	@Test
	public void removeRelationshipParentTest1_3() throws Exception {

		Person sherry1 = new Adult("Sherry", "", "", "F", 22, "VIC");
		Person sherry2 = new Adult("Sherry", "", "", "F", 22, "VIC");
		child.addRelationship("parents", sherry1);
		child.addRelationship("parents", sherry2);
		child.removeRelationship("parents", sherry1);
		child.removeRelationship("parents", sherry2);

		assertFalse(child.getRelationship().containsValue(sherry2));

	}

	// test remove parent: child relation type has been deleted from parent1
	@Test
	public void removeRelationshipParentTest2_1() throws Exception {

		Person sherry1 = new Adult("Sherry", "", "", "F", 22, "VIC");
		Person sherry2 = new Adult("Sherry", "", "", "F", 22, "VIC");
		child.addRelationship("parent", sherry1);
		child.addRelationship("parent", sherry2);
		child.removeRelationship("parent", sherry1);

		assertFalse(sherry1.getRelationship().containsKey("child"));

	}

	// test remove parent: child has been deleted from parent1
	@Test
	public void removeRelationshipParentTest2_2() throws Exception {

		Person sherry1 = new Adult("Sherry", "", "", "F", 22, "VIC");
		Person sherry2 = new Adult("Sherry", "", "", "F", 22, "VIC");
		child.addRelationship("parent", sherry1);
		child.addRelationship("parent", sherry2);
		child.removeRelationship("parent", sherry1);

		assertFalse(sherry1.getRelationship().containsValue(child));
	}

	// test remove parent: child relation type has been deleted from parent2
	@Test
	public void removeRelationshipParentTest3_1() throws Exception {

		Person sherry1 = new Adult("Sherry", "", "", "F", 22, "VIC");
		Person sherry2 = new Adult("Sherry", "", "", "F", 22, "VIC");
		child.addRelationship("parent", sherry1);
		child.addRelationship("parent", sherry2);
		child.removeRelationship("parent", sherry2);

		assertFalse(sherry2.getRelationship().containsKey("child"));

	}

	// test remove parent: child has been deleted from parent2
	@Test
	public void removeRelationshipParentTest3_2() throws Exception {

		Person sherry1 = new Adult("Sherry", "", "", "F", 22, "VIC");
		Person sherry2 = new Adult("Sherry", "", "", "F", 22, "VIC");
		child.addRelationship("parent", sherry1);
		child.addRelationship("parent", sherry2);
		child.removeRelationship("parent", sherry2);

		assertFalse(sherry2.getRelationship().containsValue(child));

	}

	// test add siblings: the relation type has been added
	@Test
	public void siblingRelationTest1_1() throws Exception {

		Person sherry1 = new Adult("Sherry", "", "", "F", 22, "VIC");
		Person sherry2 = new Adult("Sherry", "", "", "F", 22, "VIC");
		Person emma = new Child("emma", "", "", "F", 12, "VIC");
		child.addRelationship("parent", sherry1);
		child.addRelationship("parent", sherry2);
		emma.addRelationship("parent", sherry1);
		emma.addRelationship("parent", sherry2);

		assertTrue(child.getRelationship().containsKey("sibling"));

	}

	// test add siblings: the relation type has been added to the related person
	@Test
	public void siblingRelationTest1_2() throws Exception {

		Person sherry1 = new Adult("Sherry", "", "", "F", 22, "VIC");
		Person sherry2 = new Adult("Sherry", "", "", "F", 22, "VIC");
		Person emma = new YoungChild("emma", "", "", "F", 12, "VIC");
		child.addRelationship("parent", sherry1);
		child.addRelationship("parent", sherry2);
		emma.addRelationship("parent", sherry1);
		emma.addRelationship("parent", sherry2);

		assertTrue(emma.getRelationship().containsKey("sibling"));

	}

	// test add sibling: the related person has been added
	@Test
	public void siblingRelationTest1_3() throws Exception {

		Person sherry1 = new Adult("Sherry", "", "", "F", 22, "VIC");
		Person sherry2 = new Adult("Sherry", "", "", "F", 22, "VIC");
		Person emma = new YoungChild("emma", "", "", "F", 12, "VIC");
		child.addRelationship("parent", sherry1);
		child.addRelationship("parent", sherry2);
		emma.addRelationship("parent", sherry1);
		emma.addRelationship("parent", sherry2);

		assertEquals(child.getRelationship().get("sibling").get(0), emma);

	}

	// test add sibling: the person has been added to the related person
	@Test
	public void siblingRelationTest1_4() throws Exception {

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