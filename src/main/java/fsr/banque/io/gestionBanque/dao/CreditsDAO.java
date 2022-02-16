package fsr.banque.io.gestionBanque.dao;

import fsr.banque.io.gestionBanque.models.Credits;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Couche DAO Credit pour les appels BDD
 */

@Repository
public interface CreditsDAO extends JpaRepository<Credits,Long> {

    /**
     * @param mc mot cle filtre de recherche
     * @param pageable parametres de la page : taille et page
     * @return page de credit possibilité de pagination
     */

    @Query("select c from Credits c where c.compteCredit.numeroCompte =:x or c.idCredit =:x")
    Page<Credits> findCreditsByMotCle(@Param("x") Long mc, Pageable pageable);

}
