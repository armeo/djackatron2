package com.test.djackatron2.service;

import static org.junit.Assert.fail;
import static org.mockito.Matchers.anyDouble;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import com.djackatron2.dao.AccountDao;
import com.djackatron2.model.Account;
import com.djackatron2.service.FeeService;
import com.djackatron2.service.InsufficientFundException;
import com.djackatron2.service.TransferService;
import com.djackatron2.service.impl.VariableFeeService;

public class TransferServiceExceptionTest {
	
	Account accountFrom;
	Account accountTo;
	
	AccountDao accountDao;
	FeeService feeService;
	TransferService transferService;
	
	double fixedRate = 5d;
	double transferAmount = 0d;
	
	@Before
	public void setup(){
		accountFrom = new Account("1", "name1", 100d);
		accountTo = new Account("2", "name2", 100d);
		
		accountDao = mock(AccountDao.class);
		when(accountDao.findAccountByNumber(accountFrom.getNumber())).thenReturn(accountFrom);
		when(accountDao.findAccountByNumber(accountTo.getNumber())).thenReturn(accountTo);
		
		feeService = mock(FeeService.class);
		when(feeService.calculateFee(anyDouble())).thenReturn(fixedRate);
		
		
		transferService = new TransferService();
		transferService.setAccountDao(accountDao);
		transferService.setFee(feeService);
	}
	
	@Test(expected=InsufficientFundException.class)
	public void testShouldBeInsufficientFundIfTransferOverBalance() {
		transferAmount = 200d;
		transferService.transfer(transferAmount, accountFrom.getNumber(), accountTo.getNumber());
		fail();
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testShouldBeIllegalIfTransferLessThanZero() {
		transferAmount = -1d;
		transferService.transfer(transferAmount, accountFrom.getNumber(), accountTo.getNumber());
		fail();
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testShouldBeIllegalIfTransferIsZero() {
		transferService.transfer(transferAmount, accountFrom.getNumber(), accountTo.getNumber());
		fail();
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testShouldBeIllegalIfTransferLessThanMinimum() {
		transferAmount = 5d;
		double minimumTransferAmount = 10d;
		transferService.setMinimumAmount(minimumTransferAmount);
		
		transferService.transfer(transferAmount, accountFrom.getNumber(), accountTo.getNumber());
		fail();
	}
}