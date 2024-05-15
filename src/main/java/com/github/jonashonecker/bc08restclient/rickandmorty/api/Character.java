package com.github.jonashonecker.bc08restclient.rickandmorty.api;

public record Character(
        String id,
        String name,
        String status,
        String species,
        String type
) {
}
