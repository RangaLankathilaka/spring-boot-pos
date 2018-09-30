package lk.ijse.dep.service.impl;

import lk.ijse.dep.dto.CustomerDTO;
import lk.ijse.dep.dto.OrderDTO;
import lk.ijse.dep.dto.OrderDetailDTO;
import lk.ijse.dep.entity.Customer;
import lk.ijse.dep.entity.Order;
import lk.ijse.dep.entity.OrderDetail;
import lk.ijse.dep.repository.ItemRepository;
import lk.ijse.dep.repository.OrderRepository;
import lk.ijse.dep.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ItemRepository itemRepository;

    @Override
    public void saveOrder(String orderId, OrderDTO orderDTO) {

        if (!orderId.equals(orderDTO.getId())){
            throw new RuntimeException("Order Ids are mismatched");
        }

        var customerDTO = orderDTO.getCustomer();
        var customer = new Customer(customerDTO.getId(), customerDTO.getName(), customerDTO.getAddress());
        var order = new Order(orderDTO.getId(), orderDTO.getDate(),customer);

        var orderDetailDTOLists = orderDTO.getOrderDetails();
        var orderDetailsList = new ArrayList<OrderDetail>();

        orderDetailDTOLists.forEach(dto->{

            if (!dto.getOrderId().equals(order.getId())){
                throw new RuntimeException("Order Detail's order id and order id are mismatched");
            }

            var item = itemRepository.findById(dto.getItemCode()).get();
            var currentQty = item.getQtyOnHand();
            currentQty -= dto.getQtyOnHand();
            item.setQtyOnHand(currentQty);
//            itemRepository.save(item);

            var orderDetail = new OrderDetail(dto.getOrderId(), dto.getItemCode(), dto.getQtyOnHand(), dto.getUnitPrice());
            orderDetailsList.add(orderDetail);
        });

        order.setOrderDetailList(orderDetailsList);

        orderRepository.save(order);
    }

    @Override
    public OrderDTO findOrder(String orderId) {

        var order = orderRepository.findById(orderId).get();
        var customer = order.getCustomer();
        var customerDTO = new CustomerDTO(customer.getId(), customer.getName(), customer.getAddress());
        var orderDetailList = order.getOrderDetailList();
        var orderDetailDTOList = new ArrayList<OrderDetailDTO>();

        orderDetailList.forEach(od ->{
            orderDetailDTOList.add(new OrderDetailDTO(od.getOrder().getId(), od.getItem().getCode(),od.getUnitPrice(), od.getQty()));
        });

        var orderDTO = new OrderDTO(order.getId(), order.getDate(), customerDTO);
        orderDTO.setOrderDetails(orderDetailDTOList);

        return orderDTO;
    }

    @Override
    public List<OrderDTO> findAllOrders() {

        var orderList = orderRepository.findAll();
        var orderDTOList = new ArrayList<OrderDTO>();

        orderList.forEach(order->{

            var customer = order.getCustomer();
            var customerDTO = new CustomerDTO(customer.getId(), customer.getName(), customer.getAddress());

            var orderDetailList = order.getOrderDetailList();
            var orderDetailDTOList = new ArrayList<OrderDetailDTO>();

            orderDetailList.forEach(od ->{
                orderDetailDTOList.add(new OrderDetailDTO(od.getOrder().getId(), od.getItem().getCode(),od.getUnitPrice(), od.getQty()));
            });

            var orderDTO = new OrderDTO(order.getId(), order.getDate(), customerDTO);
            orderDTO.setOrderDetails(orderDetailDTOList);

            orderDTOList.add(orderDTO);

        });

        return orderDTOList;

    }
}
