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
		  dc.initialData();
	}
	
	@Test(expected = NoParentsException.class)
	 public void initialDataTest() throws IOException, NoParentsException{
	  
		dc.initialData();
	 }
	
	
	@Test
	public void addPersonTest1_1() throws IOException {
		  
		  assertTrue(dc.addPerson("Adult", "", "", "F", 20, "VIC") instanceof Adult);
		
	}
	
	@Test
	public void addPersonTest1_2() throws IOException {
		  
		  assertTrue(dc.addPerson("child", "", "", "F", 10, "VIC") instanceof Child);
	}
	
	@Test
	public void addPersonTest1_3() throws IOException {
		  
		  assertTrue(dc.addPerson("youngChild", "", "", "F", 2, "VIC") instanceof YoungChild);
	}
	
	@Test
	public void deletePersonTest1_1() throws Exception{
		
		Person adult = dc.addPerson("Adult", "", "", "F", 20, "VIC");
		dc.deletePerson(adult);
		
		assertTrue(dc.getMember().isEmpty());
			
	}
	
	@Test
	public void deletePersonTest1_2() throws Exception{
		
		Person child = dc.addPerson("Child", "", "", "F", 12, "VIC");
		dc.deletePerson(child);
		
		assertTrue(dc.getMember().isEmpty());
			
	}
	
	@Test
	public void deletePersonTest1_3() throws Exception{
		
		Person youngChild = dc.addPerson("YoungChild", "", "", "F", 2, "VIC");
		
		dc.deletePerson(youngChild);
		
		assertTrue(dc.getMember().isEmpty());
			
	}
	//test relationship be deleted too
	@Test
	public void deletePersonTest2_1() throws Exception{
		
		Person adult = dc.addPerson("Adult", "", "", "F", 20, "VIC");
		Person adult1 = dc.addPerson("Adult1", "", "", "F", 20, "VIC");
		Person adult2 = dc.addPerson("Adult2", "", "", "F", 20, "VIC");
		Person adult3 = dc.addPerson("Adult3", "", "", "F", 20, "VIC");
		Person adult4 = dc.addPerson("Adult4", "", "", "F", 20, "VIC");
		
		adult.addRelationship("friend", adult1);	
		adult.addRelationship("classmate", adult2);	
		adult.addRelationship("colleague", adult3);	
		adult.addRelationship("couple", adult4);	

		dc.deletePerson(adult);
		
		//assertTure(dc.getMe);
	}
	
//the relationship is not exist in the related person
	@Test
	public void deletePersonTest2_2() throws Exception{
		
		Person adult1 = dc.addPerson("Adult1", "", "", "F", 20, "VIC");
		Person adult2 = dc.addPerson("Adult2", "", "", "F", 20, "VIC");
		
		dc.getMember().put("Adult1", adult1);
		dc.getMember().put("Adult2", adult2);
		
		adult1.addRelationship("friend", adult2);	
		dc.deletePerson(adult1);
		assertTrue(adult2.getRelationship().isEmpty());
		
	}
	@Test
	public void deletePersonTest2_3() throws Exception{
		
		Person adult = dc.addPerson("Adult", "", "", "F", 20, "VIC");
		Person adult1 = dc.addPerson("Adult1", "", "", "F", 20, "VIC");
		Person adult2 = dc.addPerson("Adult2", "", "", "F", 20, "VIC");
		Person adult3 = dc.addPerson("Adult3", "", "", "F", 20, "VIC");
		Person adult4 = dc.addPerson("Adult4", "", "", "F", 20, "VIC");
		
		adult.addRelationship("friend", adult1);	
		adult.addRelationship("friend", adult1);	
		adult.addRelationship("friend", adult1);	
		adult.addRelationship("friend", adult1);	
		
		
		dc.deletePerson(adult);
	}
	@Test
	public void deletePersonTest2_4() throws Exception{
		
		Person adult = dc.addPerson("Adult", "", "", "F", 20, "VIC");
		Person adult1 = dc.addPerson("Adult1", "", "", "F", 20, "VIC");
		Person adult2 = dc.addPerson("Adult2", "", "", "F", 20, "VIC");
		Person adult3 = dc.addPerson("Adult3", "", "", "F", 20, "VIC");
		Person adult4 = dc.addPerson("Adult4", "", "", "F", 20, "VIC");
		
		adult.addRelationship("friend", adult1);	
		adult.addRelationship("friend", adult1);	
		adult.addRelationship("friend", adult1);	
		adult.addRelationship("friend", adult1);	
		
		
		dc.deletePerson(adult);
	}
	@Test
	public void deletePersonTest2_5() throws Exception{
		
		Person adult = dc.addPerson("Adult", "", "", "F", 20, "VIC");
		Person adult1 = dc.addPerson("Adult1", "", "", "F", 20, "VIC");
		Person adult2 = dc.addPerson("Adult2", "", "", "F", 20, "VIC");
		Person adult3 = dc.addPerson("Adult3", "", "", "F", 20, "VIC");
		Person adult4 = dc.addPerson("Adult4", "", "", "F", 20, "VIC");
		
		adult.addRelationship("friend", adult1);	
		adult.addRelationship("friend", adult1);	
		adult.addRelationship("friend", adult1);	
		adult.addRelationship("friend", adult1);	
		
		
		dc.deletePerson(adult);
	}
	
	
	//test the child be deleted
	//test child relation be deleted
	
	//test youngChild relation be deleted
	
	

}
