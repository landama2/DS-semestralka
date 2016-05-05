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
    private long instancePredmetId;

    @Column(nullable = false)
    private int skolniRok;

    @ManyToOne
    private Predmet predmet;

    @OneToMany(mappedBy = "instancePredmet")
    private List<TerminZkouska> terminZkouskaList;

    @OneToMany(mappedBy = "instancePredmet")
    private List<ZaznamPredmet> zaznamPredmetList;

    @OneToMany(mappedBy = "instancePredmet")
    private List<VyucujiciHodina> vyucujiciHodinaList;

}
