package fsr.banque.io.gestionBanque.service.compte;

import fsr.banque.io.gestionBanque.models.Compte;
import org.springframework.stereotype.Service;

@Service
public class CompteCourant extends CompteAbstraction{

    public CompteCourant() {
        typeCompte = Compte.TypeCompte.COURANT;
    }

    @Override
    Compte createAccount(Compte compte) {
        return null;
    }

}
