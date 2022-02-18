package fsr.banque.io.gestionBanque.service.compte;

import fsr.banque.io.gestionBanque.exceptions.InvalidSwitchCaseException;
import fsr.banque.io.gestionBanque.models.Compte;

public interface FabriqueCompte {

    CompteAbstraction generateAccount(Compte.TypeCompte typeCompte) throws InvalidSwitchCaseException;

}
