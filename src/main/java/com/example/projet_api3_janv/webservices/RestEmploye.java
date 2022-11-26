package com.example.projet_api3_janv.webservices;

import com.example.projet_api3_janv.entities.APIEmploye;
import com.example.projet_api3_janv.services.EmployeServiceImpl;
import com.example.projet_api3_janv.services.InterfEmployeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "*", allowedHeaders = "*",exposedHeaders = "*")
@RestController
@RequestMapping("/employes")
public class RestEmploye {
    @Autowired
    private InterfEmployeService employeServiceImpl;
    //-------------------Retrouver l'employé correspondant à un id donné--------------------------------------------------------
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<APIEmploye> getAPIEmploye(@PathVariable(value = "id") int id) throws Exception{
        System.out.println("recherche de l'employé d' id " + id);
        APIEmploye employe = employeServiceImpl.read(id);
        return new ResponseEntity<>(employe, HttpStatus.OK);
    }
    //-------------------Retrouver l'employé portant un nom donné--------------------------------------------------------
    @RequestMapping(value = "/nom={nom}", method = RequestMethod.GET)
    public ResponseEntity<List<APIEmploye>> listAPIEmployeNom(@PathVariable(value="nom") String nom) throws Exception{
        System.out.println("recherche de "+nom);
        List<APIEmploye> employes;
        employes = employeServiceImpl.read(nom);
        return new ResponseEntity<>(employes, HttpStatus.OK);
    }
    //-------------------Retrouver l'employé correspondant à un triplet (matricule,tel,mail) unique donné--------------------------------------------------------
    @RequestMapping(value = "/{matricule}/{tel}/{mail}", method = RequestMethod.GET)
    public ResponseEntity<APIEmploye> getAPIEmployeUnique(@PathVariable(value = "matricule") String matricule,
                                                  @PathVariable(value = "tel") String tel,
                                                  @PathVariable(value = "mail") String mail)  throws Exception{
        System.out.println("recherche de l'employe "+matricule+" ; "+tel+" ; "+mail);
        APIEmploye employe = employeServiceImpl.read(matricule,tel,mail);
        return new ResponseEntity<>(employe, HttpStatus.OK);
    }
    //-------------------Créer un employé--------------------------------------------------------
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<APIEmploye> createAPIEmploye(@RequestBody APIEmploye employe) throws Exception {
        System.out.println("Création de l'employé " + employe.getMatricule());
        employeServiceImpl.create(employe);
        return new ResponseEntity<>(employe, HttpStatus.OK);
    }

    //-------------------Mettre à jour unemployé d'un id donné--------------------------------------------------------
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<APIEmploye> majAPIEmploye(@PathVariable(value = "id") int id,@RequestBody APIEmploye nouvemp) throws Exception{
        System.out.println("maj de l'employé d'id =  " + id);
        nouvemp.setIdemploye(id);
        APIEmploye empact = employeServiceImpl.update(nouvemp);
        return new ResponseEntity<>(empact, HttpStatus.OK);
    }

    //-------------------Effacer un employé d'un id donné--------------------------------------------------------
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteAPIEmploye(@PathVariable(value = "id") int id) throws Exception{
        System.out.println("effacement de l'employé d'id " + id);
        APIEmploye employe = employeServiceImpl.read(id);
        employeServiceImpl.delete(employe);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //-------------------Retrouver tous les employés --------------------------------------------------------
    @RequestMapping(value =  "/all",method = RequestMethod.GET)
    public ResponseEntity<List<APIEmploye>> listAPIEmploye() throws Exception{
        System.out.println("recherche de tous les employés");
        return new ResponseEntity<>(employeServiceImpl.all(), HttpStatus.OK);
    }

    //-------------------Retrouver tous les employé triés et par page--------------------------------------------------------
    @RequestMapping(value =  "/allp",method = RequestMethod.GET)
    public ResponseEntity<Page<APIEmploye>> listAPIEmploye(Pageable pageable) throws Exception{
        System.out.println("recherche de tous les employés");
        return new ResponseEntity<>(employeServiceImpl.allp(pageable), HttpStatus.OK);
    }
    //-------------------Gérer les erreurs--------------------------------------------------------
    @ExceptionHandler({Exception.class})
    public ResponseEntity<Void>  handleIOException(Exception ex) {
        System.out.println("erreur : "+ex.getMessage());
        return ResponseEntity.notFound().header("error",ex.getMessage()).build();
    }
}
