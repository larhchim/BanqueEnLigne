package fsr.banque.io.gestionBanque.controller;

import fsr.banque.io.gestionBanque.service.compte.CompteContrat;
import fsr.banque.io.gestionBanque.service.credit.CreditContrat;
import fsr.banque.io.gestionBanque.service.credit.FabriqueCredit;
import fsr.banque.io.gestionBanque.service.retrait.RetraitContrat;
import fsr.banque.io.gestionBanque.service.virement.VirementContrat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlleur Client permet:
 * Opérations sur le comptes bancaire (consulter, créditer, débiter, supprimer)
 * Virement d’un compte à un autre compte
 */

@RestController
public class ClientController {

    private CompteContrat compteContrat;
    private CreditContrat creditContrat;
    private FabriqueCredit fabriqueCredit;
    private RetraitContrat retraitContrat;
    private VirementContrat virementContrat;

    @Autowired
    public void setVirementContrat(VirementContrat virementContrat) {
        this.virementContrat = virementContrat;
    }

    @Autowired
    public void setRetraitContrat(RetraitContrat retraitContrat) {
        this.retraitContrat = retraitContrat;
    }

    @Autowired
    public void setFabriqueCredit(FabriqueCredit fabriqueCredit) {
        this.fabriqueCredit = fabriqueCredit;
    }

    @Autowired
    public void setCreditContrat(CreditContrat creditContrat) {
        this.creditContrat = creditContrat;
    }

    @Autowired
    public void setCompteContrat(CompteContrat compteContrat) {
        this.compteContrat = compteContrat;
    }

}
