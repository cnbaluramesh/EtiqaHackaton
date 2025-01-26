package my.etiqa.customer.demo.controller;

import my.etiqa.customer.demo.entity.Order;
import my.etiqa.customer.demo.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/customers")
@Tag(name = "Customer Operations", description = "Endpoints for managing customers and their orders.")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Operation(
            summary = "Create a new order for a customer",
            description = "Creates a new order for the specified customer and validates the products",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Order created successfully"),
                    @ApiResponse(responseCode = "400", description = "Invalid input or customer/product not found"),
                    @ApiResponse(responseCode = "409", description = "Optimistic locking failure due to concurrent updates"),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
            }
    )
    @PostMapping("/{customerId}/createCustomerOrder")
    public ResponseEntity<String> createCustomerOrder(@PathVariable Integer customerId, @RequestBody Order order) {
        log.info("Creating order for customer ID: {}", customerId);

        try {
            // Validate customer
            customerService.validateCustomer(customerId);

            // Validate and process products
            customerService.validateAndProcessProducts(order);

            // Save the order
            customerService.saveOrder(order, customerId);

            log.info("Order created successfully for customer ID: {}", customerId);
            return ResponseEntity.status(HttpStatus.CREATED).body("Order Created Successfully");
        } catch (IllegalArgumentException e) {
            log.error("Validation error: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: " + e.getMessage());
        } catch (ObjectOptimisticLockingFailureException e) {
            log.error("Optimistic locking failure: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Order could not be processed due to concurrent updates.");
        } catch (Exception e) {
            log.error("Unexpected error in creating order: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error in creating order: " + e.getMessage());
        }
    }
}
