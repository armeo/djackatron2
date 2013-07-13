package com.djackatron2.service;

import org.joda.time.LocalTime;

import com.djackatron2.dao.AccountDao;
import com.djackatron2.model.Account;

public class TransferService {

	private AccountDao accountDao;
	private FeeService feeService;
	private TimeService timeService;
	private double minimumTransferAmount; 
	
	public void transfer(double transferAmount, String accountNumberFrom,
			String accountNumberTo) {
		if(timeService != null && !timeService.isAvailable(new LocalTime()))
			throw new OutOfServiceException();
		
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
	
	public void setTimeService(TimeService timeService) {
		this.timeService = timeService;
	}

}
