package fsr.banque.io.gestionBanque.dao;

import fsr.banque.io.gestionBanque.models.Utilisateur;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UtilisateurDAO extends JpaRepository<Utilisateur,Long> {

    @Query("SELECT c from Utilisateur c where c.emailUtilisateur LIKE :x or c.nomUtilisateur LIKE :x or c.prenomUtilisateur LIKE :x")
    public Page<Utilisateur> FindUtilisateurParMotCle(@Param("x") String mc, Pageable pageable);

}
