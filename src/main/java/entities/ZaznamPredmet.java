package entities;

import javax.persistence.*;
import java.util.List;

/**
 * Created by marek on 3.5.16.
 */
@Entity
@Table(name = "zaznam_predmet", schema = "public", catalog = "student_db16_18")
public class ZaznamPredmet {

    @Id
    @GeneratedValue
    @Column(name = "zaznam_predmet_id", nullable = false)
    private long zaznamPredmetId;

    @Enumerated
    @Column(nullable = false)
    private StavPredmetu stav;

    @Column(nullable = false)
    private PoradiZapisu poradiZapisu;

    @ManyToOne
    private Vyucujici vyucujici;

    @ManyToMany
    private List<TerminZkouska> terminZkouskaList;

    @ManyToOne
    private Student student;

    @ManyToOne
    private InstancePredmet instancePredmet;

    public long getZaznamPredmetId() {
        return zaznamPredmetId;
    }

    public void setZaznamPredmetId(long zaznamPredmetId) {
        this.zaznamPredmetId = zaznamPredmetId;
    }

    public StavPredmetu getStav() {
        return stav;
    }

    public void setStav(StavPredmetu stav) {
        this.stav = stav;
    }

    public PoradiZapisu getPoradiZapisu() {
        return poradiZapisu;
    }

    public void setPoradiZapisu(PoradiZapisu poradiZapisu) {
        this.poradiZapisu = poradiZapisu;
    }

    public Vyucujici getVyucujici() {
        return vyucujici;
    }

    public void setVyucujici(Vyucujici vyucujici) {
        this.vyucujici = vyucujici;
    }

    public List<TerminZkouska> getTerminZkouskaList() {
        return terminZkouskaList;
    }

    public void setTerminZkouskaList(List<TerminZkouska> terminZkouskaList) {
        this.terminZkouskaList = terminZkouskaList;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public InstancePredmet getInstancePredmet() {
        return instancePredmet;
    }

    public void setInstancePredmet(InstancePredmet instancePredmet) {
        this.instancePredmet = instancePredmet;
    }
}
