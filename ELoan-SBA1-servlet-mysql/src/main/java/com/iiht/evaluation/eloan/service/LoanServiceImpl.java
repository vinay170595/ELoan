package com.iiht.evaluation.eloan.service;

import java.util.List;

import com.iiht.evaluation.eloan.dao.ILoanDao;
import com.iiht.evaluation.eloan.dao.LoanDaoImpl;
import com.iiht.evaluation.eloan.exception.LoanException;
import com.iiht.evaluation.eloan.model.LoanInfo;


public class LoanServiceImpl implements ILoanService {

	private ILoanDao loanDao;
	public LoanServiceImpl() {
		this.loanDao =new LoanDaoImpl();
	
	}

	@Override
	public LoanInfo applyLoan(LoanInfo loanInfo) throws LoanException {
		LoanInfo loandata = loanDao.applyLoan(loanInfo);
		return loandata;
	}

	@Override
	public LoanInfo editLoan(LoanInfo loanInfo) throws LoanException {
		if (loanInfo!=null) {
			loanDao.editLoan(loanInfo);
		}
		return loanInfo;
	}

	@Override
	public LoanInfo trackLoan(int applno) throws LoanException {
		return loanDao.trackLoan(applno);
	}

	@Override
	public List<LoanInfo> getAll() throws LoanException {
		return loanDao.getAll();
	}

}
