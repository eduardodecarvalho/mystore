package com.mystore.resources;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
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
    public void findAll() throws Exception {
        final ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://localhost:" + port + "/clients", String.class);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        final String expected = "[ " +
                "   { " +
                "      \"id\":1, " +
                "      \"name\":\"Sherlock\", " +
                "      \"email\":\"sherlock@email.com\", " +
                "      \"nationalRegister\":\"12345678910\", " +
                "      \"clientType\":\"PERSON\", " +
                "      \"addresses\":[ " +
                "         { " +
                "            \"id\":1, " +
                "            \"street\":\"Baker Street\", " +
                "            \"number\":\"221B\", " +
                "            \"neighborhood\":\"West End\", " +
                "            \"zipCode\":\"NW1 6XE\", " +
                "            \"complement\":\"near Regents Park\", " +
                "            \"city\":{ " +
                "               \"id\":1, " +
                "               \"name\":\"London\" " +
                "            } " +
                "         } " +
                "      ] " +
                "   }, " +
                "   { " +
                "      \"id\":2, " +
                "      \"name\":\"John\", " +
                "      \"email\":\"john@email.com\", " +
                "      \"nationalRegister\":\"12313\", " +
                "      \"clientType\":\"PERSON\", " +
                "      \"addresses\":[ " +
                "         { " +
                "            \"id\":2, " +
                "            \"street\":\"Rua dos Pargos\", " +
                "            \"number\":\"284\", " +
                "            \"neighborhood\":\"Jurerê Internacional\", " +
                "            \"zipCode\":\"88053-345\", " +
                "            \"complement\":\"near the beach\", " +
                "            \"city\":{ " +
                "               \"id\":2, " +
                "               \"name\":\"Florianópolis\" " +
                "            } " +
                "         } " +
                "      ] " +
                "   }, " +
                "   { " +
                "      \"id\":3, " +
                "      \"name\":\"Apple\", " +
                "      \"email\":\"apple@email.com\", " +
                "      \"nationalRegister\":\"9010120901\", " +
                "      \"clientType\":\"ENTERPRISE\", " +
                "      \"addresses\":[ " +
                "         { " +
                "            \"id\":3, " +
                "            \"street\":\"One Apple Park Way\", " +
                "            \"number\":null, " +
                "            \"neighborhood\":\"Apple Park\", " +
                "            \"zipCode\":\"CA 95014\", " +
                "            \"complement\":\"a large circular building\", " +
                "            \"city\":{ " +
                "               \"id\":3, " +
                "               \"name\":\"Cupertino\" " +
                "            } " +
                "         }, " +
                "         { " +
                "            \"id\":4, " +
                "            \"street\":\"Av. Gov. Irineu Bornhausen\", " +
                "            \"number\":\"3360\", " +
                "            \"neighborhood\":\"Agronômica\", " +
                "            \"zipCode\":\"88025-200\", " +
                "            \"complement\":\"Edifício La Perle Beira Mar\", " +
                "            \"city\":{ " +
                "               \"id\":3, " +
                "               \"name\":\"Cupertino\" " +
                "            } " +
                "         } " +
                "      ] " +
                "   }, " +
                "   { " +
                "      \"id\":4, " +
                "      \"name\":\"Marie\", " +
                "      \"email\":\"maria@email.com\", " +
                "      \"nationalRegister\":\"09876543210\", " +
                "      \"clientType\":\"PERSON\", " +
                "      \"addresses\":[ " +
                " " +
                "      ] " +
                "   } " +
                "]";

        JSONAssert.assertEquals(expected, responseEntity.getBody(), false);
    }

    @Test
    public void findById() throws Exception {
        final ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://localhost:" + port + "/clients/1", String.class);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        final String expected = "{ " +
                "   \"id\":1, " +
                "   \"name\":\"Sherlock\", " +
                "   \"email\":\"sherlock@email.com\", " +
                "   \"nationalRegister\":\"12345678910\", " +
                "   \"clientType\":\"PERSON\", " +
                "   \"addresses\":[ " +
                "      { " +
                "         \"id\":1, " +
                "         \"street\":\"Baker Street\", " +
                "         \"number\":\"221B\", " +
                "         \"neighborhood\":\"West End\", " +
                "         \"zipCode\":\"NW1 6XE\", " +
                "         \"complement\":\"near Regents Park\", " +
                "         \"city\":{ " +
                "            \"id\":1, " +
                "            \"name\":\"London\" " +
                "         } " +
                "      } " +
                "   ] " +
                "}";

        JSONAssert.assertEquals(expected, responseEntity.getBody(), false);
    }

    @Test
    public void findByIdNotExistsShouldReturnError() throws JsonMappingException, JsonProcessingException {
        final ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://localhost:" + port + "/clients/99", String.class);
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }

    @Test
    public void createWithEmailAlreadyRegisteredShouldReturnError() throws Exception {
        final String clientDTOString = "{ " +
                "   \"name\":\"Sherlock\", " +
                "   \"email\":\"sherlock@email.com\", " +
                "   \"clientType\":\"PERSON\" " +
                "}";

        ClientDTO clientDTO = new ObjectMapper().readValue(clientDTOString, ClientDTO.class);
        final ResponseEntity<String> responseEntity = restTemplate.postForEntity("http://localhost:" + port + "/clients", clientDTO, String.class);
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }

    @Test
    public void createWithNationalRegisteredAlreadyRegisteredShouldReturnError() throws Exception {
        final String clientDTOString = "{ " +
                "   \"name\":\"Sherlock\", " +
                "   \"nationalRegister\":\"12345678910\", " +
                "   \"clientType\":\"PERSON\" " +
                "}";

        ClientDTO clientDTO = new ObjectMapper().readValue(clientDTOString, ClientDTO.class);
        final ResponseEntity<String> responseEntity = restTemplate.postForEntity("http://localhost:" + port + "/clients", clientDTO, String.class);
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }

    @Test
    public void create() throws Exception {
        final String clientDTOString = "{ " +
                "   \"name\":\"Watson\", " +
                "   \"email\":\"watson@email.com\", " +
                "   \"nationalRegister\":\"6547832\", " +
                "   \"clientType\":\"PERSON\" " +
                "}";

        ClientDTO clientDTO = new ObjectMapper().readValue(clientDTOString, ClientDTO.class);
        final ResponseEntity<String> responseEntity = restTemplate.postForEntity("http://localhost:" + port + "/clients", clientDTO, String.class);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());

        final Integer createdId = Integer.parseInt(responseEntity.getBody());
        final String actual = new ObjectMapper().writeValueAsString(new Client(clientRepository.findById(createdId).get()));
        JSONAssert.assertEquals(clientDTOString, actual, false);
    }

    @Test
    public void createWithAddress() throws Exception {
        final String clientDTOString = "{ " +
                "   \"name\":\"Watson\", " +
                "   \"email\":\"watson@email.com\", " +
                "   \"nationalRegister\":\"6547832\", " +
                "   \"clientType\":\"PERSON\", " +
                "   \"addresses\":[ " +
                "      { " +
                "         \"street\":\"Baker Street\", " +
                "         \"number\":\"221B\", " +
                "         \"neighborhood\":\"West End\", " +
                "         \"zipCode\":\"NW1 6XE\", " +
                "         \"complement\":\"near Regents Park\", " +
                "         \"city\":{ " +
                "            \"id\":1, " +
                "            \"name\":\"London\" " +
                "         } " +
                "      } " +
                "   ] " +
                "}";

        ClientDTO clientDTO = new ObjectMapper().readValue(clientDTOString, ClientDTO.class);
        final ResponseEntity<String> responseEntity = restTemplate.postForEntity("http://localhost:" + port + "/clients", clientDTO, String.class);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());

        final Integer createdId = Integer.parseInt(responseEntity.getBody());
        final String actual = new ObjectMapper().writeValueAsString(new Client(clientRepository.findById(createdId).get()));
        JSONAssert.assertEquals(clientDTOString, actual, false);
    }

    @Test
    public void createWithCPhones() throws Exception {
        final String clientDTOString = "{ " +
                "   \"name\":\"Watson\", " +
                "   \"email\":\"watson@email.com\", " +
                "   \"nationalRegister\":\"6547832\", " +
                "   \"clientType\":\"PERSON\", " +
                "   \"phones\":[ " +
                "      { " +
                "         \"phoneNumber\":\"+55 48 9 9999-9999\", " +
                "         \"phoneOrder\":1 " +
                "      } " +
                "   ] " +
                "}";

        ClientDTO clientDTO = new ObjectMapper().readValue(clientDTOString, ClientDTO.class);
        final ResponseEntity<String> responseEntity = restTemplate.postForEntity("http://localhost:" + port + "/clients", clientDTO, String.class);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());

        final Integer createdId = Integer.parseInt(responseEntity.getBody());
        final String actual = new ObjectMapper().writeValueAsString(new Client(clientRepository.findById(createdId).get()));
        JSONAssert.assertEquals(clientDTOString, actual, false);
    }

    @Test
    public void updateName() throws Exception {
        final String clientDTOString = "{ " +
                "   \"id\":1, " +
                "   \"name\":\"Sherlock Holmes\", " +
                "   \"email\":\"sherlock@email.com\", " +
                "   \"nationalRegister\":\"12345678910\", " +
                "   \"clientType\":\"PERSON\", " +
                "   \"addresses\":[ " +
                "      { " +
                "         \"id\":1, " +
                "         \"street\":\"Baker Street\", " +
                "         \"number\":\"221B\", " +
                "         \"neighborhood\":\"West End\", " +
                "         \"zipCode\":\"NW1 6XE\", " +
                "         \"complement\":\"near Regents Park\", " +
                "         \"city\":{ " +
                "            \"id\":1, " +
                "            \"name\":\"London\" " +
                "         } " +
                "      } " +
                "   ], " +
                "   \"phones\":[ " +
                "   ] " +
                "}";

        ClientDTO clientDTO = new ObjectMapper().readValue(clientDTOString, ClientDTO.class);
        final ResponseEntity<String> responseEntity = restTemplate.exchange("http://localhost:" + port + "/clients/" + clientDTO.getId(), HttpMethod.PUT, new HttpEntity<>(clientDTO),
                String.class);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        final String expected = new ObjectMapper().writeValueAsString(clientRepository.findById(clientDTO.getId()).get());
        JSONAssert.assertEquals(expected, clientDTOString, false);
    }

}
