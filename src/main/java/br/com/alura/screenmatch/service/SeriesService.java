package br.com.alura.screenmatch.service;

import br.com.alura.screenmatch.dto.SerieDTO;
import br.com.alura.screenmatch.repository.SerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SeriesService {
    @Autowired
    private SerieRepository serieRepository;

    public List<SerieDTO> getAllSeries() {
        return serieRepository.findAll()
                .stream()
                .map(s -> new SerieDTO(s.getId(), s.getTitle(), s.getTotalSeasons(), s.getAvaliation(),
                        s.getGenre(), s.getDirector(), s.getActors(), s.getPlot(), s.getLanguage(), s.getAwards(),
                        s.getPoster()))
                .collect(Collectors.toList());
    }
}
