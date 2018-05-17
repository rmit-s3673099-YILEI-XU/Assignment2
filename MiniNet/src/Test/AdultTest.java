package Test;

import static org.junit.Assert.*;

import org.junit.*;

import Exceptions.*;
import people.*;

public class AdultTest {

	Person adult;

	@Before
	public void setUp() {
		adult = new Adult("Emma", "", "", "F", 20, "VIC");
	}

	// test add friend relationship to an adult: the relation type has been added
	@Test
	public void addRelationshipFriendTest1_1() throws Exception {

		Person sherry = new Adult("Sherry", "", "", "F", 20, "VIC");
		adult.addRelationship("friends", sherry);
		assertTrue(adult.getRelationship().containsKey("friends"));
	}

	// test add friend relationship to an adult: the related person has been added into this person's relationship
	@Test
	public void addRelationshipFriendTest1_2() throws Exception {

		Person sherry = new Adult("Sherry", "", "", "F", 20, "VIC");
		adult.addRelationship("friends", sherry);
		assertEquals(adult.getRelationship().get("friends").get(0), sherry);
	}
	
	// test add friend relationship to an adult: the related person's relation type has been added
	@Test
	public void addRelationshipFriendTest1_3() throws Exception {

		Person sherry = new Adult("Sherry", "", "", "F", 20, "VIC");
		adult.addRelationship("friends", sherry);
		assertTrue(sherry.getRelationship().containsKey("friends"));
	}
	
	// test add friend relationship to an adult: this person has been added into related person's relationship
	@Test
	public void addRelationshipFriendTest1_4() throws Exception {

		Person sherry = new Adult("Sherry", "", "", "F", 20, "VIC");
		adult.addRelationship("friends", sherry);
		assertEquals(sherry.getRelationship().get("friends").get(0), adult);
	}
	
	// test add a child friend to an adult
	@Test(expected = NotToBeFriendsException.class)
	public void addRelationshipFriendTest2() throws Exception {

		Person sherry = new Child("Sherry", "", "", "F", 10, "VIC");
		adult.addRelationship("friends", sherry);

	}

	// test add a young child friend to an adult
	@Test(expected = TooYoungException.class)
	public void addRelationshipFriendTest3() throws Exception {

		Person sherry = new YoungChild("Sherry", "", "", "F", 2, "VIC");
		adult.addRelationship("friends", sherry);

	}

	// test add colleagues relationship: the relation type has been added
	@Test
	public void addRelationshipColleagueTest1_1() throws Exception {

		Person sherry = new Adult("Sherry", "", "", "F", 20, "VIC");
		adult.addRelationship("colleagues", sherry);
		assertTrue(adult.getRelationship().containsKey("colleagues"));

	}
	
	//  test add colleagues relationship: the related person has been added to this person's relationship
	public void addRelationshipColleagueTest1_2() throws Exception {

		Person sherry = new Adult("Sherry", "", "", "F", 20, "VIC");
		adult.addRelationship("colleagues", sherry);
		assertEquals(adult.getRelationship().get("colleagues").get(0), sherry);

	}
	
	// test add colleagues relationship: the related person's relationship type has been added
	public void addRelationshipColleagueTest1_3() throws Exception {

		Person sherry = new Adult("Sherry", "", "", "F", 20, "VIC");
		adult.addRelationship("colleagues", sherry);
		assertTrue(sherry.getRelationship().containsKey("colleagues"));

	}
	
	// test add colleagues relationship: this person has been added into related person's relationship
	public void addRelationshipColleagueTest1_4() throws Exception {

		Person sherry = new Adult("Sherry", "", "", "F", 20, "VIC");
		adult.addRelationship("colleagues", sherry);
		assertEquals(sherry.getRelationship().get("colleagues").get(0), adult);
	}

	// test add a child as a colleague
	@Test(expected = NotToBeColleaguesException.class)
	public void addRelationshipColleagueTest2() throws Exception {

		Person sherry = new Child("Sherry", "", "", "F", 10, "VIC");
		adult.addRelationship("colleagues", sherry);

	}

	// add a young child as a colleague
	@Test(expected = NotToBeColleaguesException.class)
	public void addRelationshipColleagueTest3() throws Exception {

		Person sherry = new YoungChild("Sherry", "", "", "F", 2, "VIC");
		adult.addRelationship("colleagues", sherry);

	}

	// test add adult classmates relationship: the relation type has been added
	@Test
	public void addRelationshipClassmateTest1_1() throws Exception {

		Person sherry = new Adult("Sherry", "", "", "F", 20, "VIC");
		adult.addRelationship("classmates", sherry);
		assertTrue(adult.getRelationship().containsKey("classmates"));

	}
	
	// test add adult classmates relationship: the related person has been added to this person's relationship
	@Test
	public void addRelationshipClassmateTest1_2() throws Exception {

		Person sherry = new Adult("Sherry", "", "", "F", 20, "VIC");
		adult.addRelationship("classmates", sherry);
		assertEquals(adult.getRelationship().get("classmates").get(0), sherry);
		
	}
	
	// test add adult classmates relationship: the related person's relationship type has been added
	@Test
	public void addRelationshipClassmateTest1_3() throws Exception {

		Person sherry = new Adult("Sherry", "", "", "F", 20, "VIC");
		adult.addRelationship("classmates", sherry);
		assertTrue(sherry.getRelationship().containsKey("classmates"));
	}
	
	// test add adult classmates relationship: this person has been added into related person's relationship
	@Test
	public void addRelationshipClassmateTest1_4() throws Exception {

		Person sherry = new Adult("Sherry", "", "", "F", 20, "VIC");
		adult.addRelationship("classmates", sherry);
		assertEquals(sherry.getRelationship().get("classmates").get(0), adult);
	}

	// test add child classmates relation: the relation type has been added
	@Test
	public void addRelationshipClassmateTest2_1() throws Exception {

		Person sherry = new Child("Sherry", "", "", "F", 10, "VIC");
		adult.addRelationship("classmates", sherry);
		assertTrue(adult.getRelationship().containsKey("classmates"));
	}
	
	// test add child classmates relation: the related person has been added
	@Test
	public void addRelationshipClassmateTest2_2() throws Exception {

		Person sherry = new Child("Sherry", "", "", "F", 10, "VIC");
		adult.addRelationship("classmates", sherry);
		assertEquals(adult.getRelationship().get("classmates").get(0), sherry);
	}
	
	// test add child classmates relation: the related person's relation type has been added
	@Test
	public void addRelationshipClassmateTest2_3() throws Exception {

		Person sherry = new Child("Sherry", "", "", "F", 10, "VIC");
		adult.addRelationship("classmates", sherry);
		assertTrue(sherry.getRelationship().containsKey("classmates"));
	}
	
	// test add child classmates relation: the related person's has been added to this person's relationship
	@Test
	public void addRelationshipClassmateTest2_4() throws Exception {

		Person sherry = new Child("Sherry", "", "", "F", 10, "VIC");
		adult.addRelationship("classmates", sherry);
		assertEquals(sherry.getRelationship().get("classmates").get(0), adult);
	}

	// test add a young child as a classmate
	@Test(expected = NotToBeClassmatesException.class)
	public void addRelationshipClassmateTest3() throws Exception {

		Person sherry = new YoungChild("Sherry", "", "", "F", 2, "VIC");
		adult.addRelationship("classmates", sherry);

	}

	// test add couple: relation type added
	@Test
	public void addRelationshipCoupleTest1_1() throws Exception {

		Person sherry = new Adult("Sherry", "", "", "F", 20, "VIC");
		adult.addRelationship("couple", sherry);
		assertTrue(adult.getRelationship().containsKey("couple"));

	}

	// test add couple: related person added
	@Test
	public void addRelationshipCoupleTest1_2() throws Exception {

		Person sherry = new Adult("Sherry", "", "", "F", 20, "VIC");
		adult.addRelationship("couple", sherry);
		assertEquals(adult.getRelationship().get("couple").get(0), sherry);
	}
	
	// test add couple: related person's relation type added
	@Test
	public void addRelationshipCoupleTest1_3() throws Exception {

		Person sherry = new Adult("Sherry", "", "", "F", 20, "VIC");
		adult.addRelationship("couple", sherry);
		assertTrue(sherry.getRelationship().containsKey("couple"));
		
	}
	
	// test add couple: this person has been added to related person's relationship
	@Test
	public void addRelationshipCoupleTest1_4() throws Exception {

		Person sherry = new Adult("Sherry", "", "", "F", 20, "VIC");
		adult.addRelationship("couple", sherry);
		assertEquals(sherry.getRelationship().get("couple").get(0), adult);
	}
	
	// test add child and adult to be couple
	@Test(expected = NotToBeCoupledException.class)
	public void addRelationshipCoupleTest2() throws Exception {

		Person sherry = new Child("Sherry", "", "", "F", 12, "VIC");
		adult.addRelationship("couple", sherry);

	}

	// test add young child and adult to be couple
	@Test(expected = NotToBeCoupledException.class)
	public void addRelationshipCoupleTest3() throws Exception {

		Person sherry = new YoungChild("Sherry", "", "", "F", 2, "VIC");
		adult.addRelationship("couple", sherry);

	}

	// test add a couple relation, but one person already has partner
	@Test(expected = NoAvailableException.class)
	public void addRelationshipCoupleTest4() throws Exception {

		Person sherry = new Adult("Sherry", "", "", "F", 20, "VIC");
		Person xxx = new Adult("xxx", "", "", "F", 20, "VIC");
		adult.addRelationship("couple", sherry);
		adult.addRelationship("couple", xxx);

	}

	// test remove relationship: remove friend
	@Test
	public void removeRelationshipFriendTest() throws Exception {

		Person sherry = new Adult("Sherry", "", "", "F", 20, "VIC");
		adult.addRelationship("friends", sherry);
		adult.removeRelationship("friends", sherry);
		assertFalse(sherry.getRelationship().containsKey("friends"));

	}

	// test remove relationship: remove colleague
	@Test
	public void removeRelationshipColleagueTest() throws Exception {

		Person sherry = new Adult("Sherry", "", "", "F", 20, "VIC");
		adult.addRelationship("colleagues", sherry);
		adult.removeRelationship("colleagues", sherry);
		assertFalse(sherry.getRelationship().containsKey("colleagues"));

	}

	// // test remove relationship: remove classmate
	@Test
	public void removeRelationshipClassmateTest() throws Exception {

		Person sherry = new Adult("Sherry", "", "", "F", 20, "VIC");
		adult.addRelationship("classmates", sherry);
		adult.removeRelationship("classmates", sherry);
		assertFalse(sherry.getRelationship().containsKey("classmates"));

	}
	
	// test remove relationship: remove couple
	@Test
	public void removeRelationshipCoupleTest() throws Exception {

		Person sherry = new Adult("Sherry", "", "", "F", 20, "VIC");
		adult.addRelationship("couple", sherry);
		adult.removeRelationship("couple", sherry);
		assertFalse(sherry.getRelationship().containsKey("couple"));

	}

}