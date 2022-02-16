package fsr.banque.io.gestionBanque.dao;

import fsr.banque.io.gestionBanque.models.Virement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Couche DAO Virement pour les appels BDD
 */

@Repository
public interface VirementDAO extends JpaRepository<Virement,Long> {

    /**
     * @param mc mot cle filtre de recherche numero compte recepteur
     * @param pageable parametres de la page taille et page actuel
     * @return liste des pages avec possibilité de pagination
     */

    @Query("select c from Virement c where c.numeroCompteRecepteur =:x")
    Page<Virement> findVirementByMotCle(@Param("x") Long mc, Pageable pageable);

}
