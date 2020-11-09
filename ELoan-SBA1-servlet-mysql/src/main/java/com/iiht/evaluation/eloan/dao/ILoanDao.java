package com.iiht.evaluation.eloan.dao;

import java.util.List;

import com.iiht.evaluation.eloan.exception.LoanException;
import com.iiht.evaluation.eloan.model.LoanInfo;

public interface ILoanDao {

	LoanInfo applyLoan(LoanInfo loanInfo)throws LoanException;
	LoanInfo editLoan(LoanInfo loanInfo) throws LoanException;
	LoanInfo trackLoan(int applno) throws LoanException;
	List<LoanInfo> getAll() throws LoanException;
}
