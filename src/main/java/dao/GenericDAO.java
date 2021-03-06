package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Created by marek on 4.5.16.
 */
public class GenericDAO<T> {

    private Class<T> entityClass;

//    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("EMF");
    static EntityManager em = Persistence.createEntityManagerFactory("EMF").createEntityManager();
    EntityTransaction tx = em.getTransaction();

    CriteriaBuilder cb = em.getCriteriaBuilder();
    CriteriaQuery<T> cq;
    Root<T> root;

    public GenericDAO(Class<T> entityClass) {
        this.entityClass = entityClass;
        cq = cb.createQuery(entityClass);
        root = cq.from(entityClass);
    }

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

    public T find(Long id) {
        return em.find(entityClass, id);
    }

    public List<T> findAll() {
        CriteriaQuery cq = cb.createQuery();
        cq.select(cq.from(entityClass)).orderBy();
        return em.createQuery(cq).getResultList();
    }
}
