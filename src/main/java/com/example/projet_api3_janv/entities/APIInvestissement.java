package com.example.projet_api3_janv.entities;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@ToString
@Entity
@Table(name = "APIINVESTISSEMENT", schema = "ORA7", catalog = "ORCL")
public class APIInvestissement{/*
    @Id
    private APIDisciplines disciplines;

    @Id
    private APIProjet projet;

    private int quantitejh;
*/
}