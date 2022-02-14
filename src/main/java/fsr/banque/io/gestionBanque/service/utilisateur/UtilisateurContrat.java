package fsr.banque.io.gestionBanque.service.utilisateur;

import fsr.banque.io.gestionBanque.models.Utilisateur;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UtilisateurContrat {

     Page<Utilisateur> findUserByValue(String mc , int page , int size);

     Utilisateur saveUser(Utilisateur utilisateur);

     Utilisateur updateUser(Utilisateur utilisateur,Long userId);

     List<Utilisateur> allUsers();

     Utilisateur findTheUser(Long userId);

     Utilisateur findUserByEmail(String email);

}
