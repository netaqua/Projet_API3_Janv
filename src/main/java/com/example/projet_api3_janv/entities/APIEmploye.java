package com.example.projet_api3_janv.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import javax.management.ConstructorParameters;
import javax.persistence.*;
import java.util.List;

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
    private String prenom;
    @NonNull
    private String tel;
    @NonNull
    private String mail;

    @JsonIgnore
    @OneToMany(mappedBy = "projetResp")
    @ToString.Exclude
    private List<APIProjet> projets;

}