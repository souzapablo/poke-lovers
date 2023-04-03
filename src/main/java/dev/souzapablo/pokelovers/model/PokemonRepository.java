package dev.souzapablo.pokelovers.model;

import org.springframework.data.repository.CrudRepository;

public interface PokemonRepository extends CrudRepository<Pokemon, Long> {
}
