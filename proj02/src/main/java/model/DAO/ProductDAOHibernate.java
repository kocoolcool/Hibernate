package model.DAO;


import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import model.ProductBean;
import model.ProductDAO;
import model.hibernate.HibernateUtil;

public class ProductDAOHibernate implements ProductDAO {
	private SessionFactory sessionfactory;

	public ProductDAOHibernate(SessionFactory sessionfactory) {
		this.sessionfactory = sessionfactory;
	}

	public Session getSession() {
		return sessionfactory.getCurrentSession();
	}

	public static void main(String[] args) {
		try {
			HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();
			
			ProductDAO productdao=new ProductDAOHibernate(HibernateUtil.getSessionFactory());
//select			
//			ProductBean bean=productdao.select(1);
//			System.out.println(bean);
//select all
//			List<ProductBean> bean=productdao.select();
//			System.out.println(bean);
//insert			
//			ProductBean insert=new ProductBean();
//			insert.setId(19);
//			insert.setPrice(100);
//			insert.setName("lalala");
//		ProductBean	bean2=productdao.insert(insert);
//			System.out.println(bean2);
//update
//			ProductBean bean3=productdao.update("mm", 50, null, 1, 19);
//			System.out.println(bean3);
//delete
//			boolean result=productdao.delete(19);
//				System.out.println(result);
			
			
			HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().commit();
		} finally {
			HibernateUtil.getSessionFactory();
		}
		
		
		
		
		
		
		
		
	}

	@Override
	public ProductBean select(int id) {
		return this.getSession().get(ProductBean.class, id);
	}

	@Override
	public List<ProductBean> select() {
		return	this.getSession().createQuery("from ProductBean", ProductBean.class).list();
	}
	@Override
	public ProductBean insert(ProductBean bean) {

		if (bean != null) {
			ProductBean temp = this.getSession().get(ProductBean.class, bean.getId());
			if (temp == null) {
				this.getSession().save(bean);
				return bean;
			}

		}
		return null;
	}



	@Override
	public ProductBean update(String name, double price, java.util.Date make, int expire, int id) {
			ProductBean	temp=this.getSession().get(ProductBean.class, id);
			if (temp != null) {
				temp.setExpire(expire);
				temp.setName(name);
				temp.setMake(make);
				temp.setPrice(price);
			return temp;
		}
		return null;
	}

	

	@Override
	public boolean delete(int id) {
		ProductBean temp=this.getSession().get(ProductBean.class, id);
		if(temp !=null) {
			this.getSession().delete(temp);
			return true;
		}
		
		return false;
	}
}
