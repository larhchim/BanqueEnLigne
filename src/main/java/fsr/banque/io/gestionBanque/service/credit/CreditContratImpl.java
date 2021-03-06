package fsr.banque.io.gestionBanque.service.credit;

import fsr.banque.io.gestionBanque.dao.CreditsDAO;
import fsr.banque.io.gestionBanque.exceptions.*;
import fsr.banque.io.gestionBanque.models.Compte;
import fsr.banque.io.gestionBanque.models.Credits;
import fsr.banque.io.gestionBanque.service.compte.CompteContrat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
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

    public boolean lessThan(Long one , Long two){
        return one <= two;
    }

    @Transactional
    @Override
    public Credits reglerUneMensualite(Long idCredit, BigDecimal montantApayer) throws InvalidAmountException, InvalidPayementException, InvalidBalanceException, InvalidCreditException, InvalidAccountException {

        Credits credit = findUnCredit(idCredit);

        Compte compte = credit.getCompteCredit();

        if (!compte.isEtatCompte()){
            throw new InvalidAccountException("Compte n'est plus disponible veuillez contacter votre agence");
        }

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

        if ( montantApayer.longValue() <= 0 ){
            throw new InvalidAmountException("Montant specifi?? null et/ou negative");
        }


        if (resteSolde.compareTo(zero.subtract(one)) == 1){

            if( !(montantReglee.compareTo(montantCredit) == 0) && !(montantReste.compareTo(zero) == 0) && lessThan(montantApayer.longValue(),montantReste.longValue())){

                if( montantApayer.compareTo(mensualite.subtract(one)) == 1 ) {

                    compte.setSoldeCompte(resteSolde);
                    compteContrat.updateAccount(compte,compte.getNumeroCompte());

                    montantReglee = montantReglee.add(montantApayer);
                    montantReste = montantReste.subtract(montantApayer);

                    if(montantReste.compareTo(BigDecimal.ZERO)==0){
                        nombreMensNouveau = BigDecimal.ZERO;
                    }else {
                        nombreMensNouveau = montantReste.multiply(BigDecimal.valueOf(nombreMensualite)).divide(montantReste.add(montantApayer), RoundingMode.UP);
                    }

                    credit.setNombreMensualitesCredit(nombreMensNouveau.longValue());
                    credit.setMontantReglee(montantReglee);
                    credit.setMontantReste(montantReste);

                }else {
                    throw new InvalidAmountException("Impossible de regler une somme inferieure ?? la mensualit?? d??clar??e pour votre credit");
                }



            }else {
                throw new InvalidPayementException("Credit deja pr?? pay?? et/ou vous mettez une somme sup??rieure ?? la somme credit restante");
            }


        }else {
            throw new InvalidBalanceException("Operation Impossible solde insuffisant");
        }


        return creditsDAO.save(credit);
    }

    @Override
    public Credits findUnCredit(Long id) throws InvalidCreditException {
        return creditsDAO.findById(id).orElseThrow(()->new InvalidCreditException("Credit Introuvable Veuillez saisir un numero credit valide"));
    }


}
