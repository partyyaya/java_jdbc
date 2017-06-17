package tw.iii.org;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class jdbc02 {

	public static void main(String[] args) {
		//1.driver
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("ok");
		} catch (ClassNotFoundException e) {
			System.out.println(e);
			System.exit(0);
		}
		//2.connection
		try {
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ming?"
                                   ,"root","root");
			System.out.println("ok");
		//3.SQL statment
			Statement stmt = conn.createStatement();
			String sql = "INSERT INTO cust(cname,tel,birthday) values('ming','123','1994-08-30');";
			boolean isright=stmt.execute(sql);
			
		} catch (SQLException e) {
			System.out.println(e);
		}

	}

}
