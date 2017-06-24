package tw.iii.org;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class jdbc10 {
	
	
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
		String[] result=null;
		Member mm;
		try(
		Connection conn=DriverManager.getConnection("jdbc:mysql://127.0.0.1/ming",prop);
		Statement stmt = conn.createStatement();
			)
		{	
//			if((result = checkMember(stmt, account, passwd))!=null){
//				System.out.println("welcome "+result[3]);
//			}
//			else{
//				System.out.println("error begin");
//			}
			if((mm = checkMember02(stmt, account, realname))!=null){
				System.out.println("welcome "+mm.realname);
			}
			else{
				System.out.println("error begin");
			}
		}catch(Exception e)
			{
				System.out.println(e);		
			}		
	}
	static String[] checkMember(Statement stmt,String account,String passwd) throws SQLException{
		String[] ret = new String[4];
		String sql="SELECT * from member where account='"+account+"' "
				+ "and passwd='"+passwd+"'";
		ResultSet rs= stmt.executeQuery(sql);
		if(rs.next()){
			//right member
			ret[0] = rs.getString(1);
			ret[1] = rs.getString(2);
			ret[2] = rs.getString(3);
			ret[3] = rs.getString(4);
		}
		else{
			//error account
			return ret=null;
		}
		return ret;
	}
	static Member checkMember02(Statement stmt,String account,String realname) throws SQLException{
		String sql="SELECT * from member where account='"+account+"' "
				+ "and realname='"+realname+"'";
		ResultSet rs= stmt.executeQuery(sql);
		Member mm;
		if(rs.next()){
			//right member
			mm=new Member(
			 rs.getString(2),
			 rs.getString(3),
			 rs.getString(4));
		}
		else{
			//error account
			return mm=null;
		}
		return mm;
	}
}

class Member{
	String id,account,realname;
	Member(String id,String account,String realname){
		this.id=id;
		this.account=account;
		this.realname=realname;
	}	
}
