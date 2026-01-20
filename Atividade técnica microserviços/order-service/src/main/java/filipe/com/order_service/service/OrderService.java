package filipe.com.order_service.service;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import filipe.com.order_service.DTO.OrderResponse;
import filipe.com.order_service.DTO.ProductDTO;
import filipe.com.order_service.interfaces.ProductClient;

@Service
public class OrderService {
    
    private final ProductClient productClient;

    public OrderService(ProductClient productClient) {
        this.productClient = productClient;
    }

    public OrderResponse createOrder(int id, Integer quantity) {

        ProductDTO product = productClient.getProductById(id);

        if(product == null) { return null; }

        
        BigDecimal total = product.price().multiply(new BigDecimal(quantity));

        return new OrderResponse(product.id(), product.name(), product.price(), quantity, total);
    }
}
