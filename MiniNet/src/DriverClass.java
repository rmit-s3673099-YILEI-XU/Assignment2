import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

import people.*;


public class DriverClass {
	
	private HashMap<String, Person> member;
	ArrayList<String[]> relationData;
	
	public void initialData() throws IOException
	{

		member = new HashMap<String, Person>();

//		member.put("AAA", new Adult("AAA"));
//		member.put("BBB", new Adult("BBB"));
//		member.put("CCC", new Adult("CCC"));
		
//		member.get("AAA").addRelationship("friends",member.get("BBB")); //friends from interface
//		member.get("AAA").addRelationship("colleagues", member.get("CCC"));
//		member.get("AAA").addRelationship("friends",member.get("CCC"));
//		member.get("AAA").displayProfile();
//		member.get("BBB").displayProfile();
		
		BufferedReader peopleFileReader = null;
		BufferedReader relationsFileReader = null;
		
		String[] pTextData = null;
		String[]rTextData = null;
		relationData = new ArrayList<String[]>();
		Person currentPerson;
//		String name, photo, status, gender, age,state; 
		
		try {
			relationsFileReader = new BufferedReader(new FileReader("db/relations.txt"));
			String currentLine;
			while((currentLine = relationsFileReader.readLine())!=null)
			{
				rTextData = currentLine.split(",");
				relationData.add(rTextData);
			}
			
		}catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
		
		
		try {
			peopleFileReader = new BufferedReader(new FileReader("db/people.txt"));	
			String currentLine;
			while((currentLine=peopleFileReader.readLine())!=null)
			{
				pTextData = currentLine.split(",");
				int age = Integer.parseInt(pTextData[4].trim());
				if(age<=16)
				{
					currentPerson = new Child(pTextData[0].trim(),pTextData[1].trim(),pTextData[2].trim(),pTextData[3].trim(),age, pTextData[5].trim());
					member.put(pTextData[0].trim(), currentPerson);
				}
				else{
					currentPerson = new Adult(pTextData[0].trim(),pTextData[1].trim(),pTextData[2].trim(),pTextData[3].trim(),age, pTextData[5].trim());
					member.put(pTextData[0].trim(), currentPerson);
				}
//				System.out.println(age);
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		addRelationData();
		for(String sr: member.keySet() )
		{
			member.get(sr).displayProfile();
		}
		
		
	}
	
	//如果数据有错，找不到人应该有错误提示![稍后加]
	private void addRelationData()
	{
		
		
		for(String name: member.keySet()) {
			for(String[] st: relationData)
			{
				if(name.equals(st[0].trim()))
				{
					member.get(name).addRelationship(st[2].trim(), member.get(st[1].trim()));
				}
			}
		}
	}
	
	
	public HashMap<String, Person> getMember() {
		return member;
	}



	public Person getMemberObj(String key) {
		
		return member.get(key);
	}
	
	
}
