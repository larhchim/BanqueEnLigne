package fsr.banque.io.gestionBanque.dto;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class VirementDTO {

    @NotNull(message = "Veuillez specifier le numero compte recepteur")
    private Long numeroCompteRecepteur;

    @NotNull(message = "Veuillez specifier le numero compte emetteur")
    private Long numeroCompteEmetteur;

    @DecimalMin(value = "100.0",message = "Veuillez specifier un Montant superieure ou egale à 100")
    private BigDecimal montant;

    public VirementDTO(@NotNull(message = "Veuillez specifier le numero compte recepteur") Long numeroCompteRecepteur, @NotNull(message = "Veuillez specifier le numero compte emetteur") Long numeroCompteEmetteur, @DecimalMin(value = "0.0", message = "Veuillez specifier un Montant superieure ou egale à zero") BigDecimal montant) {
        this.numeroCompteRecepteur = numeroCompteRecepteur;
        this.numeroCompteEmetteur = numeroCompteEmetteur;
        this.montant = montant;
    }

    public VirementDTO(){

    }

    public Long getNumeroCompteRecepteur() {
        return numeroCompteRecepteur;
    }

    public void setNumeroCompteRecepteur(Long numeroCompteRecepteur) {
        this.numeroCompteRecepteur = numeroCompteRecepteur;
    }

    public Long getNumeroCompteEmetteur() {
        return numeroCompteEmetteur;
    }

    public void setNumeroCompteEmetteur(Long numeroCompteEmetteur) {
        this.numeroCompteEmetteur = numeroCompteEmetteur;
    }

    public BigDecimal getMontant() {
        return montant;
    }

    public void setMontant(BigDecimal montant) {
        this.montant = montant;
    }

}
