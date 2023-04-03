package dev.souzapablo.pokelovers.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    private String name;
    @ManyToOne
    private Address address;
    @ManyToOne
    private Pokemon favoritePokemon;

    public Customer(String name, Address address) {
        this.name = name;
        this.address = address;
    }

    public Customer() {

    }

    public Long getId() {
        return id;
    }
    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Pokemon getFavoritePokemon() {
        return favoritePokemon;
    }

    public void setFavoritePokemon(Pokemon favoritePokemon) {
        this.favoritePokemon = favoritePokemon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        name = name;
    }
}
