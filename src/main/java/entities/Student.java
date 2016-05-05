package entities;

import javax.persistence.*;
import java.util.List;

/**
 * Created by marek on 3.5.16.
 */
@Entity
@Table(name = "student", schema = "public", catalog = "student_db16_18")
public class Student {

    @Id
    @GeneratedValue
    @Column(name = "student_id", nullable = false)
    private long studentId;

    @Column(nullable = false, unique = true)
    private String login;

    @Column(nullable = false)
    private String jmeno;

    @Column(nullable = false)
    private String prijmeni;

    @Column(nullable = false)
    private int rocnik;

    @Column(nullable = false)
    private int rokNastupu;

    @OneToMany(mappedBy = "zaznamPredmet")
    private List<ZaznamPredmet> zaznamPredmetList;
}
