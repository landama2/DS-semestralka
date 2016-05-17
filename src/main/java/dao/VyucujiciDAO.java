package dao;

import entities.Student;
import entities.Vyucujici;

import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by urban on 5/8/2016.
 */
public class VyucujiciDAO extends GenericDAO<Vyucujici> {

    public VyucujiciDAO(Class entityClass) {
        super(entityClass);
    }

    public List<Vyucujici> findBy(String login, String jmeno, String prijmeni) {
        cq = cb.createQuery(Vyucujici.class);
//        ArrayList<Order> orders = new ArrayList<>();
//        orders.add(cb.desc(root.get("login")));
//        orders.add(cb.desc(root.get("jmeno")));
//        orders.add(cb.desc(root.get("prijmeni")));
        if (login==null && jmeno==null & prijmeni==null) {
            return null;
        }
        ArrayList<Predicate> predicates = new ArrayList<>();

        if (login != null) {
            predicates.add(cb.equal(root.get("login"),login));
        }

        if (jmeno != null) {
            predicates.add(cb.equal(root.get("jmeno"),jmeno));
        }

        if (prijmeni != null) {
            predicates.add(cb.equal(root.get("prijmeni"),prijmeni));
        }

        for (Predicate p : predicates) {
            cq.where(p);
        }
//        for (Order order : orders) {
//            cq.orderBy(order);
//        }
        return em.createQuery(cq).getResultList();
    }

    @Override
    public List<Vyucujici> findAll() {
        cq = cb.createQuery(Vyucujici.class);
        ArrayList<Order> orders = new ArrayList<>();
        orders.add(cb.asc(root.get("prijmeni")));
        orders.add(cb.asc(root.get("jmeno")));
        orders.add(cb.asc(root.get("login")));
        cq.select(cq.from(Vyucujici.class));
        for (Order order : orders){
            cq.orderBy(order);
        }
        return em.createQuery(cq).getResultList();
    }
}
