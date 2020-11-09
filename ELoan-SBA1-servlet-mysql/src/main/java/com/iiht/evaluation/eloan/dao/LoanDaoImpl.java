package com.iiht.evaluation.eloan.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.iiht.evaluation.eloan.exception.LoanException;
import com.iiht.evaluation.eloan.model.LoanInfo;
import com.iiht.evaluation.eloan.model.User;

public class LoanDaoImpl implements ILoanDao {
	User user =new User();
	public static final String INS_QRY = "INSERT INTO loaninfo(user_name,purpose,amtrequest,doa,bstructure"
			+ ",bindicator,tindicator,address,email,mobile,status) VALUES(?,?,?,?,?,?,?,?,?,?,?)";
	public static final String UPD_QRY = "UPDATE loaninfo SET purpose=?,amtrequest=?,bstructure=?,bindicator=?,tindicator=?"
			+ ",address=?,email=?,mobile=?) WHERE applno=?";
	public static final String SELECT_BY_ID_QRY = "SELECT applno,amtrequest,doa,email,mobile,status FROM loaninfo WHERE applno=?";
	public static final String SELECT_ALL_QRY = "SELECT applno,user_name,purpose,amtrequest,doa,bstructure,bindicator,tindicator,address"
			+ ",email,mobile,status FROM loaninfo";
	
	Logger log = Logger.getLogger("userDao");
	
	@Override
	public LoanInfo applyLoan(LoanInfo loanInfo) throws LoanException {
		try(Connection con = 	ConnectionDao.connect();
				PreparedStatement pst = con.prepareStatement(INS_QRY)){
				
				pst.setString(1, loanInfo.getUsername());
				pst.setString(2, loanInfo.getPurpose());
				pst.setInt(3, loanInfo.getAmtrequest());
				pst.setString(4, loanInfo.getDoa());
				pst.setString(5, loanInfo.getBstructure());
				pst.setString(6, loanInfo.getBindicator());
				pst.setString(7, loanInfo.getTindicator());
				pst.setString(8, loanInfo.getAddress());
				pst.setString(9, loanInfo.getEmail());
				pst.setString(10, loanInfo.getMobile());
				pst.setString(11, loanInfo.getStatus());
				
				pst.executeUpdate();
				try (ResultSet generatedKeys = pst.getGeneratedKeys()) {
		            if (generatedKeys.next()) {
		                loanInfo.setApplno(generatedKeys.getString(1));
		            }
				}
			} catch (SQLException e) {
				log.error(e);
				throw new LoanException("Unable to apply the loan");
			}
		return loanInfo;
	}

	@Override
	public LoanInfo editLoan(LoanInfo loanInfo) throws LoanException {
		try(Connection con = 	ConnectionDao.connect();
				PreparedStatement pst = con.prepareStatement(UPD_QRY)){
				
				pst.setString(1, loanInfo.getPurpose());
				pst.setInt(2, loanInfo.getAmtrequest());
				pst.setString(3, loanInfo.getBstructure());
				pst.setString(4, loanInfo.getBindicator());
				pst.setString(5, loanInfo.getTindicator());
				pst.setString(6, loanInfo.getAddress());
				pst.setString(7, loanInfo.getEmail());
				pst.setString(8, loanInfo.getMobile());
				
				pst.executeUpdate();
				
			} catch (SQLException e) {
				log.error(e);
				throw new LoanException("Unable to update Loan details");
			}
		return loanInfo;
	}
	
	@Override
	public LoanInfo trackLoan(int applno) throws LoanException {
		LoanInfo loanInfo=new LoanInfo();
		try(Connection con = 	ConnectionDao.connect();
				PreparedStatement pst = con.prepareStatement(SELECT_BY_ID_QRY)){
				
				pst.setInt(1, applno);

			ResultSet rs = pst.executeQuery();
			
			if(rs.next()) {
				loanInfo.setApplno(rs.getString(1));
				loanInfo.setAmtrequest(rs.getInt(4));
				loanInfo.setDoa(rs.getString(5));
				loanInfo.setEmail(rs.getString(10));
				loanInfo.setMobile(rs.getString(11));
				loanInfo.setStatus(rs.getString(12));
			} 
		}
		catch (SQLException e) {
				log.error(e);
				throw new LoanException("Unable to track the loan");
			}
	
		return loanInfo;
	}

	@Override
	public List<LoanInfo> getAll() throws LoanException {
		List<LoanInfo> loanInfos=new ArrayList<LoanInfo>();
		
		try (Connection con = 	ConnectionDao.connect(); 
				PreparedStatement ps = con.prepareStatement(SELECT_ALL_QRY)) {

			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				LoanInfo loanInfo = new LoanInfo();
				loanInfo.setApplno(rs.getString(1));
				loanInfo.setPurpose(rs.getString(3));
				loanInfo.setAmtrequest(rs.getInt(4));
				loanInfo.setDoa(rs.getString(5));
				loanInfo.setBstructure(rs.getString(6));
				loanInfo.setBindicator(rs.getString(7));
				loanInfo.setTindicator(rs.getString(8));
				loanInfo.setAddress(rs.getString(9));
				loanInfo.setEmail(rs.getString(10));
				loanInfo.setMobile(rs.getString(11));
				loanInfo.setStatus(rs.getString(12));
				
				loanInfos.add(loanInfo);
			}
		} catch (SQLException exp) {
			log.error(exp);
			throw new LoanException("Operation Failed Due To An Error!");
		}		
		return loanInfos;
	}
	

}
