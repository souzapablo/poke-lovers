package dev.souzapablo.pokelovers.service;

import dev.souzapablo.pokelovers.model.Address;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "viacep", url = "https://viacep.com.br/ws/")
public interface IViaCepService {
    @GetMapping("/{cep}/json/")
    Address searchCep(@PathVariable("cep") String cep);
}
