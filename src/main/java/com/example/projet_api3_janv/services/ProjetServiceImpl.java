package com.example.projet_api3_janv.services;

import com.example.projet_api3_janv.entities.APIEmploye;
import com.example.projet_api3_janv.entities.APIProjet;
import com.example.projet_api3_janv.repositories.EmployeRepository;
import com.example.projet_api3_janv.repositories.ProjetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public APIProjet create(APIProjet prj) throws Exception {
        projetRepository.save(prj);
        return prj;
    }

    @Override
    public APIProjet read(Integer idprojet) throws Exception {
        return projetRepository.findById(idprojet).get();
    }
    @Override
    public APIProjet read(String nomproj) throws Exception {
        return projetRepository.findProjetByNomproj(nomproj);
    }

    @Override
    public List<APIProjet> read(LocalDate datedebut) {
        return projetRepository.findByDatedebut(datedebut);
    }



    @Override
    public APIProjet update(APIProjet projet) throws Exception {
        read(projet.getIdprojet());
        projetRepository.save(projet);
        return projet;
    }

    @Override
    public void delete(APIProjet projet) throws Exception {
        projetRepository.deleteById(projet.getIdprojet());
    }

    @Override
    public List<APIProjet> all() throws Exception {
        return projetRepository.findAll();
    }

    @Override
    public Page<APIProjet> allp(Pageable pageable) throws Exception {
        return  projetRepository.findAll(pageable);
    }

    @Override
    public List<APIProjet> getProjet(APIEmploye emp) {
        List<APIProjet> lprj = projetRepository.findProjetByIdresponsable(emp);
        return lprj;
    }

}
