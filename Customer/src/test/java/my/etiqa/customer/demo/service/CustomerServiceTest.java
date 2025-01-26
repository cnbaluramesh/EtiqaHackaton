package my.etiqa.customer.demo.service;

import my.etiqa.customer.demo.entity.Customer;
import my.etiqa.customer.demo.entity.Order;
import my.etiqa.customer.demo.entity.Product;
import my.etiqa.customer.demo.repository.CustomerRepository;
import my.etiqa.customer.demo.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CustomerServiceTest {

    @InjectMocks
    private CustomerService customerService;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private WebClient webClient;

    @Mock
    private WebClient.RequestHeadersUriSpec<?> requestHeadersUriSpec;

    @Mock
    private WebClient.RequestHeadersSpec<?> requestHeadersSpec;

    @Mock
    private WebClient.ResponseSpec responseSpec;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testValidateCustomer_ValidCustomer() {
        Customer customer = new Customer();
        customer.setId(1);
        when(customerRepository.findById(1)).thenReturn(Optional.of(customer));

        Customer result = customerService.validateCustomer(1);

        assertNotNull(result);
        assertEquals(1, result.getId());
        verify(customerRepository, times(1)).findById(1);
    }

    @Test
    void testValidateCustomer_InvalidCustomer() {
        when(customerRepository.findById(1)).thenReturn(Optional.empty());

        Exception exception = assertThrows(IllegalArgumentException.class, () -> customerService.validateCustomer(1));
        assertEquals("Invalid Customer ID: 1", exception.getMessage());
    }



}
