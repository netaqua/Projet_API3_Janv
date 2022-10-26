package com.example.projet_api3_janv;


import com.example.projet_api3_janv.entities.APIEmploye;
import com.example.projet_api3_janv.services.EmployeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;
import java.util.Map;

@Controller
@RequestMapping("/employe")
public class GestEmploye {

    @Autowired
    private EmployeServiceImpl employeServiceImpl;
    @RequestMapping("/tous")
    public String affTous(Map<String, Object> model){
        System.out.println("recherche employe");
        try {
            Collection<APIEmploye> lemp= employeServiceImpl.all();
            model.put("mesEmployes", lemp);
        } catch (Exception e) {
            System.out.println("----------erreur lors de la recherche-------- " + e);
            model.put("error",e.getMessage());
            return "error";
        }
        return "affichageTousEmployes";
    }
    @RequestMapping("/create")
    public String create(@RequestParam String matricule, String nom,String prenom, @RequestParam String tel,@RequestParam String mail, Map<String, Object> model){
        System.out.println("création de client");
        APIEmploye emp = new APIEmploye(null,matricule,nom,prenom,tel,mail,null);
        try {
            employeServiceImpl.create(emp);
            System.out.println(emp.getIdemploye());
            emp = employeServiceImpl.read(emp.getIdemploye());
            employeServiceImpl.update(emp);
            model.put("nouvEmp",emp);
        } catch (Exception e) {
            System.out.println("----------erreur lors de la création------- - " + e);
            model.put("error",e.getMessage());
            return "error";
        }
        return "nouveauEmploye";
    }
    @RequestMapping("/read")
    public String read(@RequestParam int idemploye, Map<String, Object>
            model){
        System.out.println("recherche de l'employé n° "+idemploye);
        try {
            APIEmploye emp = employeServiceImpl.read(idemploye);
            model.put("monEmp",emp);
        }catch (Exception e) {
            System.out.println("----------erreur lors de la recherche ----- --- " + e);
            model.put("error",e.getMessage());
            return "error";
        }
        return "affEmploye";
    }

    @RequestMapping("/delete")
    public String delete(@RequestParam int idEmploye, Map<String, Object>model){
        System.out.println("recherche de l'employe n° "+idEmploye);
        try {
            APIEmploye emp = employeServiceImpl.read(idEmploye);
            employeServiceImpl.delete(emp);
            model.put("delEmp",emp);
        }catch (Exception e) {
            System.out.println("----------erreur lors de la recherche -------- " + e);
            model.put("error",e.getMessage());
            return "error";
        }
        return "suppEmploye";
    }

    @RequestMapping("/update")
    public String read(@RequestParam int idEmploye,String mail,String matricule,String tel, Map<String, Object>model){
        System.out.println("recherche de l'employé numéro :"+ idEmploye);
        APIEmploye emp = new APIEmploye(matricule,mail,tel);
        try {
            emp = employeServiceImpl.read(idEmploye);
            emp.setMail(mail);
            emp.setMatricule(matricule);
            emp.setTel(tel);
            employeServiceImpl.update(emp);
            model.put("monEmp",emp);
        }catch (Exception e) {
            System.out.println("----------erreur lors de la recherche -------- " + e);
            model.put("error",e.getMessage());
            return "error";
        }
        return "updtEmploye";
    }

}