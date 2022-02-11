package fsr.banque.io.gestionBanque.service.credit;

import fsr.banque.io.gestionBanque.models.Credits;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FabriqueCreditImpl implements FabriqueCredit{

    private CreditImmobilier creditImmobilier;
    private CreditConsommation creditConsommation;

    @Autowired
    public void setCreditConsommation(CreditConsommation creditConsommation) {
        this.creditConsommation = creditConsommation;
    }

    @Autowired
    public void setCreditImmobilier(CreditImmobilier creditImmobilier) {
        this.creditImmobilier = creditImmobilier;
    }

    @Override
    public CreditAbstraction generateCredit(Credits.Credit credit) {

        CreditAbstraction creditAbstraction;

        switch (credit){
            case IMMOBILIER:
                creditAbstraction = creditImmobilier;
                break;
            case CONSOMMATION:
                creditAbstraction = creditConsommation;
                break;
            default:
                throw new IllegalArgumentException("Veuillez choisir un type de credit valide");
        }
        return creditAbstraction;
    }

}
