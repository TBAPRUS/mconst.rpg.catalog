package mconst.rpg.catalog.services;

import com.baeldung.jooq.introduction.db.public_.tables.Products;
import mconst.rpg.catalog.models.entities.ProductEntity;
import mconst.rpg.catalog.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductEntity> get(Integer limit, Integer offset) {
        return productRepository.get(limit, offset);
    }

    public Integer getTotal() {
        return productRepository.getCount();
    }

    public ProductEntity create(ProductEntity product) {
        return productRepository.insert(product);
    }
}
