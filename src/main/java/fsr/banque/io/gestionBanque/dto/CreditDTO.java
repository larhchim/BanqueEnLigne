package fsr.banque.io.gestionBanque.dto;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

public class CreditDTO {

    @NotNull(message = "Veuillez specifier le numero compte recepteur")
    @Positive(message = "Veuillez specifier le numero compte recepteur")
    private Long numeroCompte;


    @NotNull(message = "Veuillez specifier le nombre de mensualités du credit")
    @Positive(message = "Veuillez specifier le nombre de mensualités du credit")
    private Long nombreMensualites;

    @DecimalMin(value = "100.0",message = "Veuillez specifier un Montant superieure ou egale à 100")
    private BigDecimal montantCredit;

    public CreditDTO(Long numeroCompte, Long nombreMensualites, BigDecimal montantCredit) {
        this.numeroCompte = numeroCompte;
        this.nombreMensualites = nombreMensualites;
        this.montantCredit = montantCredit;
    }

    public CreditDTO(){

    }

    public Long getNumeroCompte() {
        return numeroCompte;
    }

    public void setNumeroCompte(Long numeroCompte) {
        this.numeroCompte = numeroCompte;
    }

    public Long getNombreMensualites() {
        return nombreMensualites;
    }

    public void setNombreMensualites(Long nombreMensualites) {
        this.nombreMensualites = nombreMensualites;
    }

    public BigDecimal getMontantCredit() {
        return montantCredit;
    }

    public void setMontantCredit(BigDecimal montantCredit) {
        this.montantCredit = montantCredit;
    }

}
