package com.example.projet_api3_janv;

import com.example.projet_api3_janv.entities.APIEmploye;
import com.example.projet_api3_janv.entities.APIProjet;
import com.example.projet_api3_janv.repositories.ProjetRepository;
import com.example.projet_api3_janv.services.InterfEmployeService;
import com.example.projet_api3_janv.services.InterfProjetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class GestProjet {
    @Autowired
    private ProjetRepository projetRepository;


    @Autowired
    private InterfEmployeService employeServiceImpl;
    @Autowired
    private InterfProjetService projetServiceImpl;

    @RequestMapping("/rechparemp")
    public String read(@RequestParam int idemp,  Map<String, Object> model) {
        System.out.println("recherche de l'employe n° " + idemp);
        try {
          /*  Optional<Client> ocl = clientRepository.findById(idclient);//findById lance une exception si id inconnu
            Client cl = ocl.get();*/

            APIEmploye emp= employeServiceImpl.read(idemp);
            List<APIProjet> lprj = projetRepository.findProjetByIdresponsable(emp);
            model.put("monemp",emp);
            model.put("mesprj", lprj);
        } catch (Exception e) {
            System.out.println("----------erreur lors de la recherche -------- " + e);
            model.put("error", e.getMessage());
            return "error";
        }
        return "affemprj";
    }
}
