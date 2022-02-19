package fsr.banque.io.gestionBanque.service.credit;

import fsr.banque.io.gestionBanque.dao.CreditsDAO;
import fsr.banque.io.gestionBanque.exceptions.InvalidAccountException;
import fsr.banque.io.gestionBanque.exceptions.InvalidAmountException;
import fsr.banque.io.gestionBanque.exceptions.InvalidMensualiteException;
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
public class CreditConsommation extends CreditAbstraction{

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

    public CreditConsommation() {
        credit = Credits.Credit.CONSOMMATION;
    }

    @Transactional
    @Override
    public Credits createCredit(Credits credits, Compte compte) throws InvalidAccountException,InvalidAmountException,InvalidMensualiteException {

        credits.setCompteCredit(compte);
        credits.setDateCredit(new Date());
        credits.setTypeCredit(credit);
        credits.setMontantReglee(BigDecimal.ZERO);

        if(!compte.isEtatCompte()){
            throw new InvalidAccountException("Compte n'est plus disponible veuillez contacter votre agence");
        }else if ( credits.getMontantCredit().longValue() <= 0 || credits.getMontantCredit().longValue() < 100){
            throw new InvalidAmountException("Montant specifié null et/ou negative et/ou inférieure à 100");
        }else if ( credits.getNombreMensualitesCredit() <= 0 ){
            throw new InvalidMensualiteException("Veuillez saisir une mensualité superieure strictement à 0");
        }else {

            BigDecimal C = credits.getMontantCredit();

            BigDecimal TAEG = BigDecimal.valueOf(0.0306);
            System.out.println("*******TAEG="+TAEG);//

            BigDecimal N = BigDecimal.valueOf(credits.getNombreMensualitesCredit());

            if (N.longValue()>12)
                N=N.divide(BigDecimal.valueOf(12),RoundingMode.HALF_UP);

            System.out.println("*******N="+N);//

            TAEG = TAEG.divide(BigDecimal.valueOf(12));
            System.out.println("*******TAEG /BY 12="+TAEG);//

            BigDecimal M1 = C.multiply(TAEG);
            System.out.println("*******M1="+M1);//

            double M2 = 0;
            if (credits.getNombreMensualitesCredit() > 12){
                M2 = Math.pow(1+TAEG.doubleValue(),-(12*N.intValue()));
            }else {
                M2 = Math.pow(1+TAEG.doubleValue(),-(N.intValue()));
            }

            System.out.println("*******M2="+M2);//

            BigDecimal M3 = BigDecimal.ONE.subtract(BigDecimal.valueOf(M2));
            System.out.println("*******M3="+M3);//


            BigDecimal M = M1.divide(M3, RoundingMode.HALF_UP);

            System.out.println("*******M="+M);


            credits.setMensualite(M);
            credits.setMontantReste(M.multiply(BigDecimal.valueOf(credits.getNombreMensualitesCredit())));

            compte.setSoldeCompte(compte.getSoldeCompte().add(C));

            compteContrat.updateAccount(compte,compte.getNumeroCompte());
        }


        return creditsDAO.save(credits);

    }

}
