package pku.edu.cn.conn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlConn {
	private static Connection con=null;
//	public static  Connection getConn(){
//	try {
//		Context ctx = new InitialContext();
//		DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/gcks");
//		con= ds.getConnection();
//	} catch (NamingException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	} catch (SQLException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}
//	return con;
//}
	
	public static Connection getConn(){
		try {
			Class.forName("");
			String url = "";
			con  = DriverManager.getConnection(url,"","");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}
}
