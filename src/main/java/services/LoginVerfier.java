/**
 * 
 */
package services;

import database_code.HibernateSessionProviderUtility;
import database_code.UserDao;

/**
 * @author nikhilbhardwaj01
 *
 */
public class LoginVerfier {
	public boolean verifyUser(String username, String password) {
		// First let's initialize the database
		HibernateSessionProviderUtility hspu = new HibernateSessionProviderUtility();
		if (hspu.getStatus() == false) {
			hspu.initConfiguration();
		}

		UserDao ud = new UserDao();
		return ud.find(username, password);
	}
}
