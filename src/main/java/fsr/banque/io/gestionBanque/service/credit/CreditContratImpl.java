package fsr.banque.io.gestionBanque.service.credit;

import fsr.banque.io.gestionBanque.dao.CreditsDAO;
import fsr.banque.io.gestionBanque.models.Compte;
import fsr.banque.io.gestionBanque.models.Credits;
import fsr.banque.io.gestionBanque.service.compte.CompteContrat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

public class CreditContratImpl implements CreditContrat{

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

    @Transactional
    @Override
    public Credits updateCredit(Credits credits) {
        return creditsDAO.save(credits);
    }

    @Transactional
    @Override
    public List<Credits> allCredits() {
        return creditsDAO.findAll();
    }

    @Transactional
    @Override
    public Page<Credits> rechercheCreditParMotCle(Long mc, int page, int size) {
        return creditsDAO.findCreditsByMotCle(mc, PageRequest.of(page, size));
    }

    @Transactional
    @Override
    public Credits reglerUneMensualite(Long idCredit, BigDecimal montantApayer) throws Exception {

        Credits credit = findUnCredit(idCredit);

        Compte compte = credit.getCompteCredit();
        BigDecimal soldeCompte = compte.getSoldeCompte();
        BigDecimal resteSolde = soldeCompte.subtract(montantApayer);
        BigDecimal zero = new BigDecimal(0);
        BigDecimal one = new BigDecimal(1);

        BigDecimal montantReglee = credit.getMontantReglee();
        BigDecimal montantReste = credit.getMontantReste();
        BigDecimal montantCredit = credit.getMontantCredit();
        Long nombreMensualite = credit.getNombreMensualitesCredit();

        BigDecimal mensualite = credit.getMensualite();

        BigDecimal nombreMensNouveau;


        if (resteSolde.compareTo(zero.subtract(one)) == 1){

            if( !(montantReglee.compareTo(montantCredit) == 0) && !(montantReste.compareTo(zero) == 0) ){

                if( montantApayer.compareTo(mensualite.subtract(one)) == 1 ) {

                    compte.setSoldeCompte(resteSolde);
                    compteContrat.updateAccount(compte,compte.getNumeroCompte());

                    montantReglee = montantReglee.add(montantApayer);
                    montantReste = montantReste.subtract(montantApayer);

                    nombreMensNouveau = montantReste.multiply(BigDecimal.valueOf(nombreMensualite)).divide(montantReste);

                    credit.setNombreMensualitesCredit(nombreMensNouveau.longValue());
                    credit.setMontantReglee(montantReglee);
                    credit.setMontantReste(montantReste);

                }else {
                    throw new Exception("Impossible de regler une somme inferieure à la mensualité déclarée pour votre credit");
                }



            }else {
                throw new Exception("Credit deja pré payé merci pour votre fidélité");
            }


        }else {
            throw new Exception("Operation Impossible solde insuffisant");
        }


        return creditsDAO.save(credit);
    }

    @Override
    public Credits findUnCredit(Long id) {
        return creditsDAO.getById(id);
    }


}
