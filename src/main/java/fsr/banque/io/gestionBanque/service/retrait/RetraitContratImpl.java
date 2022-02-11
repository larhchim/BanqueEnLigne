package fsr.banque.io.gestionBanque.service.retrait;

import fsr.banque.io.gestionBanque.models.Compte;
import fsr.banque.io.gestionBanque.models.Retrait;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RetraitContratImpl implements RetraitContrat {

    @Override
    public Retrait createNewRetrait(Retrait retrait, Compte compte) {
        return null;
    }

    @Override
    public List<Retrait> allRetraits() {
        return null;
    }

}
