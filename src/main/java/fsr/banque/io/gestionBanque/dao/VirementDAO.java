package fsr.banque.io.gestionBanque.dao;

import fsr.banque.io.gestionBanque.models.Virement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VirementDAO extends JpaRepository<Virement,Long> {

}
