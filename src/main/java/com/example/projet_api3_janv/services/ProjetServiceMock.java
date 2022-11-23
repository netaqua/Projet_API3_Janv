package com.example.projet_api3_janv.services;

import com.example.projet_api3_janv.entities.APIEmploye;
import com.example.projet_api3_janv.entities.APIProjet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ProjetServiceMock implements InterfProjetService{

    private List<APIProjet> lp = new ArrayList<>();
    private int numact=0;

    private InterfEmployeService employeServiceImpl;

    @Override
    public APIProjet read(String nomProjet) throws Exception {
        for(APIProjet p : lp){
            if(p.getNomproj().equals(nomProjet)) return p;
        }
        throw new Exception("nom inconnu");
    }

    @Override
    public List<APIProjet> read(LocalDate dateDebut) {
        List<APIProjet> lpDD = new ArrayList<>();
        lp.stream().filter(prj->prj.getDatedebut().equals(dateDebut)).forEach(prj->lpDD.add(prj));
        return lpDD;
    }

    @Override
    public List<APIProjet> getProjet(APIEmploye emp) {
        List<APIProjet> lpEmp = new ArrayList<>();
        lp.stream().filter(prj->prj.getIdresponsable().equals(emp)).forEach(prj->lpEmp.add(prj));
        return lpEmp;
    }

    @Override
    public Page<APIProjet> allp(Pageable pageable) throws Exception {
        return null;
    }

    @Override
    public APIProjet create(APIProjet apiProjet) throws Exception {
        numact++;
        apiProjet.setIdprojet(numact);
        APIEmploye emp = apiProjet.getIdresponsable();
        if(emp.getProjets()==null) emp.setProjets(new ArrayList<>());
        emp.getProjets().add(apiProjet);
        lp.add(apiProjet);
        return apiProjet;
    }

    @Override
    public APIProjet read(Integer id) throws Exception {
        for(APIProjet p : lp){
            if(p.getIdprojet().equals(id)) return p;
        }
        throw new Exception("Id inconnu");
    }

    @Override
    public APIProjet update(APIProjet apiProjet) throws Exception {
        Integer id = apiProjet.getIdprojet();
        APIProjet oldPrj = read(id);
        oldPrj.setNomproj(apiProjet.getNomproj());
        oldPrj.setCout(apiProjet.getCout());
        oldPrj.setDatefin(apiProjet.getDatefin());
        oldPrj.setDatedebut(apiProjet.getDatedebut());
        oldPrj.setIdresponsable(apiProjet.getIdresponsable());
        return read(oldPrj.getIdprojet());
    }

    @Override
    public void delete(APIProjet apiProjet) throws Exception {
        Iterator<APIProjet> itp= lp.iterator();
        while(itp.hasNext()) {
            APIProjet prj = itp.next();
            if(prj.getIdprojet().equals(apiProjet.getIdprojet())){
                prj.getIdresponsable().getProjets().remove(prj);
                itp.remove();
            }
        }
    }

    @Override
    public List<APIProjet> all() throws Exception {
        return lp;
    }
}
