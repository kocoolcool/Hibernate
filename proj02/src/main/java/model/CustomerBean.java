package model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.Session;

import model.hibernate.HibernateUtil;
@Entity
@Table(name="Customer")
public class CustomerBean {
	@Id
     private String custid;
     private byte[] password;
     private String email;
     private java.util.Date birth;
     public static void main(String[] args) {
		try {
			HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			CustomerBean bean = session.get(CustomerBean.class, "Alex");
			HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().commit();
			System.out.println("bean" + bean);
		} finally {
			HibernateUtil.closeSessionFactory();
		}
		
	}
	
	public String toString() {
		return "CustomerBean [custid=" + custid + ", email=" + email + ", birth=" + birth + "]";
	}
	public String getCustid() {
		return custid;
	}
	public void setCustid(String custid) {
		this.custid = custid;
	}
	public byte[] getPassword() {
		return password;
	}
	public void setPassword(byte[] password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public java.util.Date getBirth() {
		return birth;
	}
	public void setBirth(java.util.Date birth) {
		this.birth = birth;
	}
}

