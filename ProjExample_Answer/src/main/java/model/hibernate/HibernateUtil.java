package model.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtil {
	private static final SessionFactory sessionFactory = initializeSessionFactory();
	private static SessionFactory initializeSessionFactory() {
		try {
			final StandardServiceRegistry serviceRegistry =
					new StandardServiceRegistryBuilder().configure().build();
			return new MetadataSources(serviceRegistry).buildMetadata().buildSessionFactory();
			
//			Configuration configuration = new Configuration().configure();
//			ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
//					.applySettings(configuration.getProperties()).build();
//			return configuration.buildSessionFactory(serviceRegistry);
		} catch(Throwable ex) {
			ex.printStackTrace();
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
	}
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	public static void closeSessionFactory() {
		if(sessionFactory!=null) {
			sessionFactory.close();
		}
	}
}
