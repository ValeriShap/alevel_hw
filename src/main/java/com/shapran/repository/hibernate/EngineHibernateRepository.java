package com.shapran.repository.hibernate;

import com.shapran.model.Engine;
import com.shapran.util.HibernateFactoryUtil;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class EngineHibernateRepository implements CrudHibernate<Engine> {
    @Override
    public Optional<Engine> getById(String id) {
        EntityManager entityManager = HibernateFactoryUtil.getEntityManager();
        return Optional.ofNullable(entityManager.find(Engine.class, id));
    }

    @Override
    public List<Engine> getAll() {
        EntityManager entityManager = HibernateFactoryUtil.getEntityManager();
        return entityManager.createQuery("from Engine", Engine.class)
                .getResultList();
    }

    @Override
    public void delete(String id) {
        EntityManager entityManager = HibernateFactoryUtil.getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.createQuery("delete from Engine where id = :engine_id")
                .setParameter("engine_id", id)
                .executeUpdate();
        entityManager.getTransaction().commit();
    }
}
