package com.example.projet_api3_janv.repositories;

import com.example.projet_api3_janv.entities.APIEmploye;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeRepository extends JpaRepository<APIEmploye,Integer> {
    public List<APIEmploye> findByNomLike(String nom);
}