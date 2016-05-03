package entities;

import javax.persistence.*;

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
}
