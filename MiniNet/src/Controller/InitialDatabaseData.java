package Controller;

import java.sql.Connection;
import java.sql.SQLException;

public class InitialDatabaseData {

	public void initalDBData(Connection connection) {
		
		try {
			 // query from the db
			connection.prepareStatement(
							"insert into people " + "values ('Alex Smith', '', 'student at RMIT', 'M', 21, 'WA');")
					.execute();
			connection.prepareStatement(
					"insert into people " + "values ('Ben Turner', 'BenPhoto.jpg', 'manager at Coles', 'M', 35, 'VIC');")
			.execute();
			
			connection.prepareStatement(
					"insert into people " + "values ('Hannah White', 'Hannah.png', 'student at PLC', 'F', 14, 'VIC');")
			.execute();
			connection.prepareStatement(
					"insert into people " + "values ('Zoe Foster', '', 'Founder of ZFX', 'F', 28, 'VIC');")
			.execute();
			connection.prepareStatement(
					"insert into people " + "values ('Mark Turner', 'Mark.jpeg', '', 'M', 2, 'VIC');")
			.execute();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
}
