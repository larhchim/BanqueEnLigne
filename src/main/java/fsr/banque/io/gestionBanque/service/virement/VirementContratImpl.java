package fsr.banque.io.gestionBanque.service.virement;

import fsr.banque.io.gestionBanque.models.Virement;
import org.springframework.data.domain.Page;

import java.util.List;

public class VirementContratImpl implements VirementContrat{

    @Override
    public Page<Virement> findVirementByMotCle(Long mc, int page, int size) {
        return null;
    }

    @Override
    public Virement createNewVirement(Virement virement, Long emetteur, Long recepteur) {
        return null;
    }

    @Override
    public List<Virement> allVirements() {
        return null;
    }

}
