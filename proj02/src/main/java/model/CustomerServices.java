package model;

import java.util.Arrays;

import model.DAO.CustomerDAOhibernate;
import model.hibernate.HibernateUtil;


public class CustomerServices {
	private CustomerDAO customerDao;	
	public CustomerServices() {
			
			this.customerDao = new CustomerDAOhibernate(HibernateUtil.getSessionFactory());
		}

	public static void main(String[] args) {
		try {
			HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();
			
			CustomerDAO customerdao=new CustomerDAOhibernate(HibernateUtil.getSessionFactory());
		CustomerBean customerbean=new CustomerBean();
		customerbean=new CustomerServices().login("Alex","e");
		System.out.println(customerbean);
		boolean ans=new CustomerServices().changePassword("Alex", "e", "eee");
		System.out.println(ans);
		
		HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().commit();
		} finally {
			HibernateUtil.closeSessionFactory();
		}
	}
	

public CustomerBean login(String username,String password) {
	CustomerBean bean=customerDao.select(username);
	if(bean !=null) {
		if(password !=null && password.length() !=0) {
			byte[] temp=password.getBytes();
			byte[]pass=bean.getPassword();
			if(Arrays.equals(temp, pass)) {
				return bean;
			}
		}
	}
	
	return null;
}
public boolean changePassword(String username,String oldPassword,String newPassword) {
	CustomerBean bean=this.login(username, oldPassword);
	if(bean !=null) {
		if(newPassword !=null && newPassword.length() !=0) {
			byte[] temp=newPassword.getBytes();
			return customerDao.update(temp, bean.getEmail(), bean.getBirth(), username);
		}
	}
	
	return false;
}
}
