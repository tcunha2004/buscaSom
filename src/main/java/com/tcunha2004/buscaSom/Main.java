package com.tcunha2004.buscaSom;

import com.tcunha2004.buscaSom.classificacao.Artista;
import com.tcunha2004.buscaSom.classificacao.Musica;
import com.tcunha2004.buscaSom.repositorio.ArtistaRepositorio;
import com.tcunha2004.buscaSom.servico.ConsumoAPIGPT;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Main {
    Scanner scan = new Scanner(System.in);
    ArtistaRepositorio repositorio;

    Main(ArtistaRepositorio repositorio){
        this.repositorio = repositorio;
    }

    public void menu(){
        int opcao;
        do {
            System.out.println("""
                \n
                1- Cadastrar artistas
                2- Cadastrar musicas
                3- Listar musicas
                4- Buscar musicas por artista
                5- Pesquisar sobre dados do artista
                
                0- Sair
                """);
            opcao = scan.nextInt(); scan.nextLine();
            switch (opcao){
                case 1:
                    cadastrarArtista();
                    break;
                case 2:
                    cadastrarMusica();
                    break;
                case 3:
                    listarMusicas();
                    break;
                case 4:
                    buscarMusicaPorArtista();
                    break;
                case 5:
                    pesquisarArtista();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Invalido");
            }
        } while (opcao != 0);
    }

    private void cadastrarArtista() {
        System.out.println("Qual o tipo do seu artista: solo/dupla/banda?");
        var tipo = scan.nextLine();
        System.out.println("Digite o nome do seu artista/dupla/banda: ");
        var nome = scan.nextLine();
        System.out.println("Deseja cadastrar outro artista? (S/N)");
        var resposta = scan.nextLine().toUpperCase();

        Artista artista = new Artista(nome, tipo);
        repositorio.save(artista);

        switch (resposta){
            case "S":
                cadastrarArtista();
                break;
            case "N":
                break;
            default:
                System.out.println("Opcao Invalida!");
                break;
        }
    }

    private void listarArtistas(){
        List<Artista> listaDeArtistasCadastrados = repositorio.findAll();
        System.out.println("\n LISTA DE ARTISTAS:");
        listaDeArtistasCadastrados.forEach(System.out::println);
    }

    private void cadastrarMusica() {
        System.out.println("Digite o nome da musica: ");
        var nomeMusica = scan.nextLine();
        System.out.println("Digite o genero da musica: ");
        var generoMusica = scan.nextLine();
        listarArtistas();
        System.out.println("A qual artista pertence: ");
        var artistaMusicaNome = scan.nextLine();

        Optional<Artista> artista = repositorio.buscarArtistaPeloNome(artistaMusicaNome);
        if (artista.isPresent()){
            Artista artistaAchado = artista.get();
            Musica musica = new Musica(nomeMusica, generoMusica, artistaAchado);
            repositorio.save(artistaAchado);
            System.out.println("Musica salva!");
        } else {
            System.out.println("Nenhum artista correspondente achado!");
        }

    }

    private void listarMusicas() {
        List<Musica> listaMusicas = repositorio.listarMusicas();
        System.out.println("\n LISTA DE MUSICAS:");
        listaMusicas.forEach(System.out::println);
    }

    private void buscarMusicaPorArtista() {
        listarArtistas();
        System.out.println("Qual artista deseja escolher: ");
        var artistaNome = scan.nextLine();
        Optional<Artista> artista = repositorio.buscarArtistaPeloNome(artistaNome);
        if (artista.isPresent()){
            Artista artistaEncontrado = artista.get();
            List<Musica> musicasDoArtista = repositorio.listarMusicasDeUmArtista(artistaEncontrado);
            System.out.println("\n MUSICAS DO(A) " + artistaEncontrado.getNome());
            musicasDoArtista.forEach(System.out::println);
        } else {
            System.out.println("Artista nao encontrado!");
        }
    }

    private void pesquisarArtista() {
        listarArtistas();
        System.out.println("Qual artista deseja escolher: ");
        var artistaNome = scan.nextLine();
        Optional<Artista> artista = repositorio.buscarArtistaPeloNome(artistaNome);
        if (artista.isPresent()){
            Artista artistaEncontrado = artista.get();
            System.out.println("\nDados sobre o " + artistaEncontrado.getNome());
            var json = ConsumoAPIGPT.obterDados(artistaEncontrado.getNome());
            System.out.println(json);
        } else {
            System.out.println("Artista nao encontrado!");
        }
    }
}
