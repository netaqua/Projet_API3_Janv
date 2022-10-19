package com.example.projet_api3_janv.entities;

import lombok.*;
import javax.management.ConstructorParameters;
import javax.persistence.*;
@Data
@NoArgsConstructor
@AllArgsConstructor @RequiredArgsConstructor
@ToString
@Entity
@Table(name = "APIEMPLOYE", schema = "ORA7", catalog = "ORCL")
public class APIEmploye {
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employe_generator")
    @SequenceGenerator(name="employe_generator", sequenceName ="APIEMPLOYE_SEQ", allocationSize=1)
    private Integer idemploye;
    @NonNull
    private String matricule;
    private String nom;
    @NonNull
    private String prenom;
    @NonNull
    private String tel;
    private String mail;
}