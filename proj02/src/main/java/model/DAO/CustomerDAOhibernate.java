package model.DAO;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import model.CustomerBean;
import model.CustomerDAO;
import model.hibernate.HibernateUtil;

public class CustomerDAOhibernate implements CustomerDAO {
	private SessionFactory sessionfactory=null;
	
	
 public CustomerDAOhibernate(SessionFactory sessionfactory) {
		this.sessionfactory=sessionfactory;
	}

public Session getSession() {
	return sessionfactory.getCurrentSession();
}

public static void main(String[] args) {
	try {
		HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();
		
		CustomerDAO customerdao=new CustomerDAOhibernate(HibernateUtil.getSessionFactory());
//select
		CustomerBean bean=customerdao.select("Alex");
		System.out.println("bean"+bean);

//Update				
//		customerdao.update("F".getBytes(),"999@gmail.com", new CustomerBean().getBirth(), "Alex");
		
		HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().commit();
	} finally {
		HibernateUtil.closeSessionFactory();
	}
	
}

	@Override
	public CustomerBean select(String custid) {
		return (CustomerBean)this.getSession().get(CustomerBean.class, custid);
	}
	

	@Override
	public boolean update(byte[] password, String email, java.util.Date birth, String custid) {
			CustomerBean result=(CustomerBean)this.getSession().get(CustomerBean.class, custid);			
//			Date time=new Date();
//			java.util.Date temp=new java.sql.Date(time.getTime());			
//			result.setBirth(temp);			

		if(result!=null) {
			result.setPassword(password);
			result.setEmail(email);
			System.out.println("update sucess !");
		}
		return false;		
	}
}
