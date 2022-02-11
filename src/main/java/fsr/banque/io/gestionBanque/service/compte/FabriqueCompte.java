package fsr.banque.io.gestionBanque.service.compte;

import fsr.banque.io.gestionBanque.models.Compte;

public interface FabriqueCompte {

    CompteAbstraction generateAccount(fsr.banque.io.gestionBanque.models.Compte.TypeCompte typeCompte);

}
