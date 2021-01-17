package com.zovlanik.crud.repository.io.hibernate;

import com.zovlanik.crud.model.Account;
import com.zovlanik.crud.repository.AccountRepository;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class HibernateIOAccountRepositoryImpl implements AccountRepository {

    @Override
    public Account getById(Long id) {
        Account account = null;

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        account = session.load(Account.class, id);
        session.getTransaction().commit();

        return account;
    }

    @Override
    public List<Account> getAll() {
        return getAllInternal();
    }

    @Override
    public void create(Account account) {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.save(account);
        session.getTransaction().commit();

    }

    @Override
    public void update(Account account) {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.update(account);
        session.getTransaction().commit();

    }

    @Override
    public void deleteById(Long id) {
        Account account = getById(id); //да, костыль
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.delete(account);
        session.getTransaction().commit();
    }

    private List<Account> getAllInternal() {
        List<Account> la = new ArrayList<>();

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        la = session.createSQLQuery("SELECT * FROM account order by id;").addEntity(Account.class).getResultList();
        session.getTransaction().commit();

        return la;
    }

}
