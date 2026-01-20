package filipe.com.product_service.entity;

import java.math.BigDecimal;

public class Product {
    
    private int id;
    private String name;
    private BigDecimal price;

    public Product(int id, String name, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }


    public int getId() { return this.id; }
    public String getName() { return this.name; }
    public BigDecimal getPrice() { return this.price; }

    public void setId(int id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setPrice(BigDecimal price) { this.price = price; }

}
