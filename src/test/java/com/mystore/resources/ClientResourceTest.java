package com.mystore.resources;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.mystore.utils.SpringBootIntegrationTest;

public class ClientResourceTest extends SpringBootIntegrationTest {

    @Test
    void findAll() throws Exception {
        final ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://localhost:" + port + "/clients", String.class);
        Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

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
    void findById() throws Exception {
        final ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://localhost:" + port + "/clients/1", String.class);
        Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

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
    void findByIdNotExistsShouldRetornError() throws JsonMappingException, JsonProcessingException {
        final ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://localhost:" + port + "/clients/99", String.class);
        Assert.assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }

}
