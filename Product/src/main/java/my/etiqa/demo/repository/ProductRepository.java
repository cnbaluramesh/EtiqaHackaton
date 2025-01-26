package my.etiqa.demo.repository;

import my.etiqa.demo.domain.ProductEntity;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends R2dbcRepository<ProductEntity, Long> {

}
