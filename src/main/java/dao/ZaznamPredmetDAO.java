package dao;

import entities.InstancePredmet;
import entities.Student;
import entities.ZaznamPredmet;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by urban on 5/8/2016.
 */
public class ZaznamPredmetDAO extends GenericDAO<ZaznamPredmet> {

    public ZaznamPredmetDAO(Class entityClass) {
        super(entityClass);
    }

    public List<ZaznamPredmet> findBy(Student student, InstancePredmet instancePredmet) {
        cq = cb.createQuery(ZaznamPredmet.class);
        if (student == null && instancePredmet == null) {
            return null;
        }
        List<Predicate> predicates = new ArrayList<>();
        if (student != null) {
            predicates.add(cb.equal(root.get("student"),student));
        }
        if (instancePredmet != null) {
            predicates.add(cb.equal(root.get("instancePredmet"),instancePredmet));
        }
        cq.select(root).where(predicates.toArray(new Predicate[]{}));
        return em.createQuery(cq).getResultList();
    }
}
