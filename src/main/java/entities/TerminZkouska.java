package entities;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;
import java.util.List;

/**
 * Created by marek on 4.5.16.
 */
@Entity
@Table(name = "termin_zkouska", schema = "public", catalog = "student_db16_18")
public class TerminZkouska {

    @Id
    @GeneratedValue
    @Column(name = "termin_zkouska_id", nullable = false)
    private long terminZkouskaId;

    @Column(nullable = false)
    private Date datum;

    @Column(nullable = false)
    private Time casZacatku;

    @Column(name = "pocet_studentu", nullable = false)
    private int pocetStudentu;

    @ManyToOne
    private Mistnost mistnost;

    @ManyToMany
    private List<ZaznamPredmet> zaznamPredmetList;

    @ManyToOne
    private InstancePredmet instancePredmet;

    public long getTerminZkouskaId() {
        return terminZkouskaId;
    }

    public void setTerminZkouskaId(long terminZkouskaId) {
        this.terminZkouskaId = terminZkouskaId;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public Time getCasZacatku() {
        return casZacatku;
    }

    public void setCasZacatku(Time casZacatku) {
        this.casZacatku = casZacatku;
    }

    public int getPocetStudentu() {
        return pocetStudentu;
    }

    public void setPocetStudentu(int pocetStudentu) {
        this.pocetStudentu = pocetStudentu;
    }

    public Mistnost getMistnost() {
        return mistnost;
    }

    public void setMistnost(Mistnost mistnost) {
        this.mistnost = mistnost;
    }

    public List<ZaznamPredmet> getZaznamPredmetList() {
        return zaznamPredmetList;
    }

    public void setZaznamPredmetList(List<ZaznamPredmet> zaznamPredmetList) {
        this.zaznamPredmetList = zaznamPredmetList;
    }

    public InstancePredmet getInstancePredmet() {
        return instancePredmet;
    }

    public void setInstancePredmet(InstancePredmet instancePredmet) {
        this.instancePredmet = instancePredmet;
    }
}
