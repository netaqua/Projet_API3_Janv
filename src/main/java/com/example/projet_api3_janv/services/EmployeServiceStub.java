package com.example.projet_api3_janv.services;

import com.example.projet_api3_janv.entities.APIEmploye;
import com.example.projet_api3_janv.entities.APIProjet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EmployeServiceStub implements InterfEmployeService{
    @Override
    public APIEmploye create(APIEmploye apiEmploye) throws Exception {
        apiEmploye.setIdemploye(1);
        return apiEmploye;
    }

    @Override
    public APIEmploye read(Integer id) throws Exception {
        APIEmploye emp = new APIEmploye(id,"MatTest","NomTest","PrenomTest","TelTest","MailTest",new ArrayList<>());
        emp.getProjets().add(new APIProjet(1,"NomProjetTest", LocalDate.now(),LocalDate.of(2024,2,21),3500,emp));
        return emp;
    }

    @Override
    public List<APIEmploye> read(String nom) {
        List<APIEmploye> le = new ArrayList<>();
        le.add(new APIEmploye(1,"MatTest","NomTest","PrenomTest","TelTest","MailTest",new ArrayList<>()));
        le.add(new APIEmploye(2,"MatTest2","NomTest2","PrenomTest2","TelTest2","MailTest2",new ArrayList<>()));
        return le;
    }

    @Override
    public APIEmploye update(APIEmploye apiEmploye) throws Exception {
        return apiEmploye;
    }

    @Override
    public void delete(APIEmploye apiEmploye) throws Exception {
    }

    @Override
    public List<APIEmploye> all() throws Exception {
        List<APIEmploye>le = new ArrayList<>();
        le.add(new APIEmploye(1,"MatTest","NomTest","PrenomTest","TelTest","MailTest",new ArrayList<>()));
        le.add(new APIEmploye(2,"MatTest2","NomTest2","PrenomTest2","TelTest2","MailTest2",new ArrayList<>()));
        return le;
    }

    @Override
    public Page<APIEmploye> allp(Pageable pageable) throws Exception {
        return null;
    }

}
