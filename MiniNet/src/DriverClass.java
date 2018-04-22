import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import people.*;


public class DriverClass {
	
	private HashMap<String, Person> member;

	
	public void initialData()
	{

		member = new HashMap<String, Person>();

		member.put("AAA", new Adult("AAA"));
		member.put("BBB", new Adult("BBB"));
		member.put("CCC", new Adult("CCC"));
		
		member.get("AAA").addRelationship("friends",member.get("BBB")); //friends from interface
		member.get("AAA").addRelationship("colleagues", member.get("CCC"));
		member.get("AAA").addRelationship("friends",member.get("CCC"));
		member.get("AAA").displayProfile();
		member.get("BBB").displayProfile();
	}
	
	public Person getMember() {
		
		return member.get(getMember());
	}
}
