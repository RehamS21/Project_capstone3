package com.example.labority_project.Service;

import com.example.labority_project.Api.ApiException;
import com.example.labority_project.Model.Orders;
import com.example.labority_project.Model.User;
import com.example.labority_project.Repository.OrderRepository;
import com.example.labority_project.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    public List<Orders> getAllOrders(){

        return orderRepository.findAll();
    }

    public void addOrder(Orders orders){
        orderRepository.save(orders);

    }


    public Orders getOrderById(Integer user_id,Integer order_id){
        User user=userRepository.findUserById(user_id);
        Orders orders=orderRepository.findOrderById(order_id);
        if(user== null){
            throw  new ApiException("Sorry,user id is wrong");
        } else if (orders==null) {
            throw  new ApiException("Sorry,order id is wrong");
        }
        return orderRepository.findOrdersByUserId(user_id);

    }

    public void updateOrder(Integer id, Orders orders){
        Orders oldOrders =orderRepository.findOrderById(id);
        if(oldOrders ==null){
            throw new ApiException("id not found");
        }
        oldOrders.setOrder_status(orders.getOrder_status());
        oldOrders.setDate(orders.getDate());
        orderRepository.save(oldOrders);

    }

    public void deleteOrder(Integer id){
        Orders oldOrders =orderRepository.findOrderById(id);
        if(oldOrders ==null){
            throw new ApiException("id not found");
        }

        orderRepository.deleteById(id);
    }


    public void assignOrderToUser(Integer user_id,Integer order_id){

        User user=userRepository.findUserById(user_id);
        Orders orders =orderRepository.findOrderById(order_id);

        if (orders ==null || user==null){
            throw  new ApiException("Can not Assign ids could be wrong");
        }
        orders.setUser(user);
        orderRepository.save(orders);
    }
}