package fsr.banque.io.gestionBanque.dao;

import fsr.banque.io.gestionBanque.models.Retrait;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Couche DAO Retrait pour les appels BDD
 */

@Repository
public interface RetraitDAO extends JpaRepository<Retrait,Long> {

    /**
     * @param mc mot cle filtre de recherche
     * @param pageable parametres de la page taille et la page coourante
     * @return page avec pagination
     */

    @Query("select c from Retrait c where c.compteRetrait.numeroCompte =:x")
    Page<Retrait> Search(@Param("x") Long mc, Pageable pageable);
}
