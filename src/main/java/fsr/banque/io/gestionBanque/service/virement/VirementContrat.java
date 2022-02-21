package fsr.banque.io.gestionBanque.service.virement;

import fsr.banque.io.gestionBanque.exceptions.InvalidAccountException;
import fsr.banque.io.gestionBanque.exceptions.InvalidAmountException;
import fsr.banque.io.gestionBanque.exceptions.InvalidBalanceException;
import fsr.banque.io.gestionBanque.models.Virement;
import org.springframework.data.domain.Page;

import java.util.List;

public interface VirementContrat {

    Page<Virement> findVirementByMotCle(Long mc,int page,int size);

    Virement createNewVirement(Virement virement, Long emetteur, Long recepteur) throws InvalidAccountException, InvalidAmountException, InvalidBalanceException ;

    List<Virement> allVirements();

}
