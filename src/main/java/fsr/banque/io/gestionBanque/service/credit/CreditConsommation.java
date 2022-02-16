package fsr.banque.io.gestionBanque.service.credit;

import fsr.banque.io.gestionBanque.dao.CreditsDAO;
import fsr.banque.io.gestionBanque.models.Compte;
import fsr.banque.io.gestionBanque.models.Credits;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;

@Service
public class CreditConsommation extends CreditAbstraction{

    private CreditsDAO creditsDAO;

    @Autowired
    public void setCreditsDAO(CreditsDAO creditsDAO) {
        this.creditsDAO = creditsDAO;
    }

    public CreditConsommation() {
        credit = Credits.Credit.CONSOMMATION;
    }

    @Override
    Credits createCredit(Credits credits, Compte compte) {

        credits.setCompteCredit(compte);
        credits.setDateCredit(new Date());
        credits.setTypeCredit(credit);
        credits.setMontantReglee(BigDecimal.ZERO);

        BigDecimal C = credits.getMontantCredit();
        BigDecimal TAEG = BigDecimal.valueOf(0.0306);
        BigDecimal N = BigDecimal.valueOf(credits.getNombreMensualitesCredit()).divide(BigDecimal.valueOf(12));
        TAEG = TAEG.divide(BigDecimal.valueOf(12));
        BigDecimal M1 = C.multiply(TAEG);
        BigDecimal M2 = TAEG.pow(-(12*N.intValue())).add(BigDecimal.ONE);
        BigDecimal M3 = BigDecimal.ONE.subtract(M2);

        BigDecimal M = M1.divide(M3);

        credits.setMensualite(M);
        credits.setMontantReste(M.multiply(BigDecimal.valueOf(credits.getNombreMensualitesCredit())));

        return creditsDAO.save(credits);

    }

}
