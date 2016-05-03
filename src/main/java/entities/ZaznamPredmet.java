package entities;

import javax.persistence.*;

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

    @Column(nullable = false)
    private Student student;

    @ManyToOne
    private Vyucujici vyucujici;
}
