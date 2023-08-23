package com.example.labority_project.Repository;

import com.example.labority_project.Model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface OrderRepository extends JpaRepository<Orders , Integer> {

    Orders findOrderById(Integer orderId);

    Orders findOrdersByUserId(Integer userId);
}
