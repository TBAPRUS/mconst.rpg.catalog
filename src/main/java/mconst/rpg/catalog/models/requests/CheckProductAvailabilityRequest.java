package mconst.rpg.catalog.models.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CheckProductAvailabilityRequest {
    private Long count;
    private Long pricePerOne;
}
