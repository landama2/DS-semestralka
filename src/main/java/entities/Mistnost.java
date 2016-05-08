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

    public long getMistnostId() {
        return mistnostId;
    }

    public void setMistnostId(long mistnostId) {
        this.mistnostId = mistnostId;
    }

    public String getKodMistnost() {
        return kodMistnost;
    }

    public void setKodMistnost(String kodMistnost) {
        this.kodMistnost = kodMistnost;
    }

    public int getKapacita() {
        return kapacita;
    }

    public void setKapacita(int kapacita) {
        this.kapacita = kapacita;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public List<TerminHodina> getTerminHodinaList() {
        return terminHodinaList;
    }

    public void setTerminHodinaList(List<TerminHodina> terminHodinaList) {
        this.terminHodinaList = terminHodinaList;
    }

    public List<TerminZkouska> getTerminZkouskaList() {
        return terminZkouskaList;
    }

    public void setTerminZkouskaList(List<TerminZkouska> terminZkouskaList) {
        this.terminZkouskaList = terminZkouskaList;
    }
}
