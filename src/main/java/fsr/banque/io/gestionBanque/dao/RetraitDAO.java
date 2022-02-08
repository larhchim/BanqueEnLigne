package fsr.banque.io.gestionBanque.dao;

import fsr.banque.io.gestionBanque.models.Retrait;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RetraitDAO extends JpaRepository<Retrait,Long> {

}
