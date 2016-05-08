package entities;

import javax.persistence.*;
import java.util.List;

/**
 * Created by marek on 3.5.16.
 */
@Entity
@Table(name = "student", schema = "public", catalog = "student_db16_18")
public class Student {

    @Id
    @GeneratedValue
    @Column(name = "idstudent", nullable = false)
    private long studentId;

    @Column(nullable = false, unique = true)
    private String login;

    @Column(nullable = false)
    private String jmeno;

    @Column(nullable = false)
    private String prijmeni;

    @Column(nullable = false)
    private Integer rocnik;

    @OneToMany(mappedBy = "student")
    private List<ZaznamPredmet> zaznamPredmetList;

    public long getStudentId() {
        return studentId;
    }

    public void setStudentId(long studentId) {
        this.studentId = studentId;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getJmeno() {
        return jmeno;
    }

    public void setJmeno(String jmeno) {
        this.jmeno = jmeno;
    }

    public String getPrijmeni() {
        return prijmeni;
    }

    public void setPrijmeni(String prijmeni) {
        this.prijmeni = prijmeni;
    }

    public int getRocnik() {
        return rocnik;
    }

    public void setRocnik(int rocnik) {
        this.rocnik = rocnik;
    }

    public List<ZaznamPredmet> getZaznamPredmetList() {
        return zaznamPredmetList;
    }

    public void setZaznamPredmetList(List<ZaznamPredmet> zaznamPredmetList) {
        this.zaznamPredmetList = zaznamPredmetList;
    }
}
