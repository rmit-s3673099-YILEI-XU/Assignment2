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
import javafx.scene.control.Alert;
import people.*;
/**
 * This class implements main features in MiniNet System. It uses other classes to manage whole social network;
 * @author YILEI XU
 *
 */
public class DriverClass {

	private TreeMap<String, Person> member;
	private ArrayList<String[]> relationData;
	private DatabaseController databaseController = new DatabaseController();
/**
 * This method insert the initial data of members 
 * @throws IOException if file cannot be found
 */
	public void initialData() throws IOException {

		member = new TreeMap<String, Person>();
		databaseController.initialDatabase();
		BufferedReader peopleFileReader = null;
		String[] pTextData = null;	
		relationData = new ArrayList<String[]>();
		Person currentPerson;
		try {
			peopleFileReader = new BufferedReader(new FileReader("db/people.txt"));
			String currentLine;
			while ((currentLine = peopleFileReader.readLine()) != null) {
				pTextData = currentLine.split(",");
				for (int i = 0; i < pTextData.length; i++) {
					pTextData[i] = pTextData[i].replace("\"", "");
				}
				int age = Integer.parseInt(pTextData[4].trim());
				if (age < 3) {
					currentPerson = new YoungChild(pTextData[0].trim(), pTextData[1].trim(), pTextData[2].trim(),
							pTextData[3].trim(), age, pTextData[5].trim());
				} else if (age <= 16) {
					currentPerson = new Child(pTextData[0].trim(), pTextData[1].trim(), pTextData[2].trim(),
							pTextData[3].trim(), age, pTextData[5].trim());
				} else {
					currentPerson = new Adult(pTextData[0].trim(), pTextData[1].trim(), pTextData[2].trim(),
							pTextData[3].trim(), age, pTextData[5].trim());
				}
				member.put(pTextData[0].trim(), currentPerson);
			}
			peopleFileReader.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			cannotFoundFileMessage();
		}
		if (!member.isEmpty()) {
			 getRelationData();
		}

	}
/**
 * This method get the relation data from the file
 * @throws IOException if file is not found
 */
	private void getRelationData() throws IOException
	{
		BufferedReader relationsFileReader = null;
		String[] rTextData = null;
		try {
			relationsFileReader = new BufferedReader(new FileReader("db/relations.txt"));
			String currentLine;
			while ((currentLine = relationsFileReader.readLine()) != null) {
				rTextData = currentLine.split(",");
				relationData.add(rTextData);
			}
			relationsFileReader.close();
	
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		if (!relationData.isEmpty()) {
			try {
				addInitialRelationData();
			} catch (NoParentsException e) {
				// TODO Auto-generated catch block
				e.noParentsWarning();
			}
		}
		databaseController.initialDataInDB(member);
	}
/**
 * 	This method add the relation data from the file to the list
 * @throws NoParentsException if a child does not have parents
 */
	private void addInitialRelationData() throws NoParentsException {

		ArrayList<Person> noParentsChildList = new ArrayList();
		for (String[] st : relationData) {
			for (String name : member.keySet()) {
				if (name.equals(st[0].trim())) {
					if (st[2].trim().equals("parent") && (member.get(st[0].trim()) instanceof Adult)) {
						String temp = st[0];
						st[0] = st[1].trim();
						st[1] = temp.trim();
						name = st[0];
					}
					try {
						member.get(name).addRelationship(st[2].trim(), member.get(st[1].trim()));
					} catch (NotToBeFriendsException e) {
						// TODO Auto-generated catch block
						e.notToBeFriendsException();
					} catch (TooYoungException e) {
						e.tooYoungException();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
				}
			}
		}
		// warning message for the child doesn't have the parents relation
		for (Person pr : member.values()) {
			if (!(pr instanceof Adult))
				if (pr.getRelationship().get("parent").size() < 2) {
					noParentsChildList.add(pr);
				}
		}
		if (noParentsChildList.size() > 0) {
			for (Person child : noParentsChildList) {
				deletePerson(child);
			}
			throw new NoParentsException(noParentsChildList);
		}
	}
/**
 * This method is the for adding person to the member map
 * @param name the name of the person
 * @param photo the photo of the person
 * @param status the status of the person
 * @param gender the gender of the person
 * @param age the age of the person
 * @param state the state this person is from
 * @return Person the person is being added
 */
	public Person addPerson(String name, String photo, String status, String gender, int age, String state) {
		Person currentPerson;

		for (String memberName : member.keySet()) {
			if (name.equals(memberName))
				return null;
		}

		if (age < 3) {
			currentPerson = new YoungChild(name, photo, status, gender, age, state);
			return currentPerson;
		} else if (age <= 16) {
			currentPerson = new Child(name, photo, status, gender, age, state);
			return currentPerson;
		} else {
			currentPerson = new Adult(name, photo, status, gender, age, state);
			return currentPerson;
		}
	}
/**
 * This method delete the person from the map
 * @param currentPerson the person who is being deleted
 */
	public void deletePerson(Person currentPerson) {
		ArrayList<Person> childList = new ArrayList<Person>();

		for (String relationType : currentPerson.getRelationship().keySet()) {

			for (Person relatedPerson : currentPerson.getRelationship().get(relationType)) {

				currentPerson.removeRelationship(relationType, relatedPerson);
				if (relationType == "child") {
					childList.add(relatedPerson);
					// relatedPerson.removeRelationship(relationType, relatedPerson);
					// member.remove(relatedPerson.getName());
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
/**
 * This method get the person with the name from the member map
 * @return the person map which contains person and the name of the person
 */
	public TreeMap<String, Person> getMember() {
		return member;
	}
/**
 * This method get the member object which is a person
 * @param key the name of the person
 * @return the person object
 */
	public Person getMemberObj(String key) {

		return member.get(key);
	}
/**
 * This method get the DatabaseController object
 * @return databaseController
 */
	public DatabaseController getDatabaseController() {
		return databaseController;
	}
/**
 * This method check the relation which already exsit 
 * @param selectedPerson the current person 
 * @param relatedPerson the person related to the current person
 * @throws AlreadyHaveRelationException if already have relationship
 */
	public void checkAlreadyExistRelation(Person selectedPerson, Person relatedPerson)
			throws AlreadyHaveRelationException {

		for (String relation : selectedPerson.getRelationship().keySet()) {
			for (Person alreadyRelatedPerson : selectedPerson.getRelationship().get(relation)) {
				if (alreadyRelatedPerson.equals(relatedPerson))
					throw new AlreadyHaveRelationException(selectedPerson, relatedPerson);
			}
		}

	}
/**
 * This method shows the alert when the file cannot be found
 */
	private void cannotFoundFileMessage() {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setTitle("MESSAGES");
		alert.setHeaderText("ERROR!");
		alert.setContentText("people.txt file cannot be found! Fail to initail network.");
		alert.show();
	}

}
