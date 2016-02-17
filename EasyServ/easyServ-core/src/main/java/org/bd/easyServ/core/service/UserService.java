package org.bd.easyServ.core.service;

import org.bd.easyServ.core.model.User;

/**
 * The Interface UserService.
 */
public interface UserService extends AbstractService<User>
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
	 * Find by login.
	 *
	 * @param userName
	 *           the user name
	 * @return the user
	 */
	User findByLogin(String userName);


	/**
	 * Find by mail.
	 *
	 * @param email
	 *           the email
	 * @return the user
	 */
	User findByMail(String email);






}
