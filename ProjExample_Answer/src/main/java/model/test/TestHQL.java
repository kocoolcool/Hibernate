package model.test;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import model.DeptBean;
import model.EmpBean;
import model.hibernate.HibernateUtil;

public class TestHQL {
	public static void main(String[] args) {
		try {
			HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();

//			List depts1 = session.createQuery("from DeptBean").list();
//			List<DeptBean> depts2 = session.createQuery("from DeptBean", DeptBean.class).list();
//			DeptBean dept1 = (DeptBean) session.createQuery(
//					"from DeptBean where deptid=10").uniqueResult();
//			DeptBean dept2 = session.createQuery(
//					"from DeptBean where deptid=10", DeptBean.class).uniqueResult();
//
//			List<String> deptnames = session.createQuery("select deptname from DeptBean", String.class).list();
//			System.out.println("deptnames="+deptnames);
			
//1個SQL指令
//			List<Object[]> result1 = session.createQuery(
//					"select dept, emp from DeptBean dept, EmpBean emp where dept.deptid=emp.deptid").list();
//			for(Object[] element : result1) {
//				System.out.println(element[0]+"\t"+element[1]);
//			}
			System.out.println("----------------------------------------------");
			
//多個SQL	指令
//			List<List> result2 = session.createQuery(
//					"select new list (dept, emp) from DeptBean dept, EmpBean emp where dept.deptid=emp.deptid").list();
//			for(List element : result2) {
//				System.out.println(element.get(0)+"\t"+element.get(1));
//			}
			
//多個SQL	指令
//			List<Map> result3 = session.createQuery(
//					"select new map (dept as key1, emp as key2) from DeptBean dept, EmpBean emp where dept.deptid=emp.deptid").list();
//			for(Map element : result3) {
//				System.out.println(element.get("key1")+"\t"+element.get("key2"));
//			}
			

//			List<DeptBean> depts3 = session.createQuery(
//					"from DeptBean where deptid > :param1 and deptname like :param2", DeptBean.class)
//					.setParameter("param1", 10)
//					.setParameter("param2", "%s%")
//					.list();
//			System.out.println("depts3="+depts3);

//			List<Integer> data = new ArrayList<>();
//			data.add(20);
//			data.add(50);
//			List<DeptBean> depts4 = session.createQuery(
//					"from DeptBean where deptid in (:param)", DeptBean.class)
//					.setParameterList("param", data)
//					.list();
//			System.out.println("depts4="+depts4);

			List<EmpBean> emps = session.createNamedQuery("model.EmpBean.salary", EmpBean.class)
				.setParameter("salary", 1)
				.list();
			
			System.out.println("emps="+emps);
			
			HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().commit();
			HibernateUtil.getSessionFactory().getCurrentSession().close();
		} finally {
			HibernateUtil.closeSessionFactory();
		}
	}
}
