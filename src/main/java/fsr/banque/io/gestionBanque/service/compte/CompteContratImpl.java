package fsr.banque.io.gestionBanque.service.compte;

import fsr.banque.io.gestionBanque.dao.CompteDAO;
import fsr.banque.io.gestionBanque.exceptions.InvalidAccountException;
import fsr.banque.io.gestionBanque.models.Compte;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CompteContratImpl implements CompteContrat{

    private CompteDAO compteDAO;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public void setbCryptPasswordEncoder(BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

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
    public Compte findLeCompte(Long id) throws InvalidAccountException {

        Compte compte = compteDAO.findById(id).orElseThrow(() -> new InvalidAccountException("Compte indisponible"));

        if (!compte.isEtatCompte()){
            throw new InvalidAccountException("Compte n'est plus disponible veuillez contacter votre agence");
        }

        return compte;
    }

    @Transactional
    @Override
    public Compte disactivateAccount(Long idCompte, String motDePasse, String confirmation) throws InvalidAccountException {

        Compte compte = findLeCompte(idCompte);

        return null;
    }

}
