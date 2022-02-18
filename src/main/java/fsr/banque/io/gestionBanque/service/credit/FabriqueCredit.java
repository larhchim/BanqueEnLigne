package fsr.banque.io.gestionBanque.service.credit;

import fsr.banque.io.gestionBanque.exceptions.InvalidSwitchCaseException;
import fsr.banque.io.gestionBanque.models.Credits;

public interface FabriqueCredit {

    CreditAbstraction generateCredit(Credits.Credit credit) throws InvalidSwitchCaseException;

}
