package filipe.com.order_service.DTO;

import java.math.BigDecimal;

public record OrderResponse(
    int id,
    String name,
    BigDecimal price,
    Integer quantity,
    BigDecimal total
) {}
