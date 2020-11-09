package com.iiht.evaluation.eloan.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.log4j.Logger;

import com.iiht.evaluation.eloan.exception.LoanException;
import com.iiht.evaluation.eloan.model.User;

public class UserDaoImpl implements IUserDao {
	
	private static String VALIDATE_USER_QRY=
			"SELECT * FROM users WHERE user_name=?";
	private static String CHECK_USER_QRY=
			"SELECT * FROM users WHERE user_name=?";
	private static String ADD_USER_QRY=
			"INSERT INTO users VALUES(?,?,?)";
	
	Logger log = Logger.getLogger("userDao");

	public User validateUser(String userName,String password) throws LoanException {
		User user=null;
		try(Connection con = 	ConnectionDao.connect();
				PreparedStatement pst = con.prepareStatement(VALIDATE_USER_QRY);
				PreparedStatement pst1 = con.prepareStatement(CHECK_USER_QRY);){
			pst.setString(1, userName);
			ResultSet rs = pst.executeQuery();
			
			if(rs.next()) {
				user = new User();
				user.setUsername(rs.getString(1));
				user.setPassword(rs.getString(2));
				user.setRole(rs.getString(3));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
			log.error(e);
			throw new LoanException("User details could not be fetched");
		}
		return user;
	}


	@Override
	public User addUser(User user) throws LoanException {		
		try(Connection con = 	ConnectionDao.connect();
			PreparedStatement pst = con.prepareStatement(ADD_USER_QRY)){
			
			pst.setString(1, user.getUsername());
			pst.setString(2, user.getPassword());
			pst.setString(3, user.getRole());
			
			
			pst.executeUpdate();
			
		} catch (SQLException e) {
			log.error(e);
			throw new LoanException("User details could not be saved");
		}
		return user;
	}

	}

