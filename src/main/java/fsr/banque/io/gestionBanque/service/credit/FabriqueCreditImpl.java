package fsr.banque.io.gestionBanque.service.credit;

import fsr.banque.io.gestionBanque.models.Credits;

public class FabriqueCreditImpl implements FabriqueCredit{

    @Override
    public CreditAbstraction generateCredit(Credits.Credit credit) {

        CreditAbstraction creditAbstraction;

        switch (credit){
            case IMMOBILIER:
                creditAbstraction = new CreditImmobilier();
                break;
            case CONSOMMATION:
                creditAbstraction = new CreditConsommation();
                break;
            default:
                throw new IllegalArgumentException("Veuillez choisir un type de credit valide");
        }
        return creditAbstraction;
    }

}
