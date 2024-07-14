package mconst.rpg.catalog.services;

import lombok.extern.slf4j.Slf4j;
import mconst.rpg.catalog.models.entities.ProductEntity;
import mconst.rpg.catalog.models.exceptions.ExceptionBody;
import mconst.rpg.catalog.models.exceptions.NotFoundException;
import mconst.rpg.catalog.repositories.ProductRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
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

    @Cacheable(value = "productsList", condition = "#limit == 20 and #offset == 0")
    public List<ProductEntity> get(Integer limit, Integer offset) {
        return productRepository.get(limit, offset);
    }

    @Cacheable("products")
    public Optional<ProductEntity> getById(Integer id) {
        return productRepository.getById(id);
    }

    public Integer getTotal() {
        return productRepository.getCount();
    }

    @CachePut(value = "products", key = "#result.id")
    @CacheEvict(value = "productsList", allEntries = true)
    public ProductEntity create(ProductEntity product) {
        return productRepository.insert(product);
    }

    @CachePut(value = "products", key = "#id")
    @CacheEvict(value = "productsList", allEntries = true)
    public Optional<ProductEntity> addCount(Integer id, Integer count) {
        return productRepository.addCount(id, count);
    }

    public Boolean checkAvailability(Long id, Long count, Long pricePerOne) {
        var product = productRepository.getById(Math.toIntExact(id));
        if (product.isEmpty()) {
            var exceptionBody = new ExceptionBody();
            exceptionBody.addError(id, "product", "id");
            throw new NotFoundException(exceptionBody);
        }
        return product.get().getCount() >= count && product.get().getPrice() == Math.toIntExact(pricePerOne);
    }
}
