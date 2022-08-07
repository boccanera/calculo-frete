package com.example.demo;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import com.example.demo.model.Cep;
import com.example.demo.utils.CEPUtils;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

import static java.time.temporal.ChronoUnit.MINUTES;

@Slf4j
public class ViaCepClient {

    private static final String viaCepUrl = "https://viacep.com.br/ws/";
    private static final Gson gson = new Gson();

    public static Cep findCep(String cepString) {
        CEPUtils.validaCep(cepString);
        try {

            //Como vai ser a chamada do client no request
            HttpClient httpClient = HttpClient.newBuilder()
                    .connectTimeout(Duration.of(1, MINUTES))
                    .build();

//          Qual URL vamos chamar, o conteúdo.
            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .GET()
                    .uri(URI.create(viaCepUrl + cepString + "/json")).timeout(Duration.of(1, MINUTES))
                    .build();

            // Junção de ambos.
            HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());


//           log.info("[VIA CEP API] - [RESULTADO DA BUSCA: {}]", httpResponse.body());


            return gson.fromJson(httpResponse.body(), Cep.class);

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }
}
