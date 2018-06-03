package model.test;

import java.util.List;

import org.hibernate.Session;

import model.DeptBean;
import model.hibernate.HibernateUtil;

public class TestNativeSQL {

	public static void main(String[] args) {
		try {
			HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();

//			List depts1 = session.createNativeQuery("select * from dept").list();
//			System.out.println("depts1="+depts1);
			
//			List<DeptBean> depts2 = session.createNativeQuery("select * from dept", DeptBean.class).list();
//			System.out.println("depts2="+depts2);
			
						
			List depts3 = session.createNativeQuery("select * from dept")
					.addEntity(DeptBean.class)
					.list();
			System.out.println("depts3="+depts3);

			HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().commit();
			HibernateUtil.getSessionFactory().getCurrentSession().close();
		} finally {
			HibernateUtil.closeSessionFactory();
		}

	}

}
