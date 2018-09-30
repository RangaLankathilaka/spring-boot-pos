package lk.ijse.dep.service;

import lk.ijse.dep.dto.CustomerDTO;

import java.util.List;

public interface CustomerService {

    List<CustomerDTO> findAllCustomers();

    CustomerDTO findCustomer(String id);

    void saveCustomer(String id, CustomerDTO customerDTO);

    boolean updateCustomer(String id, CustomerDTO customerDTO);

    boolean deleteCustomer(String id);

    long customersCount();

    List<CustomerDTO> findCustomersByAddressLike(String address);

}
