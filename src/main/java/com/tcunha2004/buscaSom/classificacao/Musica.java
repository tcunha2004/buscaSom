package com.tcunha2004.buscaSom.classificacao;

import jakarta.persistence.*;

@Entity
public class Musica {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String nomeMusica;
    private String generoMusica;

    @ManyToOne
    private Artista artista;

    public Musica(){}

    public Musica(String nomeMusica, String generoMusica, Artista artista){
        this.nomeMusica = nomeMusica;
        this.generoMusica = generoMusica;
        artista.addMusica(this);
        this.artista = artista;
    }

    @Override
    public String toString() {
        return  " nomeMusica='" + nomeMusica + '\'' +
                ", generoMusica='" + generoMusica + '\'' +
                ", artista=" + artista.getNome();
    }
}
