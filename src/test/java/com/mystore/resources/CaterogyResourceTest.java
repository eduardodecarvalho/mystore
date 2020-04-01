package com.mystore.resources;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mystore.domain.Category;
import com.mystore.domain.dto.CategoryDTO;
import com.mystore.repositories.CaterogyRepository;
import com.mystore.utils.SpringBootIntegrationTest;

public class CaterogyResourceTest extends SpringBootIntegrationTest {

    @Autowired
    private CaterogyRepository categoryRepository;

    @Test
    void findAll() throws Exception {
        final ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://localhost:" + port + "/categories", String.class);
        Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        final String expected = "[\n" +
                "   {\n" +
                "      \"id\":1,\n" +
                "      \"name\":\"All\"\n" +
                "   },\n" +
                "   {\n" +
                "      \"id\":2,\n" +
                "      \"name\":\"Arts & Crafts\"\n" +
                "   },\n" +
                "   {\n" +
                "      \"id\":3,\n" +
                "      \"name\":\"Automotive\"\n" +
                "   },\n" +
                "   {\n" +
                "      \"id\":4,\n" +
                "      \"name\":\"Baby\"\n" +
                "   },\n" +
                "   {\n" +
                "      \"id\":5,\n" +
                "      \"name\":\"Books\"\n" +
                "   },\n" +
                "   {\n" +
                "      \"id\":6,\n" +
                "      \"name\":\"Camera & Photo\"\n" +
                "   },\n" +
                "   {\n" +
                "      \"id\":7,\n" +
                "      \"name\":\"Clothing & Accessories\"\n" +
                "   },\n" +
                "   {\n" +
                "      \"id\":8,\n" +
                "      \"name\":\"Consumer Electronics\"\n" +
                "   },\n" +
                "   {\n" +
                "      \"id\":9,\n" +
                "      \"name\":\"Grocery & Gourmet Foods\"\n" +
                "   }\n" +
                "]";
        JSONAssert.assertEquals(expected, responseEntity.getBody(), true);
    }

    @Test
    void findById() throws Exception {
        final ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://localhost:" + port + "/categories/1", String.class);
        Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        final String expected = "{\"id\":1,\"name\":\"All\"}";

        JSONAssert.assertEquals(expected, responseEntity.getBody(), true);
    }

    @Test
    void findByIdNotExistsShouldRetornError() throws JsonMappingException, JsonProcessingException {
        final ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://localhost:" + port + "/categories/99", String.class);
        Assert.assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }

    @Test
    void create() throws Exception {
        final String categoryDTOString = "{" +
                "    \"name\": \"IOT\"" +
                "} ";

        final CategoryDTO categoryDTO = new ObjectMapper().readValue(categoryDTOString, CategoryDTO.class);
        final ResponseEntity<String> responseEntity = restTemplate.postForEntity("http://localhost:" + port + "/categories", categoryDTO, String.class);
        Assert.assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());

        final Integer createdId = Integer.parseInt(responseEntity.getBody());
        final String actual = new ObjectMapper().writeValueAsString(new Category(categoryRepository.findById(createdId).get()));
        JSONAssert.assertEquals(categoryDTOString, actual, false);
    }

    @Test
    void createWithNameWithJustSpacesShouldReturnError() throws Exception {
        final String categoryDTOString = "{" +
                "    \"name\": \"     \""
                + "} ";
        final CategoryDTO categoryDTO = new ObjectMapper().readValue(categoryDTOString, CategoryDTO.class);
        final ResponseEntity<String> responseEntity = restTemplate.postForEntity("http://localhost:" + port + "/categories", categoryDTO, String.class);
        Assert.assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }

    @Test
    void createWithDuplicatedNameShouldReturnError() throws Exception {
        final String categoryDTOString = "{" +
                "      \"name\":\"Automotive       \"\n" +
                "} ";
        final CategoryDTO categoryDTO = new ObjectMapper().readValue(categoryDTOString, CategoryDTO.class);
        final ResponseEntity<String> responseEntity = restTemplate.postForEntity("http://localhost:" + port + "/categories", categoryDTO, String.class);
        Assert.assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }

}