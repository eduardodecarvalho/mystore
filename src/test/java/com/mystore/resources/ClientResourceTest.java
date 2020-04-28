package com.mystore.resources;

import static org.junit.Assert.assertFalse;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mystore.domain.Client;
import com.mystore.domain.dto.ClientDTO;
import com.mystore.repositories.ClientRepository;
import com.mystore.utils.SpringBootIntegrationTest;

public class ClientResourceTest extends SpringBootIntegrationTest {

    @Autowired
    private ClientRepository clientRepository;

    @Test
    void findAll() throws Exception {
        final ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://localhost:" + port + "/clients", String.class);
        Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        final String expected = "";

        JSONAssert.assertEquals(expected, responseEntity.getBody(), true);
    }

    @Test
    void findById() throws Exception {
        final ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://localhost:" + port + "/clients/1", String.class);
        Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        final String expected = "";

        JSONAssert.assertEquals(expected, responseEntity.getBody(), true);
    }

    @Test
    void findByIdNotExistsShouldRetornError() throws JsonMappingException, JsonProcessingException {
        final ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://localhost:" + port + "/clients/99", String.class);
        Assert.assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }

    @Test
    void create() throws Exception {
        final String clientDTOString = "";

        final ClientDTO clientDTO = new ObjectMapper().readValue(clientDTOString, ClientDTO.class);
        final ResponseEntity<String> responseEntity = restTemplate.postForEntity("http://localhost:" + port + "/clients", clientDTO, String.class);
        Assert.assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());

        final Integer createdId = Integer.parseInt(responseEntity.getBody());
        final String actual = new ObjectMapper().writeValueAsString(new Client(clientRepository.findById(createdId).get()));
        JSONAssert.assertEquals(clientDTOString, actual, false);
    }

    @Test
    void createWithNameWithJustSpacesShouldReturnError() throws Exception {
        final String clientDTOString = "";
        final ClientDTO clientDTO = new ObjectMapper().readValue(clientDTOString, ClientDTO.class);
        final ResponseEntity<String> responseEntity = restTemplate.postForEntity("http://localhost:" + port + "/clients", clientDTO, String.class);
        Assert.assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }

    @Test
    void createWithDuplicatedNameShouldReturnError() throws Exception {
        final String clientDTOString = "";
        final ClientDTO clientDTO = new ObjectMapper().readValue(clientDTOString, ClientDTO.class);
        final ResponseEntity<String> responseEntity = restTemplate.postForEntity("http://localhost:" + port + "/clients", clientDTO, String.class);
        Assert.assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }

    @Test
    void findByCategory() throws Exception {
        final ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://localhost:" + port + "/clients/categories/8", String.class);
        Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        final String expected = "";

        JSONAssert.assertEquals(expected, responseEntity.getBody(), true);
    }

    @Test
    void deleteById() {
        final Integer idToDelete = 3;
        final ResponseEntity<String> responseEntity = restTemplate.exchange("http://localhost:" + port + "/clients/" + idToDelete, HttpMethod.DELETE, null,
                String.class);
        Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertFalse(clientRepository.findById(idToDelete).isPresent());
    }

    @Test
    void deleteByIdWithQuantityShouldReturnError() {
        final Integer idToDelete = 1;
        final ResponseEntity<String> responseEntity = restTemplate.exchange("http://localhost:" + port + "/clients/" + idToDelete, HttpMethod.DELETE, null,
                String.class);
        Assert.assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }
}
