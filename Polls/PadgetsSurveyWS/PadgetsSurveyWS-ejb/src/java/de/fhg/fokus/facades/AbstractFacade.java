/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhg.fokus.facades;


import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author hgo
 */
public abstract class AbstractFacade<T> {

    private Class<T> entityClass;

    public AbstractFacade(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    protected abstract EntityManager getEntityManager();

    public void create(T entity) {
        getEntityManager().persist(entity);
    }

    public void edit(T entity) {
        getEntityManager().merge(entity);
    }

    public void remove(T entity) {
        getEntityManager().remove(getEntityManager().merge(entity));
    }

    public T find(Object id) {
        return getEntityManager().find(entityClass, id);
    }

    public List<T> findAll() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return getEntityManager().createQuery(cq).getResultList();
    }

    /**
     * Execute a given NamedQuery with one Parameter.
     * An example call: Usertable ut = usertableFacade.executeNamedQuery("Usertable.findByIdItem","userId","Igel").get(0);
     * This would search for the entity with the name 'Igel'.
     * @param NamedQuery The Queries are defined in the given EntityBean e.g. Usertable.findByIdItem in Usertable
     * @param parameterName The Name of the perameter in the namedQuery e.g. idItem
     * @param parameter The value of the parameter. In this example an id: e.g. 1
     * @return ResultList
     */
    public List<T> executeNamedQuery(String namedQuery, String parameterName, Object parameter) {
        Query q = getEntityManager().createNamedQuery(namedQuery, entityClass);
        q.setParameter(parameterName, parameter);
        return q.getResultList();
    }

    /**
     * This method is for namedQueries without parameters.
     * @param NamedQuery The Queries are defined in the given EntityBean
     * @return ResultList
     */
    public List<T> executeNamedQuery(String namedQuery) {
        Query q = getEntityManager().createNamedQuery(namedQuery, entityClass);
        return q.getResultList();
    }

    /**
     * This method execute the given sql-query and returned the result.
     * @param Query
     * @return ResultList
     */
    public List<T> executeNativeQueryForSelect(String query) {
        Query q = getEntityManager().createNativeQuery(query, entityClass);
        return q.getResultList();
    }

    /**
     * NativeQuery for update or delete.
     * @param Query
     * @return Number of entities updated or deleted.
     */
    public int executeNativeQueryForUpdate(String query) {
        Query q = getEntityManager().createNativeQuery(query);
        return q.executeUpdate();
    }

    /**
     * NativeQuery for update or delete.
     * @param Query
     * @return Number of entities updated or deleted.
     */
    public int executeNativeQueryForDelete(String query) {
        return executeNativeQueryForUpdate(query);
    }

    public List<T> findRange(int[] range) {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(range[1] - range[0]);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    public int count() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }

}
