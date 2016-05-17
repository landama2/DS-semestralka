package service;

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

    public boolean addInstanceAndUpdate(Predmet predmet, InstancePredmet instancePredmet) {
        List<InstancePredmet> currentInstances = predmet.getInstancePredmetList();
        if (currentInstances == null) {
            currentInstances = new ArrayList<>();
            currentInstances.add(instancePredmet);
            predmet.setInstancePredmetList(currentInstances);
            predmetDAO.update(predmet);
            return true;
        } else {
            if (!currentInstances.contains(instancePredmet)) {
                currentInstances.add(instancePredmet);
                predmet.setInstancePredmetList(currentInstances);
                predmetDAO.update(predmet);
                return true;
            } else {
                return false;
            }
        }
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
