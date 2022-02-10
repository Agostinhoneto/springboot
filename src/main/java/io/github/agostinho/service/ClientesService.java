package io.github.agostinho.service;

import io.github.agostinho.model.Cliente;
import io.github.agostinho.repository.ClientesRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class ClientesService {
    @Autowired

    private ClientesRepository repository;

    public ClientesService(ClientesRepository repository){
        this.repository = repository;
    }

    public void salvarCliente(Cliente cliente) {
        validarCliente(cliente);
        this.repository.persistir(cliente);
    }
    public void validarCliente(Cliente cliente){
         //aplicar validações
    }
}
