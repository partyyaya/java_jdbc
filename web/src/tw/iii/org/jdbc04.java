package tw.iii.org;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;

public class jdbc04 {

	public static void main(String[] args) {
		// Establish the connection. 
				try {
					SQLServerDataSource ds = new SQLServerDataSource();
					ds.setIntegratedSecurity(false);
					ds.setServerName("10.21.200.66");
					ds.setPortNumber(1433); 
					ds.setUser("sa");
					ds.setPassword("sa");
					ds.setDatabaseName("Northwind");				
						Connection con = ds.getConnection();
						System.out.println("ok");
					} catch (Exception e) {
						
						System.out.println(e);
					}

	}

}
