package my.etiqa.customer.demo.service;

import my.etiqa.customer.demo.entity.Customer;
import my.etiqa.customer.demo.entity.Order;
import my.etiqa.customer.demo.entity.Product;
import my.etiqa.customer.demo.repository.CustomerRepository;
import my.etiqa.customer.demo.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final OrderRepository orderRepository;
    private final WebClient webClient;

    public Customer validateCustomer(Integer customerId) {
        log.info("Validating customer ID: {}", customerId);
        return customerRepository.findById(customerId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Customer ID: " + customerId));
    }

    public void validateAndProcessProducts(Order order) {
        for (Long productId : order.getProducts()) {
            log.info("Validating product ID: {}", productId);

            Product product = fetchProduct(productId);

            if (product.getBookQuantity() < 1) {
                throw new IllegalArgumentException("Product out of stock: " + product.getBookTitle());
            }

            updateProductStock(product, productId);
        }
    }

    public Product fetchProduct(Long productId) {
        log.info("Fetching product with ID: {}", productId);
        return webClient.get()
                .uri("/product/{id}", productId)
                .retrieve()
                .bodyToMono(Product.class)
                .onErrorResume(e -> {
                    log.error("Error fetching product ID {}: {}", productId, e.getMessage());
                    throw new RuntimeException("Failed to fetch product with ID: " + productId, e);
                })
                .blockOptional()
                .orElseThrow(() -> new IllegalArgumentException("Invalid Product ID: " + productId));
    }

    public void updateProductStock(Product product, Long productId) {
        log.info("Reducing stock for product ID: {}", productId);
        product.setBookQuantity(product.getBookQuantity() - 1);

        webClient.put()
                .uri("/product/{id}", productId)
                .bodyValue(product)
                .retrieve()
                .bodyToMono(Void.class)
                .onErrorResume(e -> {
                    log.error("Error updating product ID {}: {}", productId, e.getMessage());
                    throw new RuntimeException("Failed to update stock for product with ID: " + productId, e);
                })
                .block();
    }

    @Transactional
    public void saveOrder(Order order, Integer customerId) {
        log.info("Saving order for customer ID: {}", customerId);
        order.setId(null); // Ensure the ID is auto-generated
        order.setCustomerId(customerId);
        order.setOrderDate(LocalDateTime.now());
        orderRepository.save(order);
    }
}
