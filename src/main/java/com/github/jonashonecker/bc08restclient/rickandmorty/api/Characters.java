package com.github.jonashonecker.bc08restclient.rickandmorty.api;

import java.util.List;

public record Characters(
        List<Character> results
) {
}
