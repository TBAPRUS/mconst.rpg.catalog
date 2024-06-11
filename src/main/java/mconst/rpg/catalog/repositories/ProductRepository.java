package mconst.rpg.catalog.repositories;

import com.baeldung.jooq.introduction.db.public_.tables.Products;
import mconst.rpg.catalog.models.entities.ProductEntity;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.List;

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
}
