package com.iiht.evaluation.eloan.dao;

import com.iiht.evaluation.eloan.exception.LoanException;
import com.iiht.evaluation.eloan.model.User;

public interface IUserDao {

	User validateUser(String loginid,String password)throws LoanException;
    User addUser(User user)throws LoanException;
  
}
