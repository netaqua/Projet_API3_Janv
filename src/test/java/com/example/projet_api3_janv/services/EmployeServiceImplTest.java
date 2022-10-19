package com.example.projet_api3_janv.services;

import com.example.projet_api3_janv.entities.APIEmploye;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EmployeServiceImplTest {

   @Autowired
   private EmployeServiceImpl employeServiceImp;
    APIEmploye emp;

    @BeforeEach
    void setUp() {
        try{
            emp = new APIEmploye(null,"matTest","NomTest","PrenomTest","TelTest","Mailtest");
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

   // @Test
    void read() {

    }

    @Test
    void create() {
        assertNotEquals(0,emp.getIdemploye(),"id employé non incrémenté");
        assertEquals("TelTest",emp.getTel(),"tel employé non enregistré : "+emp.getTel()+ " au lieu de TelTest");
        assertEquals("PrenomTest",emp.getPrenom(),"prénom employé non enregistré : "+emp.getPrenom()+" au lieu de PrenomTest");
        assertEquals("Mailtest",emp.getMail(),"mail employé non enregistré : "+emp.getMail()+ " au lieu de MailTest");
    }

    //@Test
    void update() {

    }

   // @Test
    void delete() {

    }

    //@Test
    void all() {

    }
}