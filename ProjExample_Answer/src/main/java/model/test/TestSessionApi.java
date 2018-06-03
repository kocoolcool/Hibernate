package model.test;

import org.hibernate.Session;

import model.DeptBean;
import model.hibernate.HibernateUtil;

public class TestSessionApi {
	public static void main(String[] args) {
		try {
			HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();

			DeptBean dept10 = session.get(DeptBean.class, 10);
			System.out.println("dept10="+dept10);
			
			DeptBean dept20 = session.load(DeptBean.class, 20);
			System.out.println("dept20="+dept20);
			
			DeptBean dept100 = session.get(DeptBean.class, 100);
			System.out.println("dept100="+dept100);
			
			DeptBean dept200 = session.load(DeptBean.class, 200);
			System.out.println("dept200 finished");
			if(dept200==null) {
				System.out.println("dept200 is null");
			} else {
				System.out.println("dept200 is not null");
			}
			
//			System.out.println("dept200="+dept200);
			
			HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().commit();
			HibernateUtil.getSessionFactory().getCurrentSession().close();
		} finally {
			HibernateUtil.closeSessionFactory();
		}
	}
}
