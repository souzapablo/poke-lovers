package dev.souzapablo.pokelovers.service.impl;

import dev.souzapablo.pokelovers.dto.CreateCustomerDto;
import dev.souzapablo.pokelovers.dto.CustomerDetailsView;
import dev.souzapablo.pokelovers.dto.CustomerView;
import dev.souzapablo.pokelovers.model.*;
import dev.souzapablo.pokelovers.service.ICustomerService;
import dev.souzapablo.pokelovers.service.IPokemonService;
import dev.souzapablo.pokelovers.service.IViaCepService;
import org.hibernate.query.IllegalQueryOperationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class CustomerService implements ICustomerService {
    private final IViaCepService viaCepService;
    private final AddressRepository addressRepository;
    private final CustomerRepository customerRepository;
    private final IPokemonService pokemonService;
    private final PokemonRepository pokemonRepository;

    public CustomerService(IViaCepService viaCepService, AddressRepository addressRepository, CustomerRepository customerRepository, IPokemonService pokemonService, PokemonRepository pokemonRepository) {
        this.viaCepService = viaCepService;
        this.addressRepository = addressRepository;
        this.customerRepository = customerRepository;
        this.pokemonService = pokemonService;
        this.pokemonRepository = pokemonRepository;
    }

    @Override
    public Iterable<CustomerView> findAll() {
        Iterable<Customer> customers = this.customerRepository.findAll();
        ArrayList<CustomerView> customerViews = new ArrayList<>();
        for (Customer customer : customers) {
            customerViews.add(new CustomerView(customer.getName(), customer.getFavoritePokemon() == null ? null : customer.getFavoritePokemon().getName(), customer.getAddress().getCity()));
        }
        return customerViews;
    }

    @Override
    public CustomerDetailsView getById(Long id) {
        Customer foundCustomer = getCustomer(id);
        return new CustomerDetailsView(foundCustomer.getName(), foundCustomer.getFavoritePokemon() == null ? null : foundCustomer.getFavoritePokemon(), foundCustomer.getAddress());
    }

    @Override
    public CustomerDetailsView add(CreateCustomerDto user) {
        Address address = getAddress(user.getCep());
        Customer customer = new Customer(user.getName(), address);
        customerRepository.save(customer);
        return new CustomerDetailsView(customer.getName(), null, customer.getAddress());
    }

    @Override
    public void addPokemon(Long id, String pokemonName) {
        Customer customer = getCustomer(id);
        Pokemon pokemon = this.pokemonService.searchPokemon(pokemonName.toLowerCase());
        if (!pokemonRepository.existsById(pokemon.getId())) {
            pokemonRepository.save(pokemon);
        }
        customer.setFavoritePokemon(pokemon);
        customerRepository.save(customer);
    }

    @Override
    public void updateAddress(Long id, String newCep) {
        Customer customer = getCustomer(id);
        Address address = getAddress(newCep);
        customer.setAddress(address);
        customerRepository.save(customer);
    }

    @Override
    public void delete(Long id) {
        Customer customer = getCustomer(id);
        customerRepository.delete(customer);
    }

    private Customer getCustomer(Long id) {
        Optional<Customer> customer = this.customerRepository.findById(id);

        if (!customer.isPresent()) throw new IllegalQueryOperationException("User not found");

        return customer.get();

    }

    private Address getAddress(String cep) {
        Address address = this.viaCepService.searchCep(cep);
        if (!addressRepository.existsById(cep)) {
            addressRepository.save(address);
        }
        return address;
    }
}
