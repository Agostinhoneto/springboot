/*
 * Copyright (C) 2022 REPLACE_WITH_NAME
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.github.agostinho.rest.controller;
import io.github.agostinho.domain.entity.Cliente;
import io.github.agostinho.domain.entity.Produto;
import io.github.agostinho.domain.repository.Produtos;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {
    private Produtos repository;

    public ProdutoController(Produtos repository) {
        this.repository = repository;
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public Produto save(@RequestBody Produto produto){
        return repository.save(produto);
    }

    @PutMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    public void update(@PathVariable Integer id,@RequestBody Produto produto){
        repository.findById(id)
                .map(p->{
                    produto.setId(p.getId());
                    repository.save(produto);
                    return produto;
                }).orElseThrow(()->
                        new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Produto não encontrado"));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    public void delete(@PathVariable Integer id){
        repository.findById(id)
                .map(p->{
                    repository.delete(p);
                    return Void.TYPE;
                }).orElseThrow(()->
                        new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "Produto não encontrado"));
    }

    @GetMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    public Produto getById(@PathVariable Integer id){
        return repository
                .findById(id)
                .orElseThrow(() ->new ResponseStatusException(NOT_FOUND,
                        "Produto não encontrado"));
    }

    @GetMapping
    public List<Produto> find(Produto filtro){
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(
                        ExampleMatcher.StringMatcher.CONTAINING);
        Example example = Example.of(filtro, matcher);
        return repository.findAll(example);
    }

}
