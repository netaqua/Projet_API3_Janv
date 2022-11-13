package com.example.projet_api3_janv.repositories;

import com.example.projet_api3_janv.entities.APIEmploye;
import com.example.projet_api3_janv.entities.APIProjet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public interface ProjetRepository extends JpaRepository<APIProjet, Integer> {
    public List<APIProjet> findProjetByIdresponsable(APIEmploye idresponsable);
    public APIProjet findProjetByNomproj(String nomproj);
    public List<APIProjet> findByDatedebut(LocalDate datedebut);
}
