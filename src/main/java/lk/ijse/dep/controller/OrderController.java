package lk.ijse.dep.controller;

import lk.ijse.dep.dto.OrderDTO;
import lk.ijse.dep.entity.Order;
import lk.ijse.dep.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PutMapping("/{id}")
    public void saveOrder(@PathVariable("id") String orderId,@RequestBody OrderDTO orderDTO){
        orderService.saveOrder(orderId, orderDTO);
    }

    @GetMapping
    public List<OrderDTO> findAllOrders(){
        return orderService.findAllOrders();
    }

    @GetMapping("/{id}")
    public OrderDTO findOrderById(@PathVariable("id") String orderId){
        return orderService.findOrder(orderId);
    }

}
