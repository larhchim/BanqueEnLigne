package fsr.banque.io.gestionBanque.service.compte;

import fsr.banque.io.gestionBanque.models.Compte;
import org.springframework.stereotype.Service;

public class FabriqueCompteImpl implements FabriqueCompte{

    @Override
    public CompteAbstraction generateAccount(Compte.TypeCompte typeCompte) {

        CompteAbstraction compte;

        switch (typeCompte){
            case COURANT:
                compte = new CompteCourant();
                break;
            case EPARGNE:
                compte = new CompteEpargne();
                break;
            default:
                throw new IllegalArgumentException("Veuillez specifier le type de compte");
        }
        return compte;
    }

}
