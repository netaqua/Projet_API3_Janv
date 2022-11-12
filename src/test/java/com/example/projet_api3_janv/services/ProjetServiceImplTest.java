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
            System.out.println(emp);
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
        assertNotEquals(0,prj.getIdProjet(),"numéro du projet non incrémenté");
        assertEquals("NomProjetTest",prj.getNomProj(),"nom du projet non enregistré : "+prj.getNomProj()+ " au lieu de NomProjetTest");
        assertEquals(3500,prj.getCout(),"cout du projet non enregistré : "+prj.getCout()+ " au lieu de 3500");
        assertEquals(emp.getIdemploye(),prj.getProjetResp().getIdemploye(),"ID du responsable du projet non enregistré : "+prj.getProjetResp().getIdemploye()+ " au lieu de "+emp.getIdemploye());
        assertEquals(LocalDate.now(),prj.getDateDebut(),"Date de debut du projet non enregistré : "+prj.getDateDebut()+ " au lieu de "+LocalDate.now());
        assertEquals(LocalDate.of(2024,2,21),prj.getDateFin(),"Date de debut du projet non enregistré : "+prj.getDateFin()+ " au lieu de "+LocalDate.of(2024,2,21));

    }

    @Test
    void read() {
        try{
            int idPrj= prj.getIdProjet();
            APIProjet prj2=projetServiceImpl.read(idPrj);
            assertEquals(prj2.getIdProjet(),prj.getIdProjet(),"numéro du projet non egale : "+prj2.getIdProjet()+"au lieu de "+prj.getIdProjet());
            assertEquals(prj2.getNomProj(),prj.getNomProj(),"nom du projet non egale : "+prj2.getNomProj()+ " au lieu de "+prj.getNomProj());
            assertEquals(prj2.getCout(),prj.getCout(),"cout du projet non egale : "+prj2.getCout()+ " au lieu de "+prj.getCout());
            assertEquals(prj2.getProjetResp().getIdemploye(),prj.getProjetResp().getIdemploye(),"ID du responsable du projet non egale : "+prj2.getProjetResp().getIdemploye()+ " au lieu de "+prj.getProjetResp().getIdemploye());
            assertEquals(prj2.getDateDebut(),prj.getDateDebut(),"Date de debut du projet non enregistré : "+prj2.getDateDebut()+ " au lieu de "+prj.getDateDebut());
            assertEquals(prj2.getDateFin(),prj.getDateFin(),"Date de debut du projet non enregistré : "+prj2.getDateFin()+ " au lieu de "+prj.getDateFin());
        }
        catch (Exception e){
            fail("recherche infructueuse "+e);
        }
    }

    @Test
    void update() {
        try{
            prj.setNomProj("NomProjetTest2");
            prj.setCout(5000);
            prj.setProjetResp(emp2);
            prj.setDateDebut(LocalDate.of(2022,12,11));
            prj.setDateFin(LocalDate.of(2026,6,24));
            prj= projetServiceImpl.update(prj);

            assertEquals(prj.getNomProj(),"NomProjetTest2","nom du projet non egale : "+prj.getNomProj()+ " au lieu de NomProjetTest2");
            assertEquals(prj.getCout(),5000,"cout du projet non egale : "+prj.getCout()+ " au lieu de 5000");
            assertEquals(prj.getProjetResp().getIdemploye(),emp2.getIdemploye(),"ID du responsable du projet non egale : "+prj.getProjetResp().getIdemploye()+ " au lieu de "+emp2.getIdemploye());
            assertEquals(prj.getDateDebut(),LocalDate.of(2022,12,11),"Date de debut du projet non enregistré : "+prj.getDateDebut()+ " au lieu de "+LocalDate.of(2022,12,11));
            assertEquals(prj.getDateFin(),LocalDate.of(2026,6,24),"Date de debut du projet non enregistré : "+prj.getDateFin()+ " au lieu de "+LocalDate.of(2026,6,24));

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
                projetServiceImpl.read(prj.getIdProjet());
            },"record non effacé");

        }
        catch(Exception e){
            fail("erreur d'effacement "+e);
        }
    }
    @Test
    void rechNomProj() {
        APIProjet prj = projetServiceImpl.read("NomTest");
        boolean trouve=false;
        if(prj.getNomProj().equals("NomProjetTest")){
            trouve=true;
        }
        else {
            fail("La recherche ne correspond pas , nom = "+prj.getNomProj());
        }
        assertTrue(trouve,"projet non trouvé");
    }
    @Test
    void rechDateDebut() {
        List<APIProjet> lprj = projetServiceImpl.read(LocalDate.now());
        boolean trouve=false;
        for(APIProjet p : lprj) {
            if (p.getDateDebut().equals(LocalDate.now())) {
                trouve = true;
            } else {
                fail("La recherche ne correspond pas , date = " + p.getDateDebut());
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
                if(p.getIdProjet().equals(prj.getIdProjet())){
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