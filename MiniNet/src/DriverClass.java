import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

import Exceptions.*;
import people.*;


public class DriverClass {
	
	private HashMap<String, Person> member;
	ArrayList<String[]> relationData;
	
	public void initialData() throws IOException
	{

		member = new HashMap<String, Person>();

		
		BufferedReader peopleFileReader = null;
		BufferedReader relationsFileReader = null;
		
		String[] pTextData = null;
		String[] rTextData = null;
		relationData = new ArrayList<String[]>();
		Person currentPerson;
		
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
				if(age<3)
				{
					currentPerson = new YoungChild(pTextData[0].trim(),pTextData[1].trim(),pTextData[2].trim(),pTextData[3].trim(),age, pTextData[5].trim());
				}
				else if(age<=16)
				{
					currentPerson = new Child(pTextData[0].trim(),pTextData[1].trim(),pTextData[2].trim(),pTextData[3].trim(),age, pTextData[5].trim());//					member.put(pTextData[0].trim(), currentPerson);
				}
				else{
					currentPerson = new Adult(pTextData[0].trim(),pTextData[1].trim(),pTextData[2].trim(),pTextData[3].trim(),age, pTextData[5].trim());

				}
				member.put(pTextData[0].trim(), currentPerson);

			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			addRelationData();
		} catch (NoParentsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(String sr: member.keySet() )
		{
			member.get(sr).displayProfile();
		}
		
		
	}
	
	//如果数据有错，找不到人应该有错误提示![稍后加]
	private void addRelationData() throws NoParentsException {

		for (String[] st : relationData) {
			
			for (String name : member.keySet()) {

				//for parent relation
				if (name.equals(st[0].trim())) {
					
					if (st[2].trim().equals("parent") && (member.get(st[0].trim()) instanceof Adult)) {
						String temp = st[0];
						st[0] = st[1].trim();
						st[1] = temp.trim();
						name = st[0];

					}
					// System.out.println(st[0]+" 000 "+st[1]);
					member.get(name).addRelationship(st[2].trim(), member.get(st[1].trim()));
				}
			}
		}
		
		//warning message for the child doesn't have the parents relation 
		for(Person pr: member.values())
		{
			if(!(pr instanceof Adult))
				if(pr.getRelationship().get("parent").size()<2)
				{
					System.out.println(pr.getName()+" doesn't have parents\n");
					throw new NoParentsException();
				}
		}
	}
	
	public void addPerson(String name, String photo, String status, String gender,int age, String state)
	{
		Person currentPerson;
		if(age<3)
		{
			currentPerson = new YoungChild(name,photo,status,gender,age,state);
		}
		else if(age<=16)
		{
			currentPerson = new Child(name,photo,status,gender,age,state);
		}
		else{
			currentPerson = new Adult(name,photo,status,gender,age,state);
		}
		member.put(name, currentPerson);
	}
	
	public HashMap<String, Person> getMember() {
		return member;
	}



	public Person getMemberObj(String key) {
		
		return member.get(key);
	}
	
	
}
