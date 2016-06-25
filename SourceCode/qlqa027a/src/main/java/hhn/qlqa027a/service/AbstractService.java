/*
 * Customers Java Swing Application Demo
 *
 * Copyright(c) 2013, devsniper.com
 */
package hhn.qlqa027a.service;

import hhn.qlqa027a.entities.BaseEntity;
import hhn.qlqa027a.util.JpaUtil;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * Abstract Service for all services.
 *
 * @param <T> entity
 *
 * @author Cem Ikta
 */
public abstract class AbstractService<T extends BaseEntity> {
    
    private EntityManager entityManager;
    private final Class<T> entityClass;

    /**
     * Create default service
     *
     * @param entityClass entity class
     */
    public AbstractService(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    /**
     * Gets entity manager from JpaUtil.
     *
     * @return entity manager
     */
    public EntityManager getEntityManager() {
        if (entityManager == null) {
            entityManager = JpaUtil.getEntityManager();
        }

        return entityManager;
    }

    /**
     * Gets entity class.
     *
     * @return entity class
     */
    public Class<T> getEntityClass() {
        return entityClass;
    }

    /**
     * Create entity.
     *
     * @param entity entity model.
     * @return created entity
     */
    public T create(T entity) {
        getEntityManager().getTransaction().begin();
        getEntityManager().persist(entity);
        getEntityManager().getTransaction().commit();

        return entity;
    }

    /**
     * Update entity.
     *
     * @param entity entity model
     * @return updated entity
     */
    public T update(T entity) {
        getEntityManager().getTransaction().begin();
        entity = getEntityManager().merge(entity);
        getEntityManager().getTransaction().commit();

        return entity;
    }

    /**
     * Remove entity.
     *
     * @param entity entity model
     */
    public void removeFromDatabase(T entity) {
        getEntityManager().getTransaction().begin();
        getEntityManager().remove(entity);
        getEntityManager().getTransaction().commit();
    }
    
    /**
     * Remove entity.
     *
     * @param entity entity model
     * @return 
     * @throws java.lang.Exception 
     */
    public boolean remove(T entity) throws Exception{
        return true;
    }

    /**
     * Find entity.
     *
     * @param id entity id
     * @return entity
     */
    public T find(int id) {
        return getEntityManager().find(entityClass, id);
    }

    /**
     * Gets entity list with named query.
     *
     * @param namedQueryName named query name from entity
     * @return result list
     */
    @SuppressWarnings("unchecked")
    public List<T> getListWithNamedQuery(String namedQueryName) {
        return getEntityManager().createNamedQuery(namedQueryName).getResultList();
    }

    /**
     * Gets entity list with named query.
     *
     * @param namedQueryName named query name from entity
     * @param resultLimit limit for result list
     * @return result list
     */
    @SuppressWarnings("unchecked")
    public List<T> getListWithNamedQuery(String namedQueryName, int resultLimit) {
        return getEntityManager().createNamedQuery(namedQueryName).
                setMaxResults(resultLimit).getResultList();
    }

    /**
     * Gets entity list with named query.
     *
     * @param namedQueryName named query name from entity
     * @param parameters parameters map for named query
     * @return result list
     */
    public List<T> getListWithNamedQuery(String namedQueryName,
            Map<String, Object> parameters) {

        return getListWithNamedQuery(namedQueryName, parameters, 0);
    }

    /**
     * Gets entity list with named query.
     *
     * @param namedQueryName named query name from entity
     * @param parameters parameters map for named query
     * @param resultLimit limit for result list
     * @return result list
     */
    @SuppressWarnings("unchecked")
    public List<T> getListWithNamedQuery(String namedQueryName,
            Map<String, Object> parameters, int resultLimit) {

        Set<Entry<String, Object>> params = parameters.entrySet();
        Query query = getEntityManager().createNamedQuery(namedQueryName);

        if (resultLimit > 0) {
            query.setMaxResults(resultLimit);
        }

        for (Entry<String, Object> entry : params) {
            query.setParameter(entry.getKey(), entry.getValue());
        }

        return query.getResultList();
    }

    /**
     * Gets entity list with named query.
     *
     * @param namedQueryName named query name from entity
     * @param parameters parameters map for named query
     * @param start start position
     * @param end end position
     * @return result list
     */
    @SuppressWarnings("unchecked")
    public List<T> getListWithNamedQuery(String namedQueryName,
            Map<String, Object> parameters, int start, int end) {

        Set<Entry<String, Object>> params = parameters.entrySet();
        Query query = getEntityManager().createNamedQuery(namedQueryName);

        for (Entry<String, Object> entry : params) {
            query.setParameter(entry.getKey(), entry.getValue());
        }

        query.setMaxResults(end - start);
        query.setFirstResult(start);

        return query.getResultList();
    }

    /**
     * Gets entity list with named query.
     *
     * @param namedQueryName named query name from entity
     * @param start start position
     * @param end end position
     * @return result list
     */
    @SuppressWarnings("unchecked")
    public List<T> getListWithNamedQuery(String namedQueryName, int start, int end) {
        Query query = getEntityManager().createNamedQuery(namedQueryName);

        query.setMaxResults(end - start);
        query.setFirstResult(start);

        return query.getResultList();
    }

    /**
     * Liefert Entity List mit eingegebene Native SQL.
     *
     * @param sql native sql
     * @return result list
     */
    @SuppressWarnings("unchecked")
    public List<T> getListByNativeQuery(String sql) {
        return getEntityManager().createNativeQuery(sql, entityClass).getResultList();
    }

    /**
     * Gets entity list with native sql query.
     *
     * @param sql native sql query
     * @param resultLimit limit for result list
     * @return result list
     */
    @SuppressWarnings("unchecked")
    public List<T> getListByNativeQuery(String sql, int resultLimit) {
        return getEntityManager().createNativeQuery(sql, entityClass)
                .setMaxResults(resultLimit).getResultList();
    }

    /**
     * Gets entity list with native sql query.
     *
     * @param sql native sql query
     * @param start start position
     * @param end end position
     * @return result list
     */
    @SuppressWarnings("unchecked")
    public List<T> getListByNativeQuery(String sql, int start, int end) {
        Query query = getEntityManager().createNativeQuery(sql, entityClass);
        query.setMaxResults(end - start);
        query.setFirstResult(start);

        return query.getResultList();
    }

}
