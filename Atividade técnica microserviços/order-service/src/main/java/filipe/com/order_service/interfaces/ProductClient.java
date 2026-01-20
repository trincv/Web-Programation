package filipe.com.order_service.interfaces;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import filipe.com.order_service.DTO.ProductDTO;

@FeignClient(name = "product-service")
public interface ProductClient {

    @GetMapping("/products/{id}")
    ProductDTO getProductById(@PathVariable("id") int id);

    @GetMapping("/products")
    List<ProductDTO> getAllProducts();
} 
