package br.com.alura.screenmatch.dto;

import br.com.alura.screenmatch.model.Categoria;



public record SeriesDTO( long id,
                         String titulo,
                         int totalTemporadas,
                         double avaliacao,
                         Categoria genero,
                         String atores,
                         String poster,
                         String sinopse) {
}
