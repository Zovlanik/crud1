package com.zovlanik.crud.repository.io.hibernate;

import com.zovlanik.crud.model.Developer;
import com.zovlanik.crud.repository.AccountRepository;
import com.zovlanik.crud.repository.DeveloperRepository;
import com.zovlanik.crud.repository.SkillRepository;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class HibernateIODeveloperRepositoryImpl implements DeveloperRepository {
    private final SkillRepository skillRepo = new HibernateIOSkillRepositoryImpl();
    private final AccountRepository accountRep = new HibernateIOAccountRepositoryImpl();

    @Override
    public Developer getById(Long id) {
        Developer developer = null;

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        developer = session.get(Developer.class, id);
        session.getTransaction().commit();


        return developer;
    }

    @Override
    public List<Developer> getAll() {
        return getAllInternal();
    }

    public List<Developer> getAllInternal() {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Query query = session.createQuery("FROM Developer ORDER BY id");
        List<Developer> ld = query.list();
        session.getTransaction().commit();

        return ld;
    }

    @Override
    public void create(Developer developer) {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.save(developer);
        session.getTransaction().commit();

    }

    @Override
    public void update(Developer developer) {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.update(developer);
        session.getTransaction().commit();

    }

    @Override
    public void deleteById(Long id) {
        Developer developer = getById(id); //да, костыль
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.delete(developer);
        session.getTransaction().commit();
    }

}
