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

package io.github.agostinho.domain.repository;

import io.github.agostinho.domain.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface Clientes extends JpaRepository<Cliente, Integer> {

    @Query(value = " select * from cliente c where c.nome like :'%nome'", nativeQuery = true)
    List<Cliente> encontrarPorNome(@Param("nome") String nome);
    @Query(" delete from Cliente c where c.nome =:nome")
    @Modifying
    void deleteByNome(String nome);
    boolean existsByNome(String nome);

    @Query("select c from Cliente c left join fetch c.pedidos where c.id =: id")
    Cliente findClienteFetchPedidos(@Param("id") Integer id);

    }
