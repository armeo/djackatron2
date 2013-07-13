package com.test.djackatron2.service;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.djackatron2.dao.AccountDao;
import com.djackatron2.model.Account;
import com.djackatron2.service.TransferService;
import com.djackatron2.service.impl.VariableFeeService;

@RunWith(value = Parameterized.class)
public class TransferServiceTest {
	
	private double fixedRate;
	private double transferAmount;
	private double fromAccountBalance;
	private double toAccountBalance;
	private Account accountFrom;
	private Account accountTo;
	
	public TransferServiceTest(Account accountFrom, Account accountTo, double fixedRate, double transferAmount, double fromAccountBalance, double toAccountBalance){
		this.accountFrom = accountFrom;
		this.accountTo = accountTo;
		this.fixedRate = fixedRate;
		this.transferAmount = transferAmount;
		this.fromAccountBalance = fromAccountBalance;
		this.toAccountBalance = toAccountBalance;
	}

	@Parameters
	public static Collection<Object[]> data() {
		Object[][] data = new Object[][] {
					{new Account("1", "name1", 100d), new Account("2", "name2", 0d), 5d, 30d, 65d, 30d},
					{new Account("1", "name1", 100d), new Account("2", "name2", 0d), 7d, 40d, 53d, 40d}
				};
		return Arrays.asList(data);
	}
	
	@Test
	public void testTranasfer() {
		AccountDao accountDao = mock(AccountDao.class);
		when(accountDao.findAccountByNumber(accountFrom.getNumber())).thenReturn(accountFrom);
		when(accountDao.findAccountByNumber(accountTo.getNumber())).thenReturn(accountTo);
		
		VariableFeeService feeService = mock(VariableFeeService.class);
		when(feeService.calculateFee(anyDouble())).thenReturn(fixedRate);
		
		TransferService transferService = new TransferService();
		transferService.setAccountDao(accountDao);
		transferService.setFee(feeService);
		
		transferService.transfer(transferAmount, accountFrom.getNumber(), accountTo.getNumber());
		
		assertThat(accountTo.getBalance(), equalTo(toAccountBalance));
		assertThat(accountFrom.getBalance(), equalTo(fromAccountBalance));
	}
}