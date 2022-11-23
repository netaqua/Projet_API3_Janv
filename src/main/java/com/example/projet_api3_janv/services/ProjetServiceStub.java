package com.example.projet_api3_janv.services;

import com.example.projet_api3_janv.entities.APIEmploye;
import com.example.projet_api3_janv.entities.APIProjet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ProjetServiceStub implements InterfProjetService{
    @Override
    public APIProjet read(String nomProjet) {
        APIEmploye emp = new APIEmploye(null,"MatTest","NomTest","PrenomTest","TelTest","MailTest",new ArrayList<>());
        APIProjet prj= new APIProjet(null,nomProjet, LocalDate.now(),LocalDate.of(2024,2,21),3500,emp);
        return prj;
    }

    @Override
    public List<APIProjet> read(LocalDate dateDebut) {
        List<APIProjet>lprj = new ArrayList<>();
        APIEmploye emp = new APIEmploye(1,"MatTest","NomTest","PrenomTest","TelTest","MailTest",new ArrayList<>());
        lprj.add(new APIProjet(1,"NomProjetTest", dateDebut,LocalDate.of(2024,2,21),3500,emp));
        lprj.add(new APIProjet(2,"NomProjetTest2", dateDebut,LocalDate.of(2025,6,16),4800,emp));
        return lprj;
    }

    @Override
    public List<APIProjet> getProjet(APIEmploye emp) {
        List<APIProjet>lprj = new ArrayList<>();
        lprj.add(new APIProjet(1,"NomProjetTest", LocalDate.now(),LocalDate.of(2024,2,21),3500,emp));
        lprj.add(new APIProjet(2,"NomProjetTest2", LocalDate.now(),LocalDate.of(2025,6,16),4800,emp));
        return lprj;
    }

    @Override
    public APIProjet create(APIProjet apiProjet) throws Exception {
        apiProjet.setIdprojet(1);
        return apiProjet;
    }

    @Override
    public APIProjet read(Integer id) throws Exception {
        APIEmploye emp = new APIEmploye(null,"MatTest","NomTest","PrenomTest","TelTest","MailTest",new ArrayList<>());
        APIProjet prj= new APIProjet(id,"NomProjetTest", LocalDate.now(),LocalDate.of(2024,2,21),3500,emp);
        return prj;
    }

    @Override
    public APIProjet update(APIProjet apiProjet) throws Exception {
        return apiProjet;
    }

    @Override
    public void delete(APIProjet apiProjet) throws Exception {

    }

    @Override
    public List<APIProjet> all() throws Exception {
        List<APIProjet>lprj = new ArrayList<>();

       APIEmploye emp = new APIEmploye(1,"MatTest","NomTest","PrenomTest","TelTest","MailTest",new ArrayList<>());
        lprj.add(new APIProjet(1,"NomProjetTest", LocalDate.now(),LocalDate.of(2024,2,21),3500,emp));
        lprj.add(new APIProjet(2,"NomProjetTest2", LocalDate.now(),LocalDate.of(2025,6,16),4800,emp));
        return lprj;
    }

    @Override
    public Page<APIProjet> allp(Pageable pageable) throws Exception {
        return null;
    }

}
