package GUI;

import java.awt.Color;
import java.util.ArrayList;

import Exceptions.*;

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
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import people.Adult;
import people.Child;
import people.Person;

public class AddRelationGUI {

	public Scene addRelationScene(Person person) {

//		GridPane pane = MainMenu.setUpPane();
//		GridPane subPane = new GridPane();
//		subPane.setAlignment(Pos.CENTER);

		Button btAdd = new Button("Add");
		Button btBack = new Button("Back");
		Label label = new Label("Please add relation for" + " " + person.getName());
		//pane.add(new Label("Please add Relation for "+ person.getName()), 0, 0);
//		pane.add(subPane, 0, 1);
//		pane.add(btAdd, 0, 2);	
//		pane.add(btCancel, 2, 2);
		
		HBox hBox = new HBox();
		hBox.getChildren().addAll(btBack, btAdd);
		hBox.setSpacing(500);

		
		// relation list

		String[] adultRelation = { "friends", "classmates", "colleagues", "couple" };
		String[] childRelation = { "friends", "classmates" };

		ToggleGroup group = new ToggleGroup();
		VBox relationList = new VBox(10);
		relationList.setPadding(new Insets(20, 50, 20, 20));
		boolean isSelected = false;
		

		if (person instanceof Adult) {

			for (String r : adultRelation) {
				RadioButton button = new RadioButton(r);
				button.setToggleGroup(group);
				button.setUserData(r);
				relationList.getChildren().add(button);
				if(!isSelected)
				{
					button.setSelected(true);
					isSelected = true;
				}
			}
		} else {

			for (String r : childRelation) {
				RadioButton button = new RadioButton(r);
				button.setToggleGroup(group);
				button.setUserData(r);

				relationList.getChildren().add(button);
				if(!isSelected)
				{
					button.setSelected(true);
					isSelected = true;
				}
			}

		}

		//set list view
		
		ListView<String> memberList = new ListView<>();
		GridPane GPaneC = MainMenu.setUpPane();
		GPaneC.add(memberList, 0, 1);
		
		for (String personName : MainMenu.dc.getMember().keySet()) {
			if (!personName.equals(person.getName()))
				memberList.getItems().add(personName);
		}
		memberList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		memberList.getSelectionModel().select(0);
		//subPane.add(memberList, 1, 0);

		
	
		GridPane GPaneL = new SelectPersonGUI().displayRelationsAction(person);
		GPaneL.add(new Label("This person's exist relationship:"), 0, 1);
		Pane paneL = new Pane();
		paneL.getChildren().add(GPaneL);
		paneL.setMaxSize(600,200);
		
//		innerpane.setAlignment(Pos.CENTER);
//		innerpane.setPadding(new Insets(5, 50, 5, 20));
//		innerpane.setHgap(5.5);
//		innerpane.setVgap(5.5);
		//subPane.add(innerpane, 0, 0);
		
//		if(!person.getRelationship().isEmpty()) {
//			GPaneL.add(new Label("Already exist relationship:"), 0, 0);
			
			//GridPane innerpane2 = new GridPane();
			
			
			//innerpane2.setAlignment(Pos.CENTER);
			//innerpane2.setHgap(5.5);
//			innerpane2.setVgap(5.5);
//			innerpane.add(innerpane2, 0, 1);
	        
//			int i = 0;
//	        for(String type: person.getRelationship().keySet() ) {
//
//	        ArrayList<Person> relatedPerson = person.getRelationship().get(type);
//
//	        for(Person r: relatedPerson) {
//	        	GPaneL.add(new Label(r.getName()), 0, i);
//	        	GPaneL.add(new Label(type), 3, i);
	        	
//	        	innerpane2.add(new Label(r.getName()), 0, i);
//	        	innerpane2.add(new Label(type), 3, i);
	        	
//	        	i++;
//	        }
//
//	        }
//		}
		//subPane.add(relationList, 0, 0);

		
		BorderPane pane = MainMenu.setUpBorderPane(label, GPaneC, hBox, GPaneL, relationList);
		hBox.setAlignment(Pos.TOP_CENTER);
		GPaneL.setAlignment(Pos.TOP_CENTER);
		
		
		
		//event
		
		btAdd.setOnAction(e -> {
	
			String relation = (String) group.getSelectedToggle().getUserData();

			Person relatedPerson = MainMenu.dc.getMemberObj(memberList.getSelectionModel().getSelectedItem());
		
			try {
				MainMenu.dc.checkAlreadyExitRelation(person, relatedPerson);
				try {
					person.addRelationship(relation, relatedPerson);
					showMessageForAddRelation();
				}catch (NotToBeFriendsException e1) {
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
			} catch (AlreadyHaveRelationException e2) {
				// TODO Auto-generated catch block
				e2.alreadyHaveRelationWarning();
			}
			
		});

		btBack.setOnAction(e -> {
			MainMenu.window.setScene(new SelectPersonGUI().selectPersonScene());// check where should it go back
		});

		Scene scene = new Scene(pane, 700, 500);
		return scene;

	}



	public Scene addParentsScene1(Person person) {

		GridPane pane = MainMenu.setUpPane();

		pane.add(new Label("The person is under 18 years old, please add the first parent"), 0, 0);

		Button btNext = new Button("Next");
		Button btBack = new Button("Back");

		ComboBox<String> comboBox1 = new ComboBox<String>();

		comboBox1.setValue("selectParent1");

		for (String name : MainMenu.dc.getMember().keySet()) {

			comboBox1.getItems().add(name);

		}



		pane.add(btNext, 0, 4);
		pane.add(btBack, 4, 4);
		pane.add(comboBox1, 0, 1);
		GridPane.setHalignment(comboBox1, HPos.CENTER);


		// events

		btNext.setOnAction(e -> {

			String name1 = comboBox1.getValue();
			Person tempParent1 = MainMenu.dc.getMemberObj(name1);
			if(tempParent1 instanceof Adult) {
			MainMenu.window.setScene(addParentsScene2(person, tempParent1));
			}else {
				try {
					throw new NotToBeCoupledException(tempParent1);
				} catch (NotToBeCoupledException e1) {
					// TODO Auto-generated catch block
					e1.notToBeCoupleWarning();
				}
			}

		});

		btBack.setOnAction(e -> {
			MainMenu.window.setScene(MainMenu.startScene());

		});
		Scene scene = new Scene(pane, 700, 500);

		return scene;
	}
	
	public Scene addParentsScene2(Person person, Person tempParent1) {

		GridPane pane = MainMenu.setUpPane();

		Button btAdd = new Button("Add");
		Button btBack = new Button("Back");

		ComboBox<String> comboBox2 = new ComboBox<String>();
		if(tempParent1.getRelationship().containsKey("couple"))
		{
			pane.add(new Label(tempParent1.getName()+" has a partter. Therefore, the second parent is:"), 0, 0);
			comboBox2.setValue(tempParent1.getRelationship().get("couple").get(0).getName());

		}else {
		
			pane.add(new Label("The first parent is "+tempParent1.getName()+". Please select the second parent for "+person.getName()), 0, 0);
			comboBox2.setValue("selectParent2");
		

		for (String name : MainMenu.dc.getMember().keySet()) {
		
			if(!name.equals(tempParent1.getName()))
			comboBox2.getItems().add(name);
	
		}
		}

		pane.add(btAdd, 0, 4);
		pane.add(btBack, 4, 4);
		pane.add(comboBox2, 0, 1);
		GridPane.setHalignment(comboBox2, HPos.CENTER);

		// events

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
			MainMenu.window.setScene(addParentsScene1(person));

		});
		Scene scene = new Scene(pane, 700, 500);

		return scene;
	}

	public void addParentsAction(Person parent1, String name2, Person child)
			throws Exception {
		Person parent2;
		parent2 = MainMenu.dc.getMemberObj(name2);
		
		if(parent2 instanceof Adult) {
			if(parent2.getRelationship().containsKey("couple")&&!(parent1.getRelationship().get("couple").get(0).equals(parent2)))
			{
				throw new NoAvailableException(parent2);
			}else {
			child.addRelationship("parent", parent1);
			child.addRelationship("parent", parent2);
			}
		}else
		{
			throw new NotToBeCoupledException(parent2);
		}
		
				MainMenu.dc.getMember().put(child.getName(), child);
				showMessageForAddParents(true);

				// need add relation UI for child @Emma
				if (child instanceof Child) {
					MainMenu.window.setScene(addRelationScene(child));
				} else {
					MainMenu.window.setScene(MainMenu.startScene());
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
	public void showMessageForEmpty() {
		Alert alert = new Alert(Alert.AlertType.WARNING);

		alert.setTitle("MESSAGES");
		alert.setHeaderText("UNSUCCESS!");
		alert.setContentText("Choosing can't be empty! Please choose relation/person!");

		alert.show();
	}
}
