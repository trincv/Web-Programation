package filipe.com.product_service.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import filipe.com.product_service.entity.Product;
import filipe.com.product_service.service.ProductService;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return this.productService.getAll();
    }
    
    @GetMapping("/{id}")
    public Product getProductById(@PathVariable int id) {
        return this.productService.getProductById(id);
    }
    
}
