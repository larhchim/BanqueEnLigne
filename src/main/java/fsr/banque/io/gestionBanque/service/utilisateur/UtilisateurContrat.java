package fsr.banque.io.gestionBanque.service.utilisateur;

import fsr.banque.io.gestionBanque.dto.UtilisateurDTO;
import fsr.banque.io.gestionBanque.exceptions.InvalidGenderException;
import fsr.banque.io.gestionBanque.models.Utilisateur;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UtilisateurContrat {

     Page<Utilisateur> findUserByValue(String mc , int page , int size);

     Utilisateur saveUser(UtilisateurDTO utilisateur) throws InvalidGenderException;

     Utilisateur updateUser(Utilisateur utilisateur,Long userId);

     List<Utilisateur> allUsers();

     Utilisateur findTheUser(Long userId);

     Utilisateur findUserByEmail(String email);

     Utilisateur.Gender genderUserFix(String str) throws InvalidGenderException;

}
