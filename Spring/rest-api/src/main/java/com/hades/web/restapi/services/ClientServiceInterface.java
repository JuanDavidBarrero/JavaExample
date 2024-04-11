package com.hades.web.restapi.services;

import java.util.List;

import com.hades.web.restapi.entity.Client;

public interface ClientServiceInterface {

    public List<Client> findAll();

    public Client findById(Long id);

    public Client save(Client client);

    public void delete(Long id);
}
