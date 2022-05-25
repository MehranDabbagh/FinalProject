package com.example.PhaseTwo.model.service.impl;

import com.example.PhaseTwo.model.entity.Customer;
import com.example.PhaseTwo.model.entity.Role;
import com.example.PhaseTwo.model.entity.Users;
import com.example.PhaseTwo.model.entity.dto.CustomerDto;
import com.example.PhaseTwo.model.repository.CustomerRepository;
import com.example.PhaseTwo.model.service.CustomerService;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {
    private CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public CustomerDto save(Customer customer) {
        customer.setRole(Role.COSTUMER);

        Customer customer1 = customerRepository.save(customer);
        return convertingToDto(customer1);
    }

    @Override
    public void update(CustomerDto customer) {
        customerRepository.save(convertingToCustomer(customer));
    }

    @Override
    public void changingPassword(Long Id, String password) {
        Customer customer = customerRepository.findById(Id).orElse(null);
        if (customer != null && customer.passwordChecking(password)) {
            customer.setPassword(password);
            customerRepository.save(customer);
            return;
        }
        throw new NullPointerException("wrong id!");
    }

    private Example<Customer> createExample(String firstName, String lastName, String email) {
        Customer customer = new Customer();
        customer.setFirstname(firstName);
        customer.setLastname(lastName);
        customer.setEmail(email);
        ExampleMatcher matcher = ExampleMatcher.matchingAll()
                .withIgnoreCase("firstName", "lastName", "email")
                .withNullHandler(ExampleMatcher.NullHandler.IGNORE)
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example<Customer> usersExample = Example.of(customer, matcher);
        return usersExample;
    }

    @Override
    public CustomerDto findById(Long id) {
        Customer customer = customerRepository.findById(id).orElse(null);
        if (customer != null) {
            return convertingToDto(customer);
        }
        throw new NullPointerException("wrong id!");
    }

    @Override
    public List<CustomerDto> findAll() {
        List<Customer> customers = customerRepository.findAll().stream().collect(Collectors.toList());
        return customers.stream().map(customer -> convertingToDto(customer)).collect(Collectors.toList());

    }

    @Override
    public void delete(CustomerDto customer) {
        customerRepository.deleteById(customer.getId());
    }

    @Override
    public List<CustomerDto> findByOptional(String firstname, String lastname, String email) {
        Example<Customer> experts = createExample(firstname, lastname, email);
        List<Customer> customers1 = new ArrayList<>();
        customerRepository.findAll(experts).forEach(customers1::add);
        return customers1.stream().map(customer -> convertingToDto(customer)).collect(Collectors.toList());
    }

    public CustomerDto convertingToDto(Customer customer) {
        CustomerDto customerDto = new CustomerDto(customer.getId(), customer.getFirstname(), customer.getLastname(), customer.getEmail(), customer.getVerified(), customer.getSingUpDate(), customer.getCredit());
        return customerDto;
    }

    public  Customer convertingToCustomer(CustomerDto customerDto) {
        Customer customer = new Customer();
        customer.setId(customerDto.getId());
        customer.setFirstname(customerDto.getFirstName());
        customer.setLastname(customerDto.getLastName());
        customer.setEmail(customerDto.getEmail());
        customer.setSingUpDate(customerDto.getSingUpDate());
        customer.setCredit(customerDto.getCredit());
        customer.setVerified(customerDto.getVerified());
        customer.setRole(customerDto.getRole());
        return customer;
    }

}
