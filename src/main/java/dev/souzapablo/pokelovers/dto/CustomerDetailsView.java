package dev.souzapablo.pokelovers.dto;

import dev.souzapablo.pokelovers.model.Address;
import dev.souzapablo.pokelovers.model.Pokemon;

public class CustomerDetailsView {
    public String name;
    public Pokemon favoritePokemon;
    public Address address;

    public CustomerDetailsView(String name, Pokemon favoritePokemon, Address address) {
        this.name = name;
        this.favoritePokemon = favoritePokemon;
        this.address = address;
    }
}
