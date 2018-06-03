package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.Session;

import model.hibernate.HibernateUtil;

@Entity
@Table(name="EMP")
@NamedQueries(
		value={
			@NamedQuery(
					name="model.EmpBean.salary",
					query="from EmpBean where salary > :salary"
			)
		}
)
public class EmpBean {
	@ManyToMany
	@JoinTable(
			name="PROJEMP",
			joinColumns=@JoinColumn(name="EMPID"),
			inverseJoinColumns=@JoinColumn(name="PROJID")
	)
	private Set<ProjBean> projs;
	public Set<ProjBean> getProjs() {
		return projs;
	}
	public void setProjs(Set<ProjBean> projs) {
		this.projs = projs;
	}

	@ManyToOne
	@JoinColumn(
			name="DEPTID",
			referencedColumnName="DEPTID",
			insertable=false,
			updatable=false
	)
	private DeptBean dept;
	public DeptBean getDept() {
		return dept;
	}
	public void setDept(DeptBean dept) {
		this.dept = dept;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int empid;
	private String empname;
	private int salary;
	private String sex;
	private byte[] photo;
	private int deptid;
	
	public static void main(String[] args) throws IOException {
		File input = new File("C:/Maven/01.jpg");
		int length = (int) input.length();
		byte[] photo = new byte[length];
		FileInputStream fis = new FileInputStream(input);
		fis.read(photo);
		fis.close();
		
		try {
			HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();	
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			
//insert			
//			EmpBean insert = new EmpBean();
//			insert.setEmpname("hahaha");
//			insert.setSalary(10000);
//			insert.setSex("M");
//			insert.setDeptid(30);
//			session.save(insert);

//select
			EmpBean select = session.get(EmpBean.class, 4);
			System.out.println("select="+select);

			Set<ProjBean> projs = select.getProjs();
			System.out.println("projs="+projs);

//			DeptBean dept = select.getDept();
//			System.out.println("dept="+dept);
			
			
//update
//			select.setEmpname("hehehe");
//			select.setSalary(1234);
//			select.setSex("F");
//			select.setPhoto(photo);
//			select.setDeptid(30);

//delete
//			session.delete(select);
			
			HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().commit();
		} finally {
			HibernateUtil.closeSessionFactory();
		}
	}
	@Override
	public String toString() {
		return "EmpBean ["+ empid+ ","+ empname+ ","+ salary+ ","+ sex+ ","+ deptid+ "]";
	}
	public int getEmpid() {
		return empid;
	}
	public void setEmpid(int empid) {
		this.empid = empid;
	}
	public String getEmpname() {
		return empname;
	}
	public void setEmpname(String empname) {
		this.empname = empname;
	}
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public byte[] getPhoto() {
		return photo;
	}
	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}
	public int getDeptid() {
		return deptid;
	}
	public void setDeptid(int deptid) {
		this.deptid = deptid;
	}
}
