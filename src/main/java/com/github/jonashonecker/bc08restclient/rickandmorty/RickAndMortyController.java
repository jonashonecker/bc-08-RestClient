package com.github.jonashonecker.bc08restclient.rickandmorty;

import com.github.jonashonecker.bc08restclient.rickandmorty.api.Character;
import com.github.jonashonecker.bc08restclient.rickandmorty.api.Characters;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/characters")
public class RickAndMortyController {
    private final RickAndMortyService rickAndMortyService;

    public RickAndMortyController(RickAndMortyService rickAndMortyService) {
        this.rickAndMortyService = rickAndMortyService;
    }

    @GetMapping
    Characters getAllCharacters () {
        return rickAndMortyService.getAllCharactersWithReducedProperties();
    }

    @GetMapping("/{id}")
    Character getAllCharacters (@PathVariable String id) {
        return rickAndMortyService.getCharacterWithReducedPropertiesById(id);
    }
}
