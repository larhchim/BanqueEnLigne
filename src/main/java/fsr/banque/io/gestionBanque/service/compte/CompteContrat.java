package fsr.banque.io.gestionBanque.service.compte;

import fsr.banque.io.gestionBanque.exceptions.InvalidAccountException;
import fsr.banque.io.gestionBanque.exceptions.InvalidAdminDeletionException;
import fsr.banque.io.gestionBanque.exceptions.InvalidConfirmationException;
import fsr.banque.io.gestionBanque.exceptions.InvalidPasswordException;
import fsr.banque.io.gestionBanque.models.Compte;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CompteContrat {

    Page<Compte> findCompteParMotCle(String mc, Long aId, int page, int size);

    Compte updateAccount(Compte compte, Long aId);

    List<Compte> allAccounts();

    Compte findLeCompte(Long id) throws InvalidAccountException;

    Compte disactivateAccount(Long idCompte,String motDePasse,String confirmation) throws InvalidAccountException, InvalidPasswordException, InvalidConfirmationException, InvalidAdminDeletionException;

}
