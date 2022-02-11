package fsr.banque.io.gestionBanque.service.credit;

import fsr.banque.io.gestionBanque.models.Compte;
import fsr.banque.io.gestionBanque.models.Credits;
import org.springframework.stereotype.Service;

@Service
public class CreditImmobilier extends CreditAbstraction{

    @Override
    Credits createCredit(Credits credits, Compte compte) {
        return null;
    }

}
