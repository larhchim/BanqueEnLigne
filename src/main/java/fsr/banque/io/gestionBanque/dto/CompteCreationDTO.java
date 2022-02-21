package fsr.banque.io.gestionBanque.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;

public class CompteCreationDTO {

    @DecimalMin(value = "0.0",message = "Veuillez specifier un solde superieure ou egale à zero")
    private BigDecimal soldeCompte;

    @NotBlank(message = "Veuillez saisir un mot de passe")
    private String motDePasse;

    @NotBlank(message = "Veuillez resaisir le mot de passe correctement")
    private String reMotDePasse;

    @NotBlank(message = "Entrez un email valide et coherent")
    @Pattern(regexp = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$" , message = "Entrez un email valide")
    private String emailUtilisateur;

    @NotBlank(message = "Entrez le type du compte ex: 'COURANT' 'EPARGNE' 'ADMIN' ")
    private String typecompte;

    public CompteCreationDTO(@DecimalMin(value = "0.0", message = "Veuillez specifier un solde superieure ou egale à zero") BigDecimal soldeCompte, @NotBlank(message = "Veuillez saisir un mot de passe") String motDePasse, @NotBlank(message = "Veuillez resaisir le mot de passe correctement") String reMotDePasse, @NotBlank(message = "Entrez un email valide et coherent") @Pattern(regexp = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$", message = "Entrez un email valide") String emailUtilisateur, @NotBlank(message = "Entrez le type du compte ex: 'COURANT' 'EPARGNE' 'ADMIN' ") String typecompte) {
        this.soldeCompte = soldeCompte;
        this.motDePasse = motDePasse;
        this.reMotDePasse = reMotDePasse;
        this.emailUtilisateur = emailUtilisateur;
        this.typecompte = typecompte;
    }

    public CompteCreationDTO(){

    }

    public BigDecimal getSoldeCompte() {
        return soldeCompte;
    }

    public void setSoldeCompte(BigDecimal soldeCompte) {
        this.soldeCompte = soldeCompte;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public String getReMotDePasse() {
        return reMotDePasse;
    }

    public void setReMotDePasse(String reMotDePasse) {
        this.reMotDePasse = reMotDePasse;
    }

    public String getEmailUtilisateur() {
        return emailUtilisateur;
    }

    public void setEmailUtilisateur(String emailUtilisateur) {
        this.emailUtilisateur = emailUtilisateur;
    }

    public String getTypecompte() {
        return typecompte;
    }

    public void setTypecompte(String typecompte) {
        this.typecompte = typecompte;
    }

}
