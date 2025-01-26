package my.etiqa.demo.service;

import my.etiqa.demo.domain.ProductEntity;
import my.etiqa.demo.exception.EntityNotFoundException;
import my.etiqa.demo.mapper.ProductMapper;
import my.etiqa.demo.dto.Product;
import my.etiqa.demo.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductService(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    @Cacheable(value = "products")
    public Flux<Product> getAllProducts() {
        log.info("Fetching all products from the database...");
        return productRepository.findAll()
                .switchIfEmpty(Mono.error(new RuntimeException("No products found")))
                .map(productMapper::mapToModel);
    }


    @Cacheable(value = "products", key = "#id")
    public Mono<Product> getProductById(Long id) {
        log.info("Fetching product with ID {} from cache or database...", id);
        return productRepository.findById(id)
                .map(productMapper::mapToModel)
                .switchIfEmpty(Mono.error(new EntityNotFoundException("Product not found")));
    }

    @CachePut(value = "products", key = "#result.id")
    public Mono<Product> addProduct(Product product) {
        log.info("Adding a new product...");
        return productRepository.save(productMapper.mapToEntity(product))
                .map(productMapper::mapToModel)
                .doOnError(e -> log.error("Error adding product: {}", e.getMessage()));
    }

    @CachePut(value = "products", key = "#id")
    public Mono<Product> updateProduct(Product product, Long id) {
        log.info("Updating product with ID {}...", id);
        return productRepository.findById(id)
                .switchIfEmpty(Mono.error(new EntityNotFoundException("Product not found")))
                .flatMap(existingProduct -> {
                    ProductEntity productEntity = productMapper.mapToEntity(product);
                    productEntity.setId(existingProduct.getId());
                    return productRepository.save(productEntity).map(productMapper::mapToModel);
                })
                .doOnError(e -> log.error("Error updating product: {}", e.getMessage()));
    }

    @CacheEvict(value = "products", key = "#id")
    public Mono<Void> deleteProduct(Long id) {
        log.info("Deleting product with ID {}...", id);
        return productRepository.findById(id)
                .switchIfEmpty(Mono.error(new EntityNotFoundException("Product not found")))
                .flatMap(product -> productRepository.deleteById(product.getId()));
    }

    @CachePut(value = "products", key = "#id")
    public Mono<Product> putProduct(Product product, Long id) {
        log.info("Replacing product with ID {}...", id);
        return productRepository.findById(id)
                .switchIfEmpty(Mono.error(new EntityNotFoundException("Product not found")))
                .flatMap(existingProduct -> {
                    ProductEntity updatedEntity = productMapper.mapToEntity(product);
                    updatedEntity.setId(existingProduct.getId());
                    return productRepository.save(updatedEntity).map(productMapper::mapToModel);
                })
                .doOnError(e -> log.error("Error replacing product: {}", e.getMessage()));
    }

    @CachePut(value = "products", key = "#id")
    public Mono<Product> patchProduct(Product product, Long id) {
        log.info("Patching product with ID {}...", id);
        return productRepository.findById(id)
                .switchIfEmpty(Mono.error(new EntityNotFoundException("Product not found")))
                .flatMap(existingProduct -> {
                    ProductEntity productEntity = existingProduct;
                    if (product.bookTitle() != null) {
                        productEntity.setBookTitle(product.bookTitle());
                    }
                    if (product.category() != null) {
                        productEntity.setCategory(product.category());
                    }
                    if (product.bookPrice() != null) {
                        productEntity.setBookPrice(product.bookPrice());
                    }
                    if (product.bookQuantity() != null) {
                        productEntity.setBookQuantity(product.bookQuantity());
                    }
                    return productRepository.save(productEntity).map(productMapper::mapToModel);
                })
                .doOnError(e -> log.error("Error patching product: {}", e.getMessage()));
    }
}
