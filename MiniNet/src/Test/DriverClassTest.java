package Test;
import Controller.*;
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
	
	@Test
	public void addPersonTest() throws IOException {
		
//		assertTrue(dc.addPerson("ddd", "", "", "F", 20, "VIC") instanceof Adult);
//		assertTrue(dc.addPerson("child", "", "", "F", 10, "VIC") instanceof Child);
//		assertTrue(dc.addPerson("youngChild", "", "", "F", 2, "VIC") instanceof YoungChild);
//		assertTrue(dc.addPerson("ddd", "", "", "M", 22, "WA") == null);
		
		
	}

}
