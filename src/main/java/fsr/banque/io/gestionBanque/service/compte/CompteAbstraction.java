package fsr.banque.io.gestionBanque.service.compte;
import fsr.banque.io.gestionBanque.exceptions.InvalidAmountException;
import fsr.banque.io.gestionBanque.exceptions.InvalidUserException;
import fsr.banque.io.gestionBanque.models.Compte;


public abstract class CompteAbstraction{

    fsr.banque.io.gestionBanque.models.Compte.TypeCompte typeCompte;

    abstract Compte createAccount(Compte compte,Long userId) throws InvalidAmountException, InvalidUserException;

}
