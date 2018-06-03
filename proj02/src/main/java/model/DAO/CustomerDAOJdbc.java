package model.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



import model.CustomerBean;
import model.CustomerDAO;

public class CustomerDAOJdbc implements CustomerDAO {
	private static final String url = "jdbc:sqlserver://localhost:1433;databaseName=java";
	private static final String Username = "sa";
	private static final String Password = "sa123456";
	private static final String Select_By_CustID = "select * from customer where custid=?";
	
 public static void main(String[] args) {
	CustomerBean custmerbean=new CustomerBean();
	custmerbean=new CustomerDAOJdbc().select("Alex");
	System.out.println(custmerbean);
 boolean update=new CustomerDAOJdbc().update("eee".getBytes(), "abc@glab.com", new java.util.Date(0), "Alex");
	System.out.println(update);
}
 
	@Override
	public CustomerBean select(String custid) {
		CustomerBean result = null;
		ResultSet rs=null;
		
		try(Connection conn = DriverManager.getConnection(url, Username, Password);
			PreparedStatement stmt=conn.prepareStatement(Select_By_CustID);) {	
			stmt.setString(1, custid);
			rs=stmt.executeQuery();
			if(rs.next()) {
				result=new CustomerBean();
				result.setCustid(rs.getString(1));
				result.setPassword(rs.getBytes(2));
				result.setEmail(rs.getString(3));
				result.setBirth(rs.getDate(4));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			if(rs!=null) {
				try {
					rs.close();
				}catch(SQLException e) {
					
				}
			}
		}
		return result;
	}

	private static final String Update = "update customer set password=?,email=?,birth=? where custid=?";

	@Override
	public boolean update(byte[] password, String email, java.util.Date birth, String custid) {
	try (Connection conn=DriverManager.getConnection(url, Username, Password);
			PreparedStatement stmt=conn.prepareStatement(Update);){
		
		stmt.setBytes(1, password);
		stmt.setString(2, email);
		if (birth !=null) {
			long time = birth.getTime();
			java.sql.Date temp = new java.sql.Date(time);
			stmt.setDate(3, temp);
		}else {
			stmt.setDate(3, null);
		}
		stmt.setString(4, custid);
	int i=stmt.executeUpdate();
	if(i==1){
		return true;
	}
	} catch (SQLException e) {
		
		e.printStackTrace();
	}
		
		
		
		return false;
	}

	

}
