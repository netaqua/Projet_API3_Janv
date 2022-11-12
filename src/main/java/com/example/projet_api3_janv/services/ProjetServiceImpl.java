package com.example.projet_api3_janv.services;

import com.example.projet_api3_janv.entities.APIEmploye;
import com.example.projet_api3_janv.entities.APIProjet;
import com.example.projet_api3_janv.repositories.EmployeRepository;
import com.example.projet_api3_janv.repositories.ProjetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
@Service
@Transactional(rollbackOn = Exception.class)
public class ProjetServiceImpl implements InterfProjetService{
    @Autowired
    private ProjetRepository projetRepository;
    @Autowired
    private EmployeRepository employeRepository;

    @Override
    public APIProjet create(APIProjet projet) throws Exception {
        projetRepository.save(projet);
        return projet;
    }

    @Override
    public APIProjet read(String nomProj) {
        return projetRepository.findProjetByNomProj(nomProj);
    }

    @Override
    public List<APIProjet> read(LocalDate dateDebut) {
        return projetRepository.findByDateDebut(dateDebut);
    }

    @Override
    public APIProjet read(Integer idProjet) throws Exception {
        return projetRepository.findById(idProjet).get();
    }

    @Override
    public APIProjet update(APIProjet projet) throws Exception {
        read(projet.getIdProjet());
        projetRepository.save(projet);
        return projet;
    }

    @Override
    public void delete(APIProjet projet) throws Exception {
        projetRepository.deleteById(projet.getIdProjet());
    }

    @Override
    public List<APIProjet> all() throws Exception {
        return projetRepository.findAll();
    }

    /*@Override
    public Page<APIProjet> allp(Pageable pageable) throws Exception {
        return  projetRepository.findAll(pageable);
    }*/

    @Override
    public List<APIProjet> getProjet(APIEmploye emp) {
        List<APIProjet> lprj = projetRepository.findProjetByProjetResp(emp);
        return lprj;
    }

}
