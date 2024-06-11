package mconst.rpg.catalog.models.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import mconst.rpg.catalog.models.dtos.ProductDto;

import java.util.List;

@Data
@AllArgsConstructor
public class GetResponse {
    private List<ProductDto> products;
    private Integer total;
}
