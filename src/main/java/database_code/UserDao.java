package database_code;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import model.User;

public class UserDao {
	public boolean find(String username, String password) {

		HibernateSessionProviderUtility hspu = new HibernateSessionProviderUtility();
		Session s = hspu.getSession();
		Query q = s.createQuery("from User where username= :u and password= :p");
		q.setString("u", username);
		q.setString("p", password);
		List<User> list = q.list();
		return (list.size() > 0);
	}
}
