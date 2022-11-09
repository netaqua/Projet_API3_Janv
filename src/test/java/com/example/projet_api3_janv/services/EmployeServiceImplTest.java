package com.example.projet_api3_janv.services;

import com.example.projet_api3_janv.entities.APIEmploye;
import com.example.projet_api3_janv.entities.APIProjet;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EmployeServiceImplTest {

   @Autowired
   private EmployeServiceImpl employeServiceImp;

    APIEmploye emp;

    @BeforeEach
    void setUp() {
        try{
            emp = new APIEmploye(null,"MatTest","NomTest","PrenomTest","TelTest","Mailtest",new ArrayList<APIProjet>(null));
            employeServiceImp.create(emp);
            System.out.println("création du client : "+ emp);
        }
        catch (Exception e){
            System.out.println("erreur de création du client : "+ emp +" erreur : "+e);
        }
    }

    @AfterEach
    void tearDown() {
        try {
            employeServiceImp.delete(emp);
            System.out.println("effacement de l'employe : "+emp);
        }
        catch (Exception e){
            System.out.println("erreur d'effacement du client :"+emp+" erreur : "+e);
        }
    }
    @Test
    void read() {
        try{
            int numEmp=emp.getIdemploye();
            APIEmploye emp2 = employeServiceImp.read(numEmp);
            assertEquals("NomTest",emp2.getNom(),"noms différents "+"NomTest"+"-"+emp2.getNom());
            assertEquals("PrenomTest",emp2.getPrenom(),"prénoms différents "+"PrenomTest"+"-"+emp2.getPrenom());
            assertEquals("MailTest",emp2.getMail(),"mail différents "+"MailTest"+"-"+emp2.getMail());
            assertEquals("TelTest",emp2.getTel(),"telephone différents "+"TelTest"+"-"+emp2.getTel());
            assertEquals("MatTest",emp2.getMatricule(),"matricule différents "+"MatTest"+"-"+emp2.getMatricule());
        }
        catch (Exception e){
            fail("recherche infructueuse "+e);
        }
    }

    @Test
    void create() {
        assertNotEquals(0,emp.getIdemploye(),"id employé non incrémenté");
        assertEquals("TelTest",emp.getTel(),"tel employé non enregistré : "+emp.getTel()+ " au lieu de TelTest");
        assertEquals("PrenomTest",emp.getPrenom(),"prénom employé non enregistré : "+emp.getPrenom()+" au lieu de PrenomTest");
        assertEquals("Mailtest",emp.getMail(),"mail employé non enregistré : "+emp.getMail()+ " au lieu de MailTest");
    }

    @Test
    void update() {
        try{
            emp.setNom("NomTest2");
            emp.setPrenom("PrenomTest2");
            emp.setMatricule("MatriculeTest2");
            emp.setTel("TelTest2");
            emp.setMail("MailTest2");
            emp = employeServiceImp.update(emp);
            assertEquals("NomTest2",emp.getNom(),"noms différents "+"NomTest2"+"-"+emp.getNom());
            assertEquals("PrenomTest2",emp.getPrenom(),"prénoms différents "+"PrenomTest2"+"-"+emp.getPrenom());
            assertEquals("MailTest2",emp.getMail(),"mail différents "+"MailTest2"+"-"+emp.getMail());
            assertEquals("TelTest2",emp.getTel(),"telephone différents "+"TelTest2"+"-"+emp.getTel());
            assertEquals("MatTest2",emp.getMatricule(),"matricule différents "+"MatTest2"+"-"+emp.getMatricule());
        }
        catch(Exception e){
            fail("erreur de mise à jour "+e);
        }
    }

   @Test
    void delete() {
        try{
            employeServiceImp.delete(emp);    Assertions.assertThrows(Exception.class, () -> {
                employeServiceImp.read(emp.getIdemploye());    //test
            },"record non effacé");
        }
        catch(Exception e){
            fail("erreur d'effacement "+e);
        }
    }

    @Test
    void all() {
        try {
            List<APIEmploye> lemp = employeServiceImp.all();
            assertNotEquals(0,lemp.size(),"la liste ne contient aucun élément");
        }catch (Exception e){
            fail("erreur de recherche de tous les employés "+e);
        }
    }

}