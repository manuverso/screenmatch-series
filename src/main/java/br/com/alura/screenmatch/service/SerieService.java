package br.com.alura.screenmatch.service;


import br.com.alura.screenmatch.dto.EpisodiosDTO;
import br.com.alura.screenmatch.dto.SeriesDTO;
import br.com.alura.screenmatch.model.Categoria;
import br.com.alura.screenmatch.model.Serie;
import br.com.alura.screenmatch.repository.SerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SerieService {

    @Autowired
    private SerieRepository repositorio;


    public List<SeriesDTO> obterTodasSeries(){
        return converteDados(repositorio.findAll());
    }

    public List<SeriesDTO> obterTop5Series(){
        return converteDados(repositorio.findTop5ByOrderByAvaliacaoDesc());
    }

    private List<SeriesDTO> converteDados(List<Serie> series){    //desacoplei dos outros metódos para esse para deixar mais limpo, já que ambos faziam esse stream()
        return series.stream()
                .map(s -> new SeriesDTO(s.getId(), s.getTitulo(),
                        s.getTotalTemporadas(), s.getAvaliacao(),
                        s.getGenero(), s.getAtores(), s.getPoster(), s.getSinopse()))
                .collect(Collectors.toList());
    }

    public List<SeriesDTO> seriesLancamento() {
        return converteDados(repositorio.encontrarEpisodiosMaisRecentes());
    }

    public SeriesDTO obterPorId(Long id) {
        Optional<Serie> serie = repositorio.findById(id);

        if (serie.isPresent()){
            Serie s = serie.get();
            return new SeriesDTO(s.getId(), s.getTitulo(),
                    s.getTotalTemporadas(), s.getAvaliacao(),
                    s.getGenero(), s.getAtores(), s.getPoster(), s.getSinopse());
        }
        return null;
    }


    public List<EpisodiosDTO> obterTemporadaPorSerie(Long id) {
        Optional<Serie> serie = repositorio.findById(id);

        if (serie.isPresent()){
            Serie s = serie.get();
            return s.getEpisodios().stream()
                    .map(e-> new EpisodiosDTO(e.getTemporadas(), e.getTitulo(), e.getnumeroEpisodio()))
                    .collect(Collectors.toList());
        }
        return null;
    }

    public List<EpisodiosDTO> obterATemporada(Long id, Long numero) {
        return repositorio.obterEpisodiosPorTemporada(id, numero)
                .stream()
                .map(e -> new EpisodiosDTO(e.getTemporadas(), e.getTitulo(),  e.getnumeroEpisodio()))
                .collect(Collectors.toList());
    }

    public List<SeriesDTO> obterSeriesPorCategoria(String nomeGenero) {
        Categoria categoria = Categoria.fromPortuga(nomeGenero);
        return converteDados(repositorio.findByGenero(categoria));
    }
}

