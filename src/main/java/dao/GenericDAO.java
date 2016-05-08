package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

/**
 * Created by marek on 4.5.16.
 */
public class GenericDAO<T> {

    private Class<T> entityClass;

    public GenericDAO(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("EMF");
    EntityManager em = entityManagerFactory.createEntityManager();
    EntityTransaction tx = em.getTransaction();

    public void create(T t){
        tx.begin();
        em.persist(t);
//        em.flush();
        tx.commit();

    }

    public T update(T t){
        tx.begin();
        T tmp = em.merge(t);
//        em.flush();
        tx.commit();
        return tmp;
    }

    public void delete(T t){
        tx.begin();
        em.remove(t);
//        em.getReference(t.getClass(), t.getId())
//        em.flush();
        tx.commit();
    }

    public T find(Object id) {
        return em.find(entityClass, id);
    }

    public List<T> findAll() {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass)).orderBy();
        return em.createQuery(cq).getResultList();
    }
}
