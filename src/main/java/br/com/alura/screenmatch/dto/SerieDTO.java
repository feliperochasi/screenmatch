package br.com.alura.screenmatch.dto;

import br.com.alura.screenmatch.model.Category;

public record SerieDTO(Long id,
                       String title,
                       Integer totalSeasons,
                       Double avalation,
                       Category genre,
                       String director,
                       String actors,
                       String plot,
                       String language,
                       String awards,
                       String poster) {
}
