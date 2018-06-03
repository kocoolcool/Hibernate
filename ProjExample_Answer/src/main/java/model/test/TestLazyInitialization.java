package model.test;

import org.hibernate.Hibernate;
import org.hibernate.Session;

import model.DeptBean;
import model.hibernate.HibernateUtil;

public class TestLazyInitialization {
	public static void main(String[] args) {
		try {
			HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();

			DeptBean dept20 = session.load(DeptBean.class, 20);
			if(dept20==null) {
				System.out.println("dept20 is null");
			} else {
				System.out.println("dept20 is not null");
			}
			
			DeptBean dept200 = session.load(DeptBean.class, 200);
			if(dept200==null) {
				System.out.println("dept200 is null");
			} else {
				System.out.println("dept200 is not null");
			}
			
			System.out.println("after load");
			
			Hibernate.initialize(dept20);
			
			HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().commit();
			HibernateUtil.getSessionFactory().getCurrentSession().close();
			
			System.out.println("after transaction commit/ session close");
			System.out.println("dept20="+dept20);
			
		} finally {
			HibernateUtil.closeSessionFactory();
		}
	}
}
