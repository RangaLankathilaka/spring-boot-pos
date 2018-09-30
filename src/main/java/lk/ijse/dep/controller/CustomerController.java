package lk.ijse.dep.controller;

import lk.ijse.dep.dto.CustomerDTO;
import lk.ijse.dep.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This is a REST API which exposes all the CRUD operations for Customer Entity
 * <p>
 * This is our final project and I am really enjoying it..
 *
 * @author Ranga Lankathilaka
 */
@RestController
@RequestMapping("api/v1/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    /**
     * This method returns all the customers in the database
     * <p>
     * You can invoke this method by sending HTTP get request to api/v1/customers
     *
     * @return List of customers
     */
    @GetMapping
    public List<CustomerDTO> findAllCustomers() {
        return customerService.findAllCustomers();
    }

    /**
     * This method returns a customer for specified customer ID
     * <p>
     * You can invoke this method by sending HTTP get request to api/v1/customers/{and some form of ID}
     *
     * @param id Customer ID
     * @return Customer
     */
    @GetMapping(value = "/{id}")
    public CustomerDTO findCustomer(@PathVariable("id") String id) {
        return customerService.findCustomer(id);
    }

    @PutMapping(value = "/{id}")
    public void saveCustomer(@PathVariable("id") String id, @RequestBody CustomerDTO customerDTO) {
        customerService.saveCustomer(id, customerDTO);
    }

    @PostMapping(value = "/{id}")
    public boolean updateCustomer(@PathVariable("id") String id, @RequestBody CustomerDTO customerDTO) {
        return customerService.updateCustomer(id, customerDTO);
    }

    @DeleteMapping(value = "/{id}")
    public boolean deleteCustomer(@PathVariable("id") String id) {
        return customerService.deleteCustomer(id);
    }

    @GetMapping(params = {"action=count"})
    public long getCustomersCount() {
        return customerService.customersCount();
    }

    @GetMapping(params = {"action=search", "q"})
    public List<CustomerDTO> findCustomersByAddressLike(@RequestParam("q") String address) {
        return customerService.findCustomersByAddressLike(address);
    }

}
