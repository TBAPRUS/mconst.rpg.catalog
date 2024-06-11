package mconst.rpg.catalog.services;

import com.baeldung.jooq.introduction.db.public_.tables.Products;
import mconst.rpg.catalog.models.entities.ProductEntity;
import org.jooq.DSLContext;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private DSLContext create;

    public ProductService(DSLContext create) {
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
