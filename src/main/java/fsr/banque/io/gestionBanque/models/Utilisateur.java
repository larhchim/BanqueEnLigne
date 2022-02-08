package fsr.banque.io.gestionBanque.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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

    private String nomUtilisateur;

    private String prenomUtilisateur;

    private String emailUtilisateur;

    @Enumerated(EnumType.STRING)
    private Gender sexeUtilisateur;

    @OneToMany(mappedBy = "utilisateur")
    private List<Compte> compteList = new ArrayList<>();

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

