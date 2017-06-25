package tw.iii.org;

import java.awt.BorderLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Properties;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

public class MyDataTable01 extends JFrame {
	//private JTable table;
	//private String [][] data={{"11","12","13"}
//							,{"21","22","23"}
//							,{"31","32","33"}
//							,{"41","42","43"}};
	//private String[] fields={"f1","f2","f3"};
	private JTable table;
	private MyTableModel model;
	//-----
	private Connection conn;
	private ResultSet rs;
	private int dataCount;
	private String[] fields;
	
	
	public MyDataTable01(){
		super("客戶資料管理");
		setLayout(new BorderLayout());
		
		try{
			initDB();
		}catch(Exception ee){
			
		}
		
		model = new MyTableModel();
		table = new JTable(model);
		JScrollPane jsp = new JScrollPane(table);
		add(jsp, BorderLayout.CENTER);
		table.getSelectionModel().addListSelectionListener(
				(e) -> MyDataTable01.this.whenSelect(e)
				);
		setVisible(true);
		setSize(800, 600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	private void whenSelect(ListSelectionEvent e){
		System.out.println(e.getFirstIndex()+"x"+e.getLastIndex());
	}
	
	private class MyTableModel extends DefaultTableModel {
		@Override
		public String getColumnName(int column) {
			return fields[column];
		}
		@Override
		public int getColumnCount() {
			return fields.length;
		}
		
		@Override
		public int getRowCount() {
			return dataCount;
		}
		@Override
		public Object getValueAt(int row, int column) {
			try{
				rs.absolute(row+1);
				return rs.getString(column+1);
			}catch(Exception e){
				return "---";
			}
			
		}
		
		@Override
		public void setValueAt(Object aValue, int row, int column) {
			super.setValueAt(aValue, row, column);
		}
		
		@Override
		public boolean isCellEditable(int row, int column) {
			return false;
		}
		
	}
	
	
	private void initDB() throws Exception {
		Properties prop = new Properties();
		prop.setProperty("user", "root");
		prop.setProperty("password", "root");

		conn = DriverManager.getConnection(
					"jdbc:mysql://127.0.0.1/ming",prop);
		String sql = "SELECT count(*) FROM gift";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		rs = pstmt.executeQuery();
		rs.next(); dataCount = rs.getInt(1);
		
		sql = "SELECT * FROM gift ORDER BY ProduceOrg";
		pstmt = conn.prepareStatement(sql,
				ResultSet.TYPE_FORWARD_ONLY, 
				ResultSet.CONCUR_UPDATABLE);
		rs = pstmt.executeQuery();
		
		ResultSetMetaData metadata = rs.getMetaData();
		fields = new String[metadata.getColumnCount()];
		for (int i=0; i<fields.length; i++){
			fields[i] = metadata.getColumnLabel(i+1);
		}
	
	}
	public static void main(String[] args) {
		new MyDataTable01();
	}

}
