package com.newbie.developerInfoHibernate.controller;


import com.newbie.developerInfoHibernate.model.Account;
import com.newbie.developerInfoHibernate.model.AccountStatus;
import com.newbie.developerInfoHibernate.repository.HibernateAccountRepositoryImpl;

import static com.newbie.developerInfoHibernate.view.DeveloperView.saveOrUpdateRecord;

public class AccountController {

    private HibernateAccountRepositoryImpl accountRepository = new HibernateAccountRepositoryImpl();

    public void deleteAccount(Account account){
        accountRepository.deleteById(account.getId());
    }

    public Account saveAccount(){
        Account account = new Account();
        account.setLogin(saveOrUpdateRecord("Login"));
        account.setPassword(saveOrUpdateRecord("Password"));
        account.setAccountStatus(AccountStatus.ACTIVE);
        return account;
    }

    public Account updateAccountLogin(Account account){
        account.setLogin(saveOrUpdateRecord("Login",account.getLogin()));
        return account;
    }

    public Account updateAccountPassword(Account account){
        account.setPassword(saveOrUpdateRecord("Account",account.getPassword()));
        return account;
    }

    public void closeSessionFactory(){
        accountRepository.close();
    }

}
