package fsr.banque.io.gestionBanque.dao;

import fsr.banque.io.gestionBanque.models.Credits;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditsDAO extends JpaRepository<Credits,Long> {

}
