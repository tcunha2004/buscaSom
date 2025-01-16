package com.tcunha2004.buscaSom.servico;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsumoAPIGPT {
    private static final String ENDPOINT = "https://api.openai.com/v1/chat/completions";
    private static final String API_KEY = System.getenv("OPENAI_API_KEY");
    public static String obterDados(String nomeArtista) {

        HttpClient client = HttpClient.newHttpClient();

        // Modelo + Conteudo da requisicao
        String requestBody = """
        {
            "model": "gpt-3.5-turbo",
            "messages": [
                {"role": "system", "content": "Você é um assistente especializado em informações sobre artistas musicais."},
                {"role": "user", "content": "Me fale sobre: %s"}
            ]
        }
        """.formatted(nomeArtista);


        // Onde sera enviado (ENDPOINT) + Autorizacao da APIKEY + Tipo do conteudo passado (json)
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(ENDPOINT))
                .header("Authorization", "Bearer " + API_KEY)
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        // Pega a resposta como string (json)
        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        String json = response.body();
        return json;
    }
}
