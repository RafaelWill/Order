package be.willekens.template;

import be.willekens.template.api.dto.customer.CustomerDto;
import be.willekens.template.api.mappers.CustomerMapper;
import be.willekens.template.domain.models.customer.Address;
import be.willekens.template.domain.models.customer.Customer;
import be.willekens.template.domain.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class CustomerControllerEndToEnd {

    @LocalServerPort
    private int port;

    private RestTemplate restTemplate = new RestTemplate();

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CustomerMapper customerMapper;

//    @Test
//    void testingCustomerControllerToGetAllCustomers_restTemplate() {
//        Address address = new Address("Teststreet","5","3800","Test Town");
//        Customer customer = customerRepository.addCustomer(new Customer("Testy","Testion","test@test.be",address
//                ,"123456789"));
//
//        restTemplate.setInterceptors(List.of(new HeaderRequestInterceptor("Authorization", "Basic cmFmYWVsOmFkbWlu")));
//
//        ResponseEntity<CustomerDto[]> customy = restTemplate.getForEntity("http://localhost:" + port + "/customers", CustomerDto[].class);
//
//        assertThat(customy.getStatusCode()).isEqualTo(HttpStatus.OK);
//        assertThat(customy.getBody()).isNotNull();
//        assertThat(customy.getBody()).isNotEmpty();
//        assertThat(Arrays.asList(customy.getBody())).contains(customerMapper.mapToCustomerDto(customer));
//    }
//
//    @Test
//    void testingCustomerControllerToGetAllCustomer_restAssured() {
//        Address address = new Address("Teststreet","5","3800","Test Town");
//        Customer customer = customerRepository.addCustomer(new Customer("Testy","Testion","test@test.be",address
//                ,"123456789"));
//
//        CustomerDto[] customers = given()
//                .baseUri("http://localhost")
//                .port(port)
//                .header("Authorization", "Basic cmFmYWVsOmFkbWlu")
//                .when().get("/customers")
//                .then()
//                .assertThat()
//                .statusCode(HttpStatus.OK.value())
//                .extract()
//                .as(CustomerDto[].class);
//
//        assertThat(customers).isNotNull();
//        assertThat(customers).isNotEmpty();
//        assertThat(Arrays.asList(customers)).contains(customerMapper.mapToCustomerDto(customer));
//    }
}
