package fsr.banque.io.gestionBanque;

import fsr.banque.io.gestionBanque.dao.UtilisateurDAO;
import fsr.banque.io.gestionBanque.models.*;
import fsr.banque.io.gestionBanque.service.compte.*;
import fsr.banque.io.gestionBanque.service.credit.CreditConsommation;
import fsr.banque.io.gestionBanque.service.credit.CreditContrat;
import fsr.banque.io.gestionBanque.service.credit.CreditImmobilier;
import fsr.banque.io.gestionBanque.service.credit.FabriqueCredit;
import fsr.banque.io.gestionBanque.service.retrait.RetraitContrat;
import fsr.banque.io.gestionBanque.service.utilisateur.UtilisateurContrat;
import fsr.banque.io.gestionBanque.service.virement.VirementContrat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
	private CompteContrat compteContrat;
	private RetraitContrat retraitContrat;
	private CreditContrat creditContrat;
	private FabriqueCredit fabriqueCredit;
	private VirementContrat virementContrat;

	@Autowired
	public void setVirementContrat(VirementContrat virementContrat) {
		this.virementContrat = virementContrat;
	}

	@Autowired
	public void setRetraitContrat(RetraitContrat retraitContrat) {
		this.retraitContrat = retraitContrat;
	}

	@Autowired
	public void setCompteContrat(CompteContrat compteContrat) {
		this.compteContrat = compteContrat;
	}

	@Autowired
	public void setFabriqueCompte(FabriqueCompte fabriqueCompte) {
		this.fabriqueCompte = fabriqueCompte;
	}

	@Autowired
	public void setUser(UtilisateurContrat user) {
		this.user = user;
	}

	@Autowired
	public void setCreditContrat(CreditContrat creditContrat) {
		this.creditContrat = creditContrat;
	}

	@Autowired
	public void setFabriqueCredit(FabriqueCredit fabriqueCredit) {
		this.fabriqueCredit = fabriqueCredit;
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

		compte1.setSoldeCompte(new BigDecimal("18000000"));
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

		/*Utilisateur utilisateur2 = new Utilisateur();

		utilisateur2.setSexeUtilisateur(Utilisateur.Gender.FEMALE);
		utilisateur2.setNomUtilisateur("SALHI");
		utilisateur2.setPrenomUtilisateur("KARIMA");
		utilisateur2.setEmailUtilisateur("salhi_karima@gmail.com");
		user.saveUser(utilisateur2);

		Compte compte2 = new Compte();

		compte2.setEtatCompte(true);
		compte2.setSoldeCompte(new BigDecimal("78500"));
		compte2.setDateCreation(new Date());
		compte2.setMotDePasse(getBCR().encode("456123789"));

		CompteCourant e = (CompteCourant) fabriqueCompte.generateAccount(Compte.TypeCompte.COURANT);
		e.createAccount(compte2,user.findUserByEmail("salhi_karima@gmail.com").getIdUtilisateur());
*/
		/*long value = Long.valueOf(90314487);
		System.out.println(value);
		Compte compte =compteContrat.findLeCompte(value);
		System.out.println("Lazy: "+compte.toString());

		for (Compte c:compteContrat.allAccounts()) {
			System.out.println(c.toString());

		}*/

		/*try {
			Retrait retrait = new Retrait();
			retrait.setDateRetrait(new Date());
			retrait.setMontantRetrait(new BigDecimal(1));
			retrait.setCompteRetrait(compteContrat.findLeCompte(Long.valueOf("90314587")));

			retraitContrat.createNewRetrait(retrait);
		}catch (Exception e){
			e.printStackTrace();
			System.out.println(e);
		}*/

		/*CreditConsommation cc = (CreditConsommation) fabriqueCredit.generateCredit(Credits.Credit.CONSOMMATION);
		Credits cd = new Credits();
		cd.setNombreMensualitesCredit(Long.valueOf(24));
		cd.setMontantCredit(BigDecimal.valueOf(50000));
		try {
			cc.createCredit(cd,compteContrat.findLeCompte(Long.valueOf("90314587")));
		} catch (Exception e) {
			e.printStackTrace();
		}*/

		/*CreditImmobilier cc = (CreditImmobilier) fabriqueCredit.generateCredit(Credits.Credit.IMMOBILIER);
		Credits cd = new Credits();
		cd.setNombreMensualitesCredit(Long.valueOf(180));
		cd.setMontantCredit(BigDecimal.valueOf(100000));
		cc.createCredit(cd,compteContrat.findLeCompte(Long.valueOf("90314587")));*/

	/*	try {
			creditContrat.reglerUneMensualite(Long.valueOf(8752),BigDecimal.valueOf(843.86));
		} catch (Exception e) {
			e.printStackTrace();
		}
*/

		/*Long emetteur = Long.valueOf(90314538);
		Long recepteur = Long.valueOf(90314587);
		Virement virement = new Virement();
		virement.setMontant(BigDecimal.valueOf(6703.97));

		try {
			virementContrat.createNewVirement(virement,emetteur,recepteur);
		} catch (Exception e) {
			e.printStackTrace();
		}*/


		/*try {
			System.out.println(compteContrat.findLeCompte(Long.valueOf(90314587)).toString());
		} catch (Exception e) {
			e.printStackTrace();
		}*/


	}

	@Bean
	BCryptPasswordEncoder getBCR(){
		return new BCryptPasswordEncoder();
	}

}


