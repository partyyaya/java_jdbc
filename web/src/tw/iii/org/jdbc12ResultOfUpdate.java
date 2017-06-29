package tw.iii.org;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

public class jdbc12ResultOfUpdate {

	public static void main(String[] args) {
				Properties prop = new Properties();
				prop.setProperty("user", "root");
				prop.setProperty("password", "root");			
				try (Connection conn = 
					DriverManager.getConnection("jdbc:mysql://127.0.0.1/ming", 
						prop))
				{
					DatabaseMetaData metaData = conn.getMetaData();
					boolean isOk = metaData.supportsResultSetConcurrency(
							ResultSet.TYPE_FORWARD_ONLY,
							ResultSet.CONCUR_UPDATABLE);
					System.out.println(isOk);
					//ResultSet.TYPE_FORWARD_ONLY:The cursor can only move forward in the result set.
					//ResultSet.CONCUR_UPDATABLE:Creates an updateable result set.
					Statement stmt = conn.createStatement(ResultSet.TYPE_FORWARD_ONLY,
							ResultSet.CONCUR_UPDATABLE);					
					String sql = "SELECT * FROM member WHERE id=2";				
					ResultSet rs = stmt.executeQuery(sql);	
					rs.next();//把索引指到1
					System.out.println(rs.getString("account"));
					rs.updateString("account", "peter");
					rs.updateString("passwd", "peter");
					rs.updateRow();
					
					PreparedStatement pstmt = conn.prepareStatement("select * from member",ResultSet.TYPE_FORWARD_ONLY,
							ResultSet.CONCUR_UPDATABLE);
					ResultSet rs2 = pstmt.executeQuery();
					while(rs2.next()){
						rs2.updateString("passwd", "111111");
						rs2.updateRow();//確定執行
					}
					
					rs2.moveToInsertRow();//把指標移到可以插入的地方
					rs2.updateString("account", "Tony");
					rs2.updateString("passwd", "Tony");
					rs2.updateString("realname", "Tony");
					rs2.insertRow();//確定插入
					System.out.println("OK");
				}catch(Exception e){
					System.out.println(e);
				}
	}
}
