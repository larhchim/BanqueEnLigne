package fsr.banque.io.gestionBanque.service.compte;

import org.springframework.data.domain.Page;

import java.util.List;

public interface CompteContrat {

    Page<Compte> findCompteParMotCle(String mc,Long aId,int numero, int size);

    Compte updateAccount(Compte compte, Long aId);

    List<Compte> allAccounts();

}
