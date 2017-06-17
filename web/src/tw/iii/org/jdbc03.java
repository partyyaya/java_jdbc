package tw.iii.org;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class jdbc03 {

	public static void main(String[] args) {
		String id,cname,tel,birthday;
		//1.driver
				try {
					Class.forName("com.mysql.jdbc.Driver");
					System.out.println("ok");
				} catch (ClassNotFoundException e) {
					System.out.println(e);
					System.exit(0);
				}
				
				try {
					Properties prop = new Properties();
					prop.setProperty("user", "root");
					prop.setProperty("password", "root");
					//2.connection
					Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ming?"
		                                   ,prop);
					System.out.println("ok");
				//3.SQL statment
					Statement stmt = conn.createStatement();
					String sql = "SELECT * FROM cust";
					ResultSet rs=stmt.executeQuery(sql);
					while(rs.next()){
						id=rs.getString("id");
						cname=rs.getString("cname");
						tel=rs.getString("tel");
						birthday = rs.getString("birthday");
						System.out.println(id+":"+cname+":"+tel+":"+birthday);
					}
				} catch (SQLException e) {
					System.out.println(e);
				}

	}

}
