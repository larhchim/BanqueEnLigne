package fsr.banque.io.gestionBanque.service.credit;

import fsr.banque.io.gestionBanque.dao.CreditsDAO;
import fsr.banque.io.gestionBanque.models.Compte;
import fsr.banque.io.gestionBanque.models.Credits;
import fsr.banque.io.gestionBanque.service.compte.CompteContrat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;

@Service
public class CreditImmobilier extends CreditAbstraction{

    private CreditsDAO creditsDAO;
    private CompteContrat compteContrat;

    @Autowired
    public void setCompteContrat(CompteContrat compteContrat) {
        this.compteContrat = compteContrat;
    }

    @Autowired
    public void setCreditsDAO(CreditsDAO creditsDAO) {
        this.creditsDAO = creditsDAO;
    }

    public CreditImmobilier() {
        credit = Credits.Credit.IMMOBILIER;
    }

    @Transactional
    @Override
    public Credits createCredit(Credits credits, Compte compte) throws Exception {

        credits.setCompteCredit(compte);
        credits.setDateCredit(new Date());
        credits.setTypeCredit(credit);
        credits.setMontantReglee(BigDecimal.ZERO);

        if(!compte.isEtatCompte()){
            throw new Exception("Compte n'est plus disponible veuillez contacter votre agence");
        }else if ( credits.getMontantCredit().longValue() <= 0 ){
            throw new Exception("Montant specifié null et/ou negative");
        }else {

            BigDecimal C = credits.getMontantCredit();
            BigDecimal N = BigDecimal.valueOf(credits.getNombreMensualitesCredit());
            Long nombre = N.longValue();



            BigDecimal TAUX = BigDecimal.valueOf(0.005);
            BigDecimal M1 = BigDecimal.valueOf(Math.pow(TAUX.doubleValue() +1,nombre));
            BigDecimal M2 = TAUX.multiply(M1);
            M2 = C.multiply(M2);
            BigDecimal M3 = M1.subtract(BigDecimal.ONE);


            BigDecimal M = M2.divide(M3, RoundingMode.HALF_UP);

            credits.setMensualite(M);
            credits.setMontantReste(M.multiply(BigDecimal.valueOf(credits.getNombreMensualitesCredit())));

            compte.setSoldeCompte(compte.getSoldeCompte().add(C));

            compteContrat.updateAccount(compte,compte.getNumeroCompte());
        }


        return creditsDAO.save(credits);

    }

}
