package pku.edu.cn.conn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MetaData {
	   static String url = "jdbc:mysql://localhost:3306/mydb" ;    
	   static String username = "root" ;   
	   static String password = "" ; 
	   ResultSet rs;
	   private static Connection conn=null;
	   public MetaData(){
		   MetaData.conn = MetaData.getConnection();
	   }
	   public static Connection getConnection(){
		  
		   try {	   
			Class.forName("com.mysql.jdbc.Driver");
			conn= DriverManager.getConnection(url, username, password);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		   return conn;   
	   }
	   public ResultSet getMetadata(){
		   String sql = "select * from t_metadata_node";
		   try {
			Class.forName("com.mysql.jdbc.Driver");  
			Connection conn = DriverManager.getConnection(url, username, password);
			Statement stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
		   } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		   return rs;
	   }
//	   public String getCenterName(String ip){
//		   String sql = "select * from t_center_node where IpAddr ="'"+ip+"';
//	   }
	   public ResultSet getCenterMetadata(){
		   String sql = "select * from t_metadata_node where nodeKind ='2'";
		   try {
			Class.forName("com.mysql.jdbc.Driver");  
			Connection conn = DriverManager.getConnection(url, username, password);
			Statement stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
		   } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		   return rs;
		   
	   }
	   public List<String> getDataCenterIpList(){
		   List<String> list = new ArrayList<String>();
		   String sql ="select ipAddr from t_metadata_node where nodeKind ='2' ";
		   Statement stmt;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()){
				list.add(rs.getString(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		   return list;
 	   }
	   public static void main(String args[]){
		   MetaData meta = new MetaData();
//		   ResultSet rs = meta.getMetadata();
//		   try {
//			while(rs.next()){
//				System.out.print(rs.getString("ipAddr")+"     ");   
//				System.out.println(rs.getString("upIpAddr")); 
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		   meta.getDataCenterIpList();
	   }
}
