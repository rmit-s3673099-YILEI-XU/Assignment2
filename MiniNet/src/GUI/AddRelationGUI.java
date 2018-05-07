package GUI;

import Exceptions.NoAvailableException;
import Exceptions.NotToBeClassmatesException;
import Exceptions.NotToBeColleaguesException;
import Exceptions.NotToBeCoupledException;
import Exceptions.NotToBeFriendsException;
import Exceptions.TooYoungException;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import people.Adult;
import people.Child;
import people.Person;

public class AddRelationGUI {

	public Scene addRelationScene(Person person) {

		GridPane pane = MainMenu.setUpPane();

		Button btAdd = new Button("Add");
		Button btCancel = new Button("Cancel");

		pane.add(new Label("Add Relation for this person"), 1, 0);
		pane.add(btAdd, 0, 6);
		pane.add(btCancel, 4, 6);
		// relation list

		String[] adultRelation = { "friends", "classmates", "colleagues", "couple" };
		String[] childRelation = { "friends", "classmates" };

		ToggleGroup group = new ToggleGroup();
		VBox relationList = new VBox(10);
		relationList.setPadding(new Insets(20, 20, 20, 20));
		pane.add(relationList, 0, 1);

		if (person instanceof Adult) {

			for (String r : adultRelation) {
				RadioButton button = new RadioButton(r);
				button.setToggleGroup(group);
				button.setUserData(r);
				relationList.getChildren().add(button);
			}
		} else {

			for (String r : childRelation) {
				RadioButton button = new RadioButton(r);
				button.setToggleGroup(group);
				button.setUserData(r);
				// System.out.println(button.getUserData());
				relationList.getChildren().add(button);
			}

		}

		ListView<String> memberList = new ListView<>();
		for (String personName : MainMenu.dc.getMember().keySet()) {
			if (!personName.equals(person.getName()))
				memberList.getItems().add(personName);
		}
		memberList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		pane.add(memberList, 6, 1);

		btAdd.setOnAction(e -> {

			String relation = (String) group.getSelectedToggle().getUserData();

			Person selectPerson = MainMenu.dc.getMemberObj(memberList.getSelectionModel().getSelectedItem());
			try {

				person.addRelationship(relation, selectPerson);
				showMessageForAddRelation();
			} catch (NotToBeFriendsException e1) {
				// TODO Auto-generated catch block
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
			// addRelationAction(person, relation, selectPerson);
		});

		btCancel.setOnAction(e -> {
			MainMenu.window.setScene(MainMenu.startScene());// check where should it go back
		});

		Scene scene = new Scene(pane, 700, 500);
		return scene;

	}

	public Scene addParentsScene1(Person person) {

		GridPane pane = MainMenu.setUpPane();

		pane.add(new Label("The person is under 18 years old, please add the first parent"), 0, 0);

		Button btAdd = new Button("Add");
		Button btBack = new Button("Back");

		ComboBox<String> comboBox1 = new ComboBox<String>();
		//ComboBox<String> comboBox2 = new ComboBox<String>();
		comboBox1.setValue("selectParent1");
		//comboBox2.setValue("selectParent2");

		for (String name : MainMenu.dc.getMember().keySet()) {
			// if (dc.getMemberObj(name) instanceof Adult) {
			comboBox1.getItems().add(name);
			//comboBox2.getItems().add(name);
			// }
		}

		// set couple automatically
		comboBox1.setOnAction(e -> {
			String name1 = comboBox1.getValue();
			Person tempParent1 = MainMenu.dc.getMemberObj(name1);
//			if (tempParent1.getRelationship().containsKey("couple")) {
//				comboBox2.getSelectionModel().select(tempParent1.getRelationship().get("couple").get(0).getName());
//
//			}

		});


		pane.add(btAdd, 0, 4);
		pane.add(btBack, 4, 4);
		pane.add(comboBox1, 0, 1);
		GridPane.setHalignment(comboBox1, HPos.CENTER);


		// events

		btAdd.setOnAction(e -> {

			String boxValue1;
			boxValue1 = comboBox1.getValue();
			MainMenu.window.setScene(addParentsScene2(person, boxValue1, comboBox1));

		});

		btBack.setOnAction(e -> {
			// MainMenu.window.setScene(addPersonScene());
			MainMenu.window.setScene(MainMenu.startScene());

		});
		Scene scene = new Scene(pane, 700, 500);

		return scene;
	}
	
	public Scene addParentsScene2(Person person, String boxValue1, ComboBox<String> comboBox1) {

		GridPane pane = MainMenu.setUpPane();

		pane.add(new Label("The person is under 18 years old, please add the second parent"), 0, 0);

		Button btAdd = new Button("Add");
		Button btBack = new Button("Back");

		//ComboBox<String> comboBox1 = new ComboBox<String>();
		ComboBox<String> comboBox2 = new ComboBox<String>();
		//comboBox1.setValue("selectParent1");
		comboBox2.setValue("selectParent2");

		for (String name : MainMenu.dc.getMember().keySet()) {
			// if (dc.getMemberObj(name) instanceof Adult) {
			//comboBox1.getItems().add(name);
			comboBox2.getItems().add(name);
			// }
		}

		// set couple automatically
		comboBox1.setOnAction(e -> {
			String name1 = comboBox1.getValue();
			Person tempParent1 = MainMenu.dc.getMemberObj(name1);
			if (tempParent1.getRelationship().containsKey("couple")) {
				comboBox2.getSelectionModel().select(tempParent1.getRelationship().get("couple").get(0).getName());

			}

		});

		comboBox2.setOnAction(e -> {
			String name2 = comboBox2.getValue();
			Person tempParent2 = MainMenu.dc.getMemberObj(name2);
			if (tempParent2.getRelationship().containsKey("couple")) {
				comboBox1.getSelectionModel().select(tempParent2.getRelationship().get("couple").get(0).getName());

			}

		});

		pane.add(btAdd, 0, 4);
		pane.add(btBack, 4, 4);
		//pane.add(comboBox1, 0, 1);
		pane.add(comboBox2, 0, 1);
		//GridPane.setHalignment(tmp[currArrPos], HPos.CENTER);
		GridPane.setHalignment(comboBox2, HPos.CENTER);

		// events

		btAdd.setOnAction(e -> {

			String boxValue2;
			//boxValue1 = comboBox1.getValue();
			boxValue2 = comboBox2.getValue();
			//if (!(boxValue1.equals("selectParent1") || boxValue2.equals("selectParent2"))) {
			if (!(boxValue1.equals(boxValue2)) && !(boxValue1.equals("selectParent1") || boxValue2.equals("selectParent2"))){
				try {
					addParentsAction(boxValue1, boxValue2, person);
				} catch (NotToBeCoupledException exception) {
					exception.notToBeCoupleWarning();
					//MainMenu.window.setScene(addParentsScene1(person));
				} catch (NoAvailableException exception) {
					exception.noAvailableWarning();
				}
			}
//			// need to consider if two name are same!!!!@Sherry
		});

		btBack.setOnAction(e -> {
			// MainMenu.window.setScene(addPersonScene());
			MainMenu.window.setScene(addParentsScene1(person));

		});
		Scene scene = new Scene(pane, 700, 500);

		return scene;
	}

	public void addParentsAction(String name1, String name2, Person child)
			throws NotToBeCoupledException, NoAvailableException {
		Person parent1, parent2;
		parent1 = MainMenu.dc.getMemberObj(name1);
		parent2 = MainMenu.dc.getMemberObj(name2);
		if (!(parent1 instanceof Adult) || !(parent2 instanceof Adult)) {
			throw new NotToBeCoupledException(parent1, parent2);
		} else {
			if (parent1.getRelationship().containsKey("couple")
					&& !(parent1.getRelationship().get("couple").get(0).equals(parent2))
					|| parent2.getRelationship().containsKey("couple")
							&& !(parent2.getRelationship().get("couple").get(0).equals(parent1))) {
				throw new NoAvailableException(parent1, parent2);

			} else {
				try {
					child.addRelationship("parent", parent1);
					child.addRelationship("parent", parent2);
				} catch (Exception e) {

				}
				MainMenu.dc.getMember().put(child.getName(), child);
				showMessageForAddParents(true);
				// AddPersonGUI addPerson = new AddPersonGUI(window);
				// addPerson.showMessageForAddPerson(true);
				// need add relation UI for child @Emma
				if (child instanceof Child) {
					MainMenu.window.setScene(addRelationScene(child));
				} else {
					MainMenu.window.setScene(MainMenu.startScene());
				}

			}
		}

	}

	public void showMessageForAddParents(boolean isSuccess) {
		Alert alert = new Alert(Alert.AlertType.WARNING);
		if (isSuccess) {
			alert.setTitle("MESSAGES");
			alert.setHeaderText("SUCCESS!");
			alert.setContentText("Congratulations! Add parents successfully!");
		} else {
			alert.setTitle("MESSAGES");
			alert.setHeaderText("FAIL!");
			alert.setContentText("Sorry, add parents unsuccessfully! Please select two different unrelated Adults!");
		}
		alert.showAndWait();
	}

	public void showMessageForAddRelation() {
		Alert alert = new Alert(Alert.AlertType.WARNING);

		alert.setTitle("MESSAGES");
		alert.setHeaderText("SUCCESS!");
		alert.setContentText("Congratulations! Add relation successfully!");

		alert.showAndWait();
	}
}
