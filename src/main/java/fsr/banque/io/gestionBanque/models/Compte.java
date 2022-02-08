package fsr.banque.io.gestionBanque.models;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Compte")
public class Compte implements Serializable {

    public enum TypeCompte {
        EPARGNE,COURANT,AUTRE;
    };

    @Id
    @TableGenerator(name = "ACCOUNT_GEN",
            initialValue = 90314285)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "ACCOUNT_GEN")
    private Long numeroCompte;

    private boolean etatCompte;

    @Temporal(TemporalType.DATE)
    private Date dateCreation;

    private BigDecimal soldeCompte;

    private String motDePasse;

    @Enumerated(EnumType.STRING)
    private TypeCompte typeCompte;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idUtilisateur")
    @JsonIgnore
    private Utilisateur utilisateur;

    @OneToMany(mappedBy = "compteVirement")
    private List<Virement> virementList = new ArrayList<>();

    @OneToMany(mappedBy = "compteRetrait")
    private List<Retrait> retraitList = new ArrayList<>();

    @OneToMany(mappedBy = "compteCredit")
    private List<Credits> creditsList = new ArrayList<>();


    public Compte(Long numeroCompte, boolean etatCompte, Date dateCreation, BigDecimal soldeCompte, TypeCompte typeCompte , String motDePasse) {
        this.numeroCompte = numeroCompte;
        this.etatCompte = etatCompte;
        this.dateCreation = dateCreation;
        this.soldeCompte = soldeCompte;
        this.typeCompte = typeCompte;
        this.motDePasse = motDePasse;
    }

    public Compte(){
    }

    public Long getNumeroCompte() {
        return numeroCompte;
    }

    public void setNumeroCompte(Long numeroCompte) {
        this.numeroCompte = numeroCompte;
    }

    public boolean isEtatCompte() {
        return etatCompte;
    }

    public void setEtatCompte(boolean etatCompte) {
        this.etatCompte = etatCompte;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public BigDecimal getSoldeCompte() {
        return soldeCompte;
    }

    public void setSoldeCompte(BigDecimal soldeCompte) {
        this.soldeCompte = soldeCompte;
    }

    public TypeCompte getTypeCompte() {
        return typeCompte;
    }

    public void setTypeCompte(TypeCompte typeCompte) {
        this.typeCompte = typeCompte;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public List<Virement> getVirementList() {
        return virementList;
    }

    public void setVirementList(List<Virement> virementList) {
        this.virementList = virementList;
    }

    public List<Retrait> getRetraitList() {
        return retraitList;
    }

    public void setRetraitList(List<Retrait> retraitList) {
        this.retraitList = retraitList;
    }

}
