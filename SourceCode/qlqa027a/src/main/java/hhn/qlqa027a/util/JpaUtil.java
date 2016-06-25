/*
 * Customers Java Swing Application Demo
 *
 * Copyright(c) 2013, devsniper.com
 */
package hhn.qlqa027a.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * JPA Util
 *
 * @author Cem Ikta
 */
public class JpaUtil {

    private static EntityManagerFactory emf = null;

    public JpaUtil() {
    }

    /**
     * Create entity manager factory.
     *
     * @return entity manager factory from persistence unit
     */
    public static EntityManagerFactory getEntityManagerFactory() {
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory("qlqa027PU");
        }
        return emf;
    }

    /**
     * Close the entity manager factory.
     */
    public static void closeEntityManagerFactory() {
        if (emf != null) {
            emf.close();
        }
    }

    /**
     * Gets new entity manager instance.
     *
     * @return new entity manager instance
     */
    public static EntityManager getEntityManager() {
        return getEntityManagerFactory().createEntityManager();
    }

}