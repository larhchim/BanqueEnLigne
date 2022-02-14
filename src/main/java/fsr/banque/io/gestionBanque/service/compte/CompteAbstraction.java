package fsr.banque.io.gestionBanque.service.compte;

import fsr.banque.io.gestionBanque.dao.CompteDAO;
import fsr.banque.io.gestionBanque.models.Compte;
import org.springframework.beans.factory.annotation.Autowired;


public abstract class CompteAbstraction{

    fsr.banque.io.gestionBanque.models.Compte.TypeCompte typeCompte;

    abstract Compte createAccount(Compte compte,Long userId);

}
