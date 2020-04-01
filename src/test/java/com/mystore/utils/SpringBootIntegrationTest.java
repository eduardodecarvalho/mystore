package com.mystore.utils;

import javax.transaction.Transactional;

import org.apache.commons.logging.LogFactory;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Transactional
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public abstract class SpringBootIntegrationTest {

    private static final org.apache.commons.logging.Log LOGGER = LogFactory.getLog(SpringBootIntegrationTest.class);

    @LocalServerPort
    protected int port;

    @Autowired
    protected TestRestTemplate restTemplate;

    protected HttpStatus getPageStatusCode(final String path) {
        try {
            return restTemplate.getForEntity(path, String.class).getStatusCode();
        } catch (final Exception e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }
    }

}
