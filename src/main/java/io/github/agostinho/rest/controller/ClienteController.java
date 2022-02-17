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
import io.github.agostinho.domain.repository.Clientes;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class ClienteController {

    private Clientes clientes;

    public ClienteController(Clientes clientes){
        this.clientes = clientes;
    }

    @GetMapping("/api/clientes/{id}")
    @ResponseBody
    public ResponseEntity<Cliente> getClienteById(@PathVariable Integer id){
        Optional<Cliente> cliente = clientes.findById(id);
        if(cliente.isPresent()) {
            return ResponseEntity.ok(cliente.get());
        }
            return ResponseEntity.notFound().build();
        }

        @PostMapping("/api/clientes")
        @ResponseBody
        public ResponseEntity save(@RequestBody Cliente cliente){
            Cliente clienteSalvo = clientes.save(cliente);
            return ResponseEntity.ok(clienteSalvo);
        }

        @DeleteMapping("/api/clientes/{id}")
        @ResponseBody
    public ResponseEntity delete(@PathVariable Integer id){
        Optional<Cliente> cliente = clientes.findById(id);
        if(cliente.isPresent()) {
            clientes.delete(cliente.get());
            return ResponseEntity.noContent().build();
        }
            return ResponseEntity.notFound().build();
    }
}


