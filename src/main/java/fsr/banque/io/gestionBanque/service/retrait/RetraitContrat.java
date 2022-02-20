package fsr.banque.io.gestionBanque.service.retrait;

import fsr.banque.io.gestionBanque.exceptions.InvalidAccountException;
import fsr.banque.io.gestionBanque.exceptions.InvalidAmountException;
import fsr.banque.io.gestionBanque.exceptions.InvalidBalanceException;
import fsr.banque.io.gestionBanque.models.Compte;
import fsr.banque.io.gestionBanque.models.Retrait;
import org.springframework.data.domain.Page;

import java.util.List;

public interface RetraitContrat {

    Retrait createNewRetrait(Retrait retrait) throws InvalidAccountException, InvalidAmountException, InvalidBalanceException;

    List<Retrait> allRetraits();

    Page<Retrait> rechercheParMotCle(Long mc,int page,int size);

}
