package fsr.banque.io.gestionBanque.service.utilisateur;

import fsr.banque.io.gestionBanque.dao.UtilisateurDAO;
import fsr.banque.io.gestionBanque.dto.UtilisateurDTO;
import fsr.banque.io.gestionBanque.exceptions.InvalidEmailException;
import fsr.banque.io.gestionBanque.exceptions.InvalidGenderException;
import fsr.banque.io.gestionBanque.exceptions.InvalidUserException;
import fsr.banque.io.gestionBanque.models.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UtilisateurContratImpl implements UtilisateurContrat{

    private UtilisateurDAO userDAO;

    @Autowired
    public void setUserDAO(UtilisateurDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Transactional
    @Override
    public Page<Utilisateur> findUserByValue(String mc, int page, int size) {
        return userDAO.FindUtilisateurParMotCle(mc, PageRequest.of(page, size));
    }

    @Transactional
    @Override
    public Utilisateur saveUser(UtilisateurDTO utilisateur) throws InvalidGenderException, InvalidEmailException, InvalidUserException {

        if (findUserByEmail(utilisateur.getEmailUtilisateur())!=null){
            throw new InvalidEmailException("Email deja existe dans la liste des utilisateurs merci de specifier un autre email");
        }

        Utilisateur user = new Utilisateur();

        user.setEmailUtilisateur(utilisateur.getEmailUtilisateur());
        user.setNomUtilisateur(utilisateur.getNomUtilisateur());
        user.setPrenomUtilisateur(utilisateur.getPrenomUtilisateur());
        user.setSexeUtilisateur(genderUserFix(String.valueOf(utilisateur.getSexeUtilisateur())));

        return userDAO.save(user);
    }

    @Transactional
    @Override
    public Utilisateur updateUser(Utilisateur utilisateur, Long userId) {

        utilisateur.setIdUtilisateur(userId);
        return userDAO.save(utilisateur);

    }

    @Transactional
    @Override
    public List<Utilisateur> allUsers() {
        return userDAO.findAll();
    }

    @Transactional
    @Override
    public Utilisateur findTheUser(Long userId) throws InvalidUserException {
        return userDAO.findById(userId).orElseThrow(() -> new InvalidUserException("Utilisateur introuvable avec l'id "+userId+" Veuillez inserez un numero valide"));
    }

    @Transactional
    @Override
    public Utilisateur findUserByEmail(String email) throws InvalidUserException {
        Utilisateur utilisateur = userDAO.findUtilisateurByEmailUtilisateur(email);
        if (utilisateur == null) throw new InvalidUserException("Utilisateur introuvable avec "+email+" Veuillez saisir un email valide");
        return utilisateur;
    }

    @Override
    public Utilisateur.Gender genderUserFix(String str) throws InvalidGenderException {

        switch (str){

            case "M" : return Utilisateur.Gender.MALE;
            case "F" : return Utilisateur.Gender.FEMALE;
            case "U" : return Utilisateur.Gender.UNKNOWN;
            default: throw new InvalidGenderException("Veuillez sp??cifier un genre Utilisateur valide en ins??rant un Caract??re en majiscule ex: M(MALE) , F(FEMALE) , U(UKNOWN)");

        }
    }

}
