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
		//1.driver,會自動註冊,可寫可不寫
//				try {
//					Class.forName("com.mysql.jdbc.Driver");
//					System.out.println("ok");
//				} catch (ClassNotFoundException e) {
//					System.out.println(e);
//					System.exit(0);
//				}			
				try {
					Properties prop = new Properties();
					prop.setProperty("user", "root");
					prop.setProperty("password", "root");
					//2.connection
					Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ming"
		                                   ,prop);
//					Connection conn = DriverManager.getConnection("jdbc:mysql://10.21.200.66/brad"
//								,prop);
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
				
				
				//-------------------------
				Properties prop2 = new Properties();
				prop2.setProperty("user", "root");
				prop2.setProperty("password", "root");
				String sql2 = "SELECT * FROM cust";
				//2.connection
				try (//裡面放入自助close的物件;格式try(裡面放入自助close的物件){執行邏輯語句}catch(){}
					//在try()裡面的東西必需要實現AutoCloseable的物件,當整個執行完會執行close()方法
					Connection conn2 = DriverManager.getConnection("jdbc:mysql://localhost:3306/ming"
					                       ,prop2);
					//3.SQL statment
					Statement stmt = conn2.createStatement();					
					ResultSet rs2=stmt.executeQuery(sql2);
					)
				{					
					while(rs2.next()){
						id=rs2.getString("id");
						cname=rs2.getString("cname");
						tel=rs2.getString("tel");
						birthday = rs2.getString("birthday");
						System.out.println(id+":"+cname+":"+tel+":"+birthday);
					}
				}
				catch (SQLException e){
					System.out.println(e);
				}		
	}	
}
