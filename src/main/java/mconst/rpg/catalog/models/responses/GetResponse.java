package mconst.rpg.catalog.models.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mconst.rpg.catalog.models.dtos.ProductDto;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetResponse {
    private List<ProductDto> products;
    private Integer total;
}
