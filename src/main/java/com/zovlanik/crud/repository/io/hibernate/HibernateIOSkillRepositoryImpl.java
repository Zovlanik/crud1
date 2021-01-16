package com.zovlanik.crud.repository.io.hibernate;

import com.zovlanik.crud.model.Skill;
import com.zovlanik.crud.repository.SkillRepository;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;

import javax.persistence.Query;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class HibernateIOSkillRepositoryImpl implements SkillRepository {

    @Override
    public Skill getById(Long id) {
        Skill skill = null;
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        skill = session.load(Skill.class, id);
        session.getTransaction().commit();
        return skill;
    }

    @Override

    //вернуть все элементы
    public List<Skill> getAll() {
        return getAllInternal();
    }

    //вернуть вообще все элементы
    List<Skill> getAllInternal() {
        List<Skill> ls;
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        ls = session.createSQLQuery("SELECT * FROM skills order by id").getResultList();

        session.getTransaction().commit();

        return ls;
    }

    @Override
    public void create(Skill skill) {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.save(skill);
        session.getTransaction().commit();

    }

    @Override
    public void update(Skill skill) {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.update(skill);
        session.getTransaction().commit();

    }

    public void deleteById(Long id) {
        Skill skill = getById(id); //да, костыль
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.delete(skill);
        session.getTransaction().commit();

    }
}
