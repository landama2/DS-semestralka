package dao;

import entities.InstancePredmet;
import entities.Predmet;

import javax.persistence.Query;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by urban on 5/8/2016.
 */
public class InstancePredmetDAO extends GenericDAO<InstancePredmet>{

    public InstancePredmetDAO(Class entityClass) {
        super(entityClass);
    }

    public List<InstancePredmet> findBy(Predmet predmet, Integer skolnirok) {
        cq = cb.createQuery(InstancePredmet.class);

        if (predmet == null && (skolnirok == null || skolnirok == 0)) {
            return null;
        }

        ArrayList<Predicate> predicates = new ArrayList<>();
        if (skolnirok != null && skolnirok != 0) {
            predicates.add(cb.equal(root.get("skolniRok"),skolnirok));
        }
        if (predmet != null) {
            predicates.add(cb.equal(root.get("predmet"),predmet));
        }
//        for (Predicate p : predicates) {
//            cq.where(p);
//        }

        cq.select(root).where(predicates.toArray(new Predicate[]{}));
        return em.createQuery(cq).getResultList();
    }

    public List<InstancePredmet> findByCurrentYearAndSemestr(Integer skolniRok, Character semestr){
        String sqlQuery = "SELECT predmet.* FROM predmet INNER JOIN instance_predmet ON predmet.idpredmet=instance_predmet.predmet_idpredmet WHERE skolnirok = ?rok AND predmet.semestr = ?semestr;";
        Query q = em.createNativeQuery(sqlQuery);
        q.setParameter( "?rok", skolniRok);
        q.setParameter( "?semestr", semestr);
        return q.getResultList();
    }
}
