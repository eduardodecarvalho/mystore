package com.mystore.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mystore.domain.Client;
import com.mystore.exceptions.MyStoreBusinessException;
import com.mystore.repositories.ClientRepository;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    public Client findById(final Integer id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new MyStoreBusinessException(MyStoreBusinessException.CLIENT_NOT_FOUND));
    }

    public Integer create(final Client client) {
        return clientRepository.save(client).getId();
    }

    public void delete(final Integer id) {
        final Client client = clientRepository.findById(id)
                .orElseThrow(() -> new MyStoreBusinessException(MyStoreBusinessException.CLIENT_NOT_FOUND));
        clientRepository.delete(client);

    }

}