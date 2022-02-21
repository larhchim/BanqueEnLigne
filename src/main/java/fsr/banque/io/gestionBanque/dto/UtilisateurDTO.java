package fsr.banque.io.gestionBanque.dto;

import fsr.banque.io.gestionBanque.models.Utilisateur;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class UtilisateurDTO {

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
    private char sexeUtilisateur;

    public UtilisateurDTO(@NotBlank(message = "*Champ nom Utilisateur est obligatoire*") @Pattern(regexp = "^[A-Za-z]*$", message = "nom utilisateur invalide") String nomUtilisateur, @NotBlank(message = "*Champ prenom Utilisateur est obligatoire*") @Pattern(regexp = "^[A-Za-z]*$", message = "prenom utilisateur invalide") String prenomUtilisateur, @NotBlank(message = "Entrez un email valide et coherent") @Pattern(regexp = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$", message = "Entrez un email valide") String emailUtilisateur, @NotNull(message = "Veuillez specifier le genre de l'utilisateur") char sexeUtilisateur) {
        this.nomUtilisateur = nomUtilisateur;
        this.prenomUtilisateur = prenomUtilisateur;
        this.emailUtilisateur = emailUtilisateur;
        this.sexeUtilisateur = sexeUtilisateur;
    }

    public UtilisateurDTO(){

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

    public char getSexeUtilisateur() {
        return sexeUtilisateur;
    }

    public void setSexeUtilisateur(char sexeUtilisateur) {
        this.sexeUtilisateur = sexeUtilisateur;
    }
}
