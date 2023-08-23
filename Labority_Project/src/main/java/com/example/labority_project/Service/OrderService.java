package com.example.labority_project.Service;

import com.example.labority_project.Api.ApiException;
import com.example.labority_project.Model.Orders;
import com.example.labority_project.Model.TestType;
import com.example.labority_project.Model.User;
import com.example.labority_project.Repository.OrderRepository;
import com.example.labority_project.Repository.TestTypeRepository;
import com.example.labority_project.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final TestTypeRepository testTypeRepository;

    public List<Orders> getAllOrders(){

        return orderRepository.findAll();
    }

    public void addOrder(Integer user_id,Orders orders){
        User user=userRepository.findUserById(user_id);
        if (user == null)
            throw new ApiException("Sorry, the user id not found");
        if(user.getTestType()==null)
            throw new ApiException("Sorry, the user dose not book test type  ");
        if (!Objects.equals(orders.getLab_name(), user.getTestType().getLaboratory().getName()))
            throw new ApiException("the Labs name are different");
        if (orders.getOrder_status().equals("confirmed"))
            orders.setOrder_status("processing");

        orders.setUser(user);
        orderRepository.save(orders);
    }

    public Double calculateTestPrice(Integer order_id){
        Orders orders = orderRepository.findOrderById(order_id);
        if (orders == null)
            throw new ApiException("Sorry, the order id not found");

        User user = userRepository.findUserById(orders.getUser().getId());
        TestType testType = testTypeRepository.findTestTypeById(user.getTestType().getId());

        if (user == null)
            throw new ApiException("No user found");
        else if (testType == null)
            throw new ApiException("Sorry no test book found");

        Double result = user.getBalance() - testType.getPrice();

        if (result < 0)
            throw new ApiException("Sorry the blance less than price");
        user.setBalance(result);

        userRepository.save(user);
        orders.setOrder_status("confirmed");
        orderRepository.save(orders);
        return result;
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

        if (!Objects.equals(orders.getLab_name(),oldOrders.getUser().getTestType().getLaboratory().getName()))
            throw new ApiException("the Labs name are different");

        oldOrders.setOrder_status(orders.getOrder_status());
        orderRepository.save(oldOrders);

    }

    public void deleteOrder(Integer id){
        Orders oldOrders =orderRepository.findOrderById(id);
        if(oldOrders ==null){
            throw new ApiException("id not found");
        }

        orderRepository.deleteById(id);
    }

    public void cancle_appointment(Integer order_id){
        Orders orders = orderRepository.findOrderById(order_id);

        if (orders == null ){
            throw new ApiException(" id is wrong");}

        User user = userRepository.findUserById(orders.getUser().getId());
        TestType testType = testTypeRepository.findTestTypeById(orders.getUser().getTestType().getId());


        if (Objects.equals(orders.getOrder_status(), "processing")) {
            testType.setAppointment(false);
            user.setTestType(null);
            orderRepository.delete(orders);
        }
        else if(Objects.equals(orders.getOrder_status(), "confirmed")) {
            throw new ApiException("your Order already confirmd,Sorry you can't cancel your appointment");

        }
        else
            throw  new ApiException("Something went wrong ");
    }

}