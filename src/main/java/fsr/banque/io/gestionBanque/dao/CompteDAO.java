package fsr.banque.io.gestionBanque.dao;

import fsr.banque.io.gestionBanque.models.Compte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompteDAO extends JpaRepository<Compte,Long> {

}
