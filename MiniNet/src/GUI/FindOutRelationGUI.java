package GUI;

import Exceptions.NoAvailableException;
import Exceptions.NotToBeCoupledException;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import people.Adult;
import people.Person;

/**
 * This class is for the user interface of find out relation between two people
 * @author CIFANG ZHANG
 *
 */
public class FindOutRelationGUI {
	
/**
 * This method is set up the scene of find out relation
 * @return Scene
 */
	public Scene findOutRelationScene() {

		Label description = new Label("Please select two people to find out the relation with them.");

		Button btSubmit = new Button("Submit");
		Button btBack = new Button("Back");
		HBox functionBox = new HBox();
		functionBox.setSpacing(300);
		functionBox.getChildren().addAll(btBack, btSubmit);
		Label resultLabel = new Label("Relation result: ");
		Label result = new Label("");
		
		ComboBox<String> comboBox1 = new ComboBox<String>();
		ComboBox<String> comboBox2 = new ComboBox<String>();
		comboBox1.setValue("selectPerson1");
		comboBox2.setValue("selectPerson2");

		comboBox1.getItems().addAll(MainMenuGUI.dc.getMember().keySet());
		comboBox2.getItems().addAll(MainMenuGUI.dc.getMember().keySet());

		HBox peoBox = new HBox();
		peoBox.getChildren().addAll(comboBox1, comboBox2);

		peoBox.setSpacing(100);
		peoBox.setAlignment(Pos.CENTER);

		VBox mainBox = new VBox(10);
		mainBox.getChildren().addAll(peoBox, resultLabel, result);
		mainBox.setPadding(new Insets(20, 20, 50, 20));

		BorderPane pane = MainMenuGUI.setUpBorderPane(description, mainBox, functionBox, null, null);
		mainBox.setAlignment(Pos.CENTER);
		functionBox.setAlignment(Pos.CENTER);

		btSubmit.setOnAction(e -> {
			
			setSubmitAction(comboBox1, comboBox2, result);

		});

		btBack.setOnAction(e -> {
			MainMenuGUI.window.setScene(MainMenuGUI.startScene());

		});
		Scene scene = new Scene(pane, 700, 500);
		scene.getStylesheets().add("GUI2.css");
		return scene;
	}
	
	/**
	 * This method is set up the submit button action
	 * @param comboBox1 the first selection comboBox
	 * @param comboBox2 the second selection comboBox
	 * @param result the find out result
	 */
	private void setSubmitAction(ComboBox<String> comboBox1, ComboBox<String> comboBox2, Label result) {

		if (comboBox1.getValue().equals("selectPerson1") || comboBox2.getValue().equals("selectPerson2")) {

			result.setText("Warning! Please choose 2 people!");
			result.setStyle("-fx-text-fill: #E33539");

		} else {
			Person person1 = MainMenuGUI.dc.getMemberObj((String) comboBox1.getValue());
			Person person2 = MainMenuGUI.dc.getMemberObj((String) comboBox2.getValue());

			if (person1.equals(person2)) {

				result.setStyle("-fx-text-fill: #E33539");
				result.setText("Warning! They are the same person. Please select again!");
			} else {
				String relation = findOutRelationAction(person1, person2);

				result.setStyle("-fx-text-fill: #FFFFFF");

				if (relation == null) {

					result.setText("No relation between: " + person1.getName() + " and " + person2.getName());
				} else {
					if (relation.equals("parent")) {
						if (person1 instanceof Adult) {
							result.setText(person1.getName() + " is a parent of " + person2.getName());
						} else {
							result.setText(person2.getName() + " is a parent of " + person1.getName());
						}
					} else if (relation.equals("child")) {
						if (person1 instanceof Adult) {
							result.setText(person1.getName() + " is a parent of " + person2.getName());
						} else {
							result.setText(person2.getName() + " is a parent of " + person1.getName());
						}
					} else {
						result.setText(person1.getName() + " and " + person2.getName() + " are " + relation);
					}
				}
			}
		}
	}
	
/**
 * This method is to get the result of find out relation
 * @param person1 the first person
 * @param person2 the second person
 * @return String the result
 */
	private String findOutRelationAction(Person person1, Person person2) {
		for (String relation : person1.getRelationship().keySet()) {
			for (Person reletedPerson : person1.getRelationship().get(relation)) {
				if (reletedPerson.equals(person2))
					return relation;
			}
		}
		return null;
	}
}
