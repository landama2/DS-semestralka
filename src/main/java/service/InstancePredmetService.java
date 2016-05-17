package service;

import dao.InstancePredmetDAO;
import entities.InstancePredmet;

/**
 * Created by urban on 5/17/2016.
 */
public class InstancePredmetService {

    InstancePredmetDAO instancePredmetDAO = new InstancePredmetDAO(InstancePredmet.class);

    public boolean createInstance(InstancePredmet instance) {
        if (isNotValid(instance)) {
            return false;
        }
        instancePredmetDAO.create(instance);
        return true;
    }

    public boolean deleteInstance(InstancePredmet instancePredmet) {
        instancePredmetDAO.delete(instancePredmet);
        return true;
    }

    private boolean isNotValid(InstancePredmet instancePredmet) {
        if (instancePredmet.getPredmet() == null && instancePredmet.getSkolniRok() == 0) {
            return true;
        }
        return false;
    }
}
