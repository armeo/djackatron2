package com.test.djackatron2.controller;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

import org.junit.Test;

import com.djackatron2.dao.AccountDao;
import com.djackatron2.model.Account;
import com.djackatron2.rest.AccountController;

public class AccountControllerTest {

	@Test
	public void testFindAccountByNumber(){
		String accountNumber = "1";
		Account account = new Account(accountNumber, "name1", 100d);
		
		AccountDao dao = mock(AccountDao.class);
		when(dao.findAccountByNumber(accountNumber)).thenReturn(account);
		
		AccountController controller = new AccountController();
		controller.setAccountDao(dao);
		
		Account accountReturn = controller.getAccountByNumber(accountNumber);
		
		assertThat(accountReturn.getName(), equalTo(account.getName()));
		
		verify(dao).findAccountByNumber(accountNumber);
	}
	
	@Test
	public void testSaveAccount(){
		String accountNumber = "1";
		Account account = new Account(accountNumber, "name1", 100d);
		
		AccountDao dao = mock(AccountDao.class);
		when(dao.save(account)).thenReturn(account);
		
		AccountController controller = new AccountController();
		controller.setAccountDao(dao);
		
		Account accountReturn = controller.saveAccount(account);
		
		assertThat(accountReturn.getName(), equalTo(account.getName()));
		
		verify(dao).save(account);
	}
	
	@Test
	public void testUpdateAccount(){
		String accountNumber = "1";
		Account account = new Account(accountNumber, "name1", 100d);
		
		AccountDao dao = mock(AccountDao.class);
		when(dao.update(account)).thenReturn(account);
		
		AccountController controller = new AccountController();
		controller.setAccountDao(dao);
		
		Account accountReturn = controller.updateAccount(account);
		
		assertThat(accountReturn.getName(), equalTo(account.getName()));
		
		verify(dao).update(account);
	}
	
	@Test
	public void testDeleteAccount(){
		String accountNumber = "1";
		Account account = new Account(accountNumber, "name1", 100d);
		
		AccountDao dao = mock(AccountDao.class);
		when(dao.delete(accountNumber)).thenReturn(account);
		
		AccountController controller = new AccountController();
		controller.setAccountDao(dao);
		
		Account accountReturn = controller.deleteAccount(accountNumber);
		
		assertThat(accountReturn.getName(), equalTo(account.getName()));
		
		verify(dao).delete(accountNumber);
	}
}
