package com.shapran.util;

import org.flywaydb.core.Flyway;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class HibernateFactoryUtil {
    private static EntityManagerFactory entityManagerFactory;

    public static EntityManager getEntityManager() {
        migrationToBd();
        if (entityManagerFactory == null) {
            entityManagerFactory =
                    Persistence.createEntityManagerFactory("persistence");
        }
        return entityManagerFactory.createEntityManager();
    }

    public static void migrationToBd(){
        Flyway flyway = Flyway.configure()
                .dataSource("jdbc:postgresql://localhost:5432/hibernate", "postgres", "6494")
                .baselineOnMigrate(true)
                .locations("db/migration")
                .load();
        flyway.migrate();
    }
}

