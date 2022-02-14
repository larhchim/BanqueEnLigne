package fsr.banque.io.gestionBanque.service.compte;

import fsr.banque.io.gestionBanque.models.Compte;

public interface FabriqueCompte {

    void generateAccount(fsr.banque.io.gestionBanque.models.Compte.TypeCompte typeCompte,Compte compte,Long userId);

}
