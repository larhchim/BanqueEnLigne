package fsr.banque.io.gestionBanque.service.credit;

import fsr.banque.io.gestionBanque.models.Credits;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class FabriqueCreditImpl implements FabriqueCredit{

    private ApplicationContext applicationContext;

    @Autowired
    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public CreditAbstraction generateCredit(Credits.Credit credit) {

        CreditAbstraction creditAbstraction;

        switch (credit){
            case IMMOBILIER:
                creditAbstraction = applicationContext.getBean(CreditImmobilier.class);
                break;
            case CONSOMMATION:
                creditAbstraction = applicationContext.getBean(CreditConsommation.class);
                break;
            default:
                throw new IllegalArgumentException("Veuillez choisir un type de credit valide");
        }
        return creditAbstraction;
    }

}
