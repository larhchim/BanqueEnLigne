package fsr.banque.io.gestionBanque.service.compte;

import fsr.banque.io.gestionBanque.dao.CompteDAO;
import fsr.banque.io.gestionBanque.models.Compte;
import fsr.banque.io.gestionBanque.models.Utilisateur;
import fsr.banque.io.gestionBanque.service.utilisateur.UtilisateurContrat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CompteAdmin extends CompteAbstraction{

    private CompteDAO cptDAO;
    private UtilisateurContrat user;

    @Autowired
    public void setUser(UtilisateurContrat user) {
        this.user = user;
    }

    @Autowired
    public void setCptDAO(CompteDAO cptDAO) {
        this.cptDAO = cptDAO;
    }

    public CompteAdmin() {
        typeCompte = Compte.TypeCompte.ADMIN;
    }

    @Transactional
    @Override
    public Compte createAccount(Compte compte,Long userId) throws Exception {

        compte.setTypeCompte(typeCompte);
        compte.setEtatCompte(true);
        Utilisateur utilisateur = user.findTheUser(userId);
        compte.setUtilisateur(utilisateur);

        if ( compte.getSoldeCompte().longValue() < 0 ){
            throw new Exception("Montant specifié null et/ou negative");
        }

        return cptDAO.save(compte);

    }

}
