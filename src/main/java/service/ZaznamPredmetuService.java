package service;

import dao.InstancePredmetDAO;
import dao.StudentDAO;
import dao.ZaznamPredmetDAO;
import entities.InstancePredmet;
import entities.Student;
import entities.ZaznamPredmet;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by urban on 5/18/2016.
 */
public class ZaznamPredmetuService {

    ZaznamPredmetDAO zaznamPredmetDAO = new ZaznamPredmetDAO(ZaznamPredmet.class);
    InstancePredmetDAO instancePredmetDAO = new InstancePredmetDAO(InstancePredmet.class);
    StudentDAO studentDAO = new StudentDAO(Student.class);

    public boolean createZaznam(ZaznamPredmet zaznamPredmet) {
        if (isNotValid(zaznamPredmet)) {
            return false;
        }
        Student student = zaznamPredmet.getStudent();
        InstancePredmet instancePredmet = zaznamPredmet.getInstancePredmet();
        List<ZaznamPredmet> found = zaznamPredmetDAO.findBy(student,instancePredmet);
        if (found == null || found.size() == 0) {
            zaznamPredmetDAO.create(zaznamPredmet);
            List<ZaznamPredmet> zaznamy = student.getZaznamPredmetList();
            if (zaznamy == null) {
                zaznamy = new ArrayList<>();
                zaznamy.add(zaznamPredmet);
                student.setZaznamPredmetList(zaznamy);
                studentDAO.update(student);
            } else {
                zaznamy.add(zaznamPredmet);
                student.setZaznamPredmetList(zaznamy);
                studentDAO.update(student);
            }
            zaznamy = instancePredmet.getZaznamPredmetList();
            if (zaznamy == null) {
                zaznamy = new ArrayList<>();
                zaznamy.add(zaznamPredmet);
                instancePredmet.setZaznamPredmetList(zaznamy);
                instancePredmetDAO.update(instancePredmet);
            } else {
                zaznamy.add(zaznamPredmet);
                instancePredmet.setZaznamPredmetList(zaznamy);
                instancePredmetDAO.update(instancePredmet);
            }
            return true;
        } else {
            return false;
        }
    }

    private boolean isNotValid(ZaznamPredmet zaznamPredmet) {
        if (zaznamPredmet.getStudent() == null && zaznamPredmet.getInstancePredmet() == null) {
            return true;
        }
        return false;
    }
}
