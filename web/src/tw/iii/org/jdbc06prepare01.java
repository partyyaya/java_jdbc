package tw.iii.org;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class jdbc06prepare01 {

	public static void main(String[] args) {
		String id,cname,tel,birthday;
		// TODO Auto-generated method stub
		//-------------------------
		Properties prop2 = new Properties();
		prop2.setProperty("user", "root");
		prop2.setProperty("password", "root");
		String sql = "INSERT INTO CUST(cname,tel,birthday) VALUES(?,?,?)";
		//2.connection
		try (//�̭���J�ۧUclose������;�榡try(�̭���J�ۧUclose������){�����޿�y�y}catch(){}
			//�btry()�̭����F�襲�ݭn��{AutoCloseable������,���Ӱ��槹�|����close()��k
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ming"
			                       ,prop2);
			//3.SQL statment
			PreparedStatement pstmt=conn.prepareStatement(sql);			
			)
		{					
			pstmt.setString(1, "peter");
			pstmt.setString(2, "123");
			pstmt.setString(3, "1995-04-05");
			pstmt.execute();//�W�ǤW�h
			
			pstmt.setString(1, "jerry");
			pstmt.setString(2, "1323");
			pstmt.setString(3, "1995-07-05");
			pstmt.execute();//�W�ǤW�h
			System.out.println("ok");
		}
		catch (SQLException e){
			System.out.println(e);
		}		
	}

}
