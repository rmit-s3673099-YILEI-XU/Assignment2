package GUI;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import people.*;

public class RemoveRelationGUI {

	public Scene removeRelationScene(Person person) {
		
		GridPane pane = MainMenu.setUpPane();
		Button btRemove = new Button("Remove");
		Button btCancel = new Button("Cancel");
		VBox layout = new VBox(10);
//		ObservableList<String[]> data =  FXCollections.observableArrayList();
//	
//		
//		String[] d = {"sss","sss"};
//		pane.add(new Label("Please choose person to remove for "+ person.getName()), 0, 0);
//		
//		TableView table = new TableView();
//		TableColumn personCol = new TableColumn("Person");
//		personCol.setPrefWidth(130);
//		personCol.setCellValueFactory(new PropertyValueFactory<>("Person"));
//	 
//	    TableColumn relationCol = new TableColumn("Relation");
//	    relationCol.setPrefWidth(130);
//	    relationCol.setCellValueFactory(new PropertyValueFactory<>("Relation"));
//	   data.add(d);
		ListView<String> relationList = new ListView<>();
		
		
		for(String relation: person.getRelationship().keySet()) {
			for (Person relatedPerson : person.getRelationship().get(relation)) {
			relationList.getItems().add(relatedPerson.getName()+"      "+relation);	
			}
		}
//		table.setItems(data);
//		table.getColumns().addAll(personCol,relationCol);
		
		relationList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		relationList.getSelectionModel().select(0);
        layout.setPadding(new Insets(20, 20, 20, 20));
	    layout.getChildren().addAll(relationList);
        
		pane.add(layout, 0, 1);
		pane.add(btRemove, 0, 2);
		pane.add(btCancel, 2, 2);

		btRemove.setOnAction(e->{
			String name, relation;
			String[] data = relationList.getSelectionModel().getSelectedItem().split("      ");
			name = data[0].trim();
			relation = data[1].trim();
			if(showRemoveRelationMessage(name,relation)) {
				if(removeRelationAction(person,name,relation)) {
				
				removeRelationSuccessfulMessage();
				relationList.getItems().clear();
				for(String relation1: person.getRelationship().keySet()) {
					for (Person relatedPerson : person.getRelationship().get(relation1)) {
					relationList.getItems().add(relatedPerson.getName()+"      "+relation1);	
					}
				}
				}
			}
		});
		
		btCancel.setOnAction(e->{
			MainMenu.window.setScene(new SelectPersonGUI().viewPersonScene(person));
		});
		
		Scene scene = new Scene(pane, 700, 500);
		return scene;
	}
	
	public boolean removeRelationAction(Person selectedPerson,String name,String relation) {
		Person reletedPerson = MainMenu.dc.getMemberObj(name);
		if(relation.equals("couple"))
		{
			if(selectedPerson.getRelationship().containsKey("child")&&selectedPerson.getRelationship().get("child").size()>0)
			{
				showUnsuccessWaning(relation);
				return false;
			}
			else {
				selectedPerson.removeRelationship(relation, reletedPerson);
				reletedPerson.removeRelationship(relation, selectedPerson);
				return true;
			}
			
		}else if(relation.equals("parent")){
			showUnsuccessWaning(relation);
			return false;
		}else if(relation.equals("child"))
		{
			if(showRemoveChildRelationMessage(name)) {
				MainMenu.dc.deletePerson(reletedPerson);
				return true;
			}
			else {
				return false;
			}
		}
		else
		{
		selectedPerson.removeRelationship(relation, reletedPerson);
		reletedPerson.removeRelationship(relation, selectedPerson);
		return true;
		}
		
	}
	
	public boolean showRemoveRelationMessage( String name,String relation) {

		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("MESSAGES");
		alert.setHeaderText("WARNING!");
		alert.setContentText(
					"Are you sure to remove "+name+" with "+relation+" relation?");
		Optional<ButtonType> result = alert.showAndWait();
		if(result.get() == ButtonType.OK) {
			return true;
		}else if(result.get() == ButtonType.CANCEL) {
			alert.close();
		}
		return false;
	}
	public boolean showRemoveChildRelationMessage( String name) {

		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("MESSAGES");
		alert.setHeaderText("CHILD WARNING!");
		alert.setContentText(
					"If remove child relation, child "+name+" would be deleted! Are you sure to remove this relation?");
		Optional<ButtonType> result = alert.showAndWait();
		if(result.get() == ButtonType.OK) {
			return true;
		}else if(result.get() == ButtonType.CANCEL) {
			alert.close();
		}
		return false;
	}
	
	public void removeRelationSuccessfulMessage() {
		Alert alert = new Alert(Alert.AlertType.WARNING);

		alert.setTitle("MESSAGES");
		alert.setHeaderText("SUCCESS!");
		alert.setContentText("Success to remove relation!");

		alert.showAndWait();
	}
	public void showUnsuccessWaning(String relation) {
		Alert alert = new Alert(Alert.AlertType.WARNING);

		alert.setTitle("MESSAGES");
		alert.setHeaderText("UNSUCCESS!");
		if(relation.equals("couple"))
			alert.setContentText("Sorry, this couple have CHILDREN, cannot be removed the COUPLE relation!");
		else
			alert.setContentText("Sorry, PARENT relation cannot be remove!");

		alert.show();
	}
}
