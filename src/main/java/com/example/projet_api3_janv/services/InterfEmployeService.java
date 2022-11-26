package com.example.projet_api3_janv.services;

import com.example.projet_api3_janv.entities.APIEmploye;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface InterfEmployeService extends InterfService<APIEmploye>{
    public List<APIEmploye> read(String nom);
    public APIEmploye read(String matricule,String tel, String mail);

    Page<APIEmploye> allp(Pageable pageable) throws Exception;
}
