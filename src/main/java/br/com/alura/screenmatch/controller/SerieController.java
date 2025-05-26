package br.com.alura.screenmatch.controller;

import br.com.alura.screenmatch.dto.SerieDTO;
import br.com.alura.screenmatch.service.SeriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/series")
public class SerieController {
    @Autowired
    private SeriesService seriesService;

    @GetMapping("")
    public List<SerieDTO> getSeries() {
        return seriesService.getAllSeries();
    }

    @GetMapping("/top5")
    public List<SerieDTO> getTop5Series() {
        return seriesService.getTop5Series();
    }

    @GetMapping("/lancamentos")
    public List<SerieDTO> getReleasesSeries() {
        return seriesService.getReleasesSeries();
    }

    @GetMapping("/{id}")
    public SerieDTO getSerieFromId(@PathVariable Long id) {
        return seriesService.getSerieFromId(id);
    }
}
