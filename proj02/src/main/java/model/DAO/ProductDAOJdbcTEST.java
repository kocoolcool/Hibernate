package model.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.ProductBean;
import model.ProductDAO;

public class ProductDAOJdbcTEST implements ProductDAO {
	public static void main(String[] args) {
		// Select一筆資料
		// ProductDAOJdbc useselect=new ProductDAOJdbc();
		// ProductBean bean=useselect.select(1);
		// System.out.println(bean);
		// Select多筆資料
		// ProductDAOJdbcTEST useselect=new ProductDAOJdbcTEST();
		// List<ProductBean> result=useselect.select();
		// System.out.println(result);
		// for (ProductBean bean : result) {
		// System.out.print(bean.getId() + ", ");
		// System.out.print(bean.getName() + ", ");
		// System.out.print(bean.getPrice() + ", ");
		// System.out.print(bean.getMake() + ", ");
		// System.out.print(bean.getExpire() + "\n");
		// }
		// insert資料
//		 ProductDAOJdbcTEST useselect=new ProductDAOJdbcTEST();
//		 ProductDAOJdbcTEST useinsert=new ProductDAOJdbcTEST();
//		 ProductBean bean=new ProductBean();
//		 bean.setId(16);
//		 bean.setName("Cookie");
//		 bean.setPrice(40);
//		 Date time=new Date();
//		 java.util.Date temp=new java.sql.Date(time.getTime());
//		 bean.setMake(temp);
//		 bean.setExpire(10);
//		 ProductBean result=useinsert.insert(bean);
//		 System.out.println(result);
		// update資料
		// ProductDAOJdbcTEST update=new ProductDAOJdbcTEST();
		// Date time = new Date();
		// ProductBean bean=update.update("Apple", 150,time, 1, 14);
		// System.out.println(bean);
//Delete
		ProductDAOJdbcTEST usedelete=new ProductDAOJdbcTEST();
		System.out.println(usedelete.delete(5));
	}

	private static final String URL = "jdbc:sqlserver://localhost:1433;database=java";
	private static final String USERNAME = "sa";
	private static final String PASSWORD = "sa123456";

	private static final String SELECT_BY_ID = "select * from product where id=?";

	@Override
	public ProductBean select(int id) {
		ProductBean result = null;
		ResultSet rs = null;
		try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				PreparedStatement pstm = conn.prepareStatement(SELECT_BY_ID);) {
			pstm.setInt(1, id);
			rs = pstm.executeQuery();

			if (rs.next()) {
				result = new ProductBean();
				result.setId(rs.getInt(1));
				result.setName(rs.getString(2));
				result.setPrice(rs.getFloat(3));
				result.setMake(rs.getDate(4));
				result.setExpire(rs.getInt(5));
			}
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			if (rs != null) {

				try {
					rs.close();
				} catch (SQLException e) {
				}
			}
		}

		return result;
	}

	private static final String SELECT_ALL = "select * from product";

	@Override
	public List<ProductBean> select() {
		ProductBean result = null;
		List<ProductBean> results = new ArrayList<ProductBean>();

		ResultSet rs = null;
		try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				PreparedStatement pstm = conn.prepareStatement(SELECT_ALL);) {
			rs = pstm.executeQuery();

			while (rs.next()) {
				result = new ProductBean();
				result.setId(rs.getInt(1));
				result.setName(rs.getString(2));
				result.setPrice(rs.getFloat(3));
				result.setMake(rs.getDate(4));
				result.setExpire(rs.getInt(5));
				results.add(result);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			if (rs != null) {

				try {
					rs.close();
				} catch (SQLException e) {
				}
			}
		}

		return results;
	}

	private static final String INSERT = "insert into product (id, name, price, make, expire) values (?, ?, ?, ?, ?)";

	@Override
	public ProductBean insert(ProductBean bean) {
		ProductBean result = null;

		if (bean != null) {
			try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
					PreparedStatement pstm = conn.prepareStatement(INSERT);) {
				result = new ProductBean();
				pstm.setInt(1, bean.getId());
				result.setId(bean.getId());
				pstm.setString(2, bean.getName());
				result.setName(bean.getName());
				Double temp = new Double(bean.getPrice());
				result.setPrice(bean.getPrice());
				pstm.setFloat(3, temp.floatValue());
				long time = bean.getMake().getTime();
				// result.setMake(bean.getMake().getTime());
				pstm.setDate(4, new java.sql.Date(time));
				pstm.setInt(5, bean.getExpire());
				int i = pstm.executeUpdate();
				if (i == 1) {
					System.out.println("Sucess");;
				}

			} catch (SQLException e) {

				e.printStackTrace();
			}
		}
		return result;
	}

	private static final String UPDATE = "update product set name=?, price=?, make=?, expire=? where id=?";

	@Override
	public ProductBean update(String name, double price, java.util.Date make, int expire, int id) {
		ProductBean result = null;
		try (	Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				PreparedStatement pstm = conn.prepareStatement(UPDATE);){
			result = new ProductBean();
		
			result.setName(name);
			result.setPrice(price);
			result.setMake(make);
			result.setExpire(expire);
			pstm.setString(1, name);
			pstm.setDouble(2, price);

			pstm.setDate(3, new java.sql.Date(make.getTime()));
			pstm.setInt(4, expire);
			pstm.setInt(5, id);
			int i = pstm.executeUpdate();
			if (i == 1)				
			 System.out.println("Update Sucess ");

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return result;
	}

	private static final String DELETE = "delete from product where id=?";

	@Override
	public boolean delete(int id) {
	try(Connection conn=DriverManager.getConnection(URL, USERNAME, PASSWORD);
			 PreparedStatement pstm=conn.prepareStatement(DELETE);) {
		
		 pstm.setInt(1, id);
		int i=pstm.executeUpdate();
		if(i==1) {
		return true;
		}
	} catch (SQLException e) {
		
		e.printStackTrace();
	}
	 	
	 return false;
	}
}
