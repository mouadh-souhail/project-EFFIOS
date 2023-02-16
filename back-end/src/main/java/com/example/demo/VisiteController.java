package com.example.demo;

import com.example.demo.model.Visite;
import com.example.demo.service.VisiteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class VisiteController {

    private final VisiteService visiteService;

    public VisiteController(VisiteService visiteService) {
        this.visiteService = visiteService;
    }

    @GetMapping("/top3visites/{uai}")
    public List<Visite> getTop3VisitesByUai(@PathVariable String uai) {
        return visiteService.getTop3VisitesByUai(uai);
    }

    @GetMapping("/visites/{uai}")
    public List<Visite> getVisitesByUaiAndAggregation(@PathVariable String uai, @RequestParam("aggregation") String aggregation) {
        return visiteService.getVisitesByUaiAndAggregation(uai, aggregation);
    }
}

