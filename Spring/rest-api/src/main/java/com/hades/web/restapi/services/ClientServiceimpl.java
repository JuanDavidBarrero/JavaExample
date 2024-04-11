package com.hades.web.restapi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hades.web.restapi.entity.Client;
import com.hades.web.restapi.repository.ClientRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ClientServiceimpl implements ClientServiceInterface {

    @Autowired
    private ClientRepository repository;

    @Override
    public List<Client> findAll() {
        return (List<Client>) repository.findAll();
    }

    @Override
    public Client findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Client save(Client client) {
        return repository.save(client);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

}
