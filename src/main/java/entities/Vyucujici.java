package entities;

import com.sun.xml.internal.messaging.saaj.packaging.mime.util.LineInputStream;

import javax.persistence.*;
import java.util.List;

/**
 * Created by marek on 3.5.16.
 */
@Entity
@Table(name = "vyucujici", schema = "public", catalog = "student_db16_18")
public class Vyucujici {

    @Id
    @GeneratedValue
    @Column(name = "vyucujici_id", nullable = false)
    private long vyucujiciId;

    @Column(nullable = false, unique = true)
    private String login;

    @Column(nullable = false)
    private String jmeno;

    @Column(nullable = false)
    private String prijmeni;

    @Column(nullable = false)
    private String celeJmeno;

    @OneToMany (mappedBy = "vyucujiciHodina")
    private List<VyucujiciHodina> vyucujiciHodinaList;

    @OneToMany (mappedBy = "zaznamPredmet")
    private List<ZaznamPredmet> zaznamPredmetList;
}
