package dao;

import entities.Student;

import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by marek on 4.5.16.
 */
public class StudentDAO extends GenericDAO<Student> {

    public StudentDAO(Class entityClass) {
        super(entityClass);
    }

    public List<Student> findBy(String login, String jmeno, String prijmeni, Integer rocnik) {
        cq = cb.createQuery(Student.class);
//        ArrayList<Order> orders = new ArrayList<>();
//        orders.add(cb.desc(root.get("login")));
//        orders.add(cb.desc(root.get("jmeno")));
//        orders.add(cb.desc(root.get("prijmeni")));
        if (login==null && jmeno==null & prijmeni==null && rocnik==0) {
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

        if (rocnik > 0) {
            predicates.add(cb.equal(root.get("rocnik"),rocnik));
        }

        cq.select(root).where(predicates.toArray(new Predicate[]{}));
//        for (Order order : orders) {
//            cq.orderBy(order);
//        }
        return em.createQuery(cq).getResultList();
    }

    @Override
    public List<Student> findAll() {
        cq = cb.createQuery(Student.class);
        ArrayList<Order> orders = new ArrayList<>();
        orders.add(cb.asc(root.get("prijmeni")));
        orders.add(cb.asc(root.get("jmeno")));
        orders.add(cb.asc(root.get("login")));
        cq.select(cq.from(Student.class));
        for (Order order : orders){
            cq.orderBy(order);
        }
        return em.createQuery(cq).getResultList();
    }


}
