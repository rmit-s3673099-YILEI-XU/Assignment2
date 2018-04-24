import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.RadioButton;
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

import javafx.*;
public class MiniNetInterface{

    Stage window;
    DriverClass dc;
    
    public MiniNetInterface(Stage window) 
    {
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

//set up layout
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

//create events

        addPersonBt.setOnAction(event ->
                {
					try {
						window.setScene(addPersonScene());
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
        );

        selectPersonBt.setOnAction(event ->{
            window.setScene(selectPersonScene());
        });

        findOutBt.setOnAction(e ->{
                    window.setScene(findOutScene());
                }
        );

        exitBt.setOnAction(event -> {
            window.close();
        });

        Scene scene = new Scene(pane, 700, 500);
        return scene;

    }

    public Scene addPersonScene() throws FileNotFoundException {

        //set up layout
        GridPane pane = new GridPane();
        pane.setAlignment(Pos.CENTER);
        pane.setPadding(new Insets(5, 5, 5, 5));
        pane.setHgap(5.5);
        pane.setVgap(5.5);

        pane.add(new Label("Name:"), 0, 0);
        pane.add(new TextField(), 1, 0);
        pane.add(new Label("Age"), 0, 1);
        pane.add(new TextField(), 1, 1);
        pane.add(new Label("Gender"), 0, 2);

        HBox root = new HBox();
        ToggleGroup group = new ToggleGroup();
        RadioButton female = new RadioButton("Female");
        female.setToggleGroup(group);
        female.setSelected(true);
        RadioButton male = new RadioButton("Male");
        male.setToggleGroup(group);
        root.getChildren().add(female);
        root.getChildren().add(male);
        pane.add(root,1, 2);
        pane.add(new Label("Status"), 0, 3);
        pane.add(new TextField(), 1, 3);
        
        pane.add(new Label("State"), 0, 4);
        ComboBox comboBox = new ComboBox();
        comboBox.getItems().add("Select State");
        comboBox.getItems().add("ACT");
        comboBox.getItems().add("NSW");
        comboBox.getItems().add("NT");
        comboBox.getItems().add("QLD");
        comboBox.getItems().add("SA");
        comboBox.getItems().add("TAS");
        comboBox.getItems().add("VIC");
        comboBox.getItems().add("WA");
        comboBox.getSelectionModel().selectFirst();

        pane.add(comboBox,1, 4);
        
        pane.add(new Label("Photo"), 0, 5);
        pane.add(new TextField(), 1, 5);
        Button upload = new Button("upload");
        pane.add(upload, 2, 5);
        
        
        
        Button btAdd = new Button("Add");
        pane.add(btAdd, 0, 6);
        Button btCancel = new Button("Cancel");
        pane.add(btCancel, 2, 6);

        // create events
        btAdd.setOnAction(e -> {
        	/*add a person*/
        });

        btCancel.setOnAction(e -> {
            window.setScene(startScene());
        });
        
        upload.setOnAction(e ->{
       // 	uploadPhoto(Person dc.);
        }
        );

        Scene scene = new Scene(pane, 700, 500);
        return scene;
    }


    public Scene selectPersonScene() {

        // set up layout

        GridPane pane = new GridPane();
        pane.setAlignment(Pos.CENTER);
        pane.setPadding(new Insets(5, 5, 5, 5));//这是啥有没有没区别
        pane.setHgap(5.5);
        pane.setVgap(5.5);

        Label label = new Label("All members as below, please select one person");
        Button submit = new Button("Submit");
        Button cancel = new Button("Cancel");
        VBox layout = new VBox(10);

        ListView<String> memberList = new ListView<>();
        memberList.getItems().addAll(dc.getMember().keySet());
        memberList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        layout.setPadding(new Insets(20, 20, 20, 20));//没区别啊
        layout.getChildren().addAll(memberList, submit);

        pane.add(label, 0, 0);
        pane.add(layout, 0, 1);
        pane.add(submit, 0, 2);
        pane.add(cancel, 1, 2);
        // create events

        submit.setOnAction(e -> {
            String selectPerson = memberList.getSelectionModel().getSelectedItem();
            System.out.print(selectPerson);
            window.setScene(modifyPersonScene(selectPerson));

        });

        cancel.setOnAction(e -> {
            window.setScene(startScene());
        });

        Scene scene = new Scene(pane, 700, 500);
        return scene;

    }

    public Scene modifyPersonScene(String name) {
        String theName = name;
// set up layout

        GridPane pane = new GridPane();
        pane.setAlignment(Pos.CENTER);
        pane.setPadding(new Insets(5, 5, 5, 5));
        pane.setHgap(5.5);
        pane.setVgap(5.5);

        Label label = new Label("Menu for modify the person, please select one");
        Button btDisplayP = new Button("Display the profile");
        Button btFindOutPC = new Button("Find out the parent or child of the person");
        Button btDelete = new Button("Delete this person");
        Button btBack = new Button("Go back to previous page");

        pane.add(label, 0, 0);
        pane.add(btDisplayP, 0, 1);
        pane.add(btFindOutPC, 0, 2);
        pane.add(btDelete, 0, 3);
        pane.add(btBack, 0, 4);

// create event

        btDisplayP.setOnAction(e ->
        {
            try {
				displayProfileAction(theName);
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

        });

        btFindOutPC.setOnAction(e ->
        {

        });

        btDelete.setOnAction(e ->
        {
            deletePersonAction();

        });

        btBack.setOnAction(e ->
        {
            window.setScene(selectPersonScene());
        });



        Scene scene = new Scene(pane, 700, 500);
        return scene;

    }

    public void deletePersonAction() {

// set up layout
        Stage stage = new Stage();
        GridPane pane = new GridPane();
        pane.setAlignment(Pos.CENTER);

        Label label = new Label("Delete this person will also delete all the relationshio of the person, are you sure you want to delete this person?");
        Button btConfirm = new Button("Yes");
        Button btCancel = new Button("Cancel");

        pane.add(label, 0, 0);
        pane.add(btConfirm, 0, 1);
        pane.add(btCancel, 1, 1);
// create event

        btConfirm.setOnAction(e ->{
                    //deletePerson();
                    //window.setScene(value);
                }
        );

        btCancel.setOnAction(e ->
        {
            stage.close();
        });

        Scene scene = new Scene(pane, 1000, 300);
        stage.setScene(scene);
        stage.show();

    }

    public void displayProfileAction(String name) throws FileNotFoundException {
        String theName = name;
        int age = dc.getMemberObj(theName).getAge();
        String status = dc.getMemberObj(theName).getStatus();
        String gender = dc.getMemberObj(theName).getGender();
        String state = dc.getMemberObj(theName).getState();
        //String photo = dc.getMemberObj(theName).getPhoto();//need what
       
		
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
		
		
		//pane.add(new Label(photo), 2, 1);
		
		final ImageView imageView = new ImageView();
        Image defaultImage = new Image (new FileInputStream("image/default.png")); 
        imageView.setImage(defaultImage);
        
        imageView.setFitHeight(100);
        imageView.setFitWidth(100);
        pane.add(imageView, 3, 0);
        
		pane.add((new Label(theName)), 4, 3);
		pane.add(new Label(Integer.toString(age)), 4, 4);
		pane.add(new Label(status), 4, 5);
		pane.add(new Label(gender), 4, 6);
		pane.add(new Label(state), 4, 7);
		
	
	
		Scene scene = new Scene(pane, 700, 500);
		 window.setScene(scene);
		 window.show();
		
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
        ComboBox comboBox1 = new ComboBox();
        ComboBox comboBox2 = new ComboBox();

        comboBox1.getItems().add("selectFirstPerson");
        comboBox1.getItems().add("小明");
        comboBox1.getItems().add("小红");
        comboBox1.getItems().add("小刚");
        comboBox1.getSelectionModel().selectFirst();
        comboBox2.getItems().add("selectSecondPerson");
        comboBox2.getItems().add("小明");
        comboBox2.getItems().add("小红");
        comboBox2.getItems().add("小刚");
        comboBox2.getSelectionModel().selectFirst();

//    		save this check to get relationship method
//   		if(comboBox1.getValue().equals(comboBox2.getValue())) {
//    			Stage stage = new Stage();
//    			Scene warning = new Scene(new Label("Cannot select same person, please select again"), 100, 20);
//    			stage.setScene(warning);
//    			stage.show();
//    			stage.close();
//    			window.setScene(findOutScene());
//    		}

        pane.add(label, 0, 0);
        pane.add(comboBox1, 0, 1);
        pane.add(comboBox2, 1, 1);
        pane.add(submit, 0, 2);
        pane.add(cancel, 1, 2);

        // create events

        submit.setOnAction(e -> {
            String selectPerson1 = (String)comboBox1.getValue();
            String selectPerson2 = (String)comboBox2.getValue();
            System.out.print(selectPerson1 + selectPerson2);
            relationshipResult(selectPerson1, selectPerson2);

            //get member object, call display profile
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
        String result="their relationship is "; //need check relationship method
        Button btBack = new Button("Back");

        pane.add(new Label(result), 0, 0);
        pane.add(btBack, 0, 7);

        btBack.setOnAction(e ->{
              stage.close();
                }
        );

        Scene scene = new Scene(pane, 300, 200);
        stage.setScene(scene);
        stage.show();
    }

//    public void uploadPhoto(Person person) {
//    		
//    		this.person = person;
//    		FileChooser fileChooser = new FileChooser();
//    		FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files","*.PNG");
//    		FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files","*.JPG");
//    		fileChooser.getExtensionFilters().addAll(extFilterPNG);
//    		
//    		File file = fileChooser.showOpenDialog(null);
//    		
//    		try {
//    			
//    			BufferedImage bufferedImage = ImageIO.read(file);
//    			Image image = SwingFXUtils.toFXImage(bufferedImage, null);
//    			
//    			
//    		} catch (IOException ex) {
//    			//Logger.getLogger((MiniNetInterface.class.getName()).log(Level.SEVERE, null, ex);
//    		}
   
    	
    		
    	
    	
    }




