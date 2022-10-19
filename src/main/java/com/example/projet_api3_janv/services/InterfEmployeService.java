package com.example.projet_api3_janv.services;

import com.example.projet_api3_janv.entities.APIEmploye;

import java.util.List;

public interface InterfEmployeService extends InterfService<APIEmploye>{
    public List<APIEmploye> read(String nom);
}
