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

    @PostMapping("/add/{user_id}")
    public ResponseEntity addOrder(@PathVariable Integer user_id, @RequestBody @Valid Orders orders){
        orderService.addOrder(user_id,orders);
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


    @GetMapping("/orderbyid/{user_id}/{order_id}")
    public ResponseEntity getorderbyuserId(@PathVariable Integer user_id,@PathVariable Integer order_id){
        return ResponseEntity.status(200).body(orderService.getOrderById(user_id,order_id));
    }

    @GetMapping("/calc/{order_id}")
    public ResponseEntity calculateOrder(@PathVariable Integer order_id){
        Double result = orderService.calculateTestPrice(order_id);
        return ResponseEntity.status(200).body(new ApiResponse("The payment completed successfully, Your order confirmed.The user balance after payment =  "+result));
    }

    @PutMapping("/unbook/{order_id}")
    public ResponseEntity cancelBookingTestTypeAppointment(@PathVariable Integer order_id){
        orderService.cancle_appointment(order_id);
        return ResponseEntity.status(200).body(new ApiResponse("The Appointment successfully unbooked"));
    }

}