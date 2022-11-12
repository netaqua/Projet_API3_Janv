package com.example.projet_api3_janv.entities;

import lombok.*;
import javax.management.ConstructorParameters;
import javax.persistence.*;
import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Data
@NoArgsConstructor
@AllArgsConstructor @RequiredArgsConstructor
@ToString
@Entity
@Table(name = "APIPROJET", schema = "ORA7", catalog = "ORCL")
public class APIProjet {
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "projet_generator")
    @SequenceGenerator(name="projet_generator", sequenceName ="APIPROJET_SEQ", allocationSize=1)
    private Integer idProjet;
    @NonNull
    private String nomProj;
    private LocalDate dateDebut;
    @NonNull
    private LocalDate dateFin;
    @NonNull
    private double cout;
    @JsonIgnore
    @ManyToOne @JoinColumn(name = "IDRESPONSABLE")
    private APIEmploye projetResp;
}
