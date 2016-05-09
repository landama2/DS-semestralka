package service;

import dao.StudentDAO;
import entities.Student;

/**
 * Created by urban on 5/9/2016.
 */
public class StudentService {

    StudentDAO studentDAO = new StudentDAO(Student.class);

    public boolean createStudent(Student student){
        if (isNotValidNew(student)){
            return false;
        }
        studentDAO.create(student);
        return true;
    }

    public boolean updateStudent(Student student){
        if (isNotValid(student)){
            return false;
        }
        studentDAO.update(student);
        return true;
    }

    private boolean isNotValid(Student student) {
        if(student.getLogin() == null){
            return true;
        }
        if(student.getJmeno() == null){
            return true;
        }
        if(student.getPrijmeni() == null){
            return true;
        }
        if(student.getRocnik() == 0){
            return true;
        }
        return false;
    }

    private boolean isNotValidNew(Student student){
        return isNotValid(student) || !studentDAO.findBy(student.getLogin(),null,null,0).isEmpty();
    }
}
