package fsr.banque.io.gestionBanque.controller;

import fsr.banque.io.gestionBanque.service.compte.CompteContrat;
import fsr.banque.io.gestionBanque.service.compte.FabriqueCompte;
import fsr.banque.io.gestionBanque.service.credit.CreditContrat;
import fsr.banque.io.gestionBanque.service.credit.FabriqueCredit;
import fsr.banque.io.gestionBanque.service.retrait.RetraitContrat;
import fsr.banque.io.gestionBanque.service.utilisateur.UtilisateurContrat;
import fsr.banque.io.gestionBanque.service.virement.VirementContrat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlleur Admin permet :
 * l’inscription des clients( d’ouvrir les comptes courants et épargnes)
 * Le droit de voir tout l’historique et toutes les opérations qui sont faites
 */

@RestController
public class AdminController {

    private CompteContrat compteContrat;
    private FabriqueCompte fabriqueCompte;
    private UtilisateurContrat utilisateurContrat;
    private VirementContrat virementContrat;
    private RetraitContrat retraitContrat;
    private CreditContrat creditContrat;
    private FabriqueCredit fabriqueCredit;

    @Autowired
    public void setCreditContrat(CreditContrat creditContrat) {
        this.creditContrat = creditContrat;
    }

    @Autowired
    public void setFabriqueCredit(FabriqueCredit fabriqueCredit) {
        this.fabriqueCredit = fabriqueCredit;
    }

    @Autowired
    public void setVirementContrat(VirementContrat virementContrat) {
        this.virementContrat = virementContrat;
    }

    @Autowired
    public void setRetraitContrat(RetraitContrat retraitContrat) {
        this.retraitContrat = retraitContrat;
    }

    @Autowired
    public void setUtilisateurContrat(UtilisateurContrat utilisateurContrat) {
        this.utilisateurContrat = utilisateurContrat;
    }

    @Autowired
    public void setFabriqueCompte(FabriqueCompte fabriqueCompte) {
        this.fabriqueCompte = fabriqueCompte;
    }

    @Autowired
    public void setCompteContrat(CompteContrat compteContrat) {
        this.compteContrat = compteContrat;
    }

}
