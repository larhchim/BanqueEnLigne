package fsr.banque.io.gestionBanque.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "Virement")
public class Virement implements Serializable {

    @Id
    @TableGenerator(name = "TRANS_GEN",
            initialValue = 1200)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "TRANS_GEN")
    private Long idVirement;

    private Long numeroCompteRecepteur;

    private BigDecimal montant;

    @Temporal(TemporalType.DATE)
    private Date dateVirement;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "numeroCompteEmetteur")
    @JsonIgnore
    private Compte compteVirement;

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
