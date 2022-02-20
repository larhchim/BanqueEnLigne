package fsr.banque.io.gestionBanque.controller;

import fsr.banque.io.gestionBanque.dto.CompteDTO;
import fsr.banque.io.gestionBanque.dto.CreditDTO;
import fsr.banque.io.gestionBanque.dto.MensualiteDTO;
import fsr.banque.io.gestionBanque.dto.RetraitDTO;
import fsr.banque.io.gestionBanque.exceptions.*;
import fsr.banque.io.gestionBanque.models.Compte;
import fsr.banque.io.gestionBanque.models.Credits;
import fsr.banque.io.gestionBanque.models.Retrait;
import fsr.banque.io.gestionBanque.service.compte.CompteContrat;
import fsr.banque.io.gestionBanque.service.credit.CreditConsommation;
import fsr.banque.io.gestionBanque.service.credit.CreditContrat;
import fsr.banque.io.gestionBanque.service.credit.CreditImmobilier;
import fsr.banque.io.gestionBanque.service.credit.FabriqueCredit;
import fsr.banque.io.gestionBanque.service.retrait.RetraitContrat;
import fsr.banque.io.gestionBanque.service.virement.VirementContrat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * Controlleur Client permet:
 * Opérations sur le comptes bancaire (consulter, créditer, débiter, supprimer)
 * Virement d’un compte à un autre compte
 */

@RestController
@RequestMapping("/Client")
public class ClientController {

    private CompteContrat compteContrat;
    private CreditContrat creditContrat;
    private FabriqueCredit fabriqueCredit;
    private RetraitContrat retraitContrat;
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
    public void setFabriqueCredit(FabriqueCredit fabriqueCredit) {
        this.fabriqueCredit = fabriqueCredit;
    }

    @Autowired
    public void setCreditContrat(CreditContrat creditContrat) {
        this.creditContrat = creditContrat;
    }

    @Autowired
    public void setCompteContrat(CompteContrat compteContrat) {
        this.compteContrat = compteContrat;
    }


    @GetMapping(value = "/getAccount/{idCompte}",produces = {"application/json"})
    public ResponseEntity<Object> chargerLeCompte(@PathVariable("idCompte") Long idCompte){

        Map<String,String> error = new HashMap<>();

        try {

            Compte compte =compteContrat.findLeCompte(idCompte);
            return new ResponseEntity<>(compte,HttpStatus.OK);

        }catch (Exception e){
            error.put("Compte","Compte Introuvable "+e);
            return new ResponseEntity<Object>(error, HttpStatus.NOT_ACCEPTABLE);
        }

    }

    @PostMapping(path = "/crediterConsommation",consumes = "application/json",produces = "application/json")
    public ResponseEntity<Object> crediterPourConsommation(@Valid @RequestBody CreditDTO creditDTO, BindingResult bindingResult){

        Map<String,String> error = new HashMap<>();

        try {

            CreditConsommation con = (CreditConsommation) fabriqueCredit.generateCredit(Credits.Credit.CONSOMMATION);

            Credits crd = new Credits();

            crd.setNombreMensualitesCredit(Long.valueOf(creditDTO.getNombreMensualites()));
            crd.setMontantCredit(creditDTO.getMontantCredit());

            return new ResponseEntity<>(con.createCredit(crd,compteContrat.findLeCompte(creditDTO.getNumeroCompte())),HttpStatus.OK);


        } catch (InvalidSwitchCaseException | InvalidAccountException | InvalidAmountException | InvalidMensualiteException e ){

            if (bindingResult.hasErrors()){

                for (FieldError fd:bindingResult.getFieldErrors()) {
                    error.put(fd.getField(), fd.getDefaultMessage());
                }

                error.put("message",e.toString());

                return new ResponseEntity<>(error,HttpStatus.NOT_ACCEPTABLE);

            }else {
                error.put("message",e.toString());
                return new ResponseEntity<>(error,HttpStatus.NOT_ACCEPTABLE);
            }

        }

    }

    @PostMapping(value = "/crediterImmobilier",consumes = "application/json",produces = "application/json")
    public ResponseEntity<Object> crediterPourImmobilier(@Valid @RequestBody CreditDTO creditDTO,BindingResult bindingResult){

        Map<String,String> error = new HashMap<>();
        try {

            CreditImmobilier cImm = (CreditImmobilier) fabriqueCredit.generateCredit(Credits.Credit.IMMOBILIER);

            Credits crd = new Credits();

            crd.setNombreMensualitesCredit(Long.valueOf(creditDTO.getNombreMensualites()));
            crd.setMontantCredit(creditDTO.getMontantCredit());

            return new ResponseEntity<>(cImm.createCredit(crd,compteContrat.findLeCompte(creditDTO.getNumeroCompte())),HttpStatus.OK);


        } catch (InvalidSwitchCaseException | InvalidAccountException | InvalidAmountException | InvalidMensualiteException e ){

            if (bindingResult.hasErrors()){

                for (FieldError fd:bindingResult.getFieldErrors()) {
                    error.put(fd.getField(), fd.getDefaultMessage());
                }

                error.put("message",e.toString());
                return new ResponseEntity<>(error,HttpStatus.NOT_ACCEPTABLE);

            }else {
                error.put("message",e.toString());
                return new ResponseEntity<>(error,HttpStatus.NOT_ACCEPTABLE);
            }

        }

    }


    @PostMapping(value = "/reglerMensualite",produces = "application/json",consumes = "application/json")
    public ResponseEntity<Object> reglerUneMensualite(@Valid @RequestBody MensualiteDTO mensualiteDTO,BindingResult bindingResult){

        Map<String,String> error = new HashMap<>();

        try {

            return new ResponseEntity<>(creditContrat.reglerUneMensualite(mensualiteDTO.getIdCredit(), mensualiteDTO.getMensualite()),HttpStatus.OK);

        } catch (InvalidAmountException | InvalidPayementException | InvalidBalanceException | InvalidCreditException | InvalidAccountException e) {

            if (bindingResult.hasErrors()){

                for (FieldError fd:bindingResult.getFieldErrors()) {
                    error.put(fd.getField(), fd.getDefaultMessage());
                }

                error.put("message",e.toString());
                return new ResponseEntity<>(error,HttpStatus.NOT_ACCEPTABLE);

            }else {
                error.put("message",e.toString());
                return new ResponseEntity<>(error,HttpStatus.NOT_ACCEPTABLE);
            }

        }

    }


    @PostMapping(value = "/debiter",consumes = "application/json",produces = "application/json")
    public ResponseEntity<Object> debiterUneSomme(@Valid @RequestBody RetraitDTO retraitDTO, BindingResult bindingResult){


        try {

            Retrait retrait = new Retrait();

            retrait.setMontantRetrait(retraitDTO.getMontantRetrait());
            retrait.setCompteRetrait(compteContrat.findLeCompte(retraitDTO.getIdCompteRetrait()));

            return new ResponseEntity<>(retraitContrat.createNewRetrait(retrait),HttpStatus.OK);

        } catch (InvalidAccountException | InvalidAmountException | InvalidBalanceException e) {

            Map<String,String> error = new HashMap<>();

            if (bindingResult.hasErrors()){

                for (FieldError fd:bindingResult.getFieldErrors()) {
                    error.put(fd.getField(), fd.getDefaultMessage());
                }

                error.put("message",e.toString());
                return new ResponseEntity<>(error,HttpStatus.NOT_ACCEPTABLE);

            }else {
                error.put("message",e.toString());
                return new ResponseEntity<>(error,HttpStatus.NOT_ACCEPTABLE);
            }

        }

    }



    @PostMapping(value = "/deleteAccount",consumes = "application/json",produces = "application/json")
    public ResponseEntity<Object> supprimerLeCompte(@Valid @RequestBody CompteDTO compteDTO,BindingResult bindingResult){

        try {

            return new ResponseEntity<>(compteContrat.disactivateAccount(compteDTO.getNumeroCompte(),compteDTO.getMotDePasse(),compteDTO.getConfirmation()),HttpStatus.OK);

        } catch (InvalidAccountException | InvalidPasswordException | InvalidConfirmationException | InvalidAdminDeletionException | InvalidHashPasswordException e) {

            Map<String,String> error = new HashMap<>();

            if (bindingResult.hasErrors()){

                for (FieldError fd:bindingResult.getFieldErrors()) {
                    error.put(fd.getField(), fd.getDefaultMessage());
                }

                error.put("message",e.toString());
                return new ResponseEntity<>(error,HttpStatus.NOT_ACCEPTABLE);

            }else {
                error.put("message",e.toString());
                return new ResponseEntity<>(error,HttpStatus.NOT_ACCEPTABLE);
            }

        }

    }





}
