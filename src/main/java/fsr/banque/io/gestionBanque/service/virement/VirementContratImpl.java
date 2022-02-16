package fsr.banque.io.gestionBanque.service.virement;

import fsr.banque.io.gestionBanque.dao.VirementDAO;
import fsr.banque.io.gestionBanque.models.Virement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class VirementContratImpl implements VirementContrat{

    private VirementDAO virementDAO;

    @Autowired
    public void setVirementDAO(VirementDAO virementDAO) {
        this.virementDAO = virementDAO;
    }

    @Transactional
    @Override
    public Page<Virement> findVirementByMotCle(Long mc, int page, int size) {
        return virementDAO.findVirementByMotCle(mc, PageRequest.of(page,size));
    }

    @Transactional
    @Override
    public Virement createNewVirement(Virement virement, Long emetteur, Long recepteur) {
        return null;
    }

    @Transactional
    @Override
    public List<Virement> allVirements() {
        return virementDAO.findAll();
    }

}
