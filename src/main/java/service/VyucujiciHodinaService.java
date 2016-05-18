package service;

import dao.InstancePredmetDAO;
import dao.VyucujiciHodinaDAO;
import entities.InstancePredmet;
import entities.VyucujiciHodina;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by urban on 5/18/2016.
 */
public class VyucujiciHodinaService {

    VyucujiciHodinaDAO vyucujiciHodinaDAO = new VyucujiciHodinaDAO(VyucujiciHodina.class);
    InstancePredmetDAO instancePredmetDAO = new InstancePredmetDAO(InstancePredmet.class);

    public boolean createVyucujiciHodina(VyucujiciHodina vyucujiciHodina) {
        if (isNotValid(vyucujiciHodina)) {
            return false;
        }
        InstancePredmet instancePredmet = vyucujiciHodina.getInstancePredmet();
        List<VyucujiciHodina> found = vyucujiciHodinaDAO.findBy(vyucujiciHodina.getVyucujici(),vyucujiciHodina.getInstancePredmet());
        if (found == null || found.size() == 0) {
            System.out.println("Prirazuju novou vyucovaci hodinu instanci!");
            List<VyucujiciHodina> currentVyucujiciHodina = instancePredmet.getVyucujiciHodinaList();
            if (currentVyucujiciHodina == null) {
                currentVyucujiciHodina = new ArrayList<>();
                currentVyucujiciHodina.add(vyucujiciHodina);
                instancePredmet.setVyucujiciHodinaList(currentVyucujiciHodina);
                vyucujiciHodinaDAO.create(vyucujiciHodina);
                instancePredmetDAO.update(instancePredmet);
                return true;
            } else {
                currentVyucujiciHodina.add(vyucujiciHodina);
                instancePredmet.setVyucujiciHodinaList(currentVyucujiciHodina);
                vyucujiciHodinaDAO.create(vyucujiciHodina);
                instancePredmetDAO.update(instancePredmet);
                return true;
            }
        } else {
            return false;
        }
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


