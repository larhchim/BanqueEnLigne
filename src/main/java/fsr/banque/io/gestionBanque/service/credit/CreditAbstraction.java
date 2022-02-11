package fsr.banque.io.gestionBanque.service.credit;

import fsr.banque.io.gestionBanque.models.Compte;
import fsr.banque.io.gestionBanque.models.Credits;
import org.springframework.data.domain.Page;

import java.util.List;

public abstract class CreditAbstraction implements CreditContrat{

    Credits.Credit credit;

    abstract Credits createCredit(Credits credits, Compte compte);

    @Override
    public Credits updateCredit(Credits credits) {
        return null;
    }

    @Override
    public List<Credits> allCredits() {
        return null;
    }

    @Override
    public Page<Credits> rechercheCreditParMotCle(Long mc, int page, int size) {
        return null;
    }

}
