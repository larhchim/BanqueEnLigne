package fsr.banque.io.gestionBanque.service.compte;

import fsr.banque.io.gestionBanque.models.Compte;
import org.springframework.stereotype.Service;

@Service
public class CompteEpargne extends CompteAbstraction{

    public CompteEpargne() {
        typeCompte = Compte.TypeCompte.EPARGNE;
    }

    @Override
    Compte createAccount(Compte compte) {
        return null;
    }

}
