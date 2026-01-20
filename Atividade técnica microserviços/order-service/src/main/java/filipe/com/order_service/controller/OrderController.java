package filipe.com.order_service.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import filipe.com.order_service.DTO.OrderRequest;
import filipe.com.order_service.DTO.OrderResponse;
import filipe.com.order_service.service.OrderService;

import org.apache.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/orders")
public class OrderController {
    
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }


    @PostMapping
    public ResponseEntity<OrderResponse> getProductById(@RequestBody OrderRequest request) {
        var response = this.orderService.createOrder(request.id(), request.quantity());
        return ResponseEntity.status(HttpStatus.SC_CREATED).body(response);
    }
    
}
