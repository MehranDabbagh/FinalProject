package com.example.PhaseTwo.model.service.impl;

import com.example.PhaseTwo.model.entity.Customer;
import com.example.PhaseTwo.model.entity.Role;
import com.example.PhaseTwo.model.entity.Users;
import com.example.PhaseTwo.model.repository.CustomerRepository;
import com.example.PhaseTwo.model.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;



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

    private Example<Customer> createExample(String firstName, String lastName, String email) {
        Customer customer = new Customer();
        Users users = new Users();
        users.setFirstname(firstName);
        users.setLastname(lastName);
        users.setEmail(email);
        customer.setUsers(users);
        ExampleMatcher matcher = ExampleMatcher.matchingAll()
                .withIgnoreCase("firstName", "lastName", "email")
                .withNullHandler(ExampleMatcher.NullHandler.IGNORE)
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example<Customer> usersExample = Example.of(customer, matcher);
        return usersExample;
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

    @Override
    public List<Customer> findByOptional(String firstname, String lastname, String email) {
        Example<Customer> experts = createExample(firstname, lastname, email);
        List<Customer> experts1 = new ArrayList<>();
        customerRepository.findAll(experts).forEach(experts1::add);
        return experts1;
    }

}
