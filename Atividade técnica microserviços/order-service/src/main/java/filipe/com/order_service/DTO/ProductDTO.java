package filipe.com.order_service.DTO;

import java.math.BigDecimal;

public record ProductDTO(
    int id,
    String name,
    BigDecimal price
) {}
