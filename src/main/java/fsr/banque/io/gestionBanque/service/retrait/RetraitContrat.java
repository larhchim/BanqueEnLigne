package fsr.banque.io.gestionBanque.service.retrait;

import fsr.banque.io.gestionBanque.models.Compte;
import fsr.banque.io.gestionBanque.models.Retrait;

import java.util.List;

public interface RetraitContrat {

    Retrait createNewRetrait(Retrait retrait, Compte compte);

    List<Retrait> allRetraits();

}
