package com.example.demo.service;

import com.example.demo.model.Visite;
import com.example.demo.repo.VisiteRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VisiteService {

    private final VisiteRepository visiteRepository;

    public VisiteService(VisiteRepository visiteRepository) {
        this.visiteRepository = visiteRepository;
    }

    public List<Visite> getTop3VisitesByUai(String uai) {
        Date end = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(end);
        calendar.add(Calendar.WEEK_OF_YEAR, -3);
        Date start = calendar.getTime();
        return visiteRepository.findByUaiAndDateBetweenOrderByNbVisitesDesc(uai, start, end)
                .stream().limit(3).collect(Collectors.toList());
    }

    public List<Visite> getVisitesByUaiAndAggregation(String uai, String aggregation) {
        if (aggregation.equals("Année")) {
            Date end = new Date();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(end);
            calendar.add(Calendar.YEAR, -1);
            Date start = calendar.getTime();
            return visiteRepository.findByUaiAndDateBetweenOrderByNbVisitesDesc(uai, start, end);
        } else if (aggregation.equals("Mois")) {
            Date end = new Date();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(end);
            calendar.add(Calendar.MONTH, -1);
            Date start = calendar.getTime();
            return visiteRepository.findByUaiAndDateBetweenOrderByNbVisitesDesc(uai, start, end);
        } else {
            throw new IllegalArgumentException("Aggregation not supported: " + aggregation);
        }
    }

    public List<Visite> getVisitesByUaiAndYear(String uai, int year) {
        Date start = Date.from(LocalDate.of(year, Month.JANUARY, 1).atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date end = Date.from(LocalDate.of(year, Month.DECEMBER, 31).atTime(LocalTime.MAX).atZone(ZoneId.systemDefault()).toInstant());
        return visiteRepository.findByUaiAndDateBetweenOrderByNbVisitesDesc(uai, start, end);
    }

}

