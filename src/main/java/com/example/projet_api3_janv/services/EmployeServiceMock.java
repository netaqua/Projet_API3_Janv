package com.example.projet_api3_janv.services;

import com.example.projet_api3_janv.entities.APIEmploye;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class EmployeServiceMock implements InterfEmployeService{

    private List<APIEmploye> le = new ArrayList<>();
    private int numact=0;

    @Override
    public List<APIEmploye> read(String nom) {
        List<APIEmploye> lenom = new ArrayList<>();
        le.stream().filter(emp->emp.getNom().equals(nom)).forEach(emp->lenom.add(emp));
        return lenom;
    }

    @Override
    public Page<APIEmploye> allp(Pageable pageable) throws Exception {
        return null;
    }

    @Override
    public APIEmploye create(APIEmploye apiEmploye) throws Exception {
        for(APIEmploye emp2 : le){
            if(emp2.getMatricule().equals(apiEmploye.getMatricule())&& (emp2.getMail().equals(apiEmploye.getMail()) && emp2.getTel().equals(apiEmploye.getTel()))) throw new Exception("doublon"); }
        numact++;
        apiEmploye.setIdemploye(numact);
        le.add(apiEmploye);
        return apiEmploye;
    }

    @Override
    public APIEmploye read(Integer id) throws Exception {
        for(APIEmploye emp : le){
            if(emp.getIdemploye().equals(id)) return emp;
        }
        throw new Exception("code inconnu");
    }

    @Override
    public APIEmploye update(APIEmploye apiEmploye) throws Exception {
        Integer id = apiEmploye.getIdemploye();
        APIEmploye oldEmp = read(id);
        oldEmp.setMatricule(apiEmploye.getMatricule());
        oldEmp.setNom(apiEmploye.getNom());
        oldEmp.setPrenom(apiEmploye.getPrenom());
        oldEmp.setTel(apiEmploye.getTel());
        oldEmp.setMail(apiEmploye.getMail());
        return read(oldEmp.getIdemploye());
    }

    @Override
    public void delete(APIEmploye empDel) throws Exception {
        Iterator<APIEmploye> ite = le.iterator();
        while(ite.hasNext()){
            APIEmploye emp = ite.next();
            if(emp.getIdemploye().equals(empDel.getIdemploye())){
                if(emp.getProjets() == null || emp.getProjets().isEmpty()) ite.remove();
                else throw new Exception("liste de projets non vide");
            }
        }
    }

    @Override
    public List<APIEmploye> all() throws Exception {
        return le;
    }
}
