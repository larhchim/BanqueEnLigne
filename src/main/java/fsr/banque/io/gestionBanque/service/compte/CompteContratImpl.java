package fsr.banque.io.gestionBanque.service.compte;

import fsr.banque.io.gestionBanque.dao.CompteDAO;
import fsr.banque.io.gestionBanque.models.Compte;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CompteContratImpl implements CompteContrat{

    private CompteDAO compteDAO;

    @Autowired
    public void setCompteDAO(CompteDAO compteDAO) {
        this.compteDAO = compteDAO;
    }

    @Transactional
    @Override
    public Page<Compte> findCompteParMotCle(String mc, Long aId, int page, int size) {
        return compteDAO.findCompteParMotCle(mc,aId, PageRequest.of(page, size));
    }

    @Transactional
    @Override
    public Compte updateAccount(Compte compte, Long aId) {
        compte.setNumeroCompte(aId);
        return compteDAO.save(compte);
    }

    @Transactional
    @Override
    public List<Compte> allAccounts() {
        return compteDAO.findAll();
    }

    @Transactional
    @Override
    public Compte findLeCompte(Long id) throws Exception {
        Compte compte = compteDAO.getById(id);
        if (!compte.isEtatCompte()){
            throw new Exception("Compte n'est plus disponible veuillez contacter votre agence");
        }
        return compte;
    }

}
