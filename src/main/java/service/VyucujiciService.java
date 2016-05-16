package service;

import dao.VyucujiciDAO;
import entities.Vyucujici;

/**
 * Created by urban on 5/14/2016.
 */
public class VyucujiciService {

    VyucujiciDAO vyucujiciDAO = new VyucujiciDAO(Vyucujici.class);

    public boolean addVyucujici(Vyucujici vyucujici) {
        if (isNotValid(vyucujici)) {
            return false;
        }
        vyucujiciDAO.create(vyucujici);
        return true;
    }

    public boolean updateVyucujici(Vyucujici vyucujici) {
        if (isNotValid(vyucujici)) {
            return false;
        } else {
            vyucujiciDAO.update(vyucujici);
            return true;
        }
    }

    private boolean isNotValid(Vyucujici vyucujici) {
        if (vyucujici.getLogin() == null) {
            return true;
        }
        if (vyucujici.getKrestni_jmeno() == null) {
            return true;
        }
        if (vyucujici.getPrijmeni() == null) {
            return true;
        }
        if (vyucujici.getCele_jmeno() == null) {
            return true;
        }
        return false;
    }
}
