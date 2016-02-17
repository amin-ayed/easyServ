/**
 *
 */
package org.bd.easyServ.core.dao.impl;

import java.util.ArrayList;
import java.util.Objects;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.TypedQuery;

import org.bd.easyServ.core.dao.UserDao;
import org.bd.easyServ.core.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


/**
 * The Class UserDaoImpl.
 */
@Repository("userDao")
@Transactional(propagation = Propagation.REQUIRED)
public class UserDaoImpl extends AbstractJpaDaoImpl<User> implements UserDao, UserDetailsService
{

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(UserDaoImpl.class);

	/** The encoder. */
	@Autowired
	private ShaPasswordEncoder encoder;


	/**
	 * Instantiates a new user dao impl.
	 */
	public UserDaoImpl()
	{
		super();
		setClasse(User.class);
	}

	

	/*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.dao.UserDao#createUser(org.guce.siat.common.model.User)
	 */
	@Transactional(readOnly = false)
	public void createUser(final User user)
	{
		final User user1 = user;
		if (LOG.isDebugEnabled())
		{
			LOG.debug("initial password : {}", user1.getPassword());
		}

		// encode password before sending to database.
		user1.setPassword(encoder.encodePassword(user.getPassword(), user.getLogin()));
		if (LOG.isDebugEnabled())
		{
			LOG.debug("encrypted password : {}", user1.getPassword());
		}

		save(user1);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.dao.UserDao#updateUser(org.guce.siat.common.model.User)
	 */
	@Transactional(readOnly = false)
	public void updateUser(final User user)
	{
		final User user1 = user;

		if (LOG.isDebugEnabled())
		{
			LOG.debug("initial password : {}", user1.getPassword());
		}

		// encode password before sending to database.
		user1.setPassword(encoder.encodePassword(user.getPassword(), user.getLogin()));

		if (LOG.isDebugEnabled())
		{
			LOG.debug("encrypted password : {}", user1.getPassword());
		}
		update(user1);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.dao.UserDao#getUserByLogin(java.lang.String)
	 */
	@Override
	public User getUserByLogin(final String username)
	{
		try
		{
			final String qlString = "SELECT u FROM User u WHERE u.login = :login AND u.deleted = false AND u.enabled = true";
			final TypedQuery<User> query = super.entityManager.createQuery(qlString, User.class);
			query.setParameter("login", username);

			return query.getSingleResult();
		}
		catch (final NoResultException | NonUniqueResultException e)
		{
			LOG.info(Objects.toString(e));
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.dao.UserDao#findByMail(java.lang.String)
	 */
	@Override
	public User findByMail(final String email)
	{
		try
		{
			final String qlString = "SELECT u FROM User u WHERE u.email = :email";
			final TypedQuery<User> query = super.entityManager.createQuery(qlString, User.class);
			query.setParameter("email", email);

			return query.getSingleResult();
		}
		catch (final NoResultException | NonUniqueResultException e)
		{
			LOG.info(Objects.toString(e));
			return null;
		}
	}



	public ShaPasswordEncoder getEncoder() {
		return encoder;
	}



	public void setEncoder(ShaPasswordEncoder encoder) {
		this.encoder = encoder;
	}



	@Transactional(readOnly = false)
	public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException
	{
		try
		{
			final User domainUser = getUserByLogin(username);
			final boolean enabled = true;
			final boolean accountNonExpired = true;
			final boolean credentialsNonExpired = true;

			final org.springframework.security.core.userdetails.User user = new org.springframework.security.core.userdetails.User(
					domainUser.getLogin(), domainUser.getPassword().toLowerCase(), enabled, accountNonExpired, credentialsNonExpired,
					Boolean.TRUE, new ArrayList<GrantedAuthority>());
			if (LOG.isDebugEnabled())
			{
				LOG.debug("Returned Password : {}", user.getPassword());
			}

			return user;

		}
		catch (final Exception exception)
		{
			LOG.error("Error : {}", exception.getMessage());
		}
		return null;
	}




	
}
