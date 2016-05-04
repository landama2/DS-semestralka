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
}
