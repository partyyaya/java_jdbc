package tw.iii.org;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.json.JSONStringer;
import org.json.JSONWriter;

public class jdbc08 {

	public static void main(String[] args) {
		Properties prop = new Properties();
		prop.setProperty("user", "root");
		prop.setProperty("password", "root");		
		try(Connection conn = 
			DriverManager.getConnection(
				"jdbc:mysql://localhost/ming",prop)){
			// 3. SQL statement
			String sql = "SELECT * FROM gift";
			JSONWriter jw = new JSONStringer().array();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			//executeQuery()用於查詢
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()){
				jw.object();
				String gid = rs.getString("gid");
				String Name = rs.getString("Name");
				String Feature = rs.getString("Feature");				
				jw.key("gid").value(gid);
				jw.key("Name").value(Name);
				jw.key("Feature").value(Feature);
				jw.endObject();
			}			
			jw.endArray();
			System.out.println(jw.toString());
		}catch(Exception e){
			System.out.println(e);
		}
	}
}
