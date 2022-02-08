package fsr.banque.io.gestionBanque.dao;

import fsr.banque.io.gestionBanque.models.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UtilisateurDAO extends JpaRepository<Utilisateur,Long> {

}
