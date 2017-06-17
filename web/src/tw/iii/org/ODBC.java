package tw.iii.org;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ODBC {

	public static void main(String[] args) {
		try {
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			try {
				Connection conn = DriverManager.getConnection("jdbc:odbc:ming");
			} catch (SQLException e) {
				System.out.println(e);
			}
		} catch (ClassNotFoundException e) {
			System.out.println(e);
		}

	}

}
