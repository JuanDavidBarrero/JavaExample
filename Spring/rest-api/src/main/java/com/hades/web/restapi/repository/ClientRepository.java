package com.hades.web.restapi.repository;

import org.springframework.data.repository.CrudRepository;

import com.hades.web.restapi.entity.Client;

public interface ClientRepository extends CrudRepository<Client, Long> {
    
}
