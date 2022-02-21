package fsr.banque.io.gestionBanque.controller;

import fsr.banque.io.gestionBanque.dto.UtilisateurDTO;
import fsr.banque.io.gestionBanque.exceptions.InvalidGenderException;
import fsr.banque.io.gestionBanque.models.Utilisateur;
import fsr.banque.io.gestionBanque.service.compte.CompteContrat;
import fsr.banque.io.gestionBanque.service.compte.FabriqueCompte;
import fsr.banque.io.gestionBanque.service.credit.CreditContrat;
import fsr.banque.io.gestionBanque.service.credit.FabriqueCredit;
import fsr.banque.io.gestionBanque.service.retrait.RetraitContrat;
import fsr.banque.io.gestionBanque.service.utilisateur.UtilisateurContrat;
import fsr.banque.io.gestionBanque.service.virement.VirementContrat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * Controlleur Admin permet :
 * l’inscription des clients( d’ouvrir les comptes courants et épargnes)
 * Le droit de voir tout l’historique et toutes les opérations qui sont faites
 */

@RestController
@RequestMapping("/Admin")
public class AdminController {

    private CompteContrat compteContrat;
    private FabriqueCompte fabriqueCompte;
    private UtilisateurContrat utilisateurContrat;
    private VirementContrat virementContrat;
    private RetraitContrat retraitContrat;
    private CreditContrat creditContrat;
    private FabriqueCredit fabriqueCredit;

    @Autowired
    public void setCreditContrat(CreditContrat creditContrat) {
        this.creditContrat = creditContrat;
    }

    @Autowired
    public void setFabriqueCredit(FabriqueCredit fabriqueCredit) {
        this.fabriqueCredit = fabriqueCredit;
    }

    @Autowired
    public void setVirementContrat(VirementContrat virementContrat) {
        this.virementContrat = virementContrat;
    }

    @Autowired
    public void setRetraitContrat(RetraitContrat retraitContrat) {
        this.retraitContrat = retraitContrat;
    }

    @Autowired
    public void setUtilisateurContrat(UtilisateurContrat utilisateurContrat) {
        this.utilisateurContrat = utilisateurContrat;
    }

    @Autowired
    public void setFabriqueCompte(FabriqueCompte fabriqueCompte) {
        this.fabriqueCompte = fabriqueCompte;
    }

    @Autowired
    public void setCompteContrat(CompteContrat compteContrat) {
        this.compteContrat = compteContrat;
    }


    @PostMapping("/createUser")
    public ResponseEntity<Object> creationUtilisateur(@Valid @RequestBody UtilisateurDTO utilisateur, BindingResult bindingResult){

        try {
            return new ResponseEntity<>(utilisateurContrat.saveUser(utilisateur),HttpStatus.OK);
        } catch (Exception e) {

            Map<String,String> error = new HashMap<>();

            if (bindingResult.hasErrors()){

                for (FieldError fd:bindingResult.getFieldErrors()) {
                    error.put(fd.getField(), fd.getDefaultMessage());
                }

                error.put("message",e.toString());
                return new ResponseEntity<>(error, HttpStatus.NOT_ACCEPTABLE);

            }else {
                error.put("message",e.toString());
                return new ResponseEntity<>(error,HttpStatus.NOT_ACCEPTABLE);
            }
        }

    }




}
