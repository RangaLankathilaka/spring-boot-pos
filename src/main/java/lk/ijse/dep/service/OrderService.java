package lk.ijse.dep.service;

import lk.ijse.dep.dto.OrderDTO;

import java.util.List;

public interface OrderService {

    void saveOrder(String orderId, OrderDTO orderDTO);

    OrderDTO findOrder(String orderId);

    List<OrderDTO> findAllOrders();

}
