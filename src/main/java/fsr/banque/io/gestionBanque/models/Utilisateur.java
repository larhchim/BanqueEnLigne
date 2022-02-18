package fsr.banque.io.gestionBanque.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Entité JPA Utilisateur
 */

@Entity
@Table(name = "Utilisateur")
public class Utilisateur implements Serializable {

    public enum Gender {
        MALE,FEMALE,UNKNOWN;
    }

    @Id
    @TableGenerator(name = "USER_GEN",
            initialValue = 95824130)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "USER_GEN")
    private Long idUtilisateur;

    @NotBlank(message = "*Champ nom Utilisateur est obligatoire*")
    @Pattern(regexp="^[A-Za-z]*$",message = "nom utilisateur invalide")
    private String nomUtilisateur;

    @NotBlank(message = "*Champ prenom Utilisateur est obligatoire*")
    @Pattern(regexp="^[A-Za-z]*$",message = "prenom utilisateur invalide")
    private String prenomUtilisateur;

    @Column(unique = true,nullable = false)
    @NotBlank(message = "Entrez un email valide et coherent")
    @Pattern(regexp = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$" , message = "Entrez un email valide")
    private String emailUtilisateur;

    @NotNull(message = "Veuillez specifier le genre de l'utilisateur")
    @Enumerated(EnumType.STRING)
    private Gender sexeUtilisateur;

    @OneToMany(mappedBy = "utilisateur")
    private List<Compte> compteList = new ArrayList<>();

    /**
     * Constructeur Utilisateur
     * @param idUtilisateur
     * @param nomUtilisateur
     * @param prenomUtilisateur
     * @param emailUtilisateur
     * @param sexeUtilisateur
     */

    public Utilisateur(Long idUtilisateur, String nomUtilisateur, String prenomUtilisateur, String emailUtilisateur, Gender sexeUtilisateur) {
        this.idUtilisateur = idUtilisateur;
        this.nomUtilisateur = nomUtilisateur;
        this.prenomUtilisateur = prenomUtilisateur;
        this.emailUtilisateur = emailUtilisateur;
        this.sexeUtilisateur = sexeUtilisateur;
    }

    public Utilisateur(){

    }

    public Long getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(Long idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public String getNomUtilisateur() {
        return nomUtilisateur;
    }

    public void setNomUtilisateur(String nomUtilisateur) {
        this.nomUtilisateur = nomUtilisateur;
    }

    public String getPrenomUtilisateur() {
        return prenomUtilisateur;
    }

    public void setPrenomUtilisateur(String prenomUtilisateur) {
        this.prenomUtilisateur = prenomUtilisateur;
    }

    public String getEmailUtilisateur() {
        return emailUtilisateur;
    }

    public void setEmailUtilisateur(String emailUtilisateur) {
        this.emailUtilisateur = emailUtilisateur;
    }

    public Gender getSexeUtilisateur() {
        return sexeUtilisateur;
    }

    public void setSexeUtilisateur(Gender sexeUtilisateur) {
        this.sexeUtilisateur = sexeUtilisateur;
    }

    public List<Compte> getCompteList() {
        return compteList;
    }

    public void setCompteList(List<Compte> compteList) {
        this.compteList = compteList;
    }
}

