package com.newbie.developerInfoHibernate.repository;

import com.newbie.developerInfoHibernate.model.Account;
import com.newbie.developerInfoHibernate.model.AccountStatus;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

public class HibernateAccountRepositoryImpl implements AccountRepository{

    private static SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
    private Transaction transaction = null;

    @Override
    public Account save(Account account) {
        Session session = sessionFactory.openSession();
        transaction = session.beginTransaction();
            Long accountId = (long)session.save(account);
            Account saveAccount = session.get(Account.class, accountId);
        transaction.commit();
        session.close();
        return saveAccount;
    }

    @Override
    public Account update(Account account) {
        Session session = sessionFactory.openSession();
        transaction = session.beginTransaction();
           session.update(account);
        transaction.commit();
        session.close();
        return account;
    }

    @Override
    public List<Account> getAll() {
        Session session = sessionFactory.openSession();
        transaction = session.beginTransaction();
            List accounts = session.createQuery("FROM Account").list();
        transaction.commit();
        session.close();
        return accounts;
    }

    @Override
    public Account getById(Long aLong) {
        Session session = sessionFactory.openSession();
        transaction = session.beginTransaction();
            Account account = session.get(Account.class, aLong);
        transaction.commit();
        session.close();
        return account;
    }

    @Override
    public Account deleteById(Long aLong) {
        Session session = sessionFactory.openSession();
        transaction = session.beginTransaction();
            Account account = session.get(Account.class, aLong);
        //  If, according to the logic of the program, you need to permanently delete entity
        //  session.delete(account);
            account.setLogin("");
            account.setPassword("");
            account.setAccountStatus(AccountStatus.DELETED);
           session.update(account);
        transaction.commit();
        session.close();
        return account;
    }

     public void close(){
        if (sessionFactory != null) sessionFactory.close();
    }
}
