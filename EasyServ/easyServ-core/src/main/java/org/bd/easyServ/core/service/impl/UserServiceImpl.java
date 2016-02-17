package org.bd.easyServ.core.service.impl;

import org.bd.easyServ.core.dao.AbstractJpaDao;
import org.bd.easyServ.core.dao.UserDao;
import org.bd.easyServ.core.model.User;
import org.bd.easyServ.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;



/**
 * The Class UserServiceImpl.
 */
@org.springframework.stereotype.Service("userService")
@Transactional(readOnly = true)
public class UserServiceImpl extends AbstractServiceImpl<User> implements UserService
{

	/** The user dao. */
	@Autowired
	UserDao userDao;



	/**
	 * Instantiates a new user service impl.
	 */
	public UserServiceImpl()
	{
		super();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.service.UserService#createUser(org.guce.siat.common.model.User)
	 */
	@Override
	@Transactional(readOnly = false)
	public void createUser(final User user)
	{
		userDao.createUser(user);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.service.UserService#updateUser(org.guce.siat.common.model.User)
	 */
	@Override
	@Transactional(readOnly = false)
	public void updateUser(final User user)
	{
		userDao.updateUser(user);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.service.UserService#findByLogin(java.lang.String, java.lang.String)
	 */
	@Override
	public User findByLogin(final String userName)
	{
		final User user = userDao.getUserByLogin(userName);
			return user;
		
	}

	

	/*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.service.impl.AbstractServiceImpl#getJpaDao()
	 */
	@Override
	public AbstractJpaDao<User> getJpaDao()
	{
		return userDao;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.service.impl.AbstractServiceImpl#setJpaDao(org.guce.siat.common.dao.AbstractJpaDao)
	 */
	@Override
	public void setJpaDao(final AbstractJpaDao<User> jpaDao)
	{
		this.userDao = (UserDao) jpaDao;
	}

	
	/*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.service.UserService#findByMail(java.lang.String)
	 */
	@Override
	public User findByMail(final String email)
	{
		return userDao.findByMail(email);
	}

	

}
