package tw.iii.org;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

public class jdbc13getPicture {

	public static void main(String[] args) {
			Properties prop = new Properties();
			prop.setProperty("user", "root");
			prop.setProperty("password", "root");			
			try (Connection conn = 
				DriverManager.getConnection("jdbc:mysql://127.0.0.1/ming", 
					prop);				
				)
			{						
			PreparedStatement pstmt = conn.prepareStatement("select * from member where id=1");
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			InputStream in = rs.getBinaryStream("img");
			FileOutputStream out = new FileOutputStream("./dir2/iii.png");
			byte[] buf = new byte[4096];int len;
			while((len=in.read(buf))!=-1){
				out.write(buf, 0, len);
			}
			out.flush();
			out.close();
			System.out.println("ok");
			}catch(Exception e){
				System.out.println(e);
			}
	}
}
