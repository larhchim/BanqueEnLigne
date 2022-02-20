package fsr.banque.io.gestionBanque.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Entité JPA Compte
 */

@Entity
@Table(name = "Compte")
public class Compte implements Serializable {

    /**
     * Enumeration Type Compte qui presente Trois possibilités de creation de comptes
     */

    public enum TypeCompte {
        EPARGNE,COURANT,ADMIN;
    };


    @Id
    @TableGenerator(name = "ACCOUNT_GEN",
            initialValue = 90314285)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "ACCOUNT_GEN")
    private Long numeroCompte;

    @JsonProperty(access =JsonProperty.Access.READ_ONLY)
    private boolean etatCompte;

    @JsonProperty(access =JsonProperty.Access.READ_ONLY)
    @Temporal(TemporalType.DATE)
    private Date dateCreation;

    @DecimalMin(value = "0.0",message = "Veuillez specifier un solde superieure ou egale à zero")
    private BigDecimal soldeCompte;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotBlank(message = "Veuillez saisir un mot de passe")
    private String motDePasse;

    @NotNull(message = "Veuillez preciser le type du compte")
    @Enumerated(EnumType.STRING)
    private TypeCompte typeCompte;

    @ManyToOne
    @JoinColumn(name = "idUtilisateur")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Utilisateur utilisateur;

    @OneToMany(mappedBy = "compteVirement")
    private List<Virement> virementList = new ArrayList<>();

    @OneToMany(mappedBy = "compteRetrait")
    private List<Retrait> retraitList = new ArrayList<>();

    @OneToMany(mappedBy = "compteCredit")
    private List<Credits> creditsList = new ArrayList<>();

    /**
     * Constructeur de l'entité Compte
     * @param numeroCompte
     * @param etatCompte
     * @param dateCreation
     * @param soldeCompte
     * @param typeCompte
     * @param motDePasse
     */

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

    public List<Credits> getCreditsList() {
        return creditsList;
    }

    public void setCreditsList(List<Credits> creditsList) {
        this.creditsList = creditsList;
    }

    /**
     * @return Chaines de caracteres des infos sur Compte
     */

    @Override
    public String toString() {
        return "Compte{" +
                "numeroCompte=" + numeroCompte +
                ", etatCompte=" + etatCompte +
                ", dateCreation=" + dateCreation +
                ", soldeCompte=" + soldeCompte +
                ", motDePasse='" + motDePasse + '\'' +
                ", typeCompte=" + typeCompte +
                '}';
    }
}
