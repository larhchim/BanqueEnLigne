package fsr.banque.io.gestionBanque;

import fsr.banque.io.gestionBanque.dao.UtilisateurDAO;
import fsr.banque.io.gestionBanque.models.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

import javax.annotation.PostConstruct;

@SpringBootApplication()
public class GestionBanqueApplication {



	public static void main(String[] args) {
		SpringApplication.run(GestionBanqueApplication.class, args);
	}



}


