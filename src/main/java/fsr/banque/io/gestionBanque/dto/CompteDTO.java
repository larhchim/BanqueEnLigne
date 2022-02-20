package fsr.banque.io.gestionBanque.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class CompteDTO {

    @NotNull(message = "Veuillez specifier le numero de votre compte")
    @Positive(message = "Veuillez specifier le numero de votre compte")
    private Long numeroCompte;

    @NotBlank(message = "Veuillez saisir la phrase de confirmation")
    private String confirmation;

    @NotBlank(message = "Veuillez saisir un mot de passe")
    private String motDePasse;

    public CompteDTO(Long numeroCompte, String confirmation,String motDePasse) {

        this.numeroCompte = numeroCompte;
        this.confirmation = confirmation;
        this.motDePasse = motDePasse;

    }

    public CompteDTO(){

    }

    public Long getNumeroCompte() {
        return numeroCompte;
    }

    public void setNumeroCompte(Long numeroCompte) {
        this.numeroCompte = numeroCompte;
    }

    public String getConfirmation() {
        return confirmation;
    }

    public void setConfirmation(String confirmation) {
        this.confirmation = confirmation;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }
}
