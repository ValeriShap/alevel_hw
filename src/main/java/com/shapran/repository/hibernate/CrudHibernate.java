package com.shapran.repository.hibernate;

import com.shapran.repository.Crud;
import com.shapran.util.HibernateFactoryUtil;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public interface CrudHibernate<T> extends Crud<T> {

    default void save(T object){
        EntityManager entityManager = HibernateFactoryUtil.getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(object);
        entityManager.getTransaction().commit();
        entityManager.close();
    }
    Optional<T> getById(final String id);

    List<T> getAll();

    void delete(final String id);
}
