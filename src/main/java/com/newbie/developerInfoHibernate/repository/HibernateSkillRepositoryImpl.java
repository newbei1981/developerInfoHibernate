package com.newbie.developerInfoHibernate.repository;

import com.newbie.developerInfoHibernate.model.Skill;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class HibernateSkillRepositoryImpl implements SkillRepository{

    private static SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
    private Transaction transaction = null;

    @Override
    public List<Skill> getAll() {
        Session session = sessionFactory.openSession();
        transaction = session.beginTransaction();
        List skills = session.createQuery("FROM Skill").list();
        transaction.commit();
        session.close();
        return skills;
    }

    @Override
    public Skill getById(Long aLong) {
        Session session = sessionFactory.openSession();
        transaction = session.beginTransaction();
        Skill skill = session.get(Skill.class, aLong);
        transaction.commit();
        session.close();
        return skill;
    }

    @Override
    public Skill save(Skill skill) {
        Session session = sessionFactory.openSession();
        transaction = session.beginTransaction();
        Long skillId = (long)session.save(skill);
        Skill saveSkill = session.get(Skill.class, skillId);
        transaction.commit();
        session.close();
        return saveSkill;
    }

    @Override
    public Skill update(Skill skill) {
        Session session = sessionFactory.openSession();
        transaction = session.beginTransaction();
        session.update(skill);
        transaction.commit();
        session.close();
        return skill;
    }

    @Override
    public Skill deleteById(Long aLong) {
        Session session = sessionFactory.openSession();
        transaction = session.beginTransaction();
        Skill skill = session.get(Skill.class, aLong);
        session.delete(skill);
        transaction.commit();
        session.close();
        return skill;
    }

    public void close(){
        if (sessionFactory!=null) sessionFactory.close();
    }
}
