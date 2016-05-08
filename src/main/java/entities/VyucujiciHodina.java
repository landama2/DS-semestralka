package entities;

import javax.persistence.*;

/**
 * Created by marek on 3.5.16.
 */
@Entity
@Table(name = "vyucujici_hodina", schema = "public", catalog = "student_db16_18")
public class VyucujiciHodina {

    @Id
    @GeneratedValue
    @Column(name = "vyucujici_hodina_id", nullable = false)
    private long vyucujiciHodinaId;

    private char typHodiny;

    @ManyToOne
    private InstancePredmet instancePredmet;

    @ManyToOne
    private Vyucujici vyucujici;

    @ManyToOne
    private TerminHodina terminHodina;

    public long getVyucujiciHodinaId() {
        return vyucujiciHodinaId;
    }

    public void setVyucujiciHodinaId(long vyucujiciHodinaId) {
        this.vyucujiciHodinaId = vyucujiciHodinaId;
    }

    public char getTypHodiny() {
        return typHodiny;
    }

    public void setTypHodiny(char typHodiny) {
        this.typHodiny = typHodiny;
    }

    public InstancePredmet getInstancePredmet() {
        return instancePredmet;
    }

    public void setInstancePredmet(InstancePredmet instancePredmet) {
        this.instancePredmet = instancePredmet;
    }

    public Vyucujici getVyucujici() {
        return vyucujici;
    }

    public void setVyucujici(Vyucujici vyucujici) {
        this.vyucujici = vyucujici;
    }

    public TerminHodina getTerminHodina() {
        return terminHodina;
    }

    public void setTerminHodina(TerminHodina terminHodina) {
        this.terminHodina = terminHodina;
    }
}
