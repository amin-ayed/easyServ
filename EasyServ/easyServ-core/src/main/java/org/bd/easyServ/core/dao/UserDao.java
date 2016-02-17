/*
 *
 */
package org.bd.easyServ.core.dao;

import org.bd.easyServ.core.model.User;



/**
 * The Interface UserDao.
 */
public interface UserDao extends AbstractJpaDao<User>
{

	

	/**
	 * Creates the user.
	 *
	 * @param user
	 *           the user
	 */
	void createUser(User user);

	/**
	 * Update user.
	 *
	 * @param user
	 *           the user
	 */
	void updateUser(User user);

	/**
	 * Gets the user by login.
	 *
	 * @param username
	 *           the username
	 * @return the user by login
	 */
	User getUserByLogin(String username);

	/**
	 * Find by mail.
	 *
	 * @param email
	 *           the email
	 * @return the user
	 */
	User findByMail(String email);


	

}
