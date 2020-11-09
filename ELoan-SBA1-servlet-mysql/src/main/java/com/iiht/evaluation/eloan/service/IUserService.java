package com.iiht.evaluation.eloan.service;

import com.iiht.evaluation.eloan.exception.LoanException;
import com.iiht.evaluation.eloan.model.User;

public interface IUserService {
	User createUser(User user)throws LoanException;
	User validateUser(String username,String password) throws LoanException;
}
