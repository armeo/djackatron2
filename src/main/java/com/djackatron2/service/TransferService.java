package com.djackatron2.service;

import com.djackatron2.dao.AccountDao;
import com.djackatron2.model.Account;

public class TransferService {

	private AccountDao accountDao;
	private FeeService feeService;
	
	public void transfer(double transferAmount, String accountNumberFrom,
			String accountNumberTo) {
		Account accountFrom = accountDao.findAccountByNumber(accountNumberFrom);
		accountFrom.setBalance(accountFrom.getBalance() - transferAmount - feeService.calculateFee(transferAmount));
		Account accountTo = accountDao.findAccountByNumber(accountNumberTo);
		accountTo.setBalance(accountTo.getBalance() + transferAmount);
	}

	public void setAccountDao(AccountDao accountDao) {
		this.accountDao = accountDao;
	}

	public void setFee(FeeService feeService) {
		this.feeService = feeService;
	}

}
