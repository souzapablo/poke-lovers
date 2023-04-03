package dev.souzapablo.pokelovers.controller;

import dev.souzapablo.pokelovers.dto.CreateCustomerDto;
import dev.souzapablo.pokelovers.dto.CustomerDetailsView;
import dev.souzapablo.pokelovers.dto.CustomerView;
import dev.souzapablo.pokelovers.service.ICustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/users")
public class CustomerController {

    private final ICustomerService customerService;

    public CustomerController(ICustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public ResponseEntity<Iterable<CustomerView>> getAll() {
        return ResponseEntity.ok(this.customerService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDetailsView> getById(@PathVariable Long id) {
        CustomerDetailsView customerView = this.customerService.getById(id);
        return ResponseEntity.ok(customerView);
    }

    @PostMapping
    public ResponseEntity<CustomerDetailsView> add(@RequestBody CreateCustomerDto createUserDto) {
        CustomerDetailsView customerDetails = this.customerService.add(createUserDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(customerDetails);
    }

    @PatchMapping("pokemon")
    public ResponseEntity<String> addPokemon(@RequestParam(name = "customerId") Long id, @RequestParam(name = "pokemon") String name) {
        this.customerService.addPokemon(id, name);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Pokemon added.");
    }

    @PatchMapping("new-address")
    public ResponseEntity<String> updateAddress(@RequestParam(name = "customerId") Long id, @RequestParam(name = "newCep") String newCep) {
        this.customerService.updateAddress(id, newCep);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Address updated.");
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        this.customerService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .body("Costumer deleted.");
    }
}
