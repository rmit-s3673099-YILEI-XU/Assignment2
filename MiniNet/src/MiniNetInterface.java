import java.util.*;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Border;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import people.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.collections.*;
import javafx.embed.swing.SwingFXUtils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;

import Exceptions.*;
import javafx.*;



public class MiniNetInterface {

    Stage window;
    DriverClass dc;
    
//    GridPane pane = new GridPane();
//    pane.setAlignment(Pos.CENTER);
//    pane.setPadding(new Insets(5, 5, 5, 5));
//    pane.setHgap(5.5);
//    pane.setVgap(5.5);


    public MiniNetInterface(Stage window) {
        this.window = window;
        dc = new DriverClass();
        try {
            dc.initialData();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public Scene startScene() {

        // set up layout
        Label label1 = new Label("Welcome to Mininet, Please choose one from the menu");

        GridPane pane = new GridPane();
        pane.setAlignment(Pos.CENTER);
        pane.setPadding(new Insets(5, 5, 5, 5));
        pane.setHgap(5.5);
        pane.setVgap(5.5);

        pane.add(label1, 0, 0);
        Button addPersonBt = new Button("Add person");
        pane.add(addPersonBt, 0, 1);
        Button selectPersonBt = new Button("Select a Person");
        pane.add(selectPersonBt, 0, 2);
        Button findOutBt = new Button("Find out relationship");
        pane.add(findOutBt, 0, 3);
        Button defineReBt = new Button("Define relationship");
        pane.add(defineReBt, 0, 4);
        Button exitBt = new Button("Exit");
        pane.add(exitBt, 0, 5);

        // create events

//        addPersonBt.setOnAction(new EventAction{
//        		public void handle() {
//        			
//        		}
//        });
        addPersonBt.setOnAction(event -> {
            window.setScene(addPersonScene());
        });

        selectPersonBt.setOnAction(event -> {
            window.setScene(selectPersonScene());
        });

        findOutBt.setOnAction(e -> {
            window.setScene(findOutScene());
        });

        exitBt.setOnAction(event -> {
            window.close();
        });

        Scene scene = new Scene(pane, 700, 500);
        return scene;

    }

    public Scene addPersonScene() {

    		
    	
        // set up layout
        GridPane pane = new GridPane();
        pane.setAlignment(Pos.CENTER);
        pane.setPadding(new Insets(5, 5, 5, 5));
        pane.setHgap(5.5);
        pane.setVgap(5.5);

        pane.add(new Label("Name:"), 0, 0);
        TextField personName = new TextField();
        pane.add(personName, 1, 0);
        pane.add(new Label("Age"), 0, 1);
        TextField personAge = new TextField();
        pane.add(personAge, 1, 1);
        pane.add(new Label("Gender"), 0, 2);

        HBox root = new HBox();
        ToggleGroup group = new ToggleGroup();
        RadioButton female = new RadioButton("Female");
        female.setToggleGroup(group);
        female.setSelected(true);
        female.setUserData("F");
        RadioButton male = new RadioButton("Male");
        male.setToggleGroup(group);
        male.setUserData("M");
        root.getChildren().add(female);
        root.getChildren().add(male);
        pane.add(root, 1, 2);
        pane.add(new Label("Status"), 0, 3);
        TextField personStatus = new TextField();
        pane.add(personStatus, 1, 3);
        pane.add(new Label("(Optional)"), 2, 3);

        pane.add(new Label("State"), 0, 4);
        ComboBox<String> comboBox = new ComboBox<String>();
        comboBox.getItems().add("Select State");
        String[] allState = {"ACT", "NSW", "NT", "QLD", "SA", "TAS", "VIC", "WA"};
        
        for(String s: allState) {
        	comboBox.getItems().add(s);
        }

        comboBox.getSelectionModel().selectFirst();

        pane.add(comboBox, 1, 4);

        pane.add(new Label("Photo"), 0, 5);
        Label personPhoto = new Label();
        pane.add(personPhoto, 1, 5);
        Button upload = new Button("Upload");
        pane.add(upload, 2, 5);
        pane.add(new Label("(Optional)"), 3, 5);

        Button btAdd = new Button("Add");
        pane.add(btAdd, 0, 6);
        Button btCancel = new Button("Cancel");
        pane.add(btCancel, 2, 6);

        // create events
        btAdd.setOnAction(e -> {
			/* add a person */

            String name = personName.getText().trim();
            String photo = personPhoto.getText().trim();
            String status = personStatus.getText().trim();
            String gender = (String) group.getSelectedToggle().getUserData();
            String ageText = personAge.getText().trim();
            String state = (String) comboBox.getValue();
            try {
                addPersonAction(name, photo, status, gender, ageText, state);
            } catch (NotFillAllNecessInfo exception) {
                exception.lackNecessInforWarning();
            } catch (NotNumberFormatException exception) {
                exception.notNumberFormatWarning();
            } catch (NoSuchAgeException exception) {
                exception.noSuchAgeWarning();
            } catch (AlreadyExistPersonException exception) {
                exception.alreadyExistPersonWarning();
            }

        });


        btCancel.setOnAction(e -> {
            window.setScene(startScene());
        });

        upload.setOnAction(e -> {
        		File photoFile;
        		photoFile= uploadPhoto(personName.getText().trim(), personPhoto);
            personPhoto.setText(photoFile.getAbsolutePath());

            //System.out.println(uploadPhoto(personName.getText().trim()));
        });

        Scene scene = new Scene(pane, 700, 500);
        return scene;
    }

    public void addPersonAction(String name, String photo, String status, String gender, String ageText, String state)
            throws NotFillAllNecessInfo, NotNumberFormatException, NoSuchAgeException, AlreadyExistPersonException {
        Person currentPerson;
        if (name.equals("") || ageText.equals("") || state.equals("Select State")) {
            throw new NotFillAllNecessInfo();
        } else if (!(ageText.matches("\\d*") || ageText.matches("-\\d*"))) {
            throw new NotNumberFormatException();
        } else {
            int age = Integer.parseInt(ageText);
            if (age < 0 || age > 150)
                throw new NoSuchAgeException();
            else {
                currentPerson = dc.addPerson(name, photo, status, gender, age, state);
                if (currentPerson == null)
                    throw new AlreadyExistPersonException();
                else if (currentPerson instanceof Adult) {
                	
                    dc.getMember().put(name, currentPerson);
                    currentPerson.setPhoto(saveImage(name, photo));
                    showMessageForAddPerson(true);
					/* add relation window */
                    window.setScene(addRelationScene(currentPerson));
                } else {
					/* add parents window */
                	 	window.setScene(addParentsScene(currentPerson));
                	 	
					/* add relation window */
                }
                //window.setScene(startScene());
            }
        }

    }
    public void showMessageForAddPerson(boolean isSuccess)
    {
        Alert alert= new Alert(Alert.AlertType.WARNING);
        if(isSuccess) {
            alert.setTitle("MESSAGES");
            alert.setHeaderText("SUCCESS!");
            alert.setContentText("Congratulations! Add person successfully!");
        }
        else {
            alert.setTitle("MESSAGES");
            alert.setHeaderText("FAIL!");
            alert.setContentText("Sorry, add person unsuccessfully!");
        }
        alert.showAndWait();
    }

    public Scene selectPersonScene() {

        // set up layout

        GridPane pane = new GridPane();
        pane.setAlignment(Pos.CENTER);
        pane.setPadding(new Insets(5, 5, 5, 5));
        pane.setHgap(5.5);
        pane.setVgap(5.5);

        Label label = new Label("All members as below, please select one person");
        Button submit = new Button("Submit");
        Button cancel = new Button("Cancel");
        VBox layout = new VBox(10);

        ListView<String> memberList = new ListView<>();
        memberList.getItems().addAll(dc.getMember().keySet());
        memberList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        layout.setPadding(new Insets(20, 20, 20, 20));
        layout.getChildren().addAll(memberList);

        pane.add(label, 0, 0);
        pane.add(layout, 0, 1);
        pane.add(submit, 0, 2);
        pane.add(cancel, 1, 2);
        // create events

        submit.setOnAction(e -> {
            String personName = memberList.getSelectionModel().getSelectedItem();
            System.out.print(personName);
            Person selectesPerson = dc.getMemberObj(personName);
//            System.out.print(selectPerson);
            window.setScene(viewPersonScene(selectesPerson));

        });

        cancel.setOnAction(e -> {
            window.setScene(startScene());
        });

        Scene scene = new Scene(pane, 700, 500);
        return scene;

    }

    public Scene viewPersonScene(Person selectedPerson) {
//        String theName = name;
        // set up layout

        GridPane pane = new GridPane();
        pane.setAlignment(Pos.CENTER);
        pane.setPadding(new Insets(5, 5, 5, 5));
        pane.setHgap(5.5);
        pane.setVgap(5.5);

        Label label = new Label("Menu for view the person, please select one");
        Button btDisplayP = new Button("Display the profile");
        Button btDisplayR = new Button("Display relations");
        Button btFindOutPC = new Button("Find out the parent or child of the person");
        Button btDelete = new Button("Delete this person");
        Button btBack = new Button("Back");

        pane.add(label, 0, 0);
        pane.add(btDisplayP, 0, 1);
        pane.add(btDisplayR, 0, 2);
        pane.add(btFindOutPC, 0, 3);
        pane.add(btDelete, 0, 4);
        pane.add(btBack, 0, 5);

        // create event

        btDisplayP.setOnAction(e -> {
            try {
                displayProfileAction(selectedPerson);
            } catch (FileNotFoundException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }

        });
        
        btDisplayR.setOnAction(e->{
        	displayRealtionsAction(selectedPerson);
        	
        });

        btFindOutPC.setOnAction(e -> {

        });

        btDelete.setOnAction(e -> {
            deletePersonAction(selectedPerson);

        });

        btBack.setOnAction(e -> {
            window.setScene(selectPersonScene());
        });

        Scene scene = new Scene(pane, 700, 500);
        return scene;

    }

    public void deletePersonAction(Person seletedPerson) {

        // set up layout
        Stage stage = new Stage();
        GridPane pane = new GridPane();
        pane.setAlignment(Pos.CENTER);

        Label label = new Label(
                "Delete this person will also delete all the relationshio of the person, are you sure you want to delete this person?");
        Button btConfirm = new Button("Yes");
        Button btCancel = new Button("Cancel");

        pane.add(label, 0, 0);
        pane.add(btConfirm, 0, 1);
        pane.add(btCancel, 1, 1);
        // create event

        btConfirm.setOnAction(e -> {
        	dc.deletePerson(seletedPerson);
        	stage.close();
        	window.setScene(selectPersonScene());
        });

        btCancel.setOnAction(e -> {
            stage.close();
        });

        Scene scene = new Scene(pane, 1000, 300);
        stage.setScene(scene);
        stage.show();

    }

    public void displayProfileAction(Person selectedPerson) throws FileNotFoundException {
     
//        Person currentPerson = dc.getMemberObj(theName);
    		String name =selectedPerson.getName();
        int age = selectedPerson.getAge();
        String status = selectedPerson.getStatus();
        String gender = selectedPerson.getGender();
        String state = selectedPerson.getState();
        String photo = selectedPerson.getPhoto();

        Button btBack = new Button("Back");
        
        GridPane pane = new GridPane();
        pane.setAlignment(Pos.CENTER);
        pane.setPadding(new Insets(5, 5, 5, 5));
        pane.setHgap(5.5);
        pane.setVgap(5.5);

        pane.add((new Label("Name: ")), 1, 3);
        pane.add(new Label("Age: "), 1, 4);
        pane.add(new Label("Status: "), 1, 5);
        pane.add(new Label("Gender: "), 1, 6);
        pane.add(new Label("State: "), 1, 7);
        pane.add(btBack, 7, 10);
        // btBack action, put it in the right position after
        btBack.setOnAction(e -> {
        	window.setScene(viewPersonScene(selectedPerson));
        });

        // pane.add(new Label(photo), 2, 1);

        ImageView imageView = new ImageView();
      
        if( photo.equals("")) {
 
            Image defaultImage = new Image(new FileInputStream("image/default.png"));
            imageView.setImage(defaultImage);
        }else {
 
        		Image image = new Image(new FileInputStream("image/"+ photo));
            imageView.setImage(image);

        }
        imageView.setFitHeight(100);
        imageView.setFitWidth(100);
        pane.add(imageView, 3, 0);

        pane.add((new Label(name)), 4, 3);
        pane.add(new Label(Integer.toString(age)), 4, 4);
        pane.add(new Label(status), 4, 5);
        pane.add(new Label(gender), 4, 6);
        pane.add(new Label(state), 4, 7);

        Scene scene = new Scene(pane, 700, 500);
        window.setScene(scene);
        window.show();

    }
    
    public void displayRealtionsAction(Person person)
    {
    		GridPane pane = new GridPane();
        pane.setAlignment(Pos.CENTER);
        pane.setPadding(new Insets(5, 5, 5, 5));
        pane.setHgap(5.5);
        pane.setVgap(5.5);
        
        if(!(person.getRelationship().equals(null))) {
        pane.add(new Label("People who have relationship with" + " " + person.getName() + " " + "is/are:"), 0, 0);
        
        int i = 2;
        for(String type: person.getRelationship().keySet() ) {
        	
        ArrayList<Person> relatedPerson = person.getRelationship().get(type);
        
        for(Person r: relatedPerson) {
        	pane.add(new Label(r.getName()), 0, i);
        	pane.add(new Label(type), 4, i);
        	i++;
        	//System.out.println(r.getName() + type + i);
        }
       
        }
        }else {
        	pane.add(new Label(person.getName() + " " + "does not have relationship with anyone."), 0, 0);
        }
        
        Button btBack = new Button("Back");
        pane.add(btBack, 2, 12);
        btBack.setOnAction(e ->{
        	window.setScene(viewPersonScene(person));
        });
        Scene scene = new Scene(pane, 700, 500);
        window.setScene(scene);
    }

    public Scene findOutScene() {

        // set up layout

        GridPane pane = new GridPane();
        pane.setAlignment(Pos.CENTER);
        pane.setPadding(new Insets(5, 5, 5, 5));
        pane.setHgap(5.5);
        pane.setVgap(5.5);

        Label label = new Label("please select two different people");
        Button submit = new Button("Submit");
        Button cancel = new Button("Cancel");
        ComboBox<String> comboBox1 = new ComboBox<String>();
        ComboBox<String> comboBox2 = new ComboBox<String>();
        String person1, person2;

        comboBox1.getItems().add("selectFirstPerson");
        comboBox1.getSelectionModel().selectFirst();
        
        comboBox2.getItems().add("selectSecondPerson");
        comboBox2.getSelectionModel().selectFirst();
        
//        for(String key: dc.getMember().keySet()) {
//        	String name = key;
//        comboBox1.getItems().add(name);
//        }
//        comboBox1.getSelectionModel().selectFirst();
//        person1 = comboBox1.getValue();
//        
//        for(String key: dc.getMember().keySet()) {
//        	String name = key;
//        while(!name.equals(person1)) {
//        	comboBox1.getItems().add(name);
//        	
//        }
//
//        }
//        
//        person2 = comboBox2.getValue();
        

        // save this check to get relationship method
        // if(comboBox1.getValue().equals(comboBox2.getValue())) {
        // Stage stage = new Stage();
        // Scene warning = new Scene(new Label("Cannot select same person, please select
        // again"), 100, 20);
        // stage.setScene(warning);
        // stage.show();
        // stage.close();
        // window.setScene(findOutScene());
        // }

        pane.add(label, 0, 0);
        pane.add(comboBox1, 0, 1);
        pane.add(comboBox2, 1, 1);
        pane.add(submit, 0, 2);
        pane.add(cancel, 1, 2);

        // create events

        submit.setOnAction(e -> {
            String selectPerson1 = (String) comboBox1.getValue();
            String selectPerson2 = (String) comboBox2.getValue();
            System.out.print(selectPerson1 + selectPerson2);
            relationshipResult(selectPerson1, selectPerson2);
            

            // get member object, call display profile
        });

        cancel.setOnAction(e -> {
            window.setScene(startScene());
        });

        Scene scene = new Scene(pane, 700, 500);
        return scene;

    }

    public void relationshipResult(String p1, String p2) {
        String name1 = p1;
        String name2 = p2;

        Stage stage = new Stage();
        GridPane pane = new GridPane();
        String result = "their relationship is "; // need check relationship method
        Button btBack = new Button("Back");

        pane.add(new Label(result), 0, 0);
        pane.add(btBack, 0, 7);

        btBack.setOnAction(e -> {
            stage.close();
        });

        Scene scene = new Scene(pane, 300, 200);
        stage.setScene(scene);
        stage.show();
    }

    public File uploadPhoto(String name, Label personPhoto) {

        
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter photoFilter = new FileChooser.ExtensionFilter("PNG files, JPG files, JPEG files","*.PNG","*.JPG","*JPEG");
        fileChooser.getExtensionFilters().addAll(photoFilter);

        File file = fileChooser.showOpenDialog(null);
       
        return file;

    }
    public String saveImage(String name, String path)
    {

		String fileType;
		
		if(!path.equals("")) {
			File file = new File(path);
			fileType = file.getName().substring(file.getName().lastIndexOf(".") + 1, file.getName().length());
			try {
				BufferedImage bufferedImage = ImageIO.read(file);
				File output = new File("image/" + name.trim() + "Photo." + fileType);
				System.out.println(output.getPath());
				ImageIO.write(bufferedImage, fileType, output);
				return output.getName();
			} catch (IOException e) {
				e.getMessage();
			}
		}
		return path;
    }
    
    public Scene addRelationScene(Person person) {

    	GridPane pane = new GridPane();
    	pane.setAlignment(Pos.CENTER);
    	pane.setPadding(new Insets(5, 5, 5, 5));
    	pane.setHgap(5.5);
    	pane.setVgap(5.5);

        
        Button btAdd = new Button("Add");
        Button btCancel = new Button("Cancel");
       

        pane.add(new Label("Add Relation for this person"), 1, 0);
        pane.add(btAdd, 0, 6);
        pane.add(btCancel, 4, 6);
        // relation list
        
        String[] adultRelation = {"friend", "classmate", "colleague", "couple"};
        String[] childRelation = {"friend", "classmate"};
        
        ToggleGroup group = new ToggleGroup();
        VBox relationList = new VBox(10);
        relationList.setPadding(new Insets(20, 20, 20, 20));
        pane.add(relationList, 0, 1);
        
        
        if(person instanceof Adult) {
        	
        	 for(String r: adultRelation) {
        		 RadioButton button = new RadioButton(r);
        		 button.setToggleGroup(group);
        		 button.setUserData(r);
        		 relationList.getChildren().add(button);
        	 }
        }else {
        	
        	for(String r: childRelation) {
       		 RadioButton button = new RadioButton(r);
       		 button.setToggleGroup(group);
       		 button.setUserData(r);
       		 //System.out.println(button.getUserData());
       		 relationList.getChildren().add(button);
        	}
        	
        }
       
     
        
        
//        RadioButton friend = new RadioButton("Friend");
//        friend.setToggleGroup(group);
//        friend.setUserData("friend");
//        RadioButton classmate = new RadioButton("Classmate");
//        classmate.setToggleGroup(group);
//        RadioButton colleague = new RadioButton("Colleague");
//        colleague.setToggleGroup(group);
//        RadioButton couple = new RadioButton("Couple");
//        couple.setToggleGroup(group);
//        
       
//        
//        if(person instanceof Adult) {
//        relationList.getChildren().addAll(friend, classmate, colleague, couple);
//        }else{
//        	relationList.getChildren().addAll(friend, classmate);
//        }
        
    
        
        // people list
        
        ListView<String> memberList = new ListView<>();
        memberList.getItems().addAll(dc.getMember().keySet());
        memberList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        pane.add(memberList, 6, 1);
        
        btAdd.setOnAction(e ->{
        	
        String relation = (String)group.getSelectedToggle().getUserData();
        
        Person selectPerson = dc.getMemberObj(memberList.getSelectionModel().getSelectedItem());
        	addRelationAction(person, relation, selectPerson);
        });
        
        btCancel.setOnAction(e ->{
        	window.setScene(startScene());//check where should it go back
        });
       
        Scene scene = new Scene(pane, 700, 500);
        return scene;

    }
        
        
    public void addRelationAction(Person person, String relation, Person selectPerson) {
    	
    	System.out.println(person.getName() + relation + selectPerson.getName());
    		
    }

    public Scene addParentsScene(Person person) {

        GridPane pane = new GridPane();
        pane.setAlignment(Pos.CENTER);
        pane.setPadding(new Insets(5, 5, 5, 5));
        pane.setHgap(5.5);
        pane.setVgap(5.5);
        
        
        pane.add(new Label("The person is not an adult, please add parents"), 0, 0);
        
        Button btAdd = new Button("Add");
        Button btBack = new Button("Back");

       
        ComboBox<String> comboBox1 = new ComboBox<String>();
        ComboBox<String> comboBox2 = new ComboBox<String>();
        comboBox1.setValue("selectParent1");
        comboBox2.setValue("selectParent2");
        
        for(String name: dc.getMember().keySet()) { 
//	        	if (dc.getMemberObj(name) instanceof Adult) {
	        	comboBox1.getItems().add(name);
	        	comboBox2.getItems().add(name);
//	        	}
        }
        
        //set couple automatically
		comboBox1.setOnAction(e -> {
			String name1 = comboBox1.getValue();
			Person tempParent1 = dc.getMemberObj(name1);
			if (tempParent1.getRelationship().containsKey("couple")) {
				comboBox2.getSelectionModel().select(tempParent1.getRelationship().get("couple").get(0).getName());
				
			}
		
		});
		
		comboBox2.setOnAction(e -> {
			String name2 = comboBox2.getValue();
			Person tempParent2 = dc.getMemberObj(name2);
			if (tempParent2.getRelationship().containsKey("couple")) {
				comboBox1.getSelectionModel().select(tempParent2.getRelationship().get("couple").get(0).getName());

			} 
	
		});
        
        
        pane.add(btAdd, 0, 4);
        pane.add(btBack, 4, 4);
        pane.add(comboBox1, 0, 1);
        pane.add(comboBox2, 1, 1);
        
        //events
        
        btAdd.setOnAction(e ->{
        	
        String boxValue1,boxValue2;
        	boxValue1 = comboBox1.getValue();
        	boxValue2 = comboBox2.getValue();
        	if(!(boxValue1.equals("selectParent1")||boxValue2.equals("selectParent2")))
        	{
        		
        		try {
        		addParentsAction(boxValue1,boxValue2,person);
        		}catch(NotToBeCoupledException exception)
        		{
        			exception.notToBeCoupleWarning();
        		}
        		catch(NoAvailableException exception)
        		{
        			exception.noAvailableWarning();
        		}
        	}
        	//need to consider if two name are same!!!!@Sherry
        }
        	);
        
        btBack.setOnAction(e ->{
        	window.setScene(addPersonScene());
        	
        });
        Scene scene = new Scene(pane, 700, 500);

        return scene;
    }
    
    public void addParentsAction(String name1, String name2,Person child) throws NotToBeCoupledException, NoAvailableException
    {
     	Person parent1, parent2;
    		parent1 = dc.getMemberObj(name1);
		parent2 = dc.getMemberObj(name2);
    		if(!(parent1 instanceof Adult)||!(parent2 instanceof Adult))
		{
			throw new NotToBeCoupledException(parent1,parent2);
		}else
		{
			if(parent1.getRelationship().containsKey("couple")&&!(parent1.getRelationship().get("couple").get(0).equals(parent2))||
					parent2.getRelationship().containsKey("couple")&&!(parent2.getRelationship().get("couple").get(0).equals(parent1)))
			{
				throw new NoAvailableException(parent1,parent2);
				
			}
			else
			{
				child.addRelationship("parent", parent1);
				child.addRelationship("parent", parent2);
				dc.getMember().put(child.getName(), child);	
				showMessageForAddParents(true);
	    			showMessageForAddPerson(true);
	    			//need add relation UI for child @Emma
	    			if(child instanceof Child) {
	    			window.setScene(addRelationScene(child));
	    			}else {
	    				window.setScene(startScene());
	    			}
	    			
			}
		}
	
    }
    
    public void showMessageForAddParents(boolean isSuccess)
    {
        Alert alert= new Alert(Alert.AlertType.WARNING);
        if(isSuccess) {
            alert.setTitle("MESSAGES");
            alert.setHeaderText("SUCCESS!");
            alert.setContentText("Congratulations! Add parents successfully!");
        }
        else {
            alert.setTitle("MESSAGES");
            alert.setHeaderText("FAIL!");
            alert.setContentText("Sorry, add parents unsuccessfully! Please select two different unrelated Adults!");
        }
        alert.showAndWait();
    }
    
    

}
