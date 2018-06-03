package model;

import java.io.File;
import java.io.FileInputStream;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.Session;

import model.hibernate.HibernateUtil;

@Entity
@Table(name = "DETAIL")
public class DetailBean {
	@OneToOne
	@JoinColumn(
			name="PHOTOID",
			referencedColumnName="ID",
			insertable=false,
			updatable=false
	)
	private ProductBean product;
	public ProductBean getProduct() {
		return product;
	}
	public void setProduct(ProductBean product) {
		this.product = product;
	}
	
	@Id
	private int photoid;
	private byte[] photo;
	public static void main(String[] args) throws Exception {
		File input1 = new File("C:/Maven/01.jpg");
		int length1 = (int) input1.length();
		byte[] photo1 = new byte[length1];
		FileInputStream fis1 = new FileInputStream(input1);
		fis1.read(photo1);
		fis1.close();

		File input2 = new File("C:/Maven/02.jpg");
		int length2 = (int) input2.length();
		byte[] photo2 = new byte[length2];
		FileInputStream fis2 = new FileInputStream(input2);
		fis2.read(photo2);
		fis2.close();

		try {
			HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();

// insert
//			DetailBean insert = new DetailBean();
//			insert.setPhotoid(10);
//			insert.setPhoto(photo1);
//			session.save(insert);

// select
			DetailBean select = session.get(DetailBean.class, 10);
			System.out.println("select="+select);
//
//			ProductBean product = select.getProduct();
//			System.out.println("product="+product);
			
// update
//			select.setPhoto(photo2);

// delete
			session.delete(select);

			HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().commit();
			HibernateUtil.getSessionFactory().getCurrentSession().close();
		} finally {
			HibernateUtil.closeSessionFactory();
		}
	}
	@Override
	public String toString() {
		return "DetailBean ["+ photoid+ "]";
	}
	public int getPhotoid() {
		return photoid;
	}
	public void setPhotoid(int photoid) {
		this.photoid = photoid;
	}
	public byte[] getPhoto() {
		return photo;
	}
	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}
}
