package com.example.projet_api3_janv.entities;


import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@ToString
@Entity
@Table(name = "APIDISCIPLINES", schema = "ORA7", catalog = "ORCL")
public class APIDisciplines{/*
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "disciplines_generator")
    @SequenceGenerator(name="disciplines_generator", sequenceName ="APIDISCIPLINES_SEQ", allocationSize=1)
    private Integer iddisciplines;

    @NonNull
    private String nom;
    private String description;
*/
}