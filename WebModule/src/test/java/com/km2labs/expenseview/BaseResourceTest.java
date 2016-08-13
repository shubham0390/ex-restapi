package com.km2labs.expenseview;

import junit.framework.TestCase;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

/**
 * Created by suze on 05/08/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test-context.xml"})
public abstract class BaseResourceTest extends TestCase {

    protected final String BASE_URL = "http://localhost:8080/expensemanager/rest/";

    @Autowired
    protected RestTemplate mRestTemplate;

    protected String getUrl(String path) {
        return BASE_URL + path;
    }
}
