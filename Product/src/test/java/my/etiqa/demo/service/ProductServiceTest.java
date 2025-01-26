package my.etiqa.demo.service;

import my.etiqa.demo.domain.ProductEntity;
import my.etiqa.demo.dto.Product;
import my.etiqa.demo.exception.EntityNotFoundException;
import my.etiqa.demo.mapper.ProductMapper;
import my.etiqa.demo.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ProductServiceTest {

    @InjectMocks
    private ProductService productService;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ProductMapper productMapper;

    private Product product;
    private ProductEntity productEntity;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Sample data setup
        product = new Product(1L, "Book Title", 19.99f, "Category", 100, "Description");
        productEntity = ProductEntity.builder()
                .id(1L)
                .bookTitle("Book Title")
                .bookPrice(19.99f)
                .category("Category")
                .bookQuantity(100)
                .description("Description")
                .build();
    }

    @Test
    void testGetAllProducts() {
        when(productRepository.findAll()).thenReturn(Flux.just(productEntity));
        when(productMapper.mapToModel(productEntity)).thenReturn(product);

        StepVerifier.create(productService.getAllProducts())
                .expectNext(product)
                .verifyComplete();

        verify(productRepository, times(1)).findAll();
    }

    @Test
    void testGetAllProducts_Empty() {
        when(productRepository.findAll()).thenReturn(Flux.empty());

        StepVerifier.create(productService.getAllProducts())
                .expectError(RuntimeException.class)
                .verify();

        verify(productRepository, times(1)).findAll();
    }

    @Test
    void testGetProductById() {
        when(productRepository.findById(1L)).thenReturn(Mono.just(productEntity));
        when(productMapper.mapToModel(productEntity)).thenReturn(product);

        StepVerifier.create(productService.getProductById(1L))
                .expectNext(product)
                .verifyComplete();

        verify(productRepository, times(1)).findById(1L);
    }

    @Test
    void testGetProductById_NotFound() {
        when(productRepository.findById(1L)).thenReturn(Mono.empty());

        StepVerifier.create(productService.getProductById(1L))
                .expectError(RuntimeException.class)
                .verify();

        verify(productRepository, times(1)).findById(1L);
    }

    @Test
    void testAddProduct() {
        when(productMapper.mapToEntity(product)).thenReturn(productEntity);
        when(productRepository.save(any(ProductEntity.class))).thenReturn(Mono.just(productEntity));
        when(productMapper.mapToModel(productEntity)).thenReturn(product);

        StepVerifier.create(productService.addProduct(product))
                .expectNext(product)
                .verifyComplete();

        verify(productRepository, times(1)).save(any(ProductEntity.class));
    }

    @Test
    void testUpdateProduct() {
        when(productRepository.findById(1L)).thenReturn(Mono.just(productEntity));
        when(productMapper.mapToEntity(product)).thenReturn(productEntity);
        when(productRepository.save(any(ProductEntity.class))).thenReturn(Mono.just(productEntity));
        when(productMapper.mapToModel(productEntity)).thenReturn(product);

        StepVerifier.create(productService.updateProduct(product, 1L))
                .expectNext(product)
                .verifyComplete();

        verify(productRepository, times(1)).findById(1L);
        verify(productRepository, times(1)).save(any(ProductEntity.class));
    }

    @Test
    void testUpdateProduct_NotFound() {
        when(productRepository.findById(1L)).thenReturn(Mono.empty());

        StepVerifier.create(productService.updateProduct(product, 1L))
                .expectError(EntityNotFoundException.class)
                .verify();

        verify(productRepository, times(1)).findById(1L);
    }

    @Test
    void testDeleteProduct() {
        when(productRepository.findById(1L)).thenReturn(Mono.just(productEntity));
        when(productRepository.deleteById(1L)).thenReturn(Mono.empty());

        StepVerifier.create(productService.deleteProduct(1L))
                .verifyComplete();

        verify(productRepository, times(1)).findById(1L);
        verify(productRepository, times(1)).deleteById(1L);
    }

    @Test
    void testDeleteProduct_NotFound() {
        when(productRepository.findById(1L)).thenReturn(Mono.empty());

        StepVerifier.create(productService.deleteProduct(1L))
                .expectError(EntityNotFoundException.class)
                .verify();

        verify(productRepository, times(1)).findById(1L);
    }

    @Test
    void testPutProduct() {
        when(productRepository.findById(1L)).thenReturn(Mono.just(productEntity));
        when(productMapper.mapToEntity(product)).thenReturn(productEntity);
        when(productRepository.save(any(ProductEntity.class))).thenReturn(Mono.just(productEntity));
        when(productMapper.mapToModel(productEntity)).thenReturn(product);

        StepVerifier.create(productService.putProduct(product, 1L))
                .expectNext(product)
                .verifyComplete();

        verify(productRepository, times(1)).findById(1L);
        verify(productRepository, times(1)).save(any(ProductEntity.class));
    }

    @Test
    void testPatchProduct() {
        when(productRepository.findById(1L)).thenReturn(Mono.just(productEntity));
        when(productRepository.save(any(ProductEntity.class))).thenReturn(Mono.just(productEntity));
        when(productMapper.mapToModel(productEntity)).thenReturn(product);

        Product partialUpdate = new Product(null, null, 25.99f, null, 150, null);

        StepVerifier.create(productService.patchProduct(partialUpdate, 1L))
                .expectNext(product)
                .verifyComplete();

        verify(productRepository, times(1)).findById(1L);
        verify(productRepository, times(1)).save(any(ProductEntity.class));
    }
}
