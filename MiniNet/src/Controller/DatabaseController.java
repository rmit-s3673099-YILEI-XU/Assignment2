package Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.TreeMap;

import org.hsqldb.Server;

import people.Person;

public class DatabaseController {

	Server hsqlServer = null;
	Connection connection = null;

	public void initialDatabase() {

		hsqlServer = new Server();
		hsqlServer.setLogWriter(null);
		hsqlServer.setSilent(true);
		hsqlServer.setDatabaseName(0, "TestDB");
		hsqlServer.setDatabasePath(0, "file:MYDB");
		hsqlServer.start();

		try {
			Class.forName("org.hsqldb.jdbcDriver");
			connection = DriverManager.getConnection("jdbc:hsqldb:TestDB", "sa", "123");
			connection.prepareStatement("drop table people if exists;").execute();
			connection.prepareStatement(
					"create table people (name varchar(20) not null, photo varchar(20), status varchar(20), gender varchar(20), age integer, state varchar(20)  );")
					.execute();
			connection.commit();
		} catch (SQLException e2) {
			e2.printStackTrace();
		} catch (ClassNotFoundException e2) {
			e2.printStackTrace();
		}
	}

	public void initialDataInDB(TreeMap<String, Person> member) {
		
		try {
			 // query from the db
			for(String name: member.keySet()) {
			
			connection.prepareStatement(
							"insert into people " + "values ('"+member.get(name).getName()+"','"+member.get(name).getPhoto() +"','"+member.get(name).getStatus()
							+"','"+member.get(name).getGender()+"',"+member.get(name).getAge()+",'"+member.get(name).getState()+"');")
					.execute();
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void modifyDatabase(Person person,String operation) {
		try {
			switch (operation) {
			case "addPerson":
				connection.prepareStatement("insert into people " + "values ('" + person.getName() + "','"
						+ person.getPhoto() + "','" + person.getStatus() + "','" + person.getGender() + "',"
						+ person.getAge() + ",'" + person.getState() + "');").execute();
				break;
			case "deletePerson":
				connection.prepareStatement("delete from people where name = '"+person.getName()+"';").execute();
				break;
			case "modifyProfile":
				connection.prepareStatement("UPDATE people SET photo= '"+person.getPhoto()+"', status= '"+person.getStatus()
						+"', gender = '"+person.getGender()+"', age = "+person.getAge()+", state = '"+person.getState()
						+ "' where name = '"+person.getName()+"';").execute();
				break;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		displayDataInDB();

	}
	
	public void displayDataInDB()
	{
		ResultSet rs = null;
		try {
		rs = connection.prepareStatement("select * from people;").executeQuery();
		while (rs.next()) {
			System.out.println("Name: " + rs.getString(1));
			System.out.println("Photo: " + rs.getString(2));
			System.out.println("Status: " + rs.getString(3));
			System.out.println("Gender: " + rs.getString(4));
			System.out.println("Age: " + rs.getInt(5));
			System.out.println("State: " + rs.getString(6));
			System.out.println();
		}

		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public  void disconnectDB()
	{
		hsqlServer.shutdown();
	}
}
