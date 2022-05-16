package com.example.PhaseTwo.model.service.impl;

import com.example.PhaseTwo.model.entity.Customer;
import com.example.PhaseTwo.model.entity.Role;
import com.example.PhaseTwo.model.repository.CustomerRepository;
import com.example.PhaseTwo.model.service.CustomerService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    private CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer save(Customer customer) {
        customer.getUsers().setRole(Role.COSTUMER);
        return customerRepository.save(customer);
    }

    @Override
    public void update(Customer customer) {
        customerRepository.save(customer);
    }

    @Override
    public void changingPassword(Long Id, String password) {
        Customer customer = customerRepository.findById(Id).orElse(null);
        if (customer != null && customer.getUsers().passwordChecking(password)) {
            customer.getUsers().setPassword(password);
            customerRepository.save(customer);
        }
    }

    @Override
    public Customer findById(Long id) {
        return customerRepository.findById(id).orElse(null);
    }

    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public void delete(Customer customer) {
        customerRepository.deleteById(customer.getId());
    }

}
