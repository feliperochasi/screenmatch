package br.com.alura.screenmatch.service;

import br.com.alura.screenmatch.dto.EpisodeDTO;
import br.com.alura.screenmatch.dto.SerieDTO;
import br.com.alura.screenmatch.model.Serie;
import br.com.alura.screenmatch.repository.SerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SeriesService {
    @Autowired
    private SerieRepository serieRepository;

    public List<SerieDTO> getAllSeries() { return convertData(serieRepository.findAll()); }

    public List<SerieDTO> getTop5Series() {
        return convertData(serieRepository.findTop5ByOrderByAvaliationDesc());
    }

    public List<SerieDTO> getReleasesSeries() { return convertData(serieRepository.getReleasesSeries()); }

    public SerieDTO getSerieFromId(Long id) {
        Optional<Serie> serie = serieRepository.findById(id);

        if (serie.isPresent()) {
            Serie s = serie.get();
            return new SerieDTO(s.getId(), s.getTitle(), s.getTotalSeasons(), s.getAvaliation(),
                    s.getGenre(), s.getDirector(), s.getActors(), s.getPlot(), s.getLanguage(), s.getAwards(),
                    s.getPoster());
        }
        return null;
    }

    public List<EpisodeDTO> getAllSeasons(Long id) {
        Optional<Serie> serie = serieRepository.findById(id);

        if (serie.isPresent()) {
            Serie s = serie.get();
            return s.getEpisodes().stream()
                    .map(e -> new EpisodeDTO(e.getSeason(), e.getTitle(), e.getNumberEp()))
                    .collect(Collectors.toList());
        }
        return null;
    }

    private List<SerieDTO> convertData(List<Serie> series) {
        return series
                .stream()
                .map(s -> new SerieDTO(s.getId(), s.getTitle(), s.getTotalSeasons(), s.getAvaliation(),
                        s.getGenre(), s.getDirector(), s.getActors(), s.getPlot(), s.getLanguage(), s.getAwards(),
                        s.getPoster()))
                .collect(Collectors.toList());
    }

    public List<EpisodeDTO> getSeasonFromNumber(Long id, Integer seasonNumber) {
        return serieRepository.getEpisodesFromSeason(id, seasonNumber)
                .stream()
                .map(e -> new EpisodeDTO(e.getSeason(), e.getTitle(), e.getNumberEp()))
                .collect(Collectors.toList());
    }
}
