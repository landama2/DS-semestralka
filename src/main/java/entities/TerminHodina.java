package entities;

import javax.persistence.*;
import java.sql.Time;

/**
 * Created by marek on 4.5.16.
 */
@Entity
@Table(name = "termin_hodina", schema = "public", catalog = "student_db16_18")
public class TerminHodina {

    @Id
    @GeneratedValue
    @Column(name = "termin_hodina_id", nullable = false)
    private long terminHodinaId;

    @Column(nullable = false)
    private Time casZacatku;

    @Column(nullable = false)
    private int pocetMinut;

    @ManyToOne
    private Mistnost mistnost;
}
