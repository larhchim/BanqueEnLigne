package fsr.banque.io.gestionBanque;

import fsr.banque.io.gestionBanque.dao.UtilisateurDAO;
import fsr.banque.io.gestionBanque.models.Compte;
import fsr.banque.io.gestionBanque.models.Utilisateur;
import fsr.banque.io.gestionBanque.service.compte.*;
import fsr.banque.io.gestionBanque.service.utilisateur.UtilisateurContrat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.Date;

@SpringBootApplication()
public class GestionBanqueApplication {

	private UtilisateurContrat user;
	private FabriqueCompte fabriqueCompte;

	@Autowired
	public void setFabriqueCompte(FabriqueCompte fabriqueCompte) {
		this.fabriqueCompte = fabriqueCompte;
	}

	@Autowired
	public void setUser(UtilisateurContrat user) {
		this.user = user;
	}

	public static void main(String[] args) {
		SpringApplication.run(GestionBanqueApplication.class, args);
	}

	@PostConstruct
	public void LoadOnceFinishAll(){

		/**
		 * Adding new Users to the data base
		 */


		/*

		Utilisateur utilisateur = new Utilisateur();

		utilisateur.setSexeUtilisateur(Utilisateur.Gender.MALE);
		utilisateur.setNomUtilisateur("LARHCHIM");
		utilisateur.setPrenomUtilisateur("ZAKARIA");
		utilisateur.setEmailUtilisateur("larhchim_zakaria@gmail.com");
		user.saveUser(utilisateur);

		Utilisateur utilisateur1 = new Utilisateur();

		utilisateur1.setSexeUtilisateur(Utilisateur.Gender.MALE);
		utilisateur1.setNomUtilisateur("JADID");
		utilisateur1.setPrenomUtilisateur("ISMAIL");
		utilisateur1.setEmailUtilisateur("jadid_ismail@gmail.com");
		user.saveUser(utilisateur1);

		Utilisateur utilisateur2 = new Utilisateur();

		utilisateur2.setSexeUtilisateur(Utilisateur.Gender.FEMALE);
		utilisateur2.setNomUtilisateur("FIHRI");
		utilisateur2.setPrenomUtilisateur("FATIMA");
		utilisateur2.setEmailUtilisateur("fihri_fatima@gmail.com");
		user.saveUser(utilisateur2);

		*/

		/***
		 * Creating Bank accounts with the above users
		 */
/*
		Compte compte = new Compte();

		compte.setEtatCompte(true);
		compte.setSoldeCompte(new BigDecimal("3000000"));
		compte.setDateCreation(new Date());
		compte.setMotDePasse(getBCR().encode("123456789"));

		CompteEpargne c = (CompteEpargne) fabriqueCompte.generateAccount(Compte.TypeCompte.EPARGNE);
        c.createAccount(compte,user.findUserByEmail("larhchim_zakaria@gmail.com").getIdUtilisateur());
*/


/*
		Compte compte1 = new Compte();

		compte1.setEtatCompte(true);
		compte1.setSoldeCompte(new BigDecimal("18000000"));
		compte1.setDateCreation(new Date());
		compte1.setMotDePasse(getBCR().encode("789456123"));



		CompteCourant d = (CompteCourant) fabriqueCompte.generateAccount(Compte.TypeCompte.COURANT);
		d.createAccount(compte1,user.findUserByEmail("jadid_ismail@gmail.com").getIdUtilisateur());



		Compte compte2 = new Compte();

		compte2.setEtatCompte(true);
		compte2.setSoldeCompte(new BigDecimal("0"));
		compte2.setDateCreation(new Date());
		compte2.setMotDePasse(getBCR().encode("456123789"));

		CompteAdmin e = (CompteAdmin) fabriqueCompte.generateAccount(Compte.TypeCompte.ADMIN);
		e.createAccount(compte2,user.findUserByEmail("fihri_fatima@gmail.com").getIdUtilisateur());

*/


	}

	@Bean
	BCryptPasswordEncoder getBCR(){
		return new BCryptPasswordEncoder();
	}

}


