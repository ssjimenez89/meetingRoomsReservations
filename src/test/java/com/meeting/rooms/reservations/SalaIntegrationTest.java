package com.meeting.rooms.reservations;

import static org.assertj.core.api.Assertions.assertThat;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.meeting.rooms.reservations.dto.SalaDTO;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SalaIntegrationTest {

    @LocalServerPort
    private int port;

    private final HttpClient httpClient = HttpClient.newHttpClient();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void shouldListAndCreateSalaWithBasicAuth() throws Exception {
        HttpRequest initialRequest = HttpRequest.newBuilder()
            .uri(URI.create("http://localhost:" + port + "/api/listar/salas"))
            .GET()
            .build();

        HttpResponse<String> initialResponse = httpClient.send(initialRequest, HttpResponse.BodyHandlers.ofString());
        assertThat(initialResponse.statusCode()).isEqualTo(200);

        SalaDTO sala = SalaDTO.builder()
            .nombre("Sala de pruebas")
            .capacidad(10)
            .disponibilidad(true)
            .reservas(new ArrayList<>())
            .build();

        String json = objectMapper.writeValueAsString(sala);
        String basicAuth = Base64.getEncoder()
            .encodeToString("admin:admin".getBytes(StandardCharsets.UTF_8));

        HttpRequest createRequest = HttpRequest.newBuilder()
            .uri(URI.create("http://localhost:" + port + "/api/salas"))
            .header("Content-Type", "application/json")
            .header("Authorization", "Basic " + basicAuth)
            .POST(HttpRequest.BodyPublishers.ofString(json))
            .build();

        HttpResponse<String> createResponse = httpClient.send(createRequest, HttpResponse.BodyHandlers.ofString());
        assertThat(createResponse.statusCode()).isEqualTo(200);

        SalaDTO createdSala = objectMapper.readValue(createResponse.body(), SalaDTO.class);
        assertThat(createdSala.getNombre()).isEqualTo("Sala de pruebas");
        assertThat(createdSala.getCapacidad()).isEqualTo(10);

        HttpRequest finalRequest = HttpRequest.newBuilder()
            .uri(URI.create("http://localhost:" + port + "/api/listar/salas"))
            .GET()
            .build();

        HttpResponse<String> finalResponse = httpClient.send(finalRequest, HttpResponse.BodyHandlers.ofString());
        assertThat(finalResponse.statusCode()).isEqualTo(200);
        assertThat(finalResponse.body()).contains("Sala de pruebas");
    }
}
