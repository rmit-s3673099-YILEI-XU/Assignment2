package Controller;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * This class is for initialize the data in database
 * @author YILEI XU
 *
 */
public class InitialDatabaseData {

	/**
	 * This method initialize the data in database
	 * @param connection
	 */
	public void initialDBData(Connection connection) {
		
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
			e.printStackTrace();
		}
		
	}
	
	
}
