package service;

import dao.InstancePredmetDAO;
import dao.PredmetDAO;
import entities.InstancePredmet;
import entities.Predmet;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by urban on 5/16/2016.
 */
public class PredmetService {

    PredmetDAO predmetDAO = new PredmetDAO(Predmet.class);
    InstancePredmetDAO instancePredmetDAO = new InstancePredmetDAO(InstancePredmet.class);

    public boolean addPredmet(Predmet predmet) {
        if (!isNotValid(predmet)) {
            predmetDAO.create(predmet);
            return true;
        }
        return false;
    }

    public boolean updatePredmet(Predmet predmet) {
        if (!isNotValid(predmet)) {
            predmetDAO.update(predmet);
            return true;
        }
        return false;
    }

    public boolean addInstanceAndUpdate(InstancePredmet instancePredmet) {
        Predmet predmet = instancePredmet.getPredmet();
        List<InstancePredmet> found = instancePredmetDAO.findBy(predmet, instancePredmet.getSkolniRok());
        if (found == null || found.size() == 0) {
            List<InstancePredmet> currentInstances = predmet.getInstancePredmetList();
            if (currentInstances == null) {
                currentInstances = new ArrayList<>();
                currentInstances.add(instancePredmet);
                predmet.setInstancePredmetList(currentInstances);
                instancePredmetDAO.create(instancePredmet);
                predmetDAO.update(predmet);
                return true;
            } else {
                currentInstances.add(instancePredmet);
                predmet.setInstancePredmetList(currentInstances);
                instancePredmetDAO.create(instancePredmet);
                predmetDAO.update(predmet);
                return true;
            }
        }
        return false;
    }

    private boolean isNotValid(Predmet predmet) {
        if (predmet.getKod() == null) {
            return true;
        }
        if (predmet.getNazev() == null) {
            return true;
        }
        if (predmet.getRozsah() == null) {
            return true;
        }
        if (predmet.getPocetKreditu() == 0) {
            return true;
        }
        if (predmet.getSemestr().equals(null)) {
            return true;
        }
        return false;
    }
}
