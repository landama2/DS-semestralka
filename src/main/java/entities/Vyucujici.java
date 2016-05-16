package entities;

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
    private String krestni_jmeno;

    @Column(nullable = false)
    private String prijmeni;

    @Column(nullable = false)
    private String cele_jmeno;

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

    public String getKrestni_jmeno() {
        return krestni_jmeno;
    }

    public void setKrestni_jmeno(String jmeno) {
        this.krestni_jmeno = jmeno;
    }

    public String getPrijmeni() {
        return prijmeni;
    }

    public void setPrijmeni(String prijmeni) {
        this.prijmeni = prijmeni;
    }

    public String getCele_jmeno() {
        return cele_jmeno;
    }

    public void setCele_jmeno(String celeJmeno) {
        this.cele_jmeno = celeJmeno;
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
