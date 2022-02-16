package fsr.banque.io.gestionBanque.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "Credits")
public class Credits implements Serializable {

    public enum Credit {
        IMMOBILIER,CONSOMMATION;
    }

    @Id
    @TableGenerator(name = "CRED_GEN",
            initialValue = 8450)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "CRED_GEN")
    private Long idCredit;

    private BigDecimal montantCredit;

    @Temporal(TemporalType.DATE)
    private Date dateCredit;

    private BigDecimal mensualite;

    @Enumerated(EnumType.STRING)
    private Credit typeCredit;

    private Long nombreMensualitesCredit;

    private BigDecimal montantReste;

    private BigDecimal montantReglee;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "numeroCompte")
    @JsonIgnore
    private Compte compteCredit;

    public Credits(Long idCredit, BigDecimal montantCredit, Date dateCredit, BigDecimal mensualite, Credit typeCredit, Long nombreMensualitesCredit, BigDecimal montantReste, BigDecimal montantReglee) {
        this.idCredit = idCredit;
        this.montantCredit = montantCredit;
        this.dateCredit = dateCredit;
        this.mensualite = mensualite;
        this.typeCredit = typeCredit;
        this.nombreMensualitesCredit = nombreMensualitesCredit;
        this.montantReste = montantReste;
        this.montantReglee = montantReglee;
    }

    public Credits(){

    }

    public Long getIdCredit() {
        return idCredit;
    }

    public void setIdCredit(Long idCredit) {
        this.idCredit = idCredit;
    }

    public BigDecimal getMontantCredit() {
        return montantCredit;
    }

    public void setMontantCredit(BigDecimal montantCredit) {
        this.montantCredit = montantCredit;
    }

    public Date getDateCredit() {
        return dateCredit;
    }

    public void setDateCredit(Date dateCredit) {
        this.dateCredit = dateCredit;
    }

    public BigDecimal getMensualite() {
        return mensualite;
    }

    public void setMensualite(BigDecimal mensualite) {
        this.mensualite = mensualite;
    }

    public Credit getTypeCredit() {
        return typeCredit;
    }

    public void setTypeCredit(Credit typeCredit) {
        this.typeCredit = typeCredit;
    }

    public Long getNombreMensualitesCredit() {
        return nombreMensualitesCredit;
    }

    public void setNombreMensualitesCredit(Long nombreMensualitesCredit) {
        this.nombreMensualitesCredit = nombreMensualitesCredit;
    }

    public BigDecimal getMontantReste() {
        return montantReste;
    }

    public void setMontantReste(BigDecimal montantReste) {
        this.montantReste = montantReste;
    }

    public BigDecimal getMontantReglee() {
        return montantReglee;
    }

    public void setMontantReglee(BigDecimal montantReglee) {
        this.montantReglee = montantReglee;
    }

    public Compte getCompteCredit() {
        return compteCredit;
    }

    public void setCompteCredit(Compte compteCredit) {
        this.compteCredit = compteCredit;
    }
}
