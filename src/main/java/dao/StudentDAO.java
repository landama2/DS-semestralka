package dao;

import entities.Student;
import utils.Utils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by marek on 4.5.16.
 */
public class StudentDAO extends GenericDAO {

    public StudentDAO(Class entityClass) {
        super(entityClass);
    }

    public List<Student> findBy(String login, String jmeno, String prijmeni, Integer rocnik) {
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

        for (Predicate p : predicates) {
            cq.where(p);
        }
        return em.createQuery(cq).getResultList();
    }


}
