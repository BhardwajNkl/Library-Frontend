/**
 * 
 */
package database_code;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import model.User;

/**
 * @author nikhilbhardwaj01
 *
 */
public class HibernateSessionProviderUtility {
	private static SessionFactory factory;
	private static boolean status = false;

	public HibernateSessionProviderUtility() {
	}

	public static void initConfiguration() {
		Configuration cfg = new Configuration();
		cfg.configure();
		status = true;
		factory = cfg.buildSessionFactory();
		// here we initialize the users table with a few entries.
		User u1 = new User("admin", "admin");
		User u2 = new User("root", "root");
		User u3 = new User("nikhil", "bhardwaj");
		User u4 = new User("nagarro", "nagarro");
		Session s = factory.openSession();
		Transaction tx = s.beginTransaction();
		s.save(u1);
		s.save(u2);
		s.save(u3);
		s.save(u4);
		tx.commit();
		s.close();
	}

	public static boolean getStatus() {
		return status;
	}

	public Session getSession() {
		return factory.openSession();
	}

	public void closeSession(Session s) {
		s.close();
	}

}
