package mconst.rpg.catalog.controllers;

import com.baeldung.jooq.introduction.db.public_.tables.Products;
import lombok.extern.slf4j.Slf4j;
import mconst.rpg.catalog.models.ProductMapper;
import mconst.rpg.catalog.models.dtos.ProductDto;
import mconst.rpg.catalog.models.responses.GetResponse;
import mconst.rpg.catalog.services.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;
    private final ProductMapper productMapper;

    public ProductController(ProductService productService, ProductMapper productMapper) {
        this.productService = productService;
        this.productMapper = productMapper;
    }

    @GetMapping()
    public GetResponse get(@RequestParam(defaultValue = "20") Integer limit, @RequestParam(defaultValue = "0") Integer offset) {
        var products = this.productService.get(limit, offset);
        var count = this.productService.getCount();
        return new GetResponse(
                productMapper.map(products),
                count
        );
    }
}
