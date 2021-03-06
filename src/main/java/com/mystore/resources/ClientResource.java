package com.mystore.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.mystore.domain.dto.ClientDTO;
import com.mystore.services.ClientService;

@RestController
@RequestMapping(value = "/clients")
public class ClientResource {

    @Autowired
    private ClientService clientService;

    @GetMapping
    public List<ClientDTO> findAll() {
        return clientService.findAll().stream().map(ClientDTO::new).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ClientDTO findById(@PathVariable final Integer id) {
        return new ClientDTO(clientService.findById(id));
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Integer create(@RequestBody final ClientDTO clientDTO) {
        return clientService.create(clientDTO.getClient());
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable final Integer id) {
        clientService.delete(id);
    }

    @PutMapping("/{id}")
    public void update(@RequestBody final ClientDTO clientDTO) {
        clientService.update(clientDTO.getClient());
    }
}
