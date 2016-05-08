package entities;

import javax.persistence.*;
import java.sql.Time;
import java.util.List;

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

    @OneToMany (mappedBy = "terminHodina")
    private List<VyucujiciHodina> vyucujiciHodinaList;

    public long getTerminHodinaId() {
        return terminHodinaId;
    }

    public void setTerminHodinaId(long terminHodinaId) {
        this.terminHodinaId = terminHodinaId;
    }

    public Time getCasZacatku() {
        return casZacatku;
    }

    public void setCasZacatku(Time casZacatku) {
        this.casZacatku = casZacatku;
    }

    public int getPocetMinut() {
        return pocetMinut;
    }

    public void setPocetMinut(int pocetMinut) {
        this.pocetMinut = pocetMinut;
    }

    public Mistnost getMistnost() {
        return mistnost;
    }

    public void setMistnost(Mistnost mistnost) {
        this.mistnost = mistnost;
    }

    public List<VyucujiciHodina> getVyucujiciHodinaList() {
        return vyucujiciHodinaList;
    }

    public void setVyucujiciHodinaList(List<VyucujiciHodina> vyucujiciHodinaList) {
        this.vyucujiciHodinaList = vyucujiciHodinaList;
    }
}
