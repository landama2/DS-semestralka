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
        String sqlQuery = "SELECT instance_predmet.* FROM predmet INNER JOIN instance_predmet ON predmet.idpredmet=instance_predmet.predmet_idpredmet WHERE skolnirok = ? AND predmet.semestr = ?;";
        Query q = em.createNativeQuery(sqlQuery,InstancePredmet.class);
        q.setParameter(1, skolniRok);
        q.setParameter(2, semestr);
        return q.getResultList();
    }

    public List<Predmet> findPredmetyToCancel() {
        String sqlQuery = "SELECT instance_predmet, COUNT(*) NumOfPredmety FROM instance_predmet INNER JOIN zaznam_predmet ON instance_predmet.intance_predmet_id = zaznam_predmet.instancepredmet_intance_predmet_id HAVING COUNT (*) < 7);";
        Query q = em.createNativeQuery(sqlQuery,InstancePredmet.class);
        return q.getResultList();
    }
}
