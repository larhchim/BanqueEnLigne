package fsr.banque.io.gestionBanque.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import fsr.banque.io.gestionBanque.models.Compte;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

public class RetraitDTO {

    @DecimalMin(value = "100.0",message = "Veuillez specifier un Montant Retrait superieure ou egale à 100")
    private BigDecimal montantRetrait;

    @NotNull(message = "Veuillez specifier le numero de compte du retrait")
    @Positive(message = "Veuillez specifier le numero de compte du retrait")
    private Long idCompteRetrait;

    public RetraitDTO(@DecimalMin(value = "100.0", message = "Veuillez specifier un Montant Retrait superieure ou egale à 100") BigDecimal montantRetrait, @NotNull(message = "Veuillez specifier le numero de compte du retrait") @Positive(message = "Veuillez specifier le numero de compte du retrait") Long idCompteRetrait) {
        this.montantRetrait = montantRetrait;
        this.idCompteRetrait = idCompteRetrait;
    }

    public RetraitDTO(){

    }

    public BigDecimal getMontantRetrait() {
        return montantRetrait;
    }

    public void setMontantRetrait(BigDecimal montantRetrait) {
        this.montantRetrait = montantRetrait;
    }

    public Long getIdCompteRetrait() {
        return idCompteRetrait;
    }

    public void setIdCompteRetrait(Long idCompteRetrait) {
        this.idCompteRetrait = idCompteRetrait;
    }

}
