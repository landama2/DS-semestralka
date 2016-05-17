package entities;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collector;

/**
 * Created by marek on 3.5.16.
 */
@Entity
@Table(name = "predmet", schema = "public", catalog = "student_db16_18")
public class Predmet {

    @Id
    @GeneratedValue
    @Column(name = "idpredmet", nullable = false)
    private int predmetId;

    @Column(nullable = false, unique = true)
    private String kod;

    @Column(nullable = false)
    private String nazev;

    private String rozsah;

    @Column(nullable = false)
    private int pocetKreditu;

    @Column(nullable = false)
    private Character semestr;

    @Column(nullable = false)
    private boolean jeZkouska;

    @OneToMany(mappedBy = "predmet")
    private List<InstancePredmet> instancePredmetList;

    public String getNazev() {
        return nazev;
    }

    public void setNazev(String nazev) {
        this.nazev = nazev;
    }

    public int getPredmetId() {
        return predmetId;
    }

    public String getKod() {
        return kod;
    }

    public void setKod(String kod) {
        this.kod = kod;
    }

    public String getRozsah() {
        return rozsah;
    }

    public void setRozsah(String rozsah) {
        this.rozsah = rozsah;
    }

    public int getPocetKreditu() {
        return pocetKreditu;
    }

    public void setPocetKreditu(int pocetKreditu) {
        this.pocetKreditu = pocetKreditu;
    }

    public Character getSemestr() {
        return semestr;
    }

    public void setSemestr(Character semestr) {
        this.semestr = semestr;
    }

    public boolean isJeZkouska() {
        return jeZkouska;
    }

    public void setJeZkouska(boolean jeZkouska) {
        this.jeZkouska = jeZkouska;
    }

    public List<InstancePredmet> getInstancePredmetList() {
        return instancePredmetList;
    }

    public void setInstancePredmetuList(List<InstancePredmet> instancePredmetList) {
        this.instancePredmetList = instancePredmetList;
    }

    public void setPredmetId(int predmetId) {
        this.predmetId = predmetId;
    }

    public void setInstancePredmetList(List<InstancePredmet> instancePredmetList) {
        this.instancePredmetList = instancePredmetList;
    }
}
