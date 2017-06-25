package tw.iii.org;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

public class jdbc15saveObject {

	public static void main(String[] args) {
			Properties prop = new Properties();
			prop.setProperty("user", "root");
			prop.setProperty("password", "root");			
			try (Connection conn = 
				DriverManager.getConnection("jdbc:mysql://127.0.0.1/ming", 
					prop);
				)
			{			
			jdbc15serialzable s1 = new jdbc15serialzable("0001",90,20,65);
			System.out.println(s1.getId()+":"+s1.total()+":"+s1.avg());
//			ObjectOutputStream oout = new ObjectOutputStream(new FileOutputStream("./dir1/s1.obj"));
//			oout.writeObject(s1);
//			oout.flush();
			
			//ObjectInputStream oin = new ObjectInputStream(new FileInputStream("./dir1/s1.obj"));
			//jdbc15serialzable s2 = (jdbc15serialzable)oin.readObject();
			//oin.close();
//			PreparedStatement pstmt = conn.prepareStatement("update member set obj=? where id=1");
//			pstmt.setBinaryStream(1, new FileInputStream("./dir1/s1.obj"));	
//			pstmt.executeUpdate();
			
//			oout.close();
			Bike b1=new Bike();
			PreparedStatement pstmt2 = conn.prepareStatement("update member set obj=? where id=2");
			pstmt2.setObject(1, s1);	
			pstmt2.executeUpdate();
			System.out.println("ok");
			}catch(Exception e){
				System.out.println(e);
			}
	}
}
