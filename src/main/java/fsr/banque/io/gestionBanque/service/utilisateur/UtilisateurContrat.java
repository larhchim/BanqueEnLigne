package fsr.banque.io.gestionBanque.service.utilisateur;

import fsr.banque.io.gestionBanque.dto.UtilisateurDTO;
import fsr.banque.io.gestionBanque.exceptions.InvalidEmailException;
import fsr.banque.io.gestionBanque.exceptions.InvalidGenderException;
import fsr.banque.io.gestionBanque.exceptions.InvalidUserException;
import fsr.banque.io.gestionBanque.models.Utilisateur;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UtilisateurContrat {

     Page<Utilisateur> findUserByValue(String mc , int page , int size);

     Utilisateur saveUser(UtilisateurDTO utilisateur) throws InvalidGenderException, InvalidEmailException, InvalidUserException;

     Utilisateur updateUser(Utilisateur utilisateur,Long userId);

     List<Utilisateur> allUsers();

     Utilisateur findTheUser(Long userId) throws InvalidUserException;

     Utilisateur findUserByEmail(String email) throws InvalidUserException;

     Utilisateur.Gender genderUserFix(String str) throws InvalidGenderException;

}
