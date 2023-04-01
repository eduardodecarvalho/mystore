package com.mystore.resources;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mystore.domain.Product;
import com.mystore.domain.dto.ProductDTO;
import com.mystore.repositories.ProductRepository;
import com.mystore.utils.SpringBootIntegrationTest;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProductResourceTest extends SpringBootIntegrationTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void findAll() throws Exception {
        final ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://localhost:" + port + "/products", String.class);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

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
                "   },\n" +
                "   {\n" +
                "      \"id\":3,\n" +
                "      \"sku\":\"TV2020SMARTSAMSUNG\",\n" +
                "      \"name\":\"Smart TV 4K Led 50pol\",\n" +
                "      \"description\":\"Wi-Fi Bluetooth HDR 3 HDMI 2 USB\",\n" +
                "      \"model\":\"UN50RU7100\",\n" +
                "      \"price\":1999.05,\n" +
                "      \"quantity\":0,\n" +
                "      \"categories\":[\n" +
                "\n" +
                "      ]\n" +
                "   }\n" +
                "]";

        JSONAssert.assertEquals(expected, responseEntity.getBody(), true);
    }

    @Test
    public void findById() throws Exception {
        final ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://localhost:" + port + "/products/1", String.class);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

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
    public void findByIdNotExistsShouldReturnError() {
        final ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://localhost:" + port + "/products/99", String.class);
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }

    @Test
    public void findByCategory() throws Exception {
        final ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://localhost:" + port + "/products/categories/8", String.class);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

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
    public void create() throws Exception {
        final String productDTOString = "{" +
                "    \"name\": \"IOT\"," +
                "    \"sku\": \"IOT\"," +
                "    \"description\": \"IOT\"," +
                "    \"model\": \"IOT\"," +
                "    \"price\": 10.0" +
                "} ";

        final ProductDTO productDTO = new ObjectMapper().readValue(productDTOString, ProductDTO.class);
        final ResponseEntity<String> responseEntity = restTemplate.postForEntity("http://localhost:" + port + "/products", productDTO, String.class);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());

        final Integer createdId = Integer.parseInt(Objects.requireNonNull(responseEntity.getBody()));
        String actual = new ObjectMapper().writeValueAsString(new Product(productRepository.findById(createdId).orElse(new Product())));
        JSONAssert.assertEquals(productDTOString, actual, false);
    }

    @Test
    public void create_withNoName() throws Exception {
        final String productDTOString = "{" +
                "    \"name\": \"\"," +
                "    \"sku\": \"IOT\"," +
                "    \"description\": \"IOT\"," +
                "    \"model\": \"IOT\"," +
                "    \"price\": 10.0" +
                "} ";

        final ProductDTO productDTO = new ObjectMapper().readValue(productDTOString, ProductDTO.class);
        final ResponseEntity<String> responseEntity = restTemplate.postForEntity("http://localhost:" + port + "/products", productDTO, String.class);
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }

}
