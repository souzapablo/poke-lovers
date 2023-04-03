package dev.souzapablo.pokelovers.service;

import dev.souzapablo.pokelovers.dto.CreateCustomerDto;
import dev.souzapablo.pokelovers.dto.CustomerDetailsView;
import dev.souzapablo.pokelovers.dto.CustomerView;

public interface ICustomerService {
    Iterable<CustomerView> findAll();

    CustomerDetailsView getById(Long id);

    CustomerDetailsView add(CreateCustomerDto user);

    void addPokemon(Long customerId, String pokemonName);

    void updateAddress(Long id, String newCep);

    void delete(Long id);
}
