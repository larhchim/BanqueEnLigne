package fsr.banque.io.gestionBanque.service.virement;

import fsr.banque.io.gestionBanque.dao.VirementDAO;
import fsr.banque.io.gestionBanque.exceptions.InvalidAccountException;
import fsr.banque.io.gestionBanque.exceptions.InvalidAmountException;
import fsr.banque.io.gestionBanque.exceptions.InvalidBalanceException;
import fsr.banque.io.gestionBanque.models.Compte;
import fsr.banque.io.gestionBanque.models.Virement;
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
public class VirementContratImpl implements VirementContrat{

    private VirementDAO virementDAO;
    private CompteContrat compteContrat;

    @Autowired
    public void setCompteContrat(CompteContrat compteContrat) {
        this.compteContrat = compteContrat;
    }

    @Autowired
    public void setVirementDAO(VirementDAO virementDAO) {
        this.virementDAO = virementDAO;
    }

    @Transactional
    @Override
    public Page<Virement> findVirementByMotCle(Long mc, int page, int size) {
        return virementDAO.findVirementByMotCle(mc, PageRequest.of(page,size));
    }

    @Transactional
    @Override
    public Virement createNewVirement(Virement virement, Long emetteur, Long recepteur) throws InvalidAccountException,InvalidAmountException,InvalidBalanceException {

        if(emetteur.compareTo(recepteur)==0) {
            throw new InvalidAccountException("Virement Impossible Compte emetteur et Compte Recepteur ont le meme numero de Compte Veuillez specifier un numero de Compte different");
        }

        Compte compteEmetteur = compteContrat.findLeCompte(emetteur);
        Compte compteRecepteur = compteContrat.findLeCompte(recepteur);
        Virement transaction = null;

        if(!compteEmetteur.isEtatCompte()){
            throw new InvalidAccountException("Votre Compte n'est plus disponible pour l'operation veuillez contacter votre agence");
        }else if(!compteRecepteur.isEtatCompte()){
            throw new InvalidAccountException("Compte Destinataire n'est plus disponible pour l'operation veuillez contacter votre agence");
        }else{

            virement.setNumeroCompteRecepteur(recepteur);
            virement.setCompteVirement(compteEmetteur);
            virement.setDateVirement(new Date());

            if ( virement.getMontant().longValue() <=0 || virement.getMontant().longValue()< 100){
                throw new InvalidAmountException("Montant specifié null et/ou negative et/ou inférieure à 100");
            }

            if ( compteEmetteur.getSoldeCompte().compareTo( virement.getMontant().subtract(BigDecimal.ONE) ) == 1 ){

                compteEmetteur.setSoldeCompte(compteEmetteur.getSoldeCompte().subtract(virement.getMontant()));
                compteContrat.updateAccount(compteEmetteur,compteEmetteur.getNumeroCompte());

                compteRecepteur.setSoldeCompte(compteRecepteur.getSoldeCompte().add(virement.getMontant()));
                compteContrat.updateAccount(compteRecepteur,compteRecepteur.getNumeroCompte());

                transaction = virementDAO.save(virement);

            }else {
                throw new InvalidBalanceException("Impossible d'effectuer le virement solde insuffisant");
            }

        }

        return transaction;
    }

    @Transactional
    @Override
    public List<Virement> allVirements() {
        return virementDAO.findAll();
    }

}
