package Controller;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;
import java.util.TreeMap;

import Exceptions.*;
import people.*;


public class DriverClass {
	
	private TreeMap<String, Person> member;
	ArrayList<String[]> relationData;
    DatabaseController databaseController = new DatabaseController();
	
	public void initialData() throws IOException
	{

		member = new TreeMap<String, Person>();
		databaseController.initialDatabase();

		
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
			relationsFileReader.close();
			
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
			
				for(int i =0;i<pTextData.length;i++)
				{
					pTextData[i]=pTextData[i].replace("\"", "");
				}
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
			peopleFileReader.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			addRelationData();
		} catch (NoParentsException e) {
			// TODO Auto-generated catch block
			e.noParentsWarning();
		}
		databaseController.initialDataInDB(member);
//		for(String sr: member.keySet() )
//		{
//			member.get(sr).displayProfile();
//		}
		
		
	}
	
	//如果数据有错，找不到人应该有错误提示![稍后加]
	private void addRelationData() throws NoParentsException {

		ArrayList<Person> noParentsChildList = new ArrayList();
		
		for (String[] st : relationData) {
			
			for (String name : member.keySet()) {
//				System.out.println("oooooo"+st[0]+" "+st[1]+" "+st[2]);
				//for parent relation
				if (name.equals(st[0].trim())) {

					if (st[2].trim().equals("parent") && (member.get(st[0].trim()) instanceof Adult)) {
						String temp = st[0];
						st[0] = st[1].trim();
						st[1] = temp.trim();
						name = st[0];

					}
//					System.out.println("iiiiiiii"+st[0]+" "+st[1]+" "+st[2]);
//					 System.out.println(st[0]+" "+st[1]+" "+st[2]);
//					System.out.println(name+ " "+member.get(name) instanceof Child);
					try {
						member.get(name).addRelationship(st[2].trim(), member.get(st[1].trim()));
					} catch (NotToBeFriendsException e) {
						// TODO Auto-generated catch block
						e.notToBeFriendsException();
					}
					catch(TooYoungException e)
					{
						e.tooYoungException();
					}
					catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
				}
			}
		}
		
		//warning message for the child doesn't have the parents relation 
		for(Person pr: member.values())
		{
			if(!(pr instanceof Adult))
				if(pr.getRelationship().get("parent").size()<2)
				{
					noParentsChildList.add(pr);
				}
		}
		if(noParentsChildList.size()>0)
		{
			for(Person child: noParentsChildList)
			{
				deletePerson(child);
			}
			throw new NoParentsException(noParentsChildList);
		}
	}
	
	public Person addPerson(String name, String photo, String status, String gender,int age, String state)
	{
		Person currentPerson;
		
		for(String memberName:member.keySet())
		{
			if(name.equals(memberName))
				return null;
		}
		
		if(age<3)
		{
			currentPerson = new YoungChild(name,photo,status,gender,age,state);
			return currentPerson;
		}
		else if(age<=16)
		{
			currentPerson = new Child(name,photo,status,gender,age,state);
			return currentPerson;
		}
		else{
			currentPerson = new Adult(name,photo,status,gender,age,state);
			return currentPerson;
		}
//		member.put(name, currentPerson);
	}
	
	public void deletePerson(Person currentPerson)
	{
		ArrayList<Person> childList = new ArrayList<Person>();
		
		for(String relationType: currentPerson.getRelationship().keySet())
		{
			
			for(Person relatedPerson: currentPerson.getRelationship().get(relationType))
				{
				
					currentPerson.removeRelationship(relationType, relatedPerson); 
					if(relationType=="child") {
						childList.add(relatedPerson);
//						relatedPerson.removeRelationship(relationType, relatedPerson);
//						member.remove(relatedPerson.getName());
					}
				}
			
		}
		member.remove(currentPerson.getName());
		databaseController.modifyDatabase(currentPerson, "deletePerson");

		
		if (childList.size() > 0) {
			for (Person child : childList) {
				for (String relationType : child.getRelationship().keySet()) {

					for (Person relatedPerson : child.getRelationship().get(relationType)) {
						
						child.removeRelationship(relationType, relatedPerson);

					}
				}
				member.remove(child.getName());
				databaseController.modifyDatabase(child, "deletePerson");
		
			}
		}
		
	}
	
	public void modifyDatabase(Person person,String operation) {
		
		databaseController.modifyDatabase(person, operation);
		
	}
	public TreeMap<String, Person> getMember() {
		return member;
	}



	public Person getMemberObj(String key) {
		
		return member.get(key);
	}
	
//	public static HashMap<String, String> sortMapByKey(HashMap<String, String> orignalMap) {
//	    if (orignalMap == null || orignalMap.isEmpty()) {
//	        return null;
//	    }
//
//	    HashMap<String, String> map = new HashMap<String, String>(
//                new Comparator<String>() {
//                    public int compare(String obj1, String obj2) {
//                        // 降序排序
//                        return obj2.compareTo(obj1);
//                    }
//                });
//	    sortMap.putAll(oriMap);
//	    return sortMap;
//	}
	
	
}
