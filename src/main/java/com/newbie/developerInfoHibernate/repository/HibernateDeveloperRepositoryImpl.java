package com.newbie.developerInfoHibernate.repository;

import com.newbie.developerInfoHibernate.model.Account;
import com.newbie.developerInfoHibernate.model.Developer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

public class HibernateDeveloperRepositoryImpl implements DeveloperRepository{

    HibernateAccountRepositoryImpl accountRepository = new HibernateAccountRepositoryImpl();

    private static SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
    private Transaction transaction = null;

    public void close(){
        if (sessionFactory != null) sessionFactory.close();
    }

    @Override
    public List<Developer> getAll() {
        Session session = sessionFactory.openSession();
        transaction = session.beginTransaction();
        List developers = session.createQuery("FROM Developer").list();
        transaction.commit();
        session.close();
        return developers;
    }

    @Override
    public Developer getById(Long aLong) {
        Session session = sessionFactory.openSession();
        transaction = session.beginTransaction();
        Developer developer = session.get(Developer.class, aLong);
        transaction.commit();
        session.close();
        return developer;
    }

    @Override
    public Developer save(Developer developer) {
        Session session = sessionFactory.openSession();
        transaction = session.beginTransaction();
        Long developerId = (long)session.save(developer);
        Developer saveDeveloper = session.get(Developer.class, developerId);
        transaction.commit();
        session.close();
        return saveDeveloper;
    }

    @Override
    public Developer update(Developer developer) {
        Session session = sessionFactory.openSession();
        transaction = session.beginTransaction();
        session.update(developer);
        transaction.commit();
        session.close();
        return developer;
    }

    @Override
    public Developer deleteById(Long aLong) {
        Session session = sessionFactory.openSession();
        transaction = session.beginTransaction();
        Developer developer = session.get(Developer.class, aLong);
        session.delete(developer);
        transaction.commit();
        session.close();
        return developer;
    }

    public long authentication(String login, String password){
        Session session = sessionFactory.openSession();
        transaction = session.beginTransaction();
        String hqlAccount = " from Account WHERE login LIKE :login and password LIKE :password";
        String hqlDeveloper = "from Developer d WHERE d.account.id = :accId";
        Query query = null;
            query = session.createQuery(hqlAccount);
            query.setParameter("login",login);
            query.setParameter("password",password);
        List<Account> resultsList = query.list();

        if (resultsList.size() != 0) {
                query = session.createQuery(hqlDeveloper);
                query.setParameter("accId",resultsList.get(0).getId());
                List<Developer> developerResultList = query.list();
                if (developerResultList.size() != 0) {
                    session.close();
                    return developerResultList.get(0).getId();
            }
        }
        session.close();
       // transaction.commit();
        return -1;
    }
}
