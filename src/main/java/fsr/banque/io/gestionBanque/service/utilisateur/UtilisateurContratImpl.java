package fsr.banque.io.gestionBanque.service.utilisateur;

import fsr.banque.io.gestionBanque.models.Utilisateur;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UtilisateurContratImpl implements UtilisateurContrat{

    @Override
    public Page<Utilisateur> findUserByValue(String mc, int page, int size) {
        return null;
    }

    @Override
    public Utilisateur saveUser(Utilisateur utilisateur) {
        return null;
    }

    @Override
    public Utilisateur updateUser(Utilisateur utilisateur, Long userId) {
        return null;
    }

    @Override
    public List<Utilisateur> allUsers() {
        return null;
    }

}
