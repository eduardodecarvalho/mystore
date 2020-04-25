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
import com.mystore.domain.Product;
import com.mystore.domain.dto.ProductDTO;
import com.mystore.repositories.ProductRepository;
import com.mystore.utils.SpringBootIntegrationTest;

public class ProductResourceTest extends SpringBootIntegrationTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    void findAll() throws Exception {
        final ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://localhost:" + port + "/products", String.class);
        Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        final String expected = "[\n" +
                "   {\n" +
                "      \"id\":1,\n" +
                "      \"sku\":\"NB2020I7DELL\",\n" +
                "      \"name\":\"Notebook\",\n" +
                "      \"description\":\"Notebook Dell i7 2020\",\n" +
                "      \"model\":\"Dell Inspiron 15 5000\",\n" +
                "      \"price\":2000.00,\n" +
                "      \"quantity\":10,\n" +
                "      \"categories\":[\n" +
                "         {\n" +
                "            \"id\":8,\n" +
                "            \"name\":\"Consumer Electronics\"\n" +
                "         }\n" +
                "      ]\n" +
                "   },\n" +
                "   {\n" +
                "      \"id\":2,\n" +
                "      \"sku\":\"ER2020PWAAMZON\",\n" +
                "      \"name\":\"Kindle\",\n" +
                "      \"description\":\"E-Reader Amazon Kindle Papperwhite\",\n" +
                "      \"model\":\"Paperwhite 10a 8gb\",\n" +
                "      \"price\":150.31,\n" +
                "      \"quantity\":40,\n" +
                "      \"categories\":[\n" +
                "         {\n" +
                "            \"id\":5,\n" +
                "            \"name\":\"Books\"\n" +
                "         },\n" +
                "         {\n" +
                "            \"id\":8,\n" +
                "            \"name\":\"Consumer Electronics\"\n" +
                "         }\n" +
                "      ]\n" +
                "   }\n" +
                "]";
        JSONAssert.assertEquals(expected, responseEntity.getBody(), true);
    }

    @Test
    void findById() throws Exception {
        final ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://localhost:" + port + "/products/1", String.class);
        Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        final String expected = "   {\n" +
                "      \"id\":1,\n" +
                "      \"sku\":\"NB2020I7DELL\",\n" +
                "      \"name\":\"Notebook\",\n" +
                "      \"description\":\"Notebook Dell i7 2020\",\n" +
                "      \"model\":\"Dell Inspiron 15 5000\",\n" +
                "      \"price\":2000.00,\n" +
                "      \"quantity\":10,\n" +
                "      \"categories\":[\n" +
                "         {\n" +
                "            \"id\":8,\n" +
                "            \"name\":\"Consumer Electronics\"\n" +
                "         }\n" +
                "      ]\n" +
                "   }\n";

        JSONAssert.assertEquals(expected, responseEntity.getBody(), true);
    }

    @Test
    void findByIdNotExistsShouldRetornError() throws JsonMappingException, JsonProcessingException {
        final ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://localhost:" + port + "/products/99", String.class);
        Assert.assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }

    @Test
    void create() throws Exception {
        final String productDTOString = "{" +
                "    \"name\": \"IOT\"," +
                "    \"sku\": \"IOT\"," +
                "    \"description\": \"IOT\"," +
                "    \"model\": \"IOT\"," +
                "    \"price\": 10.0" +
                "} ";

        final ProductDTO productDTO = new ObjectMapper().readValue(productDTOString, ProductDTO.class);
        final ResponseEntity<String> responseEntity = restTemplate.postForEntity("http://localhost:" + port + "/products", productDTO, String.class);
        Assert.assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());

        final Integer createdId = Integer.parseInt(responseEntity.getBody());
        final String actual = new ObjectMapper().writeValueAsString(new Product(productRepository.findById(createdId).get()));
        JSONAssert.assertEquals(productDTOString, actual, false);
    }

    @Test
    void createWithNameWithJustSpacesShouldReturnError() throws Exception {
        final String productDTOString = "{" +
                "    \"name\": \"     \"," +
                "    \"sku\": \"IOT\"," +
                "    \"description\": \"IOT\"," +
                "    \"model\": \"IOT\"," +
                "    \"price\": 10.0" +
                "} ";
        final ProductDTO productDTO = new ObjectMapper().readValue(productDTOString, ProductDTO.class);
        final ResponseEntity<String> responseEntity = restTemplate.postForEntity("http://localhost:" + port + "/products", productDTO, String.class);
        Assert.assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }

    @Test
    void createWithDuplicatedNameShouldReturnError() throws Exception {
        final String productDTOString = "{" +
                "    \"name\": \"Kindle\"," +
                "    \"sku\": \"IOT\"," +
                "    \"description\": \"IOT\"," +
                "    \"model\": \"IOT\"," +
                "    \"price\": 10.0" +
                "} ";
        final ProductDTO productDTO = new ObjectMapper().readValue(productDTOString, ProductDTO.class);
        final ResponseEntity<String> responseEntity = restTemplate.postForEntity("http://localhost:" + port + "/products", productDTO, String.class);
        Assert.assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }

    @Test
    void findByCategory() throws Exception {
        final ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://localhost:" + port + "/products/categories/8", String.class);
        Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        final String expected = "[\n" +
                "   {\n" +
                "      \"id\":1,\n" +
                "      \"sku\":\"NB2020I7DELL\",\n" +
                "      \"name\":\"Notebook\",\n" +
                "      \"description\":\"Notebook Dell i7 2020\",\n" +
                "      \"model\":\"Dell Inspiron 15 5000\",\n" +
                "      \"price\":2000.00,\n" +
                "      \"quantity\":10,\n" +
                "      \"categories\":[\n" +
                "         {\n" +
                "            \"id\":8,\n" +
                "            \"name\":\"Consumer Electronics\"\n" +
                "         }\n" +
                "      ]\n" +
                "   },\n" +
                "   {\n" +
                "      \"id\":2,\n" +
                "      \"sku\":\"ER2020PWAAMZON\",\n" +
                "      \"name\":\"Kindle\",\n" +
                "      \"description\":\"E-Reader Amazon Kindle Papperwhite\",\n" +
                "      \"model\":\"Paperwhite 10a 8gb\",\n" +
                "      \"price\":150.31,\n" +
                "      \"quantity\":40,\n" +
                "      \"categories\":[\n" +
                "         {\n" +
                "            \"id\":5,\n" +
                "            \"name\":\"Books\"\n" +
                "         },\n" +
                "         {\n" +
                "            \"id\":8,\n" +
                "            \"name\":\"Consumer Electronics\"\n" +
                "         }\n" +
                "      ]\n" +
                "   }\n" +
                "]";

        JSONAssert.assertEquals(expected, responseEntity.getBody(), true);
    }
}
