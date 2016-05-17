package entities;

import javax.persistence.*;
import java.util.List;

/**
 * Created by marek on 3.5.16.
 */
@Entity
@Table(name = "instance_predmet", schema = "public", catalog = "student_db16_18")
public class InstancePredmet {

    @Id
    @GeneratedValue
    @Column(name = "intance_predmet_id", nullable = false)
    private int instancePredmetId;

    @Column(nullable = false)
    private int skolniRok;

    @ManyToOne
    @JoinColumn(name = "predmet_idpredmet")
    private Predmet predmet;

    @OneToMany(mappedBy = "instancePredmet")
    private List<TerminZkouska> terminZkouskaList;

    @OneToMany(mappedBy = "instancePredmet")
    private List<ZaznamPredmet> zaznamPredmetList;

    @OneToMany(mappedBy = "instancePredmet")
    private List<VyucujiciHodina> vyucujiciHodinaList;

    public long getInstancePredmetId() {
        return instancePredmetId;
    }

    public void setInstancePredmetId(int instancePredmetId) {
        this.instancePredmetId = instancePredmetId;
    }

    public int getSkolniRok() {
        return skolniRok;
    }

    public void setSkolniRok(int skolniRok) {
        this.skolniRok = skolniRok;
    }

    public Predmet getPredmet() {
        return predmet;
    }

    public void setPredmet(Predmet predmet) {
        this.predmet = predmet;
    }

    public List<TerminZkouska> getTerminZkouskaList() {
        return terminZkouskaList;
    }

    public void setTerminZkouskaList(List<TerminZkouska> terminZkouskaList) {
        this.terminZkouskaList = terminZkouskaList;
    }

    public List<ZaznamPredmet> getZaznamPredmetList() {
        return zaznamPredmetList;
    }

    public void setZaznamPredmetList(List<ZaznamPredmet> zaznamPredmetList) {
        this.zaznamPredmetList = zaznamPredmetList;
    }

    public List<VyucujiciHodina> getVyucujiciHodinaList() {
        return vyucujiciHodinaList;
    }

    public void setVyucujiciHodinaList(List<VyucujiciHodina> vyucujiciHodinaList) {
        this.vyucujiciHodinaList = vyucujiciHodinaList;
    }
}
