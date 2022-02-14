package fsr.banque.io.gestionBanque.service.utilisateur;

import fsr.banque.io.gestionBanque.dao.UtilisateurDAO;
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
    public Utilisateur saveUser(Utilisateur utilisateur) {
        return userDAO.save(utilisateur);
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
    public Utilisateur findTheUser(Long userId) {
        return userDAO.getById(userId);
    }

    @Transactional
    @Override
    public Utilisateur findUserByEmail(String email) {
        return userDAO.findUtilisateurByEmailUtilisateur(email);
    }

}
