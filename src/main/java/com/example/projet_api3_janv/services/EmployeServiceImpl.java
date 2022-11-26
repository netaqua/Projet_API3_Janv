package com.example.projet_api3_janv.services;

import com.example.projet_api3_janv.entities.APIEmploye;
import com.example.projet_api3_janv.repositories.EmployeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(rollbackOn = Exception.class)
public class EmployeServiceImpl implements InterfEmployeService{
    @Autowired
    private EmployeRepository employeRepository;
    @Override
    public List<APIEmploye> read(String nom) {
        return employeRepository.findByNomLike(nom+"%");
    }

    @Override
    public APIEmploye read(String matricule, String tel, String mail) {
        return employeRepository.findAPIEmployeByMatriculeAndTelAndMail(matricule,tel,mail);
    }

    @Override
    public APIEmploye create(APIEmploye employe) throws Exception {
        employeRepository.save(employe);
        return employe;
    }
    @Override
    public APIEmploye read(Integer id) throws Exception {
        Optional<APIEmploye> oemp= employeRepository.findById(id);
        return oemp.get();
    }
    @Override
    public APIEmploye update(APIEmploye employe) throws Exception {
        read(employe.getIdemploye());
        employeRepository.save(employe);
        return employe;
    }
    @Override
    public void delete(APIEmploye employe) throws Exception {
        employeRepository.deleteById(employe.getIdemploye());
    }
    @Override
    public List<APIEmploye> all() throws Exception {
        return employeRepository.findAll();
    }

    @Override
    public Page<APIEmploye> allp(Pageable pageable) throws Exception {
        //pagination
        return employeRepository.findAll(pageable);
    }
}