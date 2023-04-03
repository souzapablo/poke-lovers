package dev.souzapablo.pokelovers.service;

import dev.souzapablo.pokelovers.model.Pokemon;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "pokemon", url = "https://pokeapi.co/api/v2/pokemon")
public interface IPokemonService {
    @GetMapping("/{name}")
    Pokemon searchPokemon(@PathVariable("name") String name);
}
