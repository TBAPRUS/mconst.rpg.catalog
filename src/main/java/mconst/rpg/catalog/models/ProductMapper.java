package mconst.rpg.catalog.models;

import mconst.rpg.catalog.models.dtos.ProductDto;
import mconst.rpg.catalog.models.entities.ProductEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface ProductMapper {
    ProductDto map(ProductEntity products);
    ProductEntity map(ProductDto products);
    List<ProductDto> map(List<ProductEntity> products);
}
