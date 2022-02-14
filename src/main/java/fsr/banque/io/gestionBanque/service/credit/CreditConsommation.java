package fsr.banque.io.gestionBanque.service.credit;

import fsr.banque.io.gestionBanque.models.Compte;
import fsr.banque.io.gestionBanque.models.Credits;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CreditConsommation extends CreditAbstraction{

    public CreditConsommation() {
        credit = Credits.Credit.CONSOMMATION;
    }

    @Override
    Credits createCredit(Credits credits, Compte compte) {
        credits.setCompteCredit(compte);
        credits.setDateCredit(new Date());
        credits.setTypeCredit(credit);
        return null;
    }

}
