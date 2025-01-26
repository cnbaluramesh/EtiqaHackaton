package my.etiqa.demo.mapper;

import my.etiqa.demo.domain.ProductEntity;
import my.etiqa.demo.dto.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductEntity mapToEntity(Product product);

    Product mapToModel(ProductEntity productEntity);
}
