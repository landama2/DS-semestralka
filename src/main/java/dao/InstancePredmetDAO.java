package dao;

import entities.InstancePredmet;
import entities.Predmet;

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

        if (predmet != null) {
            predicates.add(cb.equal(root.get("predmet"),predmet));
        }
        if (skolnirok != null && skolnirok != 0) {
            predicates.add(cb.equal(root.get("skolniRok"),skolnirok));
        }
        for (Predicate p : predicates) {
            cq.where(p);
        }
        return em.createQuery(cq).getResultList();
    }
}
