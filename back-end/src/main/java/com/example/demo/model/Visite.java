package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Date;

@Entity
@Table(name = "fr_en_dnma_par_uai_appareils")
public class Visite {

    @Id
    private Long id;
    private String uai;
    private Date date;
    private Integer nb_visites;
    private String appareil;

    // Constructeurs, getters, setters, etc.
}

