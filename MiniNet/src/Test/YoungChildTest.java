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

	// test young child is too young to have friend relationship
	@Test(expected = TooYoungException.class)
	public void addRelationshipFriendTest4() throws Exception {
		Person sherry = new YoungChild("Sherry", "", "", "F", 2, "VIC");
		youngChild.addRelationship("friends", sherry);
	}

	// test add colleagues to young child
	@Test(expected = NotToBeColleaguesException.class)
	public void addRelationshipColleaguesTest1() throws Exception {
		Person sherry = new Child("Sherry", "", "", "F", 2, "VIC");
		youngChild.addRelationship("colleagues", sherry);
	}

	// test add a couple to young child
	@Test(expected = NotToBeCoupledException.class)
	public void addRelationshipCoupleTest1_1() throws Exception {
		Person sherry = new Child("Sherry", "", "", "F", 24, "VIC");
		youngChild.addRelationship("couple", sherry);
	}

	// test add young child couple to the adult
	@Test(expected = NotToBeCoupledException.class)
	public void addRelationshipCoupleTest1_2() throws Exception {
		Person sherry = new Child("Sherry", "", "", "F", 1, "VIC");
		Person adult = new Adult("Sherry", "", "", "F", 24, "VIC");
		adult.addRelationship("couple", sherry);
	}
	
	// test add classmates to young child
	@Test(expected = NotToBeClassmatesException.class)
	public void addRelationshipClassmatesTest1() throws Exception {
		Person sherry = new Child("Sherry", "", "", "F", 2, "VIC");
		youngChild.addRelationship("classmates", sherry);
	}

	// test add parent: only one parent, won't insert to relationship network
	@Test
	public void addRelationshipParentTest1() throws Exception {

		Person sherry = new Adult("Sherry", "", "", "F", 22, "VIC");
		youngChild.addRelationship("parent", sherry);
		assertTrue(youngChild.getRelationship().get("parent").size() == 0);

	}

	// test add parent: the first parent has been added
	@Test
	public void addRelationshipParentTest2_1() throws Exception {

		Person sherry1 = new Adult("Sherry", "", "", "F", 22, "VIC");
		Person sherry2 = new Adult("Sherry", "", "", "F", 22, "VIC");
		youngChild.addRelationship("parent", sherry1);
		youngChild.addRelationship("parent", sherry2);

		assertEquals(youngChild.getRelationship().get("parent").get(0), sherry1);

	}

	// test add parent: the second parent has been added
	@Test
	public void addRelationshipParentTest2_2() throws Exception {

		Person sherry1 = new Adult("Sherry", "", "", "F", 22, "VIC");
		Person sherry2 = new Adult("Sherry", "", "", "F", 22, "VIC");
		youngChild.addRelationship("parent", sherry1);
		youngChild.addRelationship("parent", sherry2);

		assertEquals(youngChild.getRelationship().get("parent").get(1), sherry2);

	}

	// test add parent: the young child has been added to the first parent
	@Test
	public void addRelationshipParentTest2_3() throws Exception {

		Person sherry1 = new Adult("Sherry", "", "", "F", 22, "VIC");
		Person sherry2 = new Adult("Sherry", "", "", "F", 22, "VIC");
		youngChild.addRelationship("parent", sherry1);
		youngChild.addRelationship("parent", sherry2);

		assertEquals(sherry1.getRelationship().get("child").get(0), youngChild);

	}

	// test add parent: the young child has been added to the second parent
	@Test
	public void addRelationshipParentTest2_4() throws Exception {

		Person sherry1 = new Adult("Sherry", "", "", "F", 22, "VIC");
		Person sherry2 = new Adult("Sherry", "", "", "F", 22, "VIC");
		youngChild.addRelationship("parent", sherry1);
		youngChild.addRelationship("parent", sherry2);

		assertEquals(sherry2.getRelationship().get("child").get(0), youngChild);

	}

	// test remove parents: parents type has been removed from young child
	@Test
	public void removeRelationshipParentTest1_1() throws Exception {

		Person sherry1 = new Adult("Sherry", "", "", "F", 22, "VIC");
		Person sherry2 = new Adult("Sherry", "", "", "F", 22, "VIC");
		youngChild.addRelationship("parent", sherry1);
		youngChild.addRelationship("parent", sherry2);
		youngChild.removeRelationship("parent", sherry1);
		youngChild.removeRelationship("parent", sherry2);

		assertFalse(sherry1.getRelationship().containsKey("child"));

	}

	// test remove parents: parent1 has been removed from child
	@Test
	public void removeRelationshipParentTest1_2() throws Exception {

		Person sherry1 = new Adult("Sherry", "", "", "F", 22, "VIC");
		Person sherry2 = new Adult("Sherry", "", "", "F", 22, "VIC");
		youngChild.addRelationship("parents", sherry1);
		youngChild.addRelationship("parents", sherry2);
		youngChild.removeRelationship("parents", sherry1);
		youngChild.removeRelationship("parents", sherry2);

		assertFalse(youngChild.getRelationship().containsValue(sherry1));

	}

	// test remove parents: parent2 has been removed from the child
	@Test
	public void removeRelationshipParentTest1_3() throws Exception {

		Person sherry1 = new Adult("Sherry", "", "", "F", 22, "VIC");
		Person sherry2 = new Adult("Sherry", "", "", "F", 22, "VIC");
		youngChild.addRelationship("parents", sherry1);
		youngChild.addRelationship("parents", sherry2);
		youngChild.removeRelationship("parents", sherry1);
		youngChild.removeRelationship("parents", sherry2);

		assertFalse(youngChild.getRelationship().containsValue(sherry2));

	}

	// test remove parent: child relation type has been deleted from parent1
	@Test
	public void removeRelationshipParentTest2_1() throws Exception {

		Person sherry1 = new Adult("Sherry", "", "", "F", 22, "VIC");
		Person sherry2 = new Adult("Sherry", "", "", "F", 22, "VIC");
		youngChild.addRelationship("parent", sherry1);
		youngChild.addRelationship("parent", sherry2);
		youngChild.removeRelationship("parent", sherry1);

		assertFalse(sherry1.getRelationship().containsKey("child"));

	}

	// test remove parent: young child has been deleted from parent1
	@Test
	public void removeRelationshipParentTest2_2() throws Exception {

		Person sherry1 = new Adult("Sherry", "", "", "F", 22, "VIC");
		Person sherry2 = new Adult("Sherry", "", "", "F", 22, "VIC");
		youngChild.addRelationship("parent", sherry1);
		youngChild.addRelationship("parent", sherry2);
		youngChild.removeRelationship("parent", sherry1);

		assertFalse(sherry1.getRelationship().containsValue(youngChild));
	}

	// test remove parent: child relation type has been deleted from parent2
	@Test
	public void removeRelationshipParentTest3_1() throws Exception {

		Person sherry1 = new Adult("Sherry", "", "", "F", 22, "VIC");
		Person sherry2 = new Adult("Sherry", "", "", "F", 22, "VIC");
		youngChild.addRelationship("parent", sherry1);
		youngChild.addRelationship("parent", sherry2);
		youngChild.removeRelationship("parent", sherry2);

		assertFalse(sherry2.getRelationship().containsKey("child"));

	}

	// test remove parent: young child has been deleted from parent2
	@Test
	public void removeRelationshipParentTest3_2() throws Exception {

		Person sherry1 = new Adult("Sherry", "", "", "F", 22, "VIC");
		Person sherry2 = new Adult("Sherry", "", "", "F", 22, "VIC");
		youngChild.addRelationship("parent", sherry1);
		youngChild.addRelationship("parent", sherry2);
		youngChild.removeRelationship("parent", sherry2);

		assertFalse(sherry2.getRelationship().containsValue(youngChild));

	}

	// test sibling relation: sibling relation type be added to the youngChild
	@Test
	public void siblingRelationTest1_1() throws Exception {

		Person sherry1 = new Adult("Sherry", "", "", "F", 22, "VIC");
		Person sherry2 = new Adult("Sherry", "", "", "F", 22, "VIC");
		Person emma = new YoungChild("emma", "", "", "F", 2, "VIC");
		youngChild.addRelationship("parent", sherry1);
		youngChild.addRelationship("parent", sherry2);
		emma.addRelationship("parent", sherry1);
		emma.addRelationship("parent", sherry2);

		assertTrue(youngChild.getRelationship().containsKey("sibling"));

	}

	// test sibling relation: sibling relation type be added to the related person
	@Test
	public void siblingRelationTest1_2() throws Exception {

		Person sherry1 = new Adult("Sherry", "", "", "F", 22, "VIC");
		Person sherry2 = new Adult("Sherry", "", "", "F", 22, "VIC");
		Person emma = new YoungChild("emma", "", "", "F", 2, "VIC");
		youngChild.addRelationship("parent", sherry1);
		youngChild.addRelationship("parent", sherry2);
		emma.addRelationship("parent", sherry1);
		emma.addRelationship("parent", sherry2);

		assertTrue(emma.getRelationship().containsKey("sibling"));

	}

	// test sibling relation: the sibling related person has been added
	@Test
	public void siblingRelationTest1_3() throws Exception {

		Person sherry1 = new Adult("Sherry", "", "", "F", 22, "VIC");
		Person sherry2 = new Adult("Sherry", "", "", "F", 22, "VIC");
		Person emma = new YoungChild("emma", "", "", "F", 2, "VIC");
		youngChild.addRelationship("parent", sherry1);
		youngChild.addRelationship("parent", sherry2);
		emma.addRelationship("parent", sherry1);
		emma.addRelationship("parent", sherry2);

		assertEquals(youngChild.getRelationship().get("sibling").get(0), emma);

	}

	// test sibling relation: the youngChild has been added to the related person as
	// siblings
	@Test
	public void siblingRelationTest1_4() throws Exception {

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
