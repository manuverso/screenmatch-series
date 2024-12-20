package br.com.alura.screenmatch.repository;

import br.com.alura.screenmatch.model.Categoria;
import br.com.alura.screenmatch.model.Episodios;
import br.com.alura.screenmatch.model.Serie;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SerieRepository extends JpaRepository<Serie, Long> {
    Optional<Serie> findByTituloContainingIgnoreCase(String nomeSerie);

    List<Serie> findByAtoresContainingIgnoreCaseAndAvaliacaoGreaterThanEqual(String nomeAtor, double avaliacao);

    List<Serie> findTop5ByOrderByAvaliacaoDesc();

    List<Serie> findByGenero(Categoria categoria);

    /*O desafio será fazer uma derived query onde podemos fazer uma busca pelas séries que tenham
   até um número máximo de temporadas e com uma avaliação maior ou igual a um determinado valor.*/
    //minha resposta: List<Serie> findByTituloContainIgnoreCaseOrderByTotalTemporadasLessThanEqualAndAvaliacaoGreaterThanEqualDesc(String nomeSerie, int temporadas, double avaliacao);

    //resposta da prof:
    //List<Serie> findByTotalTemporadasLessThanEqualAndAvaliacaoGreaterThanEqual(int totalTemporadas, double avaliacao);

    @Query("SELECT s FROM Serie s WHERE s.totalTemporadas <= :totalTemporadas AND s.avaliacao >= :avaliacao") //usando JPQL
    List<Serie> seriesPorTemporadaEAvaliacao(int totalTemporadas, double avaliacao);


    @Query("SELECT e FROM Serie s JOIN s.episodios e WHERE e.titulo ILIKE %:trechoEpisodio%")
    List<Episodios> episodiosPorTrecho(String trechoEpisodio);


    @Query("SELECT e FROM Serie s JOIN s.episodios e WHERE s = :serie ORDER BY e.avaliacao DESC LIMIT 5")
    List<Episodios> topEpisodiosPorSerie(Serie serie);


    @Query("SELECT e FROM Serie s JOIN s.episodios e WHERE s = :serie AND YEAR(e.dataLancamento) >= :anoLancamento")
    List<Episodios> episodiosPorSerieEAno(Serie serie, int anoLancamento);


    @Query("SELECT s FROM Serie s " +
            "JOIN s.episodios e " +
            "GROUP BY s " +
            "ORDER BY MAX(e.dataLancamento) DESC LIMIT 5")
    List<Serie> encontrarEpisodiosMaisRecentes();


    @Query("SELECT e FROM Serie s JOIN s.episodios e WHERE s.id = :id AND e.temporadas = :numero")
    List<Episodios> obterEpisodiosPorTemporada(Long id, Long numero);
}
