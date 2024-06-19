package mconst.rpg.catalog.services;

import com.baeldung.jooq.introduction.db.public_.tables.Products;
import lombok.extern.slf4j.Slf4j;
import mconst.rpg.catalog.models.entities.ProductEntity;
import mconst.rpg.catalog.repositories.ProductRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ProductService {
    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Cacheable(value = "products", condition = "#limit == 20 and #offset == 0")
    public List<ProductEntity> get(Integer limit, Integer offset) {
        log.info("ProductService get {}, {}", limit, offset);
        return productRepository.get(limit, offset);
    }

    @Cacheable("products")
    public Optional<ProductEntity> getById(Integer id) {
        log.info("ProductService getById {}", id);
        return productRepository.getById(id);
    }

    public Integer getTotal() {
        return productRepository.getCount();
    }

    public ProductEntity create(ProductEntity product) {
        return productRepository.insert(product);
    }

    public Optional<ProductEntity> addCount(Integer id, Integer count) {
        return productRepository.addCount(id, count);
    }
}
