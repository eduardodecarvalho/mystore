package com.mystore.services;

import com.mystore.domain.Client;
import com.mystore.exceptions.MyStoreBusinessException;
import com.mystore.repositories.ClientRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    public Client findById(final Integer id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new MyStoreBusinessException(MyStoreBusinessException.CLIENT_NOT_FOUND));
    }

    @Transactional
    public Integer create(final Client client) {
        if (clientRepository.countByEmail(client.getEmail()) > 0) {
            throw new MyStoreBusinessException(MyStoreBusinessException.EMAIL_ALREDY_REGISTERED);
        }
        if (clientRepository.countByNationalRegister(client.getNationalRegister()) > 0) {
            throw new MyStoreBusinessException(MyStoreBusinessException.NATIONAL_REGISTER_ALREDY_REGISTERED);
        }
        client.getAddresses().forEach(a -> a.setClient(client));
        client.getPhones().forEach(p -> p.setClient(client));
        return clientRepository.save(client).getId();
    }

    public void delete(final Integer id) {
        final Client client = clientRepository.findById(id)
                .orElseThrow(() -> new MyStoreBusinessException(MyStoreBusinessException.CLIENT_NOT_FOUND));
        clientRepository.delete(client);

    }

    public Integer update(final Integer id, final Client client) {
        if (clientRepository.existsById(id)) {
            client.getAddresses().forEach(a -> a.setClient(client));
            client.getPhones().forEach(p -> p.setClient(client));
            return clientRepository.save(client).getId();
        }
        throw new MyStoreBusinessException(MyStoreBusinessException.CLIENT_NOT_FOUND);
    }
}
