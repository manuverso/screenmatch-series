package br.com.alura.screenmatch.controller;

import br.com.alura.screenmatch.dto.EpisodiosDTO;
import br.com.alura.screenmatch.dto.SeriesDTO;
import br.com.alura.screenmatch.service.SerieService;
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
    private SerieService service;

    @GetMapping
    public List<SeriesDTO> obterSeries (){
        return service.obterTodasSeries();
    }

    @GetMapping("/top5")
    public List<SeriesDTO> obterTop5 (){
        return service.obterTop5Series();
    }

    @GetMapping("/lancamentos")
    public List<SeriesDTO> obterLancamentos(){
        return service.seriesLancamento();
    }

    @GetMapping("/{id}")
    public SeriesDTO obterPorId(@PathVariable Long id) {
        return service.obterPorId(id);
    }

    @GetMapping("/{id}/temporadas/todas")
    public List<EpisodiosDTO> obterTemporadas(@PathVariable Long id){
        return service.obterTemporadaPorSerie(id);
    }

    @GetMapping("/{id}/temporadas/{numero}")
    public List<EpisodiosDTO> obterNumeroTemporada(@PathVariable Long id, @PathVariable Long numero){
        return service.obterATemporada(id, numero);
    }

    @GetMapping("/categoria/{nomeGenero}")
    public List<SeriesDTO> obterSeriesPorCategoria(@PathVariable String nomeGenero) {
        return service.obterSeriesPorCategoria(nomeGenero);
    }
}
