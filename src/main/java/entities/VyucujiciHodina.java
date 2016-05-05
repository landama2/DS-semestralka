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

}
