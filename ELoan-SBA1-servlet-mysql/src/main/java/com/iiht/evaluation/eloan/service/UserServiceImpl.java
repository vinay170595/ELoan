package com.iiht.evaluation.eloan.service;

import com.iiht.evaluation.eloan.dao.IUserDao;
import com.iiht.evaluation.eloan.dao.UserDaoImpl;
import com.iiht.evaluation.eloan.exception.LoanException;
import com.iiht.evaluation.eloan.model.User;

public class UserServiceImpl implements IUserService {
	private IUserDao UserDao;

	public UserServiceImpl() {
		this.UserDao=new UserDaoImpl();
	}

	@Override
	public User createUser(User user) throws LoanException {
		if(user!=null) {
			UserDao.addUser(user);
		}
		return user;
	}


	@Override
	public User validateUser(String username, String password) throws LoanException {
		User user = UserDao.validateUser(username,password);
		if(user==null || !password.equals(user.getPassword())) {
			throw new LoanException("Invalid User Credits");
		}
		return user;
	}


}
