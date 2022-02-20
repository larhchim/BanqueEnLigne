package fsr.banque.io.gestionBanque.service.retrait;

import fsr.banque.io.gestionBanque.dao.RetraitDAO;
import fsr.banque.io.gestionBanque.exceptions.InvalidAccountException;
import fsr.banque.io.gestionBanque.exceptions.InvalidAmountException;
import fsr.banque.io.gestionBanque.exceptions.InvalidBalanceException;
import fsr.banque.io.gestionBanque.models.Compte;
import fsr.banque.io.gestionBanque.models.Retrait;
import fsr.banque.io.gestionBanque.service.compte.CompteContrat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
public class RetraitContratImpl implements RetraitContrat {

    private RetraitDAO retraitDAO;
    private CompteContrat compteContrat;

    @Autowired
    public void setCompteContrat(CompteContrat compteContrat) {
        this.compteContrat = compteContrat;
    }

    @Autowired
    public void setRetraitDAO(RetraitDAO retraitDAO) {
        this.retraitDAO = retraitDAO;
    }

    @Transactional
    @Override
    public Retrait createNewRetrait(Retrait retrait) throws InvalidAccountException, InvalidAmountException,InvalidBalanceException{

        Compte compte = retrait.getCompteRetrait();

        if(!retrait.getCompteRetrait().isEtatCompte()){
            throw new InvalidAccountException("Compte n'est plus disponible veuillez contacter votre agence");
        }else if ( retrait.getMontantRetrait().longValue() <=0 || retrait.getMontantRetrait().longValue() < 100 ){
            throw new InvalidAmountException("Montant specifié null et/ou negative et/ou Montant retrait inferieure à 100");
        }else if( retrait.getMontantRetrait().doubleValue() % 100 != 0 ){
            throw new InvalidAmountException("Montant specifié ne peut pas etre retiré du GAB(Guichet automatique bancaire) veuillez specifier un montant sans centimes ex:100$,1000$...");
        }else {

            if (compte.getSoldeCompte().subtract(retrait.getMontantRetrait()).compareTo(BigDecimal.ZERO) == 1){

                compte.setSoldeCompte(compte.getSoldeCompte().subtract(retrait.getMontantRetrait()));
                compteContrat.updateAccount(compte,compte.getNumeroCompte());
                System.out.println(compte.getSoldeCompte());

            }
            else {
                throw new InvalidBalanceException("operation impossible solde insuffisant");
            }

        }

        retrait.setDateRetrait(new Date());


        return retraitDAO.save(retrait);

    }

    @Transactional
    @Override
    public List<Retrait> allRetraits() {
        return retraitDAO.findAll();
    }

    @Transactional
    @Override
    public Page<Retrait> rechercheParMotCle(Long mc, int page, int size) {
        return retraitDAO.Search(mc, PageRequest.of(page, size));
    }

}
