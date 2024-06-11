package mconst.rpg.catalog.models.entities;

import com.baeldung.jooq.introduction.db.public_.enums.ProductStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductEntity {
    private Integer id;
    private String name;
    private String description;
    private Boolean isInfinite;
    private Integer count;
    private Boolean isEndless;
    private Instant endDate;
    private Integer price;
    private Integer discountPrice;
    private ProductStatus status;
}