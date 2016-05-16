package dao;

import entities.Predmet;

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
        for (Predicate p : predicates) {
            cq.where(p);
        }
        return em.createQuery(cq).getResultList();
    }
}
