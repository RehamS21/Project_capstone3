package com.example.labority_project.Controller;

import com.example.labority_project.Api.ApiResponse;
import com.example.labority_project.Model.Orders;
import com.example.labority_project.Service.OrderService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/order")
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/get")
    public ResponseEntity getAllOrders(){

        return ResponseEntity.status(200).body(orderService.getAllOrders());
    }

    @PostMapping("/add")
    public ResponseEntity addOrder(@RequestBody @Valid Orders orders){
        orderService.addOrder(orders);
        return ResponseEntity.status(200).body(new ApiResponse("Order Added"));
    }


    @PutMapping("/update/{id}")
    public ResponseEntity updateOrder(@PathVariable Integer id,@RequestBody @Valid Orders orders){
        orderService.updateOrder(id, orders);
        return ResponseEntity.status(200).body(new ApiResponse("Order Updated"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteOrder(@PathVariable Integer id){
        orderService.deleteOrder(id);
        return ResponseEntity.status(200).body(new ApiResponse("Order Deleted"));
    }

    @PutMapping("/{user_id}/assign/{order_id}")
    public ResponseEntity assignOrderToUser(@PathVariable Integer user_id,@PathVariable Integer order_id){
        orderService.assignOrderToUser(user_id,order_id);
        return ResponseEntity.status(200).body(new ApiResponse("Order Is Assigned To User"));

    }

    @GetMapping("/orderbyid/{user_id}/{order_id}")
    public ResponseEntity getorderbyuserId(@PathVariable Integer user_id,@PathVariable Integer order_id){
        return ResponseEntity.status(200).body(orderService.getOrderById(user_id,order_id));
    }


}