package com.github.jonashonecker.bc08restclient.rickandmorty;

import com.github.jonashonecker.bc08restclient.rickandmorty.api.Character;
import com.github.jonashonecker.bc08restclient.rickandmorty.api.Characters;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/characters")
public class RickAndMortyController {
    private final RickAndMortyService rickAndMortyService;

    public RickAndMortyController(RickAndMortyService rickAndMortyService) {
        this.rickAndMortyService = rickAndMortyService;
    }

    @GetMapping
    List<Character> getAllCharacters (@RequestParam(required = false) String status) {
        if (status == null) {
            return rickAndMortyService.getAllCharactersWithReducedProperties();
        } else {
            return rickAndMortyService.getAllCharactersWithReducedPropertiesByStatus(status);
        }
    }

    @GetMapping("/{id}")
    Character getCharacterById (@PathVariable String id) {
        return rickAndMortyService.getCharacterWithReducedPropertiesById(id);
    }
}
