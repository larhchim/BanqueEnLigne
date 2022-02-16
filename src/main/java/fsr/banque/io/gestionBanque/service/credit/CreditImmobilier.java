package fsr.banque.io.gestionBanque.service.credit;

import fsr.banque.io.gestionBanque.dao.CreditsDAO;
import fsr.banque.io.gestionBanque.models.Compte;
import fsr.banque.io.gestionBanque.models.Credits;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;

@Service
public class CreditImmobilier extends CreditAbstraction{

    private CreditsDAO creditsDAO;

    @Autowired
    public void setCreditsDAO(CreditsDAO creditsDAO) {
        this.creditsDAO = creditsDAO;
    }

    public CreditImmobilier() {
        credit = Credits.Credit.IMMOBILIER;
    }

    @Override
    Credits createCredit(Credits credits, Compte compte) {

        credits.setCompteCredit(compte);
        credits.setDateCredit(new Date());
        credits.setTypeCredit(credit);
        credits.setMontantReglee(BigDecimal.ZERO);

        BigDecimal C = credits.getMontantCredit();
        BigDecimal TASS = BigDecimal.ZERO, TAEG = BigDecimal.ZERO;
        BigDecimal N = BigDecimal.valueOf(credits.getNombreMensualitesCredit()).divide(BigDecimal.valueOf(12));
        Long nombre = N.longValue();

        if ( nombre < 10 ){
            TAEG = BigDecimal.valueOf(0.007);
        }else if( nombre >= 10 && nombre < 15){
            TAEG = BigDecimal.valueOf(0.008);
        }else if( nombre >= 15 && nombre < 20 ){
            TAEG = BigDecimal.valueOf(0.0095);
        }else if( nombre >= 20 && nombre <25){
            TAEG = BigDecimal.valueOf(0.011);
        }else {
            TAEG = BigDecimal.valueOf(0.0125);
        }

        if ( nombre < 35 ){
            TASS = BigDecimal.valueOf(0.44);
        }else if( nombre >= 35 && nombre < 45 ){
            TASS = BigDecimal.valueOf(0.66);
        }else if( nombre >= 45 && nombre < 55){
            TASS = BigDecimal.valueOf(0.81);
        }else {
            TASS = BigDecimal.valueOf(0.96);
        }

        BigDecimal TAUX = TAEG.add(TASS);

        BigDecimal M1 = C.multiply(TAUX).divide(BigDecimal.valueOf(12));
        BigDecimal M2 = BigDecimal.ONE.add(TAUX.divide(BigDecimal.valueOf(12)));
        BigDecimal M3 = BigDecimal.ONE.subtract( M2.pow(-nombre.intValue()) );

        BigDecimal M = M1.divide(M3);

        credits.setMensualite(M);
        credits.setMontantReste(M.multiply(BigDecimal.valueOf(credits.getNombreMensualitesCredit())));

        return creditsDAO.save(credits);

    }

}
