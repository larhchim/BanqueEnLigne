package fsr.banque.io.gestionBanque.service.compte;

import fsr.banque.io.gestionBanque.models.Compte;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FabriqueCompteImpl implements FabriqueCompte{

    private CompteCourant compteCourant;
    private CompteEpargne compteEpargne;
    private CompteAdmin compteAdmin;

    @Autowired
    public void setCompteAdmin(CompteAdmin compteAdmin) {
        this.compteAdmin = compteAdmin;
    }

    @Autowired
    public void setCompteCourant(CompteCourant compteCourant) {
        this.compteCourant = compteCourant;
    }

    @Autowired
    public void setCompteEpargne(CompteEpargne compteEpargne) {
        this.compteEpargne = compteEpargne;
    }

    @Override
    public void generateAccount(Compte.TypeCompte typeCompte,Compte cmpt,Long userId) {

        CompteAbstraction compte;

        switch (typeCompte){
            case COURANT:
                compteCourant.createAccount(cmpt,userId);
               // compte = compteCourant;
                break;
            case EPARGNE:
                compteEpargne.createAccount(cmpt,userId);
              //  compte = compteEpargne;
                break;
            case ADMIN:
                compteAdmin.createAccount(cmpt,userId);
             //   compte = compteAdmin;
                break;
            default:
                throw new IllegalArgumentException("Veuillez specifier le type de compte");
        }

       // return compte;

    }

}
