package model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.Session;

import model.hibernate.HibernateUtil;

@Entity
@Table(name="PROJ")
public class ProjBean {
	@ManyToMany
	@JoinTable(
			name="PROJEMP",
			joinColumns=@JoinColumn(name="PROJID"),
			inverseJoinColumns=@JoinColumn(name="EMPID")
	)
	private Set<EmpBean> emps;
	public Set<EmpBean> getEmps() {
		return emps;
	}
	public void setEmps(Set<EmpBean> emps) {
		this.emps = emps;
	}

	@Id
	private int projid;
	private String projname;
	public static void main(String[] args) {
		try {
			HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//insert			
//			ProjBean insert = new ProjBean();
//			insert.setProjid(400);
//			insert.setProjname("xxxxxx");
//			session.save(insert);

//select			
			ProjBean select = session.get(ProjBean.class, 100);
			System.out.println("select="+select);
			
			Set<EmpBean> emps = select.getEmps();
			System.out.println("emps="+emps);

//update
//			select.setProjname("hohoho");
		
//delete
			session.delete(select);

			HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().commit();
			HibernateUtil.getSessionFactory().getCurrentSession().close();
		} finally {
			HibernateUtil.closeSessionFactory();
		}
	}
	@Override
	public String toString() {
		return "ProjBean ["+ projid+ ","+ projname+ "]";
	}
	public int getProjid() {
		return projid;
	}
	public void setProjid(int projid) {
		this.projid = projid;
	}
	public String getProjname() {
		return projname;
	}
	public void setProjname(String projname) {
		this.projname = projname;
	}
}
