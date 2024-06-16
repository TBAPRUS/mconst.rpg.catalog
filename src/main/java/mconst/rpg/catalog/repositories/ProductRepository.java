package mconst.rpg.catalog.repositories;

import com.baeldung.jooq.introduction.db.public_.tables.Products;
import mconst.rpg.catalog.models.entities.ProductEntity;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepository {
    private DSLContext create;

    public ProductRepository(DSLContext create) {
        this.create = create;
    }

    public List<ProductEntity> get(Integer limit, Integer offset) {
        return create
                .select()
                .from(Products.PRODUCTS)
                .limit(limit)
                .offset(offset)
                .fetchInto(ProductEntity.class);
    }

    public Integer getCount() {
        return create
                .fetchCount(Products.PRODUCTS);
    }

    public ProductEntity insert(ProductEntity product) {
        return create
                .insertInto(Products.PRODUCTS, Products.PRODUCTS.NAME, Products.PRODUCTS.DESCRIPTION, Products.PRODUCTS.IS_INFINITE, Products.PRODUCTS.COUNT, Products.PRODUCTS.PRICE)
                .values(product.getName(), product.getDescription(), product.getIsInfinite(), product.getCount(), product.getPrice())
                .returning()
                .fetchOneInto(ProductEntity.class);
    }

    public Optional<ProductEntity> addCount(Integer id, Integer count) {
        return Optional.ofNullable(create
                .update(Products.PRODUCTS)
                .set(Products.PRODUCTS.COUNT, Products.PRODUCTS.COUNT.add(count))
                .where(Products.PRODUCTS.ID.eq(id))
                .returning()
                .fetchOneInto(ProductEntity.class));
    }
}
