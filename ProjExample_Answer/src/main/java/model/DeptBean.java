package model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.Session;

import model.hibernate.HibernateUtil;

@Entity
@Table(name="DEPT")
public class DeptBean implements Serializable {
	@OneToMany(
			mappedBy="dept",
			cascade={CascadeType.REMOVE}
	)
	private Set<EmpBean> emps;
	public Set<EmpBean> getEmps() {
		return emps;
	}
	public void setEmps(Set<EmpBean> emps) {
		this.emps = emps;
	}
	
	@Id
	@Column(name="DEPTID")
	private int deptid;
	
	@Column(name="DEPTNAME")
	private String deptname;

	public static void main(String[] args) {
		try {
			HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();

//insert
//			DeptBean insert = new DeptBean();
//			insert.setDeptid(70);
//			insert.setDeptname("Delphi");
//			session.save(insert);

//select
			DeptBean select = session.get(DeptBean.class, 50);
			System.out.println("select="+select);
			
			Set<EmpBean> emps = select.getEmps();
			System.out.println("emps="+emps);
			
//update
//			select.setDeptname("hahahaha");
			
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
		return "DeptBean [deptid=" + deptid + ", deptname=" + deptname + "]";
	}
	public int getDeptid() {
		return deptid;
	}
	public void setDeptid(int deptid) {
		this.deptid = deptid;
	}
	public String getDeptname() {
		return deptname;
	}
	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}	
}
