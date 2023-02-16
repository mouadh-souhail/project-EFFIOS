package com.example.demo.repo;

import com.example.demo.model.Visite;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface VisiteRepository extends JpaRepository<Visite, Long> {

    List<Visite> findByUaiAndDateBetweenOrderByNbVisitesDesc(String uai, Date start, Date end);

}

