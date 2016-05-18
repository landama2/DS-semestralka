package service;

import dao.VyucujiciHodinaDAO;
import entities.VyucujiciHodina;

/**
 * Created by urban on 5/18/2016.
 */
public class VyucujiciHodinaService {

    VyucujiciHodinaDAO vyucujiciHodinaDAO = new VyucujiciHodinaDAO(VyucujiciHodina.class);

    public boolean createVyucujiciHodina(VyucujiciHodina vyucujiciHodina) {
        if (isNotValid(vyucujiciHodina)) {
            return false;
        }
        vyucujiciHodinaDAO.create(vyucujiciHodina);
        return true;
    }

    private boolean isNotValid(VyucujiciHodina vyucujiciHodina) {
        if (vyucujiciHodina.getVyucujici() == null && vyucujiciHodina.getInstancePredmet() == null) {
            return true;
        }
        if (vyucujiciHodina.getVyucujici() != null && vyucujiciHodina.getInstancePredmet()!=null) {
            if (vyucujiciHodinaDAO.findBy(vyucujiciHodina.getVyucujici(),vyucujiciHodina.getInstancePredmet()).size() > 0) {
                return true;
            }
        }
        return false;
    }

}


