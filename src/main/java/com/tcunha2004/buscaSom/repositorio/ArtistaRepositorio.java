package com.tcunha2004.buscaSom.repositorio;

import com.tcunha2004.buscaSom.classificacao.Artista;
import com.tcunha2004.buscaSom.classificacao.Musica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ArtistaRepositorio extends JpaRepository<Artista, Integer> {

    @Query("SELECT a FROM Artista a WHERE a.nome ILIKE :nome")
    Optional<Artista> buscarArtistaPeloNome(String nome);

    @Query("SELECT m FROM Artista a JOIN a.musicas m")
    List<Musica> listarMusicas();

    @Query("SELECT m FROM Artista a JOIN a.musicas m WHERE a = :artista")
    List<Musica> listarMusicasDeUmArtista(Artista artista);
}
