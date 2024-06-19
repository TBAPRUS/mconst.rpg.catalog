package mconst.rpg.catalog.controllers;

import lombok.extern.slf4j.Slf4j;
import mconst.rpg.catalog.models.ProductMapper;
import mconst.rpg.catalog.models.dtos.ProductDto;
import mconst.rpg.catalog.models.requests.AddCountRequest;
import mconst.rpg.catalog.models.responses.GetResponse;
import mconst.rpg.catalog.services.ProductService;
import org.apache.coyote.BadRequestException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.NOT_FOUND;

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
        log.info("ProductController get {}, {}", limit, offset);
        var products = productService.get(limit, offset);
        var total = productService.getTotal();
        return new GetResponse(
                productMapper.map(products),
                total
        );
    }

    @GetMapping("/{id}")
    public ProductDto getById(@PathVariable Integer id) {
        log.info("ProductController getById {}", id);
        var product = productService.getById(id);
        return productMapper.map(product
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND)));
    }

    @PostMapping()
    public ProductDto post(@RequestBody ProductDto product) {
        var productEntity = productMapper.map(product);
        return productMapper.map(productService.create(productEntity));
    }

    @PostMapping("/{id}/add-count")
    public ProductDto addCount(@PathVariable Integer id, @RequestBody AddCountRequest addCountRequest) throws BadRequestException {
        if (addCountRequest.getCount() < 0) {
            throw new BadRequestException("Count should be equal or more than 0");
        }
        var product = productService.addCount(id, addCountRequest.getCount());
        return productMapper.map(product
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND)));
    }
}
