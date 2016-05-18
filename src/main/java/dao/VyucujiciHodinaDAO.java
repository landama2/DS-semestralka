package dao;

import entities.InstancePredmet;
import entities.Vyucujici;
import entities.VyucujiciHodina;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by urban on 5/8/2016.
 */
public class VyucujiciHodinaDAO extends GenericDAO<VyucujiciHodina> {

    public VyucujiciHodinaDAO(Class entityClass) {
        super(entityClass);
    }

    public List<VyucujiciHodina> findBy(Vyucujici vyucujici, InstancePredmet instancePredmet) {
        cq = cb.createQuery(VyucujiciHodina.class);

        if (vyucujici == null && instancePredmet == null) {
            return null;
        }
        List<Predicate> predicates = new ArrayList<>();
        if (vyucujici != null) {
            predicates.add(cb.equal(root.get("vyucujici"),vyucujici));
        }
        if (instancePredmet != null) {
            predicates.add(cb.equal(root.get("instancePredmet"),instancePredmet));
        }
        cq.select(root).where(predicates.toArray(new Predicate[]{}));
        return em.createQuery(cq).getResultList();
    }
}
