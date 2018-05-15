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

	@Test
	public void addRelationshipFriendTest1_1() throws Exception {

		Person sherry = new Adult("Sherry", "", "", "F", 20, "VIC");
		adult.addRelationship("friends", sherry);
		assertTrue(adult.getRelationship().containsKey("friends"));
	}
	
	@Test
	public void addRelationshipFriendTest1_2() throws Exception {

		Person sherry = new Adult("Sherry", "", "", "F", 20, "VIC");
		adult.addRelationship("friends", sherry);
		assertEquals(adult.getRelationship().get("friends").get(0), sherry);
	}

	@Test
	public void addRelationshipFriendTest1_3() throws Exception {

		Person sherry = new Adult("Sherry", "", "", "F", 20, "VIC");
		adult.addRelationship("friends", sherry);
		assertTrue(sherry.getRelationship().containsKey("friends"));
	}
	
	@Test
	public void addRelationshipFriendTest1_4() throws Exception {

		Person sherry = new Adult("Sherry", "", "", "F", 20, "VIC");
		adult.addRelationship("friends", sherry);
		assertEquals(sherry.getRelationship().get("friends").get(0), adult);
	}
	
	@Test(expected = NotToBeFriendsException.class)
	public void addRelationshipFriendTest2() throws Exception {

		Person sherry = new Child("Sherry", "", "", "F", 10, "VIC");
		adult.addRelationship("friends", sherry);

	}

	@Test(expected = TooYoungException.class)
	public void addRelationshipFriendTest3() throws Exception {

		Person sherry = new YoungChild("Sherry", "", "", "F", 2, "VIC");
		adult.addRelationship("friends", sherry);

	}

	@Test
	public void addRelationshipColleagueTest1_1() throws Exception {

		Person sherry = new Adult("Sherry", "", "", "F", 20, "VIC");
		adult.addRelationship("colleagues", sherry);
		assertTrue(adult.getRelationship().containsKey("colleagues"));

	}
	
	public void addRelationshipColleagueTest1_2() throws Exception {

		Person sherry = new Adult("Sherry", "", "", "F", 20, "VIC");
		adult.addRelationship("colleagues", sherry);
		assertEquals(adult.getRelationship().get("colleagues").get(0), sherry);

	}
	
	public void addRelationshipColleagueTest1_3() throws Exception {

		Person sherry = new Adult("Sherry", "", "", "F", 20, "VIC");
		adult.addRelationship("colleagues", sherry);
		assertTrue(sherry.getRelationship().containsKey("colleagues"));

	}
	
	public void addRelationshipColleagueTest1_4() throws Exception {

		Person sherry = new Adult("Sherry", "", "", "F", 20, "VIC");
		adult.addRelationship("colleagues", sherry);
		assertEquals(sherry.getRelationship().get("colleagues").get(0), adult);
	}

	@Test(expected = NotToBeColleaguesException.class)
	public void addRelationshipColleagueTest2() throws Exception {

		Person sherry = new Child("Sherry", "", "", "F", 10, "VIC");
		adult.addRelationship("colleagues", sherry);

	}

	@Test(expected = NotToBeColleaguesException.class)
	public void addRelationshipColleagueTest3() throws Exception {

		Person sherry = new YoungChild("Sherry", "", "", "F", 2, "VIC");
		adult.addRelationship("colleagues", sherry);

	}

	@Test
	public void addRelationshipClassmateTest1_1() throws Exception {

		Person sherry = new Adult("Sherry", "", "", "F", 20, "VIC");
		adult.addRelationship("classmates", sherry);
		assertTrue(adult.getRelationship().containsKey("classmates"));

	}
	
	@Test
	public void addRelationshipClassmateTest1_2() throws Exception {

		Person sherry = new Adult("Sherry", "", "", "F", 20, "VIC");
		adult.addRelationship("classmates", sherry);
		assertEquals(adult.getRelationship().get("classmates").get(0), sherry);
		
	}
	
	@Test
	public void addRelationshipClassmateTest1_3() throws Exception {

		Person sherry = new Adult("Sherry", "", "", "F", 20, "VIC");
		adult.addRelationship("classmates", sherry);
		assertTrue(sherry.getRelationship().containsKey("classmates"));
	}
	
	@Test
	public void addRelationshipClassmateTest1_4() throws Exception {

		Person sherry = new Adult("Sherry", "", "", "F", 20, "VIC");
		adult.addRelationship("classmates", sherry);
		assertEquals(sherry.getRelationship().get("classmates").get(0), adult);
	}

	@Test
	public void addRelationshipClassmateTest2_1() throws Exception {

		Person sherry = new Child("Sherry", "", "", "F", 10, "VIC");
		adult.addRelationship("classmates", sherry);
		assertTrue(adult.getRelationship().containsKey("classmates"));
	}
	
	@Test
	public void addRelationshipClassmateTest2_2() throws Exception {

		Person sherry = new Child("Sherry", "", "", "F", 10, "VIC");
		adult.addRelationship("classmates", sherry);
		assertEquals(adult.getRelationship().get("classmates").get(0), sherry);
	}
	
	@Test
	public void addRelationshipClassmateTest2_3() throws Exception {

		Person sherry = new Child("Sherry", "", "", "F", 10, "VIC");
		adult.addRelationship("classmates", sherry);
		assertTrue(sherry.getRelationship().containsKey("classmates"));
	}
	
	@Test
	public void addRelationshipClassmateTest2_4() throws Exception {

		Person sherry = new Child("Sherry", "", "", "F", 10, "VIC");
		adult.addRelationship("classmates", sherry);
		assertEquals(sherry.getRelationship().get("classmates").get(0), adult);
	}

	@Test(expected = NotToBeClassmatesException.class)
	public void addRelationshipClassmateTest3() throws Exception {

		Person sherry = new YoungChild("Sherry", "", "", "F", 2, "VIC");
		adult.addRelationship("classmates", sherry);

	}

	@Test
	public void addRelationshipCoupleTest1_1() throws Exception {

		Person sherry = new Adult("Sherry", "", "", "F", 20, "VIC");
		adult.addRelationship("couple", sherry);
		assertTrue(adult.getRelationship().containsKey("couple"));

	}

	@Test
	public void addRelationshipCoupleTest1_2() throws Exception {

		Person sherry = new Adult("Sherry", "", "", "F", 20, "VIC");
		adult.addRelationship("couple", sherry);
		assertEquals(adult.getRelationship().get("couple").get(0), sherry);
	}
	
	@Test
	public void addRelationshipCoupleTest1_3() throws Exception {

		Person sherry = new Adult("Sherry", "", "", "F", 20, "VIC");
		adult.addRelationship("couple", sherry);
		assertTrue(sherry.getRelationship().containsKey("couple"));
		
	}
	
	@Test
	public void addRelationshipCoupleTest1_4() throws Exception {

		Person sherry = new Adult("Sherry", "", "", "F", 20, "VIC");
		adult.addRelationship("couple", sherry);
		assertEquals(sherry.getRelationship().get("couple").get(0), adult);
	}
	
	@Test(expected = NotToBeCoupledException.class)
	public void addRelationshipCoupleTest2() throws Exception {

		Person sherry = new Child("Sherry", "", "", "F", 12, "VIC");
		adult.addRelationship("couple", sherry);

	}

	@Test(expected = NotToBeCoupledException.class)
	public void addRelationshipCoupleTest3() throws Exception {

		Person sherry = new YoungChild("Sherry", "", "", "F", 2, "VIC");
		adult.addRelationship("couple", sherry);

	}

	@Test(expected = NoAvailableException.class)
	public void addRelationshipCoupleTest4() throws Exception {

		Person sherry = new Adult("Sherry", "", "", "F", 20, "VIC");
		Person xxx = new Adult("xxx", "", "", "F", 20, "VIC");
		adult.addRelationship("couple", sherry);
		adult.addRelationship("couple", xxx);

	}

	@Test
	public void removeRelationshipFriendTest() throws Exception {

		Person sherry = new Adult("Sherry", "", "", "F", 20, "VIC");
		adult.addRelationship("friends", sherry);
		adult.removeRelationship("friends", sherry);
		assertFalse(sherry.getRelationship().containsKey("friends"));

	}

	@Test
	public void removeRelationshipColleagueTest() throws Exception {

		Person sherry = new Adult("Sherry", "", "", "F", 20, "VIC");
		adult.addRelationship("colleagues", sherry);
		adult.removeRelationship("colleagues", sherry);
		assertTrue(sherry.getRelationship().get("colleagues").size() == 0);

	}

	@Test
	public void removeRelationshipClassmateTest() throws Exception {

		Person sherry = new Adult("Sherry", "", "", "F", 20, "VIC");
		adult.addRelationship("classmates", sherry);
		adult.removeRelationship("classmates", sherry);
		assertTrue(sherry.getRelationship().get("classmates").size() == 0);

	}

	@Test
	public void removeRelationshipCoupleTest() throws Exception {

		Person sherry = new Adult("Sherry", "", "", "F", 20, "VIC");
		adult.addRelationship("couple", sherry);
		adult.removeRelationship("couple", sherry);
		assertFalse(sherry.getRelationship().containsKey("couple"));

	}

}