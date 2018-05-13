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

public class FindOutRelationGUI {

	public Scene findOutRelationScene() {

		Label description = new Label("Please select two people to find out the relation with them.");

		Button btDetect = new Button("Detect");
		Button btBack = new Button("Back");
		HBox functionBox = new HBox();
		functionBox.setSpacing(300);
		functionBox.getChildren().addAll(btBack, btDetect);
		Label result = new Label("Relation result: ");

		ComboBox<String> comboBox1 = new ComboBox<String>();
		ComboBox<String> comboBox2 = new ComboBox<String>();
		comboBox1.setValue("selectPerson1");
		comboBox2.setValue("selectPerson2");

		// initial the data in 2 conboBox
		comboBox1.getItems().addAll(MainMenu.dc.getMember().keySet());
		comboBox2.getItems().addAll(MainMenu.dc.getMember().keySet());

		HBox peoBox = new HBox();
		peoBox.getChildren().addAll(comboBox1, comboBox2);

		peoBox.setSpacing(100);
		peoBox.setAlignment(Pos.CENTER);

		VBox mainBox = new VBox(10);
		mainBox.getChildren().addAll(peoBox, result);
		mainBox.setPadding(new Insets(20, 20, 50, 20));

		BorderPane pane = MainMenu.setUpBorderPane(description, mainBox, functionBox, null, null);
		mainBox.setAlignment(Pos.CENTER);
		functionBox.setAlignment(Pos.CENTER);

		// events

		btDetect.setOnAction(e -> {

			if (comboBox1.getValue().equals("selectPerson1") || comboBox2.getValue().equals("selectPerson2")) {

				result.setStyle("-fx-text-fill: #E33539");
				result.setText("Relation result: Warning! Please choose 2 people!");

			} else {
				Person person1 = MainMenu.dc.getMemberObj(comboBox1.getValue());
				Person person2 = MainMenu.dc.getMemberObj(comboBox2.getValue());

				if (person1.equals(person2)) {

					result.setStyle("-fx-text-fill: #E33539");
					result.setText("Relation result: Warning! They are the same person. Please select again!");
				} else {
					String relation = findOutRelationAction(person1, person2);

					result.setStyle("-fx-text-fill: #000000");

					if (relation == null) {

						result.setText("Relation result: No relation between: " + person1.getName() + " and "
								+ person2.getName());
					} else {
						if (relation.equals("parent")) {
							if (person1 instanceof Adult) {
								result.setText("Relation result: " + person1.getName() + " is a parent of "
										+ person2.getName());
							} else {
								result.setText("Relation result: " + person2.getName() + " is a parent of "
										+ person1.getName());
							}
						} else {
							result.setText("Relation result: " + person1.getName() + " and " + person2.getName()
									+ " are " + relation);
						}
					}
				}

			}
		});

		btBack.setOnAction(e -> {
			MainMenu.window.setScene(MainMenu.startScene());

		});
		Scene scene = new Scene(pane, 700, 500);
		// scene.getStylesheets().add("GUI.css");
		return scene;
	}

	public String findOutRelationAction(Person person1, Person person2) {
		for (String relation : person1.getRelationship().keySet()) {
			for (Person reletedPerson : person1.getRelationship().get(relation)) {
				if (reletedPerson.equals(person2))
					return relation;
			}
		}
		return null;
	}
}
