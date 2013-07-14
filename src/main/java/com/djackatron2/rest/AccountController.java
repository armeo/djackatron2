package com.djackatron2.rest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.djackatron2.dao.AccountDao;
import com.djackatron2.model.Account;

@Controller
@RequestMapping("/account")
public class AccountController {

	private AccountDao accountDao;
	
	public void setAccountDao(AccountDao dao) {
		this.accountDao = dao;
	}
	
	@RequestMapping
	@ResponseBody
	public Account getAccountByNumber(String accountNumber){
		return accountDao.findAccountByNumber(accountNumber);
	}

	public Account saveAccount(Account account) {
		return accountDao.save(account);
	}

	public Account updateAccount(Account account) {
		return accountDao.update(account);
	}

	public Account deleteAccount(String accountNumber) {
		return accountDao.delete(accountNumber);
	}
}
