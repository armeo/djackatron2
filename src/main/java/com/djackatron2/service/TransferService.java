package com.djackatron2.service;

import com.djackatron2.dao.AccountDao;
import com.djackatron2.model.Account;
import com.djackatron2.service.impl.VariableFeeService;

public class TransferService {

	private AccountDao accountDao;
	private FeeService feeService;
	private double minimumTransferAmount; 
	
	public void transfer(double transferAmount, String accountNumberFrom,
			String accountNumberTo) {
		if(transferAmount <= 0 || this.minimumTransferAmount > transferAmount)
			throw new IllegalArgumentException();
		
		Account accountFrom = accountDao.findAccountByNumber(accountNumberFrom);
		double totalDeduct = transferAmount + feeService.calculateFee(transferAmount);
		if(accountFrom.getBalance() < totalDeduct)
			throw new InsufficientFundException();
		
		accountFrom.setBalance(accountFrom.getBalance() - totalDeduct);
		Account accountTo = accountDao.findAccountByNumber(accountNumberTo);
		accountTo.setBalance(accountTo.getBalance() + transferAmount);
	}

	public void setAccountDao(AccountDao accountDao) {
		this.accountDao = accountDao;
	}

	public void setFee(FeeService feeService) {
		this.feeService = feeService;
	}

	public void setMinimumAmount(double minimumTransferAmount) {
		this.minimumTransferAmount = minimumTransferAmount;
	}

}
