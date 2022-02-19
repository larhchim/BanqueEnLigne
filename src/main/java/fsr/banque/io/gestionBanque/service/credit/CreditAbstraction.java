package fsr.banque.io.gestionBanque.service.credit;

import fsr.banque.io.gestionBanque.exceptions.InvalidAccountException;
import fsr.banque.io.gestionBanque.exceptions.InvalidAmountException;
import fsr.banque.io.gestionBanque.exceptions.InvalidMensualiteException;
import fsr.banque.io.gestionBanque.models.Compte;
import fsr.banque.io.gestionBanque.models.Credits;

public abstract class CreditAbstraction{

    Credits.Credit credit;

    abstract Credits createCredit(Credits credits, Compte compte) throws InvalidAmountException, InvalidAccountException, InvalidMensualiteException;

}
