package com.tcunha2004.buscaSom.classificacao;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Artista {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String nome;
    @Enumerated(EnumType.STRING)
    private Tipo tipo;

    @OneToMany(mappedBy = "artista", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Musica> musicas;

    public Artista(){};

    public Artista(String nome, String tipo){
        this.nome = nome;
        this.tipo = Tipo.fromString(tipo);
    }

    public void addMusica(Musica musica) {
        this.musicas.add(musica);
    }

    public String getNome() {
        return nome;
    }

    @Override
    public String toString() {
        return " nome='" + nome + '\'' +
                ", tipo=" + tipo +
                ", musicas=" + musicas;
    }
}
