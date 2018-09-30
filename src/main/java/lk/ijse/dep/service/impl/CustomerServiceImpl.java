package lk.ijse.dep.service.impl;

import lk.ijse.dep.dto.CustomerDTO;
import lk.ijse.dep.entity.Customer;
import lk.ijse.dep.repository.CustomerRepository;
import lk.ijse.dep.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Transactional(readOnly = true)
    @Override
    public List<CustomerDTO> findAllCustomers() {
        var customers = customerRepository.findAll();
        var dtos = new ArrayList<CustomerDTO>();
        customers.forEach(c -> {
            dtos.add(new CustomerDTO(c.getId(), c.getName(), c.getAddress()));
        });
        return dtos;
    }

    @Transactional(readOnly = true)
    @Override
    public CustomerDTO findCustomer(String id) {
        var customer =  customerRepository.findById(id).get();
        return new CustomerDTO(customer.getId(), customer.getName(),customer.getAddress());
    }

    @Override
    public void saveCustomer(String id, CustomerDTO customerDTO) {
        if (!id.equals(customerDTO.getId())){
            throw new RuntimeException("Customer IDs are mismatched");
        }
        var customer = new Customer(customerDTO.getId(),customerDTO.getName(),customerDTO.getAddress());
        customerRepository.save(customer);
    }

    @Override
    public boolean updateCustomer(String id, CustomerDTO customerDTO) {
        if (customerRepository.existsById(id)) {
            saveCustomer(id, customerDTO);
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean deleteCustomer(String id) {
        customerRepository.deleteById(id);
        return true;
    }

    @Override
    public long customersCount() {
        return customerRepository.count();
    }

    @Override
    public List<CustomerDTO> findCustomersByAddressLike(String address) {
        var customers =  customerRepository.mataOniAddressMeaAkurenPatanGanna(address + "%");
//   3:     var customers =  customerRepository.mataOniAddressMeaAkurenPatanGanna(null,address );
        var dtos = new ArrayList<CustomerDTO>();
        customers.forEach(c->{
            dtos.add(new CustomerDTO(c.getId(), c.getName(), c.getAddress()));
        });
        return dtos;
    }

}
