/*
 *
 */
package org.guce.siat.common.dao;

import java.util.List;

import org.guce.siat.common.model.Administration;
import org.guce.siat.common.model.Bureau;
import org.guce.siat.common.model.User;
import org.guce.siat.common.utils.enums.FileTypeCode;
import org.guce.siat.common.utils.enums.PositionType;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;



/**
 * The Interface UserDao.
 */
public interface UserDao extends AbstractJpaDao<User>
{

	/**
	 * Load user by username.
	 *
	 * @param username
	 *           the username
	 * @return the user details
	 * @throws UsernameNotFoundException
	 *            the username not found exception
	 */
	UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

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

	/**
	 * Update fail attempts.
	 *
	 * @param user
	 *           the user
	 * @return the user
	 */
	User updateFailAttempts(User user);

	

}
