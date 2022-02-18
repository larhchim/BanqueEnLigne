package fsr.banque.io.gestionBanque.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Entité JPA Virement
 */

@Entity
@Table(name = "Virement")
public class Virement implements Serializable {

    @Id
    @TableGenerator(name = "TRANS_GEN",
            initialValue = 1200)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "TRANS_GEN")
    private Long idVirement;

    @NotNull(message = "Veuillez specifier le numero compte recepteur")
    private Long numeroCompteRecepteur;

    @DecimalMin(value = "0.0",message = "Veuillez specifier un Montant superieure ou egale à zero")
    private BigDecimal montant;

    @JsonProperty(access=JsonProperty.Access.READ_ONLY)
    @Temporal(TemporalType.DATE)
    private Date dateVirement;

    @ManyToOne
    @JoinColumn(name = "numeroCompteEmetteur")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotNull(message = "Veuillez specifier les informations du compte virement")
    private Compte compteVirement;

    /**
     * Constructeur Virement
     * @param idVirement
     * @param numeroCompteRecepteur
     * @param montant
     * @param dateVirement
     */

    public Virement(Long idVirement, Long numeroCompteRecepteur, BigDecimal montant, Date dateVirement) {
        this.idVirement = idVirement;
        this.numeroCompteRecepteur = numeroCompteRecepteur;
        this.montant = montant;
        this.dateVirement = dateVirement;
    }

    public Virement(){

    }

    public Long getIdVirement() {
        return idVirement;
    }

    public void setIdVirement(Long idVirement) {
        this.idVirement = idVirement;
    }

    public Long getNumeroCompteRecepteur() {
        return numeroCompteRecepteur;
    }

    public void setNumeroCompteRecepteur(Long numeroCompteRecepteur) {
        this.numeroCompteRecepteur = numeroCompteRecepteur;
    }

    public BigDecimal getMontant() {
        return montant;
    }

    public void setMontant(BigDecimal montant) {
        this.montant = montant;
    }

    public Date getDateVirement() {
        return dateVirement;
    }

    public void setDateVirement(Date dateVirement) {
        this.dateVirement = dateVirement;
    }

    public Compte getCompteVirement() {
        return compteVirement;
    }

    public void setCompteVirement(Compte compteVirement) {
        this.compteVirement = compteVirement;
    }
}
