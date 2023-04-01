package com.mystore.resources;

import com.mystore.domain.dto.ClientDTO;
import com.mystore.services.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/clients")
public class ClientResource {

    private final ClientService clientService;

    public ClientResource(ClientService clientService) {
        this.clientService = clientService;
    }

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
    public Integer update(@PathVariable final Integer id, @RequestBody final ClientDTO clientDTO) {
        return clientService.update(id, clientDTO.getClient());
    }
}
