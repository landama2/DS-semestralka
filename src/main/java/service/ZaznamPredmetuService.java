package service;

import dao.ZaznamPredmetDAO;
import entities.ZaznamPredmet;

/**
 * Created by urban on 5/18/2016.
 */
public class ZaznamPredmetuService {

    ZaznamPredmetDAO zaznamPredmetDAO = new ZaznamPredmetDAO(ZaznamPredmet.class);

    public boolean createZaznam(ZaznamPredmet zaznamPredmet) {
        if (isNotValid(zaznamPredmet)) {
            return false;
        }
        zaznamPredmetDAO.create(zaznamPredmet);
        return true;
    }

    private boolean isNotValid(ZaznamPredmet zaznamPredmet) {
        if (zaznamPredmet.getVyucujici() == null && zaznamPredmet.getStudent() == null && zaznamPredmet.getInstancePredmet() == null) {
            return true;
        }
        return false;
    }
}
