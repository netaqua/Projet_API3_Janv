package com.example.projet_api3_janv.services;

import com.example.projet_api3_janv.entities.APIEmploye;
import com.example.projet_api3_janv.entities.APIProjet;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ProjetServiceImplTest {
    @Autowired
    private EmployeServiceImpl employeServiceImpl;

    @Autowired
    private ProjetServiceImpl projetServiceImpl;

    APIEmploye emp,emp2;
    APIProjet prj;

    @BeforeEach
    void setUp() {
        try{
            emp = new APIEmploye(null,"MatTest","NomTest","PrenomTest","TelTest","MailTest",new ArrayList<>());
            employeServiceImpl.create(emp);
            System.out.println("création du client : "+ emp);
            emp2 = new APIEmploye(null,"MatTest2","NomTest2","PrenomTest2","TelTest2","MailTest2",new ArrayList<>());
            employeServiceImpl.create(emp2);
            System.out.println("création du client : "+emp2);
            prj = new APIProjet(null,"NomProjetTest", LocalDate.now(),LocalDate.of(2024,2,21),3500,emp);
            projetServiceImpl.create(prj);
            System.out.println("création du projet : "+prj);
        }
        catch (Exception e){
            System.out.println("erreur de création du projet "+e);
        }
    }

    @AfterEach
    void tearDown() {
        try{
            projetServiceImpl.delete(prj);
        }
        catch(Exception e){
            System.out.println("erreur d'effacement du projet "+e);
        }
        try{
            employeServiceImpl.delete(emp);
            employeServiceImpl.delete(emp2);
        }
        catch(Exception e){
            System.out.println("erreur d'effacement de l'employé"+e);
        }
    }

    @Test
    void create() {
        assertNotEquals(0,prj.getIdprojet(),"numéro du projet non incrémenté");
        assertEquals("NomProjetTest",prj.getNomproj(),"nom du projet non enregistré : "+prj.getNomproj()+ " au lieu de NomProjetTest");
        assertEquals(3500,prj.getCout(),"cout du projet non enregistré : "+prj.getCout()+ " au lieu de 3500");
        assertEquals(emp.getIdemploye(),prj.getIdresponsable().getIdemploye(),"ID du responsable du projet non enregistré : "+prj.getIdresponsable().getIdemploye()+ " au lieu de "+emp.getIdemploye());
        assertEquals(LocalDate.now(),prj.getDatedebut(),"Date de debut du projet non enregistré : "+prj.getDatedebut()+ " au lieu de "+LocalDate.now());
        assertEquals(LocalDate.of(2024,2,21),prj.getDatefin(),"Date de debut du projet non enregistré : "+prj.getDatefin()+ " au lieu de "+LocalDate.of(2024,2,21));

    }

    @Test
    void read() {
        try{
            int idPrj= prj.getIdprojet();
            APIProjet prj2=projetServiceImpl.read(idPrj);
            assertEquals(prj2.getIdprojet(),prj.getIdprojet(),"numéro du projet non egale : "+prj2.getIdprojet()+"au lieu de "+prj.getIdprojet());
            assertEquals(prj2.getNomproj(),prj.getNomproj(),"nom du projet non egale : "+prj2.getNomproj()+ " au lieu de "+prj.getNomproj());
            assertEquals(prj2.getCout(),prj.getCout(),"cout du projet non egale : "+prj2.getCout()+ " au lieu de "+prj.getCout());
            assertEquals(prj2.getIdresponsable().getIdemploye(),prj.getIdresponsable().getIdemploye(),"ID du responsable du projet non egale : "+prj2.getIdresponsable().getIdemploye()+ " au lieu de "+prj.getIdresponsable().getIdemploye());
            assertEquals(prj2.getDatedebut(),prj.getDatedebut(),"Date de debut du projet non enregistré : "+prj2.getDatedebut()+ " au lieu de "+prj.getDatedebut());
            assertEquals(prj2.getDatefin(),prj.getDatefin(),"Date de debut du projet non enregistré : "+prj2.getDatefin()+ " au lieu de "+prj.getDatefin());
        }
        catch (Exception e){
            fail("recherche infructueuse "+e);
        }
    }

    @Test
    void update() {
        try{
            prj.setNomproj("NomProjetTest2");
            prj.setCout(5000);
            prj.setIdresponsable(emp2);
            prj.setDatedebut(LocalDate.of(2022,12,11));
            prj.setDatefin(LocalDate.of(2026,6,24));
            prj= projetServiceImpl.update(prj);

            assertEquals(prj.getNomproj(),"NomProjetTest2","nom du projet non egale : "+prj.getNomproj()+ " au lieu de NomProjetTest2");
            assertEquals(prj.getCout(),5000,"cout du projet non egale : "+prj.getCout()+ " au lieu de 5000");
            assertEquals(prj.getIdresponsable().getIdemploye(),emp2.getIdemploye(),"ID du responsable du projet non egale : "+prj.getIdresponsable().getIdemploye()+ " au lieu de "+emp2.getIdemploye());
            assertEquals(prj.getDatedebut(),LocalDate.of(2022,12,11),"Date de debut du projet non enregistré : "+prj.getDatedebut()+ " au lieu de "+LocalDate.of(2022,12,11));
            assertEquals(prj.getDatefin(),LocalDate.of(2026,6,24),"Date de debut du projet non enregistré : "+prj.getDatefin()+ " au lieu de "+LocalDate.of(2026,6,24));

        }
        catch(Exception e){
            fail("erreur de mise à jour "+e);
        }
    }

    @Test
    void delete() {
        try{
            projetServiceImpl.delete(prj);
            Assertions.assertThrows(Exception.class, () -> {
                projetServiceImpl.read(prj.getIdprojet());
            },"record non effacé");

        }
        catch(Exception e){
            fail("erreur d'effacement "+e);
        }
    }
    @Test
    void rechNomProj() {
        APIProjet prj = projetServiceImpl.read("NomProjetTest");
        boolean trouve=false;
        if(prj.getNomproj().equals("NomProjetTest")){
            trouve=true;
        }
        else {
            fail("La recherche ne correspond pas , nom = "+prj.getNomproj());
        }
        assertTrue(trouve,"projet non trouvé");
    }
    @Test
    void rechDateDebut() {
        List<APIProjet> lprj = projetServiceImpl.read(LocalDate.now());
        boolean trouve=false;
        for(APIProjet p : lprj) {
            if (p.getDatedebut().equals(LocalDate.now())) {
                trouve = true;
            } else {
                fail("La recherche ne correspond pas , date = " + p.getDatedebut());
            }
        }
        assertTrue(trouve,"projet non trouvé");
    }
    @Test
    void affCollection(){
        try {
            Collection<APIProjet> lprj = projetServiceImpl.getProjet(emp);
            boolean trouve = false;
            for(APIProjet p:lprj){
                if(p.getIdprojet().equals(prj.getIdprojet())){
                    trouve=true;
                    break;
                }
            }
            assertTrue(trouve,"projet absente de la liste de l'employe");
        }
        catch(Exception e){
            fail("Erreur de recherche "+e);
        }
    }

}