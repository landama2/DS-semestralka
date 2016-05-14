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

    private boolean isNotValid(Vyucujici vyucujici) {
        if (vyucujici.getLogin() == null) {
            return true;
        }
        if (vyucujici.getJmeno() == null) {
            return true;
        }
        if (vyucujici.getPrijmeni() == null) {
            return true;
        }
        if (vyucujici.getCeleJmeno() == null) {
            return true;
        }
        return false;
    }
}
