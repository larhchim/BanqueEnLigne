package fsr.banque.io.gestionBanque.service.compte;

import org.springframework.data.domain.Page;

import java.util.List;

public abstract class Compte implements CompteContrat{

    fsr.banque.io.gestionBanque.models.Compte.TypeCompte typeCompte;

    abstract Compte createAccount(Compte compte);

    @Override
    public Page<Compte> findCompteParMotCle(String mc, Long aId, int numero, int size) {
        return null;
    }

    @Override
    public Compte updateAccount(Compte compte, Long aId) {
        return null;
    }

    @Override
    public List<Compte> allAccounts() {
        return null;
    }

}
