package dao;

import entities.Predmet;

import javax.persistence.Query;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by urban on 5/8/2016.
 */
public class PredmetDAO extends GenericDAO<Predmet> {

    public PredmetDAO(Class entityClass) {
        super(entityClass);
    }

    public List<Predmet> findBy(String kod, String nazev, String rozsah, Integer pocetKreditu, Boolean jeZkouska, Character semestr) {
        cq = cb.createQuery(Predmet.class);

        if (kod == null && nazev == null && rozsah == null && pocetKreditu == 0) {
            return null;
        }
        ArrayList<Predicate> predicates = new ArrayList<>();

        if (kod != null) {
            predicates.add(cb.equal(root.get("kod"),kod));
        }
        if (nazev != null) {
            predicates.add(cb.equal(root.get("nazev"),nazev));
        }
        if (rozsah != null) {
            predicates.add(cb.equal(root.get("rozsah"),rozsah));
        }
        if (pocetKreditu != 0) {
            predicates.add(cb.equal(root.get("pocet_kreditu"),pocetKreditu));
        }
        if (jeZkouska != null) {
            predicates.add(cb.equal(root.get("jezkouska"),jeZkouska));
        }
        if (semestr != null) {
            predicates.add(cb.equal(root.get("semestr"),semestr));
        }
        cq.select(root).where(predicates.toArray(new Predicate[]{}));
        return em.createQuery(cq).getResultList();
    }

    @Override
    public List<Predmet> findAll() {
        cq = cb.createQuery(Predmet.class);
        ArrayList<Order> orders = new ArrayList<>();
//        orders.add(cb.asc(root.get("rozsah")));
        orders.add(cb.asc(root.get("kod")));
        orders.add(cb.asc(root.get("nazev")));
        cq.select(cq.from(Predmet.class));
        for (Order order : orders){
            cq.orderBy(order);
        }
        return em.createQuery(cq).getResultList();
    }
}
