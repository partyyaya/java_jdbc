package tw.iii.org;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class jdbc09 {
	
	
	public static void main(String[] args) {
		
		Properties prop = new Properties();
		prop.setProperty("user", "root");
		prop.setProperty("password", "root");
		//String sql="select * from gift where id=216 ";
		//String sql="select count(*) from gift";
		//String sql="select count(*) from gift where acount=? and passed=? ";
		//String sql = "INSERT INTO member (account,passwd,realname) values('ming','123','hangming')";
		String account = "eric", passwd = "123456", realname = "Eric Ho";
		String sql = "INSERT INTO member (account,passwd,realname)" +
				" VALUES ('" + account + "','" + passwd + "','" + realname + "')";
		try(
		Connection conn=DriverManager.getConnection("jdbc:mysql://127.0.0.1/ming",prop);
		Statement stmt = conn.createStatement();
			)
		{	
			//executeUpdate()回傳int:代表影響幾筆資料			
			if (!isDataRepeat(account, stmt)){				
				stmt.executeUpdate(sql);				
			}else{
				System.out.println("帳號重複了");
			}			
			System.out.println("OK");
//			ResultSet rs = stmt.executeQuery(sql);
//			rs.next();
//			System.out.println(rs.getString(1));
		}catch(Exception e)
			{
				System.out.println(e);		
			}		
	}
	static boolean isDataRepeat(String account,Statement stmt) throws SQLException{
		String sql="SELECT count(*) from member where account='"+account+"'";
		ResultSet rs= stmt.executeQuery(sql);
		if(rs.next()){
			int num = rs.getInt(1);
			if(num>0)
				return true;
			else
				return false;
		}
		else
		return false;
	}
}
