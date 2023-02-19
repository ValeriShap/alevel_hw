package com.shapran.repository.hibernate;

import com.shapran.model.Car;
import com.shapran.util.HibernateFactoryUtil;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class CarHibernateRepository implements CrudHibernate<Car> {

    @Override
    public Optional<Car> getById(String id) {
        EntityManager entityManager = HibernateFactoryUtil.getEntityManager();
        return Optional.ofNullable(entityManager.find(Car.class, id));
    }

    @Override
    public List<Car> getAll() {
        EntityManager entityManager = HibernateFactoryUtil.getEntityManager();
        return entityManager.createQuery("from Car", Car.class)
                .getResultList();
    }

    @Override
    public void delete(String id) {
        EntityManager entityManager = HibernateFactoryUtil.getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.createQuery("delete from Car where id = :id")
                .setParameter("id", id)
                .executeUpdate();
        entityManager.getTransaction().commit();
    }
}
