package com.example.projet_api3_janv.webservices;

import com.example.projet_api3_janv.entities.APIEmploye;
import com.example.projet_api3_janv.entities.APIProjet;
import com.example.projet_api3_janv.services.InterfEmployeService;
import com.example.projet_api3_janv.services.InterfProjetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@CrossOrigin(origins = "*", allowedHeaders = "*",exposedHeaders = "*")
@RestController
@RequestMapping("/projets")
public class RestProjet {
    @Autowired
    private InterfProjetService projetServiceImpl;
    @Autowired
    private InterfEmployeService employeServiceImpl;

    //-------------------Retrouver le projet correspondant à un n° donné--------------------------------------------------------
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<APIProjet> getAPIProjet(@PathVariable(value = "id") int id)  throws Exception{
        System.out.println("recherche du projet n° " + id);
        APIProjet prj = projetServiceImpl.read(id);
        return new ResponseEntity<>(prj, HttpStatus.OK);
    }
    //-------------------Retrouver le projet portant un nom donné--------------------------------------------------------
    @RequestMapping(value = "/nomproj={nomproj}", method = RequestMethod.GET)
    public ResponseEntity<APIProjet> listAPIProjetByNom(@PathVariable(value="nomproj") String nomproj) throws Exception{
        System.out.println("recherche de "+nomproj);
        APIProjet prj = projetServiceImpl.read(nomproj);
        return new ResponseEntity<>(prj, HttpStatus.OK);
    }

    //-------------------Retrouver les projets correspondant à un n° d'employé donné--------------------------------------------------------
    @RequestMapping(value = "/idresponsable={idresponsable}", method = RequestMethod.GET)
    public ResponseEntity<List<APIProjet>> getProjetByIdresponsable(@PathVariable(value = "idresponsable") int idresponsable)  throws Exception{
        System.out.println("recherche des projets de l'employé d'id " + idresponsable);
        APIEmploye emp = employeServiceImpl.read(idresponsable);
        List<APIProjet> lprj = projetServiceImpl.getProjet(emp);
        return new ResponseEntity<>(lprj, HttpStatus.OK);
    }

    //-------------------Créer un projet--------------------------------------------------------
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<APIProjet> createAPIProjet(@RequestBody APIProjet prj) throws Exception {
        System.out.println("Création du projet géré par " + prj.getIdresponsable().getMatricule());
        projetServiceImpl.create(prj);
        return new ResponseEntity<>(prj, HttpStatus.OK);
    }

    //-------------------Mettre à jour un projet d'un n° donné--------------------------------------------------------
    @RequestMapping(value = "/{idprojet}", method = RequestMethod.PUT)
    public ResponseEntity<APIProjet> majAPIProjet(@PathVariable(value = "idprojet") int idprojet,@RequestBody APIProjet nouvprj) throws Exception{
        System.out.println("maj du projet n° " + idprojet);
        nouvprj.setIdprojet(idprojet);
        APIProjet prjact = projetServiceImpl.update(nouvprj);
        return new ResponseEntity<>(prjact, HttpStatus.OK);
    }

    //-------------------Effacer un projet d'un id donné--------------------------------------------------------
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteAPIProjet(@PathVariable(value = "id") int id) throws Exception{
        System.out.println("effacement du projet n°" + id);
        APIProjet prj = projetServiceImpl.read(id);
        projetServiceImpl.delete(prj);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //-------------------Retrouver toutes les commandes --------------------------------------------------------
    @RequestMapping(value =  "/all",method = RequestMethod.GET)
    public ResponseEntity<List<APIProjet>> listAPIProjet() throws Exception{
        System.out.println("recherche de tous les projets");
        return new ResponseEntity<>(projetServiceImpl.all(), HttpStatus.OK);
    }

    //-------------------Retrouver toutes les commandes paginées--------------------------------------------------------
    @RequestMapping(value =  "/allp",method = RequestMethod.GET)
    public ResponseEntity<Page<APIProjet>> listAPIProjet(Pageable pageable) throws Exception{
        System.out.println("recherche de toutes les projets avec pagination");
        return new ResponseEntity<>(projetServiceImpl.allp(pageable), HttpStatus.OK);
    }

    //-------------------Gérer les erreurs--------------------------------------------------------
    @ExceptionHandler({Exception.class})
    public ResponseEntity<Void>  handleIOException(Exception ex) {
        System.out.println("erreur : "+ex.getMessage());
        return ResponseEntity.notFound().header("error",ex.getMessage()).build();
    }
}
