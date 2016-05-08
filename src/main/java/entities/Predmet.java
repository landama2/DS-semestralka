package entities;

import javax.persistence.*;
import java.util.List;

/**
 * Created by marek on 3.5.16.
 */
@Entity
@Table(name = "predmet", schema = "public", catalog = "student_db16_18")
public class Predmet {

    @Id
    @GeneratedValue
    @Column(name = "predmet_id", nullable = false)
    private long predmetId;

    @Column(nullable = false, unique = true)
    private String kod;

    @Column(nullable = false)
    private int kapacita;

    private String rozsah;

    @Column(nullable = false)
    private int pocetKreditu;

    @Column(nullable = false)
    private char semestr;

    @Column(nullable = false)
    private boolean jeZkouska;

    @OneToMany(mappedBy = "predmet")
    private List<InstancePredmet> instancePredmetList;


    public long getPredmetId() {
        return predmetId;
    }

    public String getKod() {
        return kod;
    }

    public void setKod(String kod) {
        this.kod = kod;
    }

    public int getKapacita() {
        return kapacita;
    }

    public void setKapacita(int kapacita) {
        this.kapacita = kapacita;
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

    public char getSemestr() {
        return semestr;
    }

    public void setSemestr(char semestr) {
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

    public void setPredmetId(long predmetId) {
        this.predmetId = predmetId;
    }

    public void setInstancePredmetList(List<InstancePredmet> instancePredmetList) {
        this.instancePredmetList = instancePredmetList;
    }
}
