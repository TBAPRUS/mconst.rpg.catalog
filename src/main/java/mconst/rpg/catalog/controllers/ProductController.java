package mconst.rpg.catalog.controllers;

import lombok.extern.slf4j.Slf4j;
import mconst.rpg.catalog.models.ProductMapper;
import mconst.rpg.catalog.models.dtos.ProductDto;
import mconst.rpg.catalog.models.responses.GetResponse;
import mconst.rpg.catalog.services.ProductService;
import org.springframework.web.bind.annotation.*;

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
        var products = productService.get(limit, offset);
        var total = productService.getTotal();
        return new GetResponse(
                productMapper.map(products),
                total
        );
    }

    @PostMapping()
    public ProductDto post(@RequestBody ProductDto product) {
        var productEntity = productMapper.map(product);
        return productMapper.map(productService.create(productEntity));
    }
}
