package my.etiqa.demo.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import my.etiqa.demo.dto.Product;
import my.etiqa.demo.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static my.etiqa.demo.constant.ApiConstants.*;


@RestController
@RequiredArgsConstructor
@RequestMapping(path = BASE_API)
@Tag(name = PRODUCT_CONTROLLER_TAG, description = PRODUCT_CONTROLLER_DESCRIPTION)
public class ProductController {

    private final ProductService productService;

    @GetMapping(PRODUCT)
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Get all products",
            description = "Fetches all available products from the database.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully retrieved the list of products"),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
            }
    )
    public Flux<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping(PRODUCT_BY_ID)
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Get product by ID",
            description = "Fetches a single product by its ID.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully retrieved the product"),
                    @ApiResponse(responseCode = "404", description = "Product not found"),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
            }
    )
    public Mono<Product> getProductById(@PathVariable("id") Long id) {
        return productService.getProductById(id);
    }

    @PostMapping(PRODUCT)
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
            summary = "Create a new product",
            description = "Adds a new product to the database.",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Successfully created the product"),
                    @ApiResponse(responseCode = "400", description = "Invalid product details"),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
            }
    )
    public Mono<Product> createProduct(@RequestBody Product product) {
        return productService.addProduct(product);
    }

    @PutMapping(PRODUCT_BY_ID)
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Update a product",
            description = "Updates the details of an existing product by its ID.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully updated the product"),
                    @ApiResponse(responseCode = "404", description = "Product not found"),
                    @ApiResponse(responseCode = "400", description = "Invalid product details"),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
            }
    )
    public Mono<Product> updateProduct(@PathVariable("id") Long id, @RequestBody Product product) {
        return productService.updateProduct(product, id);
    }

    @DeleteMapping(PRODUCT_BY_ID)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(
            summary = "Delete a product",
            description = "Deletes a product from the database by its ID.",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Successfully deleted the product"),
                    @ApiResponse(responseCode = "404", description = "Product not found"),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
            }
    )
    public Mono<Void> deleteProduct(@PathVariable("id") Long id) {
        return productService.deleteProduct(id);
    }
}
