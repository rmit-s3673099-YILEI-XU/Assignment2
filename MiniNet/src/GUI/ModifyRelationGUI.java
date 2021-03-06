package GUI;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

import Exceptions.*;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import people.*;

/**
 * This class is the modify relation user interface
 * @author CIFANG ZHANG
 *
 */
public class ModifyRelationGUI {
	
	/**
	 * This method is set up the add relation scene
	 * @param person the person has been selected
	 * @return Scene
	 */ 
	public Scene addRelationScene(Person person) {

		Label label = new Label("Modify relation for" + " " + person.getName());
		Button btAdd = new Button("Add");
		Button btBack = new Button("Back");
		Button btRemove = new Button("Remove");
		ListView<String> relationList = new ListView<>();
		ComboBox<String> relationBox = new ComboBox<String>();
		ComboBox<String> personBox = new ComboBox<String>();

		intialRelationElement(relationBox,personBox,relationList,btAdd,btRemove,person);

		HBox hBoxAddRelation = new HBox();
		hBoxAddRelation.getChildren().addAll(relationBox, personBox, btAdd);
		hBoxAddRelation.setSpacing(50);

		VBox vBoxModifyRelation = new VBox(10);

		vBoxModifyRelation.setPadding(new Insets(20, 20, 20, 20));
		Label labelRemoveMessage = new Label("EXIST RELATION LIST");
		vBoxModifyRelation.getChildren().addAll(hBoxAddRelation, labelRemoveMessage, relationList);

		HBox hBoxBotton = new HBox();
		hBoxBotton.getChildren().addAll(btBack, btRemove);
		hBoxBotton.setSpacing(300);

		BorderPane pane = MainMenuGUI.setUpBorderPane(label, vBoxModifyRelation, hBoxBotton, null, null);

		hBoxBotton.setAlignment(Pos.TOP_CENTER);
		vBoxModifyRelation.setAlignment(Pos.TOP_CENTER);
		hBoxAddRelation.setAlignment(Pos.TOP_CENTER);
		
		btRemove.setOnAction(e -> {			
			setRemoveAction(relationList, person,btRemove);
		});
		btAdd.setOnAction(e -> {
			setAddAction(relationBox, personBox, person, relationList);
		});
		btBack.setOnAction(e -> {
			MainMenuGUI.window.setScene(new SelectPersonGUI().viewPersonScene(person));// check where should it go back
		});

		Scene scene = new Scene(pane, 700, 500);
		scene.getStylesheets().add("GUI2.css");
		System.out.println("Modify relation");
		return scene;
	}
	
	/**
	 * initialize relation element especially for relationBox, personBox, relationList
	 * @param relationBox is a comboBox to store relations
	 * @param personBox is a comboBox to store all people except selected person
	 * @param relationList is a viewList to store already exist related people
	 * @param btAdd is a button to add relation and related person
	 * @param btRemove is a button to remove related person from selected person's relation list
	 * @param person is selected person 
	 */
	private void intialRelationElement(ComboBox<String> relationBox,ComboBox<String> personBox, ListView<String> relationList,Button btAdd,Button btRemove,Person person) {
		String[] adultRelation = { "friends", "classmates", "colleagues", "couple" };
		String[] childRelation = { "friends", "classmates" };

		relationBox.setValue("selectRelation");
		personBox.setValue("selectPerson");

		if (person instanceof Adult) {
			relationBox.getItems().addAll(adultRelation);
		} else if (person instanceof Child) {
			relationBox.getItems().addAll(childRelation);
		} else {
			relationBox.setDisable(true);
			personBox.setDisable(true);
			btAdd.setDisable(true);
			btRemove.setDisable(true);
		}

		for (String personName : MainMenuGUI.dc.getMember().keySet()) {
			if (!personName.equals(person.getName()))
				personBox.getItems().add(personName);
		}
		
		for (String relation : person.getRelationship().keySet()) {
			for (Person relatedPerson : person.getRelationship().get(relation)) {
				relationList.getItems().add(relatedPerson.getName() + "      " + relation);
			}
		}
		relationList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		relationList.getSelectionModel().select(0);
		relationList.setMaxWidth(250);
		relationList.setMaxHeight(350);
		
		if(relationList.getItems().size()==0)
		{
			btRemove.setDisable(true);
		}
	}
	
	/**
	 * This method is to set on the action to the remove button
	 * @param relationList the list of relation
	 * @param person the selected person
	 */
	private void setRemoveAction(ListView<String> relationList, Person person,Button btRemove) {
		
		String name, relation;
		String[] data = relationList.getSelectionModel().getSelectedItem().split("      ");
		name = data[0].trim();
		relation = data[1].trim();
		if (showRemoveRelationMessage(name, relation)) {
			if (removeRelationAction(person, name, relation)) {
				removeRelationSuccessfulMessage();
				System.out.println("Remove relation");
				relationList.getItems().clear();
				for (String relation1 : person.getRelationship().keySet()) {
					for (Person relatedPerson : person.getRelationship().get(relation1)) {
						relationList.getItems().add(relatedPerson.getName() + "      " + relation1);
					}
				}
				if(relationList.getItems().size()>0) {
					relationList.getSelectionModel().select(0);
				}else {
					btRemove.setDisable(true);
				}
			}
		}
	}
	
	/**
	 * This method is set on the action of add button	
	 * @param relationBox the comboBox of relation
	 * @param personBox the comboBox of person
	 * @param person the person has been selected
	 * @param relationList the list of the relation
	 */
	private void setAddAction(ComboBox<String> relationBox, ComboBox<String> personBox, Person person, ListView<String> relationList) {
		
		if (relationBox.getSelectionModel().getSelectedItem().equals("selectRelation")
				|| personBox.getSelectionModel().getSelectedItem().equals("selectPerson")) {
			showMessageForAddRelation(false);
		} else {
			String relation = relationBox.getSelectionModel().getSelectedItem();
			Person relatedPerson = MainMenuGUI.dc.getMemberObj(personBox.getSelectionModel().getSelectedItem());
			try {
				MainMenuGUI.dc.checkAlreadyExistRelation(person, relatedPerson);
				try {
					person.addRelationship(relation, relatedPerson);
					// refresh the list
					relationList.getItems().clear();
					for (String relation1 : person.getRelationship().keySet()) {
						for (Person newRelatedPerson : person.getRelationship().get(relation1)) {
							relationList.getItems().add(newRelatedPerson.getName() + "      " + relation1);
						}
					}
					if(relationList.getItems().size()>0) {
						relationList.getSelectionModel().select(0);
					}
					System.out.println("Add relation");
					showMessageForAddRelation(true);
				} catch (NotToBeFriendsException e1) {
					e1.notToBeFriendsException();
				} catch (TooYoungException e1) {
					e1.tooYoungException();
				} catch (NoAvailableException e1) {
					e1.noAvailableWarning();
				} catch (NotToBeCoupledException e1) {
					e1.notToBeCoupleWarning();
				} catch (NotToBeColleaguesException e1) {
					e1.notToBeColleaguesWarning();
				} catch (NotToBeClassmatesException e1) {
					e1.notToBeClassmatesWarning();
				} catch (Exception e1) {

				}
			} catch (AlreadyHaveRelationException e2) {
				e2.alreadyHaveRelationWarning();
			}
		}
	}
	
	/**
	 * This method is set up the scene of add the first parent
	 * @param person the selected person
	 * @return Scene the scene for add first parent
	 */
	public Scene addParentsScene1(Person person) {

		GridPane pane = MainMenuGUI.setUpPane();
		pane.add(new Label("The person is under 18 years old, please add the first parent"), 0, 0);
		Button btNext = new Button("Next");
		Button btBack = new Button("Back");
		ComboBox<String> comboBox1 = new ComboBox<String>();
		comboBox1.setValue("selectParent1");

		for (String name : MainMenuGUI.dc.getMember().keySet()) {
			comboBox1.getItems().add(name);
		}

		pane.add(btBack, 0, 4);
		pane.add(btNext, 4, 4);
		pane.add(comboBox1, 0, 1);
		GridPane.setHalignment(comboBox1, HPos.CENTER);

		btNext.setOnAction(e -> {

			String name1 = comboBox1.getValue();
			Person tempParent1 = MainMenuGUI.dc.getMemberObj(name1);
			if (tempParent1 instanceof Adult) {
				MainMenuGUI.window.setScene(addParentsScene2(person, tempParent1));
			} else {
				try {
					throw new NotToBeCoupledException(tempParent1);
				} catch (NotToBeCoupledException e1) {
					e1.notToBeCoupleWarning();
				}
			}
		});

		btBack.setOnAction(e -> {
			MainMenuGUI.window.setScene(MainMenuGUI.startScene());
		});
		Scene scene = new Scene(pane, 700, 500);
		scene.getStylesheets().add("GUI2.css");
		System.out.println("Add parent1");
		return scene;
	}
	
	/**
	 * This method is set up the scene of add the second parent
	 * @param person the selected person
	 * @return Scene the scene for add second person
	 */
	public Scene addParentsScene2(Person person, Person tempParent1) {

		GridPane pane = MainMenuGUI.setUpPane();

		Button btAdd = new Button("Add");
		Button btBack = new Button("Back");

		ComboBox<String> comboBox2 = new ComboBox<String>();
		if (tempParent1.getRelationship().containsKey("couple")) {
			pane.add(new Label(tempParent1.getName() + " has a partner. Therefore, the second parent is:"), 0, 0);
			comboBox2.setValue(tempParent1.getRelationship().get("couple").get(0).getName());
		} else {
			pane.add(new Label("The first parent is " + tempParent1.getName() + ". Please select the second parent for "
					+ person.getName()), 0, 0);
			comboBox2.setValue("selectParent2");
			
			for (String name : MainMenuGUI.dc.getMember().keySet()) {
				if (!name.equals(tempParent1.getName()))
					comboBox2.getItems().add(name);
			}
		}
		pane.add(btBack, 0, 4);
		pane.add(btAdd, 4, 4);
		pane.add(comboBox2, 0, 1);
		GridPane.setHalignment(comboBox2, HPos.CENTER);

		btAdd.setOnAction(e -> {
			String boxValue2 = comboBox2.getValue();
			if (!boxValue2.equals("selectParent2")) {
				try {
					addParentsAction(tempParent1, boxValue2, person);
				} catch (NotToBeCoupledException exception) {
					exception.notToBeCoupleWarning();
				} catch (NoAvailableException exception) {
					exception.noAvailableWarning();
				} catch (Exception exception) {
					
				}
			}
		});

		btBack.setOnAction(e -> {
			MainMenuGUI.window.setScene(addParentsScene1(person));
		});
		Scene scene = new Scene(pane, 700, 500);
		scene.getStylesheets().add("GUI2.css");
		System.out.println("Add parent2");
		return scene;
	}
	
	/**
	 * This method is about add parents action
	 * @param parent1 the first parent
	 * @param name2 the name of the second person
	 * @param child the child has been selected
	 * @throws Exception if the two parents cannot be couple
	 */
	private void addParentsAction(Person parent1, String name2, Person child) throws Exception {
		Person parent2;
		parent2 = MainMenuGUI.dc.getMemberObj(name2);

		if (parent2 instanceof Adult) {
			if (parent2.getRelationship().containsKey("couple")
					&& !(parent1.getRelationship().get("couple").get(0).equals(parent2))) {
				throw new NoAvailableException(parent2);
			} else {
				
				child.addRelationship("parent", parent1);
				child.addRelationship("parent", parent2);
			}
		} else {
			throw new NotToBeCoupledException(parent2);
		}

		MainMenuGUI.dc.getMember().put(child.getName(), child);
		System.out.println(child.getName()+" has been added");
		showMessageForAddChild(true);
		MainMenuGUI.window.setScene(MainMenuGUI.startScene());
	}
	
	/**
	 * This method is about remove the relation action
	 * @param selectedPerson the person has been selected
	 * @param name the related person's name
	 * @param relation the relation of them
	 * @return true if removed, false if cannot be removed
	 */
	private boolean removeRelationAction(Person selectedPerson, String name, String relation) {
		Person relatedPerson = MainMenuGUI.dc.getMemberObj(name);
		if (relation.equals("couple")) {
			if (selectedPerson.getRelationship().containsKey("child")
					&& selectedPerson.getRelationship().get("child").size() > 0) {
				showUnsuccessWarning(relation);
				return false;
			} else {
				selectedPerson.removeRelationship(relation, relatedPerson);
				relatedPerson.removeRelationship(relation, selectedPerson);
				return true;
			}
		} else if (relation.equals("parent") || relation.equals("sibling")) {
			showUnsuccessWarning(relation);
			return false;
		} else if (relation.equals("child")) {
			if (showRemoveChildRelationMessage(name)) {
				MainMenuGUI.dc.deletePerson(relatedPerson);
				return true;
			} else {
				return false;
			}
		} else {
			selectedPerson.removeRelationship(relation, relatedPerson);
			relatedPerson.removeRelationship(relation, selectedPerson);
			return true;
		}
	}
	
	/**
	 * This method shows the message of successful for add parents
	 * @param isSuccess if the process is successful
	 */
	private void showMessageForAddChild(boolean isSuccess) {
		Alert alert = new Alert(Alert.AlertType.WARNING);
		if (isSuccess) {
			alert.setTitle("MESSAGES");
			alert.setHeaderText("SUCCESS!");
			alert.setContentText("Congratulations! Add person successfully!");
		} else {
			alert.setTitle("MESSAGES");
			alert.setHeaderText("FAIL!");
			alert.setContentText("Sorry, add parents unsuccessfully! Please select two different unrelated Adults!");
		}
		alert.showAndWait();
	}
		
	/**
	 * This method is to show the message of show the message after add relation
	 */
	private void showMessageForAddRelation(boolean isSuccess) {
		Alert alert = new Alert(Alert.AlertType.WARNING);

		alert.setTitle("MESSAGES");

		if (isSuccess) {
			alert.setHeaderText("SUCCESS!");
			alert.setContentText("Congratulations! Add relation successfully!");
		} else {
			alert.setHeaderText("WARNING!");
			alert.setContentText("Please select RELATION/PERSON!");
		}
		alert.showAndWait();
	}
	
	/**
	 * This method is about show the message of empty selection
	 */
	private void showMessageForEmpty() {
		Alert alert = new Alert(Alert.AlertType.WARNING);

		alert.setTitle("MESSAGES");
		alert.setHeaderText("UNSUCCESS!");
		alert.setContentText("Choosing can't be empty! Please choose relation/person!");

		alert.show();
	}
	
	/**
	 * This method is show the warning message when the remove button has been clicked
	 * @param name the name of the person
	 * @param relation the relation of the two people
	 * @return true if click OK, false if do not choose remove
	 */
	private boolean showRemoveRelationMessage(String name, String relation) {

		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("MESSAGES");
		alert.setHeaderText("WARNING!");
		alert.setContentText("Are you sure to remove " + name + " with " + relation + " relation?");
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {
			return true;
		} else if (result.get() == ButtonType.CANCEL) {
			alert.close();
		}
		return false;
	}
	
	/**
	 * This method is showing the message when a person has a child and the person going to be deleted
	 * @param name the child's name
	 * @return true if click OK, false if cancel or close the stage
	 */
	private boolean showRemoveChildRelationMessage(String name) {

		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("MESSAGES");
		alert.setHeaderText("CHILD WARNING!");
		alert.setContentText(
				"If remove child relation, child " + name + " would be deleted! Are you sure to remove this relation?");
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {
			return true;
		} else if (result.get() == ButtonType.CANCEL) {
			alert.close();
		}
		return false;
	}
	
/**
 * This method is showing the message if the remove relation successful
 */
	private void removeRelationSuccessfulMessage() {
		Alert alert = new Alert(Alert.AlertType.WARNING);

		alert.setTitle("MESSAGES");
		alert.setHeaderText("SUCCESS!");
		alert.setContentText("Success to remove relation!");

		alert.showAndWait();
	}
	
/**
 * This method is showing the message if the remove relation is not working 
 * @param relation the relation going to be removed
 */
	private void showUnsuccessWarning(String relation) {
		Alert alert = new Alert(Alert.AlertType.WARNING);

		alert.setTitle("MESSAGES");
		alert.setHeaderText("UNSUCCESS!");
		if (relation.equals("couple"))
			alert.setContentText("Sorry, this couple have CHILDREN, cannot be removed the COUPLE relation!");
		else if (relation.equals("parent")) {
			alert.setContentText("Sorry, PARENT relation cannot be remove!");
		} else {
			alert.setContentText("Sorry, SIBLING relation cannot be remove!");
		}

		alert.show();
	}
}
