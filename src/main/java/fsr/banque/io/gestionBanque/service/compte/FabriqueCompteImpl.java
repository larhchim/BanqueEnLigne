package fsr.banque.io.gestionBanque.service.compte;

import fsr.banque.io.gestionBanque.models.Compte;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FabriqueCompteImpl implements FabriqueCompte{

    private CompteCourant compteCourant;
    private CompteEpargne compteEpargne;

    @Autowired
    public void setCompteCourant(CompteCourant compteCourant) {
        this.compteCourant = compteCourant;
    }

    @Autowired
    public void setCompteEpargne(CompteEpargne compteEpargne) {
        this.compteEpargne = compteEpargne;
    }

    @Override
    public CompteAbstraction generateAccount(Compte.TypeCompte typeCompte) {

        CompteAbstraction compte;

        switch (typeCompte){
            case COURANT:
                compte = compteCourant;
                break;
            case EPARGNE:
                compte = compteEpargne;
                break;
            default:
                throw new IllegalArgumentException("Veuillez specifier le type de compte");
        }
        return compte;
    }

}
