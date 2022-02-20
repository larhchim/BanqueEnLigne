package fsr.banque.io.gestionBanque.dto;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

public class MensualiteDTO {

    @NotNull(message = "Veuillez specifier le numero de votre Credit")
    @Positive(message = "Veuillez specifier le numero de votre Credit")
    private Long idCredit;

    @DecimalMin(value = "0.0",message = "Veuillez specifier un Montant superieure ou egale à 0")
    private BigDecimal mensualite;

    public MensualiteDTO(Long idCredit, BigDecimal mensualite) {
        this.idCredit = idCredit;
        this.mensualite = mensualite;
    }

    public MensualiteDTO(){

    }

    public Long getIdCredit() {
        return idCredit;
    }

    public void setIdCredit(Long idCredit) {
        this.idCredit = idCredit;
    }

    public BigDecimal getMensualite() {
        return mensualite;
    }

    public void setMensualite(BigDecimal mensualite) {
        this.mensualite = mensualite;
    }
}
