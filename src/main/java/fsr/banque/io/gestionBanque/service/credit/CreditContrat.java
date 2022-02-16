package fsr.banque.io.gestionBanque.service.credit;

import fsr.banque.io.gestionBanque.models.Compte;
import fsr.banque.io.gestionBanque.models.Credits;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;
import java.util.List;

public interface CreditContrat {

    Credits updateCredit(Credits credits);

    List<Credits> allCredits();

    Page<Credits> rechercheCreditParMotCle(Long mc,int page,int size);

    Credits reglerUneMensualite(Long idCredit, BigDecimal mensualite) throws Exception;

    Credits findUnCredit(Long id);

}
