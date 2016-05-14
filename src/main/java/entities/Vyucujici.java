package entities;

import com.sun.xml.internal.messaging.saaj.packaging.mime.util.LineInputStream;

import javax.persistence.*;
import java.util.List;

/**
 * Created by marek on 3.5.16.
 */
@Entity
@Table(name = "vyucujici", schema = "public", catalog = "student_db16_18")
public class Vyucujici {

    @Id
    @GeneratedValue
    @Column(name = "idvyucujici", nullable = false)
    private long vyucujiciId;

    @Column(nullable = false, unique = true)
    private String login;

    @Column(nullable = false)
    private String jmeno;

    @Column(nullable = false)
    private String prijmeni;

    @Column(nullable = false)
    private String celeJmeno;

    @OneToMany (mappedBy = "vyucujici")
    private List<VyucujiciHodina> vyucujiciHodinaList;

    @OneToMany (mappedBy = "vyucujici")
    private List<ZaznamPredmet> zaznamPredmetList;

    public long getVyucujiciId() {
        return vyucujiciId;
    }

    public void setVyucujiciId(long vyucujiciId) {
        this.vyucujiciId = vyucujiciId;
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

    public String getCeleJmeno() {
        return celeJmeno;
    }

    public void setCeleJmeno(String celeJmeno) {
        this.celeJmeno = celeJmeno;
    }

    public List<VyucujiciHodina> getVyucujiciHodinaList() {
        return vyucujiciHodinaList;
    }

    public void setVyucujiciHodinaList(List<VyucujiciHodina> vyucujiciHodinaList) {
        this.vyucujiciHodinaList = vyucujiciHodinaList;
    }

    public List<ZaznamPredmet> getZaznamPredmetList() {
        return zaznamPredmetList;
    }

    public void setZaznamPredmetList(List<ZaznamPredmet> zaznamPredmetList) {
        this.zaznamPredmetList = zaznamPredmetList;
    }
}
