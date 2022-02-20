package fsr.banque.io.gestionBanque.service.compte;

import fsr.banque.io.gestionBanque.dao.CompteDAO;
import fsr.banque.io.gestionBanque.exceptions.*;
import fsr.banque.io.gestionBanque.models.Compte;
import fsr.banque.io.gestionBanque.service.security.HashPasswordContrat;
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
    private HashPasswordContrat hashPasswordContrat;

    @Autowired
    public void setHashPasswordContrat(HashPasswordContrat hashPasswordContrat) {
        this.hashPasswordContrat = hashPasswordContrat;
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
    public Compte disactivateAccount(Long idCompte, String motDePasse, String confirmation) throws InvalidAccountException, InvalidPasswordException, InvalidConfirmationException, InvalidAdminDeletionException, InvalidHashPasswordException {

        Compte compte = findLeCompte(idCompte);
        String deletion = "Je confirme la suppression de mon compte";

        System.out.println("****db**** Le mot de passe dans data base");
        System.out.println(compte.getMotDePasse());
        System.out.println("****received****");
        System.out.println("le mot de passe recu est :"+motDePasse);
        System.out.println("le mot de passe recu crypté:***");
        System.out.println(hashPasswordContrat.hashPassword(motDePasse));


        if ( !compte.getMotDePasse().equals(hashPasswordContrat.hashPassword(motDePasse)) ){
            throw new InvalidPasswordException("Mot de passe incorrect Veuillez saisir votre mot de passe");
        }else if( !deletion.equals(confirmation) ){
            throw new InvalidConfirmationException("La phrase de confirmation est erronée Veuillez taper: 'Je confirme la suppression de mon compte' en respectant les miniscules et majiscules ");
        }else if( compte.getTypeCompte().equals(Compte.TypeCompte.ADMIN) ){
            throw new InvalidAdminDeletionException("Vous ne pouvez pas supprimer un compte Admin");
        }else {
            compte.setEtatCompte(false);
        }

        return updateAccount(compte,compte.getNumeroCompte());
    }

}
