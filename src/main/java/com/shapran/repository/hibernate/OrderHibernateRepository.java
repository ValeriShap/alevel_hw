package com.shapran.repository.hibernate;

import com.shapran.model.Order;
import com.shapran.util.HibernateFactoryUtil;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class OrderHibernateRepository implements CrudHibernate<Order> {

    @Override
    public Optional<Order> getById(String id) {
        EntityManager entityManager = HibernateFactoryUtil.getEntityManager();
        return Optional.ofNullable(entityManager.find(Order.class, id));
    }

    @Override
    public List<Order> getAll() {
        EntityManager entityManager = HibernateFactoryUtil.getEntityManager();
        return entityManager.createQuery("from Order", Order.class)
                .getResultList();
    }

    @Override
    public void delete(String id) {
        EntityManager entityManager = HibernateFactoryUtil.getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.createQuery("delete from Order where id = order_id")
                .setParameter("order_id", id)
                .executeUpdate();
        entityManager.getTransaction().commit();
    }
}
