package com.github.jonashonecker.bc08restclient.rickandmorty;

import com.github.jonashonecker.bc08restclient.rickandmorty.api.Characters;
import com.github.jonashonecker.bc08restclient.rickandmorty.api.Character;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class RickAndMortyService {

    private final RestClient restClient;

    public RickAndMortyService(RestClient.Builder restClientBuilder) {
        this.restClient = restClientBuilder
                .baseUrl("https://rickandmortyapi.com/api")
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
