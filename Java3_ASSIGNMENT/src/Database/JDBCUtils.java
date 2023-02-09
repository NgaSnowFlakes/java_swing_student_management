package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCUtils {
	public Connection getConnection() {
		Connection c = null;
		
		try {
			String user = "sa";
			String pass = "songlong";
			String url = "jdbc:sqlserver://localhost:1433; databaseName=JAVA3_ASM;encrypt=true;trustServerCertificate=true";
			c=  DriverManager.getConnection(url, user, pass);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return c;
	}
}
