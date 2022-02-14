package fsr.banque.io.gestionBanque.service.retrait;

import fsr.banque.io.gestionBanque.dao.RetraitDAO;
import fsr.banque.io.gestionBanque.models.Compte;
import fsr.banque.io.gestionBanque.models.Retrait;
import fsr.banque.io.gestionBanque.service.compte.CompteContrat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
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
    public Retrait createNewRetrait(Retrait retrait) throws Exception {

        Compte compte = retrait.getCompteRetrait();

        if (compte.getSoldeCompte().subtract(retrait.getMontantRetrait()).compareTo(new BigDecimal(0.0)) == 1){
            compte.setSoldeCompte(compte.getSoldeCompte().subtract(retrait.getMontantRetrait()));
            compteContrat.updateAccount(compte,compte.getNumeroCompte());
            System.out.println(compte.getSoldeCompte());
        }
        else {
            throw new Exception("operation impossible solde insuffisant");
        }

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
