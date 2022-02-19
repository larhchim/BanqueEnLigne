package fsr.banque.io.gestionBanque.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Entité JPA Retrait
 */

@Entity
@Table(name = "Retrait")
public class Retrait implements Serializable {

    @Id
    @TableGenerator(name = "WITHDRAWAL_GEN",
            initialValue = 9999)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "WITHDRAWAL_GEN")
    private Long idRetrait;

    @DecimalMin(value = "0.0",message = "Veuillez specifier un Montant Retrait superieure ou egale à zero")
    private BigDecimal montantRetrait;

    @JsonProperty(access =JsonProperty.Access.READ_ONLY)
    @Temporal(TemporalType.DATE)
    private Date dateRetrait;

    @ManyToOne
    @JoinColumn(name = "numeroCompte")
    @JsonIgnore
    private Compte compteRetrait;

    /**
     * Constructeur Retrait
     * @param idRetrait
     * @param montantRetrait
     * @param dateRetrait
     */

    public Retrait(Long idRetrait, BigDecimal montantRetrait, Date dateRetrait) {
        this.idRetrait = idRetrait;
        this.montantRetrait = montantRetrait;
        this.dateRetrait = dateRetrait;
    }

    public Retrait(){

    }

    public Long getIdRetrait() {
        return idRetrait;
    }

    public void setIdRetrait(Long idRetrait) {
        this.idRetrait = idRetrait;
    }

    public BigDecimal getMontantRetrait() {
        return montantRetrait;
    }

    public void setMontantRetrait(BigDecimal montantRetrait) {
        this.montantRetrait = montantRetrait;
    }

    public Date getDateRetrait() {
        return dateRetrait;
    }

    public void setDateRetrait(Date dateRetrait) {
        this.dateRetrait = dateRetrait;
    }

    public Compte getCompteRetrait() {
        return compteRetrait;
    }

    public void setCompteRetrait(Compte compteRetrait) {
        this.compteRetrait = compteRetrait;
    }
}
