package br.com.alura.screenmatch.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

@Entity
@Table (name = "episodios")
public class Episodios {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private int temporadas;

    private String titulo;

    private int numeroEpisodio;

    private double avaliacao;

    private LocalDate dataLancamento;

    @ManyToOne
    private Serie serie;

    public Episodios(){}

    public Episodios(int numeroTemporada, DadosEpisodio dadosEpisodio) {
        this.temporadas = numeroTemporada;
        this.titulo = dadosEpisodio.titulo();
        this.numeroEpisodio = dadosEpisodio.episodio();

        try {
            this.avaliacao = Double.valueOf(dadosEpisodio.avaliacao());
        }catch (NumberFormatException ex){
            this.avaliacao = 0.0;
        }

        try {
            this.dataLancamento = LocalDate.parse(dadosEpisodio.dataLancamento());
        }catch (DateTimeParseException ex){
            this.dataLancamento = null;
        }
    }

    public int getTemporadas() {
        return temporadas;
    }

    public void setTemporadas(int temporadas) {
        this.temporadas = temporadas;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getnumeroEpisodio() {
        return numeroEpisodio;
    }

    public void setnumeroEpisodio(int episodio) {
        this.numeroEpisodio = episodio;
    }

    public double getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(double avaliacao) {
        this.avaliacao = avaliacao;
    }

    public LocalDate getDataLancamento() {
        return dataLancamento;
    }

    public void setDataLancamento(LocalDate dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Serie getSerie() {
        return serie;
    }

    public void setSerie(Serie serie) {
        this.serie = serie;
    }

    @Override
    public String toString() {
        return
                "temporadas=" + temporadas +
                        ", titulo='" + titulo + '\'' +
                        ", episodio=" + numeroEpisodio +
                        ", avaliacao=" + avaliacao +
                        ", dataLancamento=" + dataLancamento;
    }
}
