package com.github.jonashonecker.bc08restclient.rickandmorty;

import com.github.jonashonecker.bc08restclient.rickandmorty.api.Characters;
import com.github.jonashonecker.bc08restclient.rickandmorty.api.Character;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class RickAndMortyService {

    private final RestClient restClient;

    public RickAndMortyService(RestClient.Builder restClientBuilder, @Value("${rickandmorty.api.base-url}") String basicUrl) {
        this.restClient = restClientBuilder
                .baseUrl(basicUrl)
                .build();
    }

    public List<Character> getAllCharactersWithReducedProperties() {
        return this.restClient.get()
                .uri("/character")
                .retrieve()
                .body(Characters.class)
                .results();
    }

    public Character getCharacterWithReducedPropertiesById(String id) {
        return this.restClient.get()
                .uri("/character/{id}", id)
                .retrieve()
                .body(Character.class);
    }

    public List<Character> getAllCharactersWithReducedPropertiesByStatus(String status) {
        return getAllCharactersWithReducedProperties().stream()
                .filter(c -> c.status().equals(status))
                .toList();
    }
}
