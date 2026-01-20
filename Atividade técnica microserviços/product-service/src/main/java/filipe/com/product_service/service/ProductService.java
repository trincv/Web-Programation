package filipe.com.product_service.service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import filipe.com.product_service.entity.Product;

@Service
public class ProductService {
    
    private static List<Product> products = Arrays.asList(
        new Product(1, "Notebook", new BigDecimal(4500)),
        new Product(2, "Mouse", new BigDecimal(150)),
        new Product(3, "Teclado", new BigDecimal(250))
    );
    

    public List<Product> getAll() {
        return products;
    }

    public Product getProductById(int id) {
        return products.stream()
                       .filter(p -> p.getId()==id)
                       .findFirst()
                       .orElse(null);
    }
}
