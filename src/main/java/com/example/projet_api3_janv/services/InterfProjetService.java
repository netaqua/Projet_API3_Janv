package com.example.projet_api3_janv.services;

import com.example.projet_api3_janv.entities.APIEmploye;
import com.example.projet_api3_janv.entities.APIProjet;

import java.time.LocalDate;
import java.util.List;

public interface InterfProjetService extends InterfService<APIProjet>{
    APIProjet read(String nomProjet);

    List<APIProjet> read(LocalDate dateDebut);

    List<APIProjet> getProjet(APIEmploye emp);
}
