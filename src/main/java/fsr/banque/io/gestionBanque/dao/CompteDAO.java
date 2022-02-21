package fsr.banque.io.gestionBanque.dao;

import fsr.banque.io.gestionBanque.models.Compte;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Couche DAO Compte pour les appels BDD
 */

@Repository
public interface CompteDAO extends JpaRepository<Compte,Long> {

    /***
     * @param mc1 premier mot cle
     * @param pageable controle de la page de retour filtré
     * @return une page des comptes avec pagination
     */

    @Query("select c from Compte c where (c.utilisateur.emailUtilisateur LIKE :x or c.utilisateur.nomUtilisateur LIKE :x or c.utilisateur.prenomUtilisateur LIKE :x ) and c.etatCompte = true ")
    Page<Compte> findCompteParMotCle(@Param("x") String mc1,Pageable pageable);

}
