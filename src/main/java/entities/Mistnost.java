package entities;

import javax.persistence.*;
import java.util.List;

/**
 * Created by marek on 4.5.16.
 */
@Entity
@Table(name = "mistnost", schema = "public", catalog = "student_db16_18")
public class Mistnost {

    @Id
    @GeneratedValue
    @Column(name = "mistnost_id", nullable = false)
    private long mistnostId;

    @Column(nullable = false)
    private String kodMistnost;

    @Column(nullable = false)
    private int kapacita;

    @Column(nullable = false)
    private String adresa;

    @OneToMany(mappedBy = "mistnost")
    private List<TerminHodina> terminHodinaList;

    @OneToMany(mappedBy = "mistnost")
    private List<TerminZkouska> terminZkouskaList;


}
