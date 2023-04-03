package dev.souzapablo.pokelovers.dto;

public class CustomerView {
    public String name;
    public String favoritePokemon;
    public String city;

    public CustomerView(String name, String pokemonName, String city) {
        this.name = name;
        this.favoritePokemon = pokemonName;
        this.city = city;
    }
}
