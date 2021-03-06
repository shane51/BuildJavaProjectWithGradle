package hello.integration;


import static org.assertj.core.api.Assertions.assertThat;
import java.net.URL;

import hello.Customer;
import hello.CustomerRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;



@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CustomerRestControllerIntegrationTest {

    @LocalServerPort
    private int port;

    private URL base;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private CustomerRepository repository;

    @Before
    public void setUp() throws Exception {
        this.base = new URL("http://localhost:" + port);
        repository.deleteAll();
        repository.save(new Customer("Alice", "Smith"));
        repository.save(new Customer("Bob", "Smith"));
    }

    @Test
    public void shouldGetExistCustomer() throws Exception {
        assertThat(this.restTemplate.getForObject(base.toString() + "/customers",
                String.class)).contains("Alice");
    }

    @Test
    public void shouldNotGetNonExistCustomer() throws Exception {
        assertThat(this.restTemplate.getForObject(base.toString() + "/customers",
                String.class)).doesNotContain("Allen");
    }
}